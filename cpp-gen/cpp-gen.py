#!/usr/bin/env python3

import subprocess
import argparse
import json
import sys
import os
from py.util import readFileToString, writeStringToFile, checkValidFile
from py.parser import CppParser
from py.generator import Generator, GenerationUnit

def main():
   argParser = argparse.ArgumentParser(description='Generate C++ from C++ using the Clang parser and Java StringTemplate')
   argParser.add_argument('cpp_file_path', type=str, help='Path to source file to be parsed')
   argParser.add_argument('h_file_path', type=str, help='Path to header file to be parsed')
   argParser.add_argument('compile_commands_path', type=str, help='Path to compile_commands.json generated by CMAKE')
   argParser.add_argument('stg_source_path', type=str, help='Path to source StringTemplate group file (.stg)')
   argParser.add_argument('stg_source_instance_name', type=str, help='Source StringTemplate instance name')
   argParser.add_argument('stg_header_path', type=str, help='Path to header StringTemplate group file (.stg)')
   argParser.add_argument('stg_header_instance_name', type=str, help='Header StringTemplate instance name')
   argParser.add_argument('-o', '--output-paths', type=str, metavar=('SOURCE_PATH', 'HEADER_PATH'), nargs=2, help='Paths to cpp file and header file')
   argParser.add_argument('-x', '--ast-template', type=str, nargs=1, help='Name of code generator AST template, ex. [ example, interface ]')
   argParser.add_argument('-j', '--output-json', action="store_true", help="Output data as JSON")

   args = argParser.parse_args()
   sourcePath = args.cpp_file_path
   headerPath = args.h_file_path
   compileCommandsPath = args.compile_commands_path

   checkValidFile(sourcePath)
   checkValidFile(headerPath)
   checkValidFile(compileCommandsPath)

   headerDir = os.path.dirname(headerPath)
   sourceDir = os.path.dirname(sourcePath)
   headerName = os.path.basename(headerPath)
   sourceName = os.path.basename(sourcePath)
   scriptDir = os.path.dirname(os.path.abspath(__file__))

   configPath = os.path.join(scriptDir, 'config.json')
   configStr = readFileToString(configPath)
   configJson = json.loads(configStr)

   cppGeneratorToolPath = configJson.get('cppGeneratorToolPath')
   if cppGeneratorToolPath is None:
       print('CppGeneratorToolPath not specified in config.json')
       exit(1)
   elif not os.path.exists(cppGeneratorToolPath):
       print(f'CppGeneratorToolPath contains invalid path in config.json')
       exit(1)
    
   clangVersion = configJson.get('clangVersion')

   extraIncludePaths = configJson.get('extraIncludePaths')

   parser = CppParser(compileCommandsPath, clangVersion)

   cppVersion = configJson.get('cppVersion', 'c++17')
   parser.addFlag(f'-std={cppVersion}')

   for includePath in extraIncludePaths:
       parser.addInclude(includePath)

   jsonStr = parser.parse(sourcePath, headerPath)

   if args.output_json:
      print(jsonStr)

   if args.ast_template is None:
      astTemplate = "example"
   else:
      astTemplate = args.ast_template[0]

   generator = Generator(cppGeneratorToolPath, astTemplate, args.stg_source_path, args.stg_source_instance_name, args.stg_header_path, args.stg_header_instance_name)

   if args.output_paths is None:
      sourceOutputPath = f'{sourceDir}/generated_file_{sourceName}'
      headerOutputPath = f'{headerDir}/generated_file_{headerName}'
   else:
      sourceOutputPath = args.output_paths[0]
      headerOutputPath = args.output_paths[1]

   print(f'Printing to {sourceOutputPath} and {headerOutputPath}')

   generationUnit = GenerationUnit(jsonStr, sourceOutputPath, headerOutputPath)
   generator.addGenerationUnit(generationUnit)

   generator.run()

if __name__ == "__main__":
   main()
