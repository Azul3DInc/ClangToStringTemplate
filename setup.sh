#!/bin/bash

# Exit on any error
set -e

# 1. Build the Java project
echo "Building Java project..."
cd java_project
./build_script.sh  # Assuming you have a build script for the Java project
cd ..

# 2. Install the Python script
echo "Installing Python script..."
pip install ./python_script/

# 3. Install the Vim plugin
echo "Installing Vim plugin..."
mkdir -p ~/.vim/bundle/
cp -r python_script/vim_plugin ~/.vim/bundle/

# 4. Configuration for the .jar path
read -p "Enter the path to the .jar file (default: /usr/local/my_project/java_project/build_output.jar): " jar_path
jar_path=${jar_path:-/usr/local/my_project/java_project/build_output.jar}
echo "{\"jar_path\": \"$jar_path\"}" > python_script/config.json

echo "Installation completed!"
