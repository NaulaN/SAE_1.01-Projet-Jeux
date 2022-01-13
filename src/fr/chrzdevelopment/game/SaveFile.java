package fr.chrzdevelopment.game;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class SaveFile
{


    public static JSONObject load(String resPath)
    {
        File f = new File(resPath);
        if (!f.exists())
            f.mkdir();

        f = new File(resPath + "save.json");
        if (!f.exists()) {
            try {
                f.createNewFile();
                FileWriter fileWriter = new FileWriter(f);
                fileWriter.write("{\"maxLvl\":0,\"playerName\":\"Naulan\",\"howManyPlay\":0,\"totalCoins\":0}");
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) { e.printStackTrace(); }
        }

        try (FileReader file = new FileReader(resPath + "save.json")) {
            return new JSONObject(new JSONTokener(file));
        } catch (IOException e) { e.printStackTrace(); }

        return new JSONObject();
    }

    public static void write(JSONObject saveFile, String resPath)
    {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(resPath + "save.json"))) {
            saveFile.write(writer);
        } catch (IOException e) { e.printStackTrace(); }
    }
}
