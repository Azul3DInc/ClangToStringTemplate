#!/usr/bin/env python3

import subprocess
import json
import sys
import os
from py.util import writeStringToFile

class GenerationUnit():
   def __init__(self, jsonStr, cppDestinationPath, headerDestinationPath):
      self.jsonStr = jsonStr
      self.cppDestinationPath = cppDestinationPath
      self.headerDestinationPath = headerDestinationPath

   def getJsonStr(self):
      return self.jsonStr
    
   def getHeaderDestinationPath(self):
      return self.headerDestinationPath
    
   def getCppDestinationPath(self):
      return self.cppDestinationPath


class Generator():
   def __init__(self, cppGeneratorToolPath, template, sourceStgPath, sourceStgInstanceName, headerStgPath, headerStgInstanceName):
      self.generationUnits = []
      self.cppGeneratorToolPath = cppGeneratorToolPath
      self.template = template
      self.sourceStgPath = sourceStgPath
      self.sourceStgInstanceName = sourceStgInstanceName
      self.headerStgPath = headerStgPath
      self.headerStgInstanceName = headerStgInstanceName

   def run(self):
      for unit in self.generationUnits:
            stdoutStr = self.generate(unit.getJsonStr())

            stdoutArray = stdoutStr.split("%%%FILE_DELIMITER%%%\n")

            writeStringToFile(stdoutArray[0], unit.cppDestinationPath)
            writeStringToFile(stdoutArray[1], unit.headerDestinationPath)

   def generate(self, jsonArg):
            cppGeneratorToolCommand = ['java', '-jar', self.cppGeneratorToolPath, '-x', self.template, '-j', jsonArg]

            cppGeneratorToolCommand.extend(['-c', self.sourceStgPath])
            cppGeneratorToolCommand.extend(['-i', self.sourceStgInstanceName])
            cppGeneratorToolCommand.extend(['-h', self.headerStgPath])
            cppGeneratorToolCommand.extend(['-s', self.headerStgInstanceName])

            process = subprocess.Popen(cppGeneratorToolCommand, stdout=subprocess.PIPE, stderr=subprocess.PIPE)

            stdout, stderr = process.communicate()
            stdoutStr = stdout.decode('utf-8')
            stderrStr = stderr.decode('utf-8')

            if stderrStr.strip() != "":
                print(f"{stderrStr.strip()}")
                sys.exit(1)

            return stdoutStr

   def addGenerationUnit(self, generationUnit):
      self.generationUnits.append(generationUnit)

