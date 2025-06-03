package com.RDR2Hunt.Craft.spring_boot.datainit;

import com.RDR2Hunt.Craft.spring_boot.services.ExcelLoaderService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ExcelLoaderService excelLoaderService;

    @Override
    public void run(String... args) {
        System.out.println("🟡 Iniciando carga masiva...");
        excelLoaderService.importarDesdeExcel();
        System.out.println("✅ Proceso terminado.");
    }
    @PostConstruct
    public void debugInit() {
        System.out.println("🔧 ExcelLoaderService inicializado.");
    }
    @PostConstruct
    public void init() {
        System.out.println("📦 DataInitializer preparado.");
    }
}

