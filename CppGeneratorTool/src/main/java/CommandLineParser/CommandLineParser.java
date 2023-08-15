package CommandLineParser;

import org.apache.commons.cli.*;

public class CommandLineParser
{

    public String astTemplate;
    public String rawJson;
    public String sourceStgPath;
    public String sourceStgInstanceName;
    public String headerStgPath;
    public String headerStgInstanceName;

    public CommandLineParser(String[] args) 
    {
        Options options = new Options();

        options.addOption("x", true, "AST Template");
        options.addOption("j", true, "Raw JSON");
        options.addOption("c", true, "Source STG Path");
        options.addOption("i", true, "Source STG Instance Name");
        options.addOption("h", true, "Header STG Path");
        options.addOption("s", true, "Header STG Instance Name");

        try
        {
            CommandLine cmd = new DefaultParser().parse(options, args);

            astTemplate = cmd.getOptionValue("x");
            rawJson = cmd.getOptionValue("j");
            sourceStgPath = cmd.getOptionValue("c");
            sourceStgInstanceName = cmd.getOptionValue("i");
            headerStgPath = cmd.getOptionValue("h");
            headerStgInstanceName = cmd.getOptionValue("s");

        }
        catch (ParseException e)
        {
            System.out.println(e.getMessage());
            new HelpFormatter().printHelp("UtilityName", options);
            System.exit(1);
        }
    }
}
