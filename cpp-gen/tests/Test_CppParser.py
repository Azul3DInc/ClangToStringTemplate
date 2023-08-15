#!/usr/bin/python

import unittest
import json
import sys
import os

script_dir = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '../')))

from py.parser import CppParser

class Test_CppParser(unittest.TestCase):
   def test_Initialize(self):
      # SETUP

      # EXERCISE

      sut = CppParser()

      # VERIFY

   def test_AddFlag(self):
      # SETUP

      cp = CppParser()

      # EXERCISE

      cp.addFlag('-Werror')

      # VERIFY

      cp.clangCommandList[-1] == '-Werror'

   def test_GetFileIncludeTokens(self):
      # SETUP
      test_directory = script_dir
      cp = CppParser()

      # EXERCISE

      tokens = cp.getFileIncludeTokens(f'{test_directory}/test_files/include/IFoo.h')
    
      # VERIFY

      self.assertListEqual(tokens, ["\"../../include/Bar/Bar.h\""])

   def test_ParseFooAST(self):
      # SETUP

      test_directory = script_dir
      cp = CppParser(f'{test_directory}/test_files/compile_commands.json')
      cp.addIncludes([f'{test_directory}/include'])

      # EXERCISE
    
      jsonStr = cp.parse(f'{test_directory}/test_files/src/IFoo.cpp', f'{test_directory}/test_files/include/IFoo.h')

      # VERIFY

      jsonAST = json.loads(jsonStr)
      self.assertEqual(jsonAST.get("name"), "fs")

   def test_compileCommandsJson(self):
      # SETUP

      test_directory = script_dir
      cp = CppParser(f'{test_directory}/test_files/compile_commands.json')

      # EXERCISE
        
      fileIncludePaths = cp.getCmakeIncludePaths("/home/user/project/file.cpp")

      # VERIFY

      self.assertEqual(len(fileIncludePaths), 2)

if __name__ == '__main__':
    unittest.main()
