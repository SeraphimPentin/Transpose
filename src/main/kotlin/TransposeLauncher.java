
import org.kohsuke.args4j.*;

//import org.kohsuke.args4j.CmdLineException;
//import org.kohsuke.args4j.CmdLineParser;
//import org.kohsuke.args4j.Option;
//import org.kohsuke.args4j.Argument;


public class TransposeLauncher {

    @Option(name = "-a ", required = false, usage = "Each word takes up num characters")
    private Boolean a = true;

    @Option(name = "-t", required = false, usage = "Crop to the right size", forbids = {"-r"})
    private boolean t = true;

    @Option(name = "-r", usage = "Align the word to the right border", forbids = {"-t"})
    private boolean r = true;

    @Argument(metaVar = "OutputName", usage = "Output file name")
    private String outputFileName;

    @Argument(metaVar = "InputName", index = 1, usage = "Input file name")
    private String inputFileName;

    public TransposeLauncher() {
    }


    public static void main(String[] args) {
        new TransposeLauncher().parserArgs(args);
    }


    private void parserArgs(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar transpose.jar [-a num] -t -r [-o ofile] file");
            parser.printUsage(System.err);

        }
    }

}