#!/usr/bin/env python3

import os
import sys

def readFileToString(filePath):
   with open(filePath, 'r') as file:
      fileContent = file.read()
   return fileContent

def writeStringToFile(fileContent, filePath):
   with open(filePath, 'w') as file:
      file.write(fileContent)

def checkValidFile(filePath):
   if not os.path.isfile(filePath):
      print(f"Error: Invalid file \'{filePath}\'")
      sys.exit(1)
