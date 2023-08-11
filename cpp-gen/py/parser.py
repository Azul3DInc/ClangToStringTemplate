#!/usr/bin/env python3

import subprocess
import json
import re
from py.util import readFileToString

class CppParser():
   def __init__(self, compileCommandsPath=None, clangppVersion="clang++"):
      self.clangCommandList = [clangppVersion, "-Werror", "-fsyntax-only", "-x", "c++-header", "-Xclang", "-ast-dump=json"]
      self.compileCommandsPath = compileCommandsPath

   def parse(self, cppPath, headerPath):

      if self.compileCommandsPath:
            includePaths = self.getCmakeIncludePaths(cppPath)
            self.addIncludes(includePaths)

      self.clangCommandList.append(headerPath)

      clangProcess = subprocess.run(self.clangCommandList, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
      
      if clangProcess.returncode == 0:
            jsonAST = json.loads(clangProcess.stdout.decode())
      else:
            errorMessage = clangProcess.stderr.decode()
            print(f"Error: {errorMessage}")
            raise Exception(errorMessage)

      innerNodes = jsonAST.get("inner", None)

      for obj in innerNodes:
            if obj.get("loc").get("file") == headerPath:
               jsonData = obj
    
      fileIncludes = self.getFileIncludeTokens(cppPath)
      jsonData.update({"includes" : fileIncludes})

      return json.dumps(jsonData, indent=3)

   def addFlag(self, flagStr, valueStr=None):
      self.clangCommandList.append(flagStr)
      if valueStr:
            self.clangCommandList.append(valueStr)

   def addInclude(self, includePath):
       self.addFlag("-isystem", valueStr=includePath)

   def getFileIncludeTokens(self, filePath):
      includes = []
      include_regex = r'#include\s+(<[^>]+>|"[^"]+")'

      fileContent = readFileToString(filePath)
      matches = re.findall(include_regex, fileContent)
      includes.extend(matches)
      return includes


   def getCmakeIncludePaths(self, cppPath):
      commandsStr = readFileToString(self.compileCommandsPath)
      commandsJson = json.loads(commandsStr)

      def extractCmakeIncludePaths(inputString):
            includePaths = []
            pattern = r'-isystem\s+(\S+)'
            matches = re.findall(pattern, inputString)
            includePaths.extend(matches)
            return includePaths

      for obj in commandsJson:
            if obj["file"] == cppPath:
               fullCommand = obj["command"]
               return extractCmakeIncludePaths(fullCommand)
      return []

   def addIncludes(self, includePaths):
      for includePath in includePaths:
            self.addInclude(includePath)


