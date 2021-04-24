
import org.kohsuke.args4j.*;

import java.io.IOException;

public class TransposeLauncher {

    @Option(name = "-a", usage = "Each word takes up num characters")
    int a = 10;

    @Option(name = "-t", usage = "Crop the word to the desired size")
    boolean t = false;

    @Option(name = "-r", usage = "Align the word to the right border")
    boolean r = false;

    @Option(name = "-o", metaVar = "OutputName", usage = "Output file name")
    String out = null;

    @Argument
    String input = null;


    public static void main(String[] args) throws IOException {
        new TransposeLauncher().parserArgs(args);
    }

    void parserArgs(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);

        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar transpose.jar [-a num] [-t] [-r] [-o ofile] [file]");
            parser.printUsage(System.err);
        }
    }
}