package com.example.java_spring_8_2.modules.image;

import com.example.java_spring_8_2.modules.AppModuleContract;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class ImageFilter implements AppModuleContract {
    @Override
    public boolean CanBeExecuted(String filePath) {
        return filePath.endsWith(".jpg");
    }

    @Override
    public String GetDescription() {
        return "Применение фильтра к изображению";
    }

    @Override
    public void Execute(String filePath) {
        try {
            File file = new File(filePath);
            BufferedImage image = ImageIO.read(file);

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    Color color = new Color(image.getRGB(x, y));
                    int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                    Color sepiaColor = new Color(Math.min(255, gray + 40), Math.min(255, gray + 20), Math.min(255, gray));
                    image.setRGB(x, y, sepiaColor.getRGB());
                }
            }

            File outputFile = new File("C:\\Users\\IgorL\\mForJava\\f" + file.getName());
            ImageIO.write(image, "jpg", outputFile);

            System.out.println("Фильтр успешно применен. Результат сохранен в файл: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}