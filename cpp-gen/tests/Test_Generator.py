#!/usr/bin/python

import unittest
import json
import sys
import os

script_dir = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '../')))

from py.generator import Generator, GenerationUnit

class Test_Generator(unittest.TestCase):
   def test_InitializeGenerator(self):
      # SETUP

      generator = Generator("/opt/CppGeneratorTool.jar", "example", \
              "ExampleSource.stg", "ExampleImplementationFile", \
              "ExampleHeader.stg", "ExampleSpecificationFile")

      # EXERCISE

      # VERIFY

   def test_AddGenerationUnit(self):
      # SETUP

      generator = Generator("/opt/CppGeneratorTool.jar", "example", \
              "ExampleSource.stg", "ExampleImplementationFile", \
              "ExampleHeader.stg", "ExampleSpecificationFile")
      gu = GenerationUnit("{}", "output.cpp", "output.h")
    
      # EXERCISE

      generator.addGenerationUnit(gu)


      # VERIFY

      len(generator.generationUnits) == 1
      self.assertEqual(generator.generationUnits[0].getJsonStr(), "{}")
      

if __name__ == '__main__':
   unittest.main()
