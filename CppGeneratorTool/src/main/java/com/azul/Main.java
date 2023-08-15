package com.azul;

import Generator.Generator;
import CommandLineParser.CommandLineParser;

public class Main {
    public static void main(String[] args) {
        CommandLineParser options = new CommandLineParser(args);

        Generator generator = new Generator(options.rawJson, options.astTemplate, options.sourceStgPath, options.sourceStgInstanceName, options.headerStgPath, options.headerStgInstanceName);

        generator.run();
    }
}
