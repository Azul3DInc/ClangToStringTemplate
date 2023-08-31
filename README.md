# ClangToStringTemplate

## Introduction

ClangToStringTemplate is a C++ to StringTemplate code generation framework. ClangToStringTemplate enables you to leverage the Clang parser with StringTemplate4. This combination allows you to generate C++ based on structurally similar C++ code generalized as custom ASTs.
Designed with modularity in mind, you may also mix and match your custom ASTs and String Template Group files.
This project also provides an optional Vim plugin that can be used to generate C++ files on the fly.

## Requirements

You must have working installations of Python3, Clang, Maven, and the JDK ($\geq$ ver. 16) to run the tool.

Projects using ClangToStringTemplate must have CMake ($\geq$ ver. 2.8.5) as your build system with `compile_commands.json` enabled. You can do so with the following CMake option:

```cmake
set(CMAKE_EXPORT_COMPILE_COMMANDS ON)
```

This allows the tool to include the dependencies to fully parse your code with Clang.

## Installation

Run `./setup.sh` in ClangToStringTemplate. The script will output the path to your jar. 

Rename `config-sample.json` to `config.json` and place into the cpp-gen folder. Modify your `config.json` in cpp-gen to include the jar path.
If you're using the Vim plugin, you'll need to place the cpp-gen folder where you store your Vim plugins. Additionally, include the `config.json` in that same folder.

## Example Mock Generation

ClangToStringTemplate includes mock generation for interfaces as a fully functioning example demonstrating its usage.  

## Vim Plugin

ClangToStringTemplate also includes a Vim plugin integration. The plugin can automatically gather the necessary file paths to invoke the tool and seamlessly fit into your development workflow.

You can install the Vim plugin with `vim-plug` with:

```vim
Plug 'Azul3DInc/ClangToStringTemplate', {'rtp': 'cpp-gen/'}
```

Remember, you'll need to add the CppGeneratorTool.jar to the Plugin's `config.json`. The Vim plugin cannot be installed standalone and you must also clone CppGeneratorTool and build the jar. 
The cpp-gen folder will be located in your vim plugins folder.

To see an example, first build `Application` in `Test_Application` with CMake. Then vim into an interface in `Test_Application` and type `:GenerateMock`.

## Extending to fit your workflow

If you want to generate based on your own templates and ASTs, you must implement them for the CppGeneratorTool and update its jar file.

Here is a list of steps to get started:
1. Write custom AST in CppGeneratorTool that builds itself from the Clang AST.
2. Add AST to jar command line arguments
3. Write .stg templates
4. (optional) extend Vim plugin to call cpp-gen.py with your AST and templates

Here's an example python invocation:
```bash
python3 cpp-gen.py src/Interface.cpp include/Interface.h build/compile_commands.json MockSource.stg MockImplementationFile MockHeader.stg MockSpecificationFile \
   --ast-template interface --output-paths src/Mocks/Mock.cpp include/Mocks/Mock.h
```

## Known Issues
- In the event of a segmentation fault during the parsing process, consider upgrading your version of clang in `config.json`. Known to fix this issue with clang++15.

## Relevant Resources
[StringTemplate cheat sheet](https://github.com/antlr/stringtemplate4/blob/master/doc/cheatsheet.md)

## License

For licensing information, including third-party dependencies, see the [LICENSES](LICENSES) file.

