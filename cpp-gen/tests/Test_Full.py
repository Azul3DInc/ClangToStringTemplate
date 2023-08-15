#!/usr/bin/python

import unittest
import json
import sys
import os
from py.util import readFileToString

scriptDir = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '../')))

from py.generator import Generator, GenerationUnit

class Test_Full(unittest.TestCase):
   def test_GenerateMock(self):
      # SETUP

      configPath = os.path.join(scriptDir, '../config.json')
      configStr = readFileToString(configPath)
      configJson = json.loads(configStr)
      
      gu = GenerationUnit('{"key": "value"}', 'output.cpp', 'output.h')

      generator = Generator(configJson.get("cppGeneratorToolPath"), "example", \
              "ExampleSource.stg", "ExampleImplementationFile", \
              "ExampleHeader.stg", "ExampleSpecificationFile")

      generator.addGenerationUnit(gu)

      # EXERCISE

      stdout = generator.generate(gu.getJsonStr())

      # VERIFY

      self.assertEqual(len(stdout), 2)

if __name__ == '__main__':
   unittest.main()
