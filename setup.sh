cd CppGeneratorTool

./setup.sh

echo "Rename config-sample.json to config.json and move to cpp-gen folder"

echo "Place the following path in cpp-gen/config.json"
echo $(realpath out/artifacts/CppGeneratorTool_jar/CppGeneratorTool.jar)

cd ..
