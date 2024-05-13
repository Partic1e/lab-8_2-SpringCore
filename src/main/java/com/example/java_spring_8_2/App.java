package com.example.java_spring_8_2;

import com.example.java_spring_8_2.modules.AppModuleContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "com.example.java_spring_8_2")
public class App {
    private static List<AppModuleContract> modules;

    @Autowired
    public App(List<AppModuleContract> modules) {
        App.modules = modules;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        startProgram(args);
    }
    public static void startProgram(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь файла (чтобы выйти введите q): ");
        String filePath = scanner.nextLine();
        if (filePath.equals("q")) {
            System.out.println("Exit");
            return;
        }

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Файл не найден");
            return;
        }

        var allAppModules = new ArrayList<AppModuleContract>();
        for (var module : modules) {
            if (module.CanBeExecuted(filePath)) {
                allAppModules.add(module);
            }
        }

        System.out.println("Список функций:");
        for (int i = 0; i < allAppModules.size(); i++) {
            System.out.println(Integer.valueOf(i).toString() + " " + allAppModules.get(i).GetDescription());
        }

        System.out.print("Выберите нужную функцию");
        allAppModules.get(scanner.nextInt()).Execute(filePath);
        startProgram(args);
    }
}