package io.github.dalgiechgo.aimia_test;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class UseJson {

    String path;

    public UseJson(String path){
        this.path = path;
    }

    public JSONObject read() throws IOException, ParseException {
        Reader reader = new FileReader(this.path);

        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(reader);
        JSONObject joByFile = (JSONObject) obj;

        System.out.println(joByFile);
        return joByFile;
    }

    public void write(JSONObject joToFile) throws IOException {
        String strOfJo = joToFile.toString();
        File jsonFile = new File(this.path);

        BufferedWriter bw = new BufferedWriter(new FileWriter(jsonFile));
        bw.write(strOfJo);
        bw.close();
    }
}
