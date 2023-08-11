import unittest
import os

script_dir = os.path.dirname(os.path.abspath(__file__))

if __name__ == '__main__':
   test_loader = unittest.TestLoader()
   test_suite = test_loader.discover(f'{script_dir}/tests', pattern='Test_*.py')
   
   test_runner = unittest.TextTestRunner(verbosity=2)
   test_runner.run(test_suite)

