package com.example.java_spring_8_2.modules.image;

import com.example.java_spring_8_2.modules.AppModuleContract;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


@Component
public class ImageSizeCounter implements AppModuleContract {
    @Override
    public boolean CanBeExecuted(String filePath) {
        return filePath.endsWith(".jpg");
    }

    @Override
    public String GetDescription() {
        return "Вывод размера изображения";
    }

    @Override
    public void Execute(String filePath) {
        File file = new File(filePath);
        try {
            BufferedImage image = ImageIO.read(file);
            System.out.println("Размер изображения: " + image.getWidth() + "X" + image.getHeight());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}