package org.example;

import Generator.Generator;
import Application.commandLineParser;

public class Main {
    public static void main(String[] args) {
        commandLineParser options = new commandLineParser(args);

        Generator generator = new Generator(options.rawJson, options.astTemplate, options.sourceStgPath, options.sourceStgInstanceName, options.headerStgPath, options.headerStgInstanceName);

        generator.run();
    }
}