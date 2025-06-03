package com.RDR2Hunt.Craft.spring_boot.services;

import com.RDR2Hunt.Craft.spring_boot.models.*;
import com.RDR2Hunt.Craft.spring_boot.repositories.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ExcelLoaderService {

    private final ItemRepository itemRepo;
    private final MaterialRepository materialRepo;
    private final TipoRepository tipoRepo;
    private final OutfitRepository outfitRepo;
    private final ItemMaterialRepository itemMaterialRepo;

    @PersistenceContext
    private EntityManager entityManager;

    public void importarDesdeExcel() {
        try {
            String filePath = "src/main/resources/data/RedDeadwithTrapperInfo.xlsx";
            File file = new File(filePath);
            if (!file.exists()) {
                System.err.println("❌ Archivo Excel no encontrado: " + filePath);
                return;
            }

            try (FileInputStream fis = new FileInputStream(file);
                 Workbook workbook = new XSSFWorkbook(fis)) {
                System.out.println("📚 Hojas encontradas en el archivo:");
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                    System.out.println("🔸 " + workbook.getSheetName(i));
                }

                importarDesdeTrapperClothing(workbook);
                importarDesdeTrapperSets(workbook);
            }

            entityManager.flush();
            System.out.println("🎉 Importación completa.");
        } catch (Exception e) {
            System.err.println("❌ Error durante importación: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void importarDesdeTrapperClothing(Workbook workbook) {
        System.out.println("📄 importando Trapper Clothing...");
        Sheet sheet = workbook.getSheet("Trapper Clothing");
        if (sheet == null) {
            System.out.println("⚠️ Hoja Trapper Clothing no encontrada.");
            return;
        }

        Map<Integer, String> tiposPorFila = Map.of(
                3, "Hats",
                29, "Vests",
                34, "Chaps",
                45, "Boots"
        );

        String tipoActual = null;

        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            if (tiposPorFila.containsKey(i)) {
                tipoActual = tiposPorFila.get(i);
                continue;
            }

            if (tipoActual == null) continue;

            Tipo tipo = tipoRepo.findByNombre(tipoActual).orElse(null);
            if (tipo == null) {
                System.out.println("⚠️ Tipo no encontrado: " + tipoActual);
                continue;
            }

            String nombreItem = getCellString(row.getCell(0));
            if (nombreItem == null || nombreItem.isEmpty()) continue;

            Item item = new Item();
            item.setNombre(nombreItem);
            item.setPrecio(0.0);
            item.setTipo(tipo);
            item.setOutfit(null); // No hay outfit específico en esta hoja

            itemRepo.saveAndFlush(item);


            for (int j = 1; j <= 3; j++) {
                String matStr = getCellString(row.getCell(j));
                if (matStr == null || matStr.isEmpty()) continue;

                crearRelacionMaterial(item, matStr);

            }

            System.out.println("✅ [Clothing] " + item.getNombre());
            System.out.println("➡ Fila " + i + ": ");
            Cell celdaNombre = row.getCell(0);

            System.out.println("🧾 Item leído: " + nombreItem);

        }

    }

    private void importarDesdeTrapperSets(Workbook workbook) {
        System.out.println("📄 importando Trapper Sets...");
        Sheet sheet = workbook.getSheet("Trapper Sets");
        if (sheet == null) {
            System.out.println("⚠️ Hoja Trapper Sets no encontrada.");
            return;
        }

        int[] columnasOutfit = {3, 9, 15, 22, 29, 36, 43, 50, 57, 63, 70, 77, 83, 90, 97, 104};

        for (int col : columnasOutfit) {
            Row headerRow = sheet.getRow(1);
            if (headerRow == null) continue;

            String nombreOutfit = getCellString(headerRow.getCell(col));
            if (nombreOutfit == null || nombreOutfit.isEmpty()) continue;

            Outfit outfit = outfitRepo.findByNombre(nombreOutfit).orElse(null);
            if (outfit == null) {
                System.out.println("⚠️ Outfit no encontrado: " + nombreOutfit);
                continue;
            }

            for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String nombreItem = getCellString(row.getCell(col));
                if (nombreItem == null || nombreItem.isEmpty()) continue;

                Item item = new Item();
                item.setNombre(nombreItem);
                item.setPrecio(0.0);
                item.setOutfit(outfit);
                item.setTipo(null); // No hay tipo definido

                itemRepo.saveAndFlush(item);

                for (int j = 1; j <= 3; j++) {
                    String matStr = getCellString(row.getCell(col + j));
                    if (matStr == null || matStr.isEmpty()) continue;

                    crearRelacionMaterial(item, matStr);
                }

                System.out.println("✅ [Set] " + item.getNombre() + " (" + outfit.getNombre() + ")");
                System.out.println("🎯 Procesando columna " + col);

                Cell celdaOutfit = headerRow.getCell(col);

                System.out.println("🎽 Outfit leído: " + nombreOutfit);

            }
        }
    }

    private void crearRelacionMaterial(Item item, String matStr) {
        int cantidad = 1;
        String nombreMaterial;

        if (matStr.matches("\\d+x\\s+.+")) {
            String[] partes = matStr.split("x\\s+", 2);
            cantidad = Integer.parseInt(partes[0].trim());
            nombreMaterial = partes[1].trim();
        } else {
            nombreMaterial = matStr.trim();
        }

        Material material = materialRepo.findByNombre(nombreMaterial)
                .orElseGet(() -> {
                    Material nuevo = new Material();
                    nuevo.setNombre(nombreMaterial);
                    return materialRepo.save(nuevo);
                });

        ItemMaterial im = new ItemMaterial();
        im.setItem(item);
        im.setMaterial(material);
        im.setCantidad(cantidad);
        itemMaterialRepo.save(im);
    }

    private String getCellString(Cell cell) {
        if (cell == null) {
            System.out.println("⚠️ Celda nula");
            return null;
        }

        CellType type = cell.getCellType();
        System.out.println("🔍 Celda tipo: " + type + " | contenido: " + cell.toString());

        return switch (type) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue()).trim();
            case FORMULA -> {
                try {
                    yield cell.getStringCellValue().trim();
                } catch (IllegalStateException e) {
                    yield String.valueOf(cell.getNumericCellValue()).trim();
                }
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue()).trim();
            case BLANK, ERROR -> null;
            default -> {
                System.out.println("⚠️ Tipo de celda no manejado: " + type);
                yield null;
            }
        };
    }

}
