package com.example;

import java.io.*;

public class CompilerLogic {

    public static String compileAndRun(String code, String userInput) {
        try {
            // Save code to file
            File file = new File("UserProgram.java");
            FileWriter writer = new FileWriter(file);
            writer.write(code);
            writer.close();

            // Compile
            Process compile = Runtime.getRuntime().exec("javac UserProgram.java");
            compile.waitFor();

            // Run
            Process run = Runtime.getRuntime().exec("java UserProgram");

            // Send input to the process
            BufferedWriter inputWriter = new BufferedWriter(
                new OutputStreamWriter(run.getOutputStream())
            );
            inputWriter.write(userInput);
            inputWriter.newLine();
            inputWriter.flush();
            inputWriter.close();

            // Capture output
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(run.getInputStream())
            );
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            return output.toString();

        } catch (Exception e) {
            return "Exception: " + e.getMessage();
        }
    }
}




