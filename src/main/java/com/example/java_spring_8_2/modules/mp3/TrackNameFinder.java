package com.example.java_spring_8_2.modules.mp3;

import com.example.java_spring_8_2.modules.AppModuleContract;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.springframework.stereotype.Component;

import java.io.File;


@Component
public class TrackNameFinder implements AppModuleContract {
    @Override
    public boolean CanBeExecuted(String filePath) {
        return filePath.endsWith(".mp3");
    }

    @Override
    public String GetDescription() {
        return "Вывод названия трека из тегов";
    }

    @Override
    public void Execute(String filePath) {
        File file = new File(filePath);
        try {
            AudioFile track = AudioFileIO.read(file);
            System.out.println("Название трека: " + track.
                    getTag().
                    getFirst(FieldKey.TITLE));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}