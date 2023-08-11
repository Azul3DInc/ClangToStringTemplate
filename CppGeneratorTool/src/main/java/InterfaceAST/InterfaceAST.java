package InterfaceAST;

import ClangAST.ClangAST;
import Utils.Utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InterfaceAST
{
    public File file;

    public InterfaceAST(ClangAST clangAST)
    {
        this.file = new File(clangAST.getRoot());
    }

    public class File
    {
        public String namespace;
        public List<Class> classes;
        public List<String> includes;

        public File(ClangAST.ClangNode root)
        {
            this.namespace = root.getName();
            this.classes = new ArrayList<>();
            this.includes = root.getIncludes();

            includes.add("<vector>");

            Iterator<ClangAST.ClangNode> classIt = root.getInnerIterator();

            while (classIt.hasNext())
            {
                ClangAST.ClangNode classNode = classIt.next();

                Class klass = new Class(classNode, namespace);

                this.classes.add(klass);
            }
        }

        @Override
        public String toString()
        {

            return "File{" +
                    "namespace='" + namespace + '\'' +
                    ", classes=" + classes +
                    ", includes=" + includes +
                    '}';
        }

        public class Class
        {
            public String name;
            public String classType;
            public List<Method> methods;

            public Class(ClangAST.ClangNode classNode, String namespace)
            {
                this.name = classNode.getName();
                this.classType = classNode.getTagUsed();
                this.methods = new ArrayList<>();

                HashMap<String, Integer> methodCount = new HashMap<>();

                Iterator<ClangAST.ClangNode> methodIt = classNode.getInnerIterator();

                while(methodIt.hasNext())
                {

                    ClangAST.ClangNode methodNode = methodIt.next();
                    Method method;

                    if (methodNode.getKind().equals("AccessSpecDecl"))
                    {
                        continue;
                    }
                    else
                    {
                        method = new Method(methodNode, name, namespace);
                    }

                    if (method.name.charAt(0) != '~' && !method.name.equals(name) && !method.name.equals("operator="))
                    {
                        if (!methodNode.getPure())
                        {
                            System.err.println("Error: Non-pure virtual method in interface");
                            System.exit(1);
                        }
                        if (methodCount.containsKey(method.name))
                        {
                            int count = methodCount.get(method.name) + 1;
                            method.uniqueName = method.name + count;
                            methodCount.put(method.name, count);
                        }
                        else
                        {
                            methodCount.put(method.name, 0);
                        }

                        int index = 0;

                        for (Method.Parameter param : method.parameters)
                        {
                            index += 1;
                            param.varName = method.uniqueName + "_param_" + index;
                        }

                        methods.add(method);
                    }
                }
            }

            @Override
            public String toString()
            {
                return "Class{" +
                        "name='" + name + '\'' +
                        ", classType='" + classType + '\'' +
                        ", methods=" + methods +
                        '}';
            }

            public class Method
            {
                public String name;
                public String uniqueName;
                public String returnType;
                public String returnTypeVar;
                public boolean isSelfReferenced;
                public boolean isVoid;

                public List<Parameter> parameters;

                public Method(ClangAST.ClangNode methodNode, String className, String namespace)
                {
                    this.name = methodNode.getName();
                    this.uniqueName = methodNode.getName();
                    this.returnType = Utils.removeNamespaceScopeResolution(Utils.removeParametersFromQualType(methodNode.getQualType()), namespace);
                    this.returnTypeVar = Utils.removeRightmostRef(returnType);
                    this.isSelfReferenced = className.equals(Utils.removeRightmostRef(returnType));
                    this.isVoid = returnType.equals("void");
                    this.parameters = new ArrayList<>();

                    Iterator<ClangAST.ClangNode> paramIt = methodNode.getInnerIterator();
                    int index = 1;

                    while (paramIt.hasNext())
                    {
                        ClangAST.ClangNode paramNode = paramIt.next();
                        Parameter param = new Parameter(paramNode, name, index);
                        index += 1;

                        if (param.name != null)
                        {
                            parameters.add(param);
                        }
                    }
                }

                @Override
                public String toString()
                {
                    return "Method{" +
                            "name='" + name + '\'' +
                            ", uniqueName='" + uniqueName + '\'' +
                            ", returnType='" + returnType + '\'' +
                            ", isVoid=" + isVoid +
                            ", parameters=" + parameters +
                            '}';
                }

                public class Parameter
                {
                    public String name;
                    public String varName;
                    public String type;
                    public String varType;
                    public boolean isIterable;

                    public Parameter(ClangAST.ClangNode param, String methodName, int index)
                    {
                        this.name = param.getName();
                        this.varName = methodName + "_param_" + index;
                        this.type = param.getQualType();
                        this.varType = Utils.removeRightmostRef(Utils.removeConst(type));
                        this.isIterable = false;

                        if (name == null || name.equals(""))
                        {
                            name = "param_" + index;
                        }

                        if (type.contains("vector"))
                        {
                            includes.add("<iterator>");
                            includes.add("<algorithm>");

                            isIterable = true;
                        }

                    }

                    @Override
                    public String toString()
                    {
                        return "Parameter{" +
                                "name='" + name + '\'' +
                                ", varName='" + varName + '\'' +
                                ", type='" + type + '\'' +
                                ", varType='" + varType + '\'' +
                                '}';
                    }
                }
            }
        }
    }

    @Override
    public String toString()
    {
        return "InterfaceAST{" +
                "file=" + file +
                '}';
    }
}
