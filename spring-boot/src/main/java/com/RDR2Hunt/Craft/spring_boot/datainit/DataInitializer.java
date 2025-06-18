package com.RDR2Hunt.Craft.spring_boot.datainit;

import com.RDR2Hunt.Craft.spring_boot.services.ExcelLoaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ExcelLoaderService excelLoaderService;

    @Value("${data.init.enabled:false}") // por defecto es false si no se define
    private boolean dataInitEnabled;

    @Override
    public void run(String... args) {
        if (dataInitEnabled) {
            System.out.println("🟡 Iniciando carga masiva...");
            excelLoaderService.importarDesdeExcel();
            System.out.println("✅ Proceso terminado.");
        } else {
            System.out.println("⏭️ Carga masiva desactivada.");
        }
    }
}
