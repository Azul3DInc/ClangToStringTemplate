package Generator;

import ClangAST.ClangAST;
import InterfaceAST.InterfaceAST;
import java.util.stream.Collectors;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;


public class Generator
{
    ClangAST clangAST;
    String astTemplate;
    String stSourcePath;
    String stSourceInstanceName;
    String stHeaderPath;
    String stHeaderInstanceName;

    public Generator(String json, String astTemplate, String stSourcePath, String stSourceInstanceName, String stHeaderPath, String stHeaderInstanceName)
    {
        this.clangAST = new ClangAST().parse(json);
        this.astTemplate = astTemplate;
        this.stSourcePath = stSourcePath;
        this.stSourceInstanceName = stSourceInstanceName;
        this.stHeaderPath = stHeaderPath;
        this.stHeaderInstanceName = stHeaderInstanceName;
    }

    public void run()
    {
        if (astTemplate.equals("interface")) {
            InterfaceAST interfaceAST = new InterfaceAST(clangAST);
            interfaceAST.file.includes = interfaceAST.file.includes.stream().distinct().collect(Collectors.toList());

            outputSourceHeaderFiles(interfaceAST.file);
        }
    }

    public void outputSourceHeaderFiles(Object astFile)
    {
        ST stApplicationImplementation = getSTInstance(stSourcePath, stSourceInstanceName);
        String applicationImplementation = renderSTInstance(astFile, stApplicationImplementation);
        System.out.println(applicationImplementation);

        System.out.println("%%%FILE_DELIMITER%%%");

        ST stApplicationSpecification = getSTInstance(stHeaderPath, stHeaderInstanceName);
        String applicationSpecification = renderSTInstance(astFile, stApplicationSpecification);
        System.out.println(applicationSpecification);
    }

    public String renderSTInstance(Object astFile, ST stSpecification)
    {
        stSpecification.add("node", astFile);
        return stSpecification.render();
    }

    public ST getSTInstance(String stPath, String stInstanceName)
    {
        STGroup group = new STGroupFile(stPath);
        return group.getInstanceOf(stInstanceName);
    }

}