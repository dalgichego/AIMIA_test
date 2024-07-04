package io.github.dalgiechgo.aimia_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder;
import java.util.logging.Logger;

public class ProcessBuilderTest {
    public static void doTest(Logger logger) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("python", "plugins\\pythonFiles\\test.py");
//        ProcessBuilder processBuilder = new ProcessBuilder("python", "C:\\Users\\kyung\\Desktop\\work\\AIMIA\\AIMIAplugin\\test\\pythonFiles\\test.py", "HiHi");
        Process process = processBuilder.start();

        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while((line = reader.readLine()) != null){
            logger.info("py : " + line);
        }

        
    }
}
