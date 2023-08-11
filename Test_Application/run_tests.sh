#!/bin/bash

find include/Mocks -mindepth 1 ! -name .gitignore -exec rm -r {} +

rm -rf build
cmake -H. -Bbuild

cmake --build ./build --target Application

python3 ../cpp-gen/cpp-gen.py src/IFoo.cpp include/IFoo.h build/compile_commands.json MockSource.stg MockImplementationFile MockHeader.stg MockSpecificationFile \
   --ast-template interface --output-paths src/Mocks/MockIFoo.cpp include/Mocks/MockIFoo.h 

python3 ../cpp-gen/cpp-gen.py src/IFunction.cpp include/IFunction.h build/compile_commands.json MockSource.stg MockImplementationFile MockHeader.stg MockSpecificationFile \
   --ast-template interface --output-paths src/Mocks/MockIFunction.cpp include/Mocks/MockIFunction.h

python3 ../cpp-gen/cpp-gen.py src/IParameterType.cpp include/IParameterType.h build/compile_commands.json MockSource.stg MockImplementationFile MockHeader.stg MockSpecificationFile \
   --ast-template interface --output-paths src/Mocks/MockIParameterType.cpp include/Mocks/MockIParameterType.h

python3 ../cpp-gen/cpp-gen.py src/IReturnType.cpp include/IReturnType.h build/compile_commands.json MockSource.stg MockImplementationFile MockHeader.stg MockSpecificationFile \
   --ast-template interface --output-paths src/Mocks/MockIReturnType.cpp include/Mocks/MockIReturnType.h

cd build

cmake --build . --target Test_Foo
cmake --build . --target Test_Function
cmake --build . --target Test_ParameterType
cmake --build . --target Test_ReturnType

ctest -V

cd ..
