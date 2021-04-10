
import org.kohsuke.args4j.*;
import java.io.IOException;

public class TransposeLauncher {

    @Option(name = "-a", usage = "Each word takes up num characters")
    private int a = 10;

    @Option(name = "-t", usage = "Crop the word to the desired size")
    private boolean t = false;

    @Option(name = "-r", usage = "Align the word to the right border")
    private boolean r = false;

    @Option(name = "-o", metaVar = "OutputName", usage = "Output file name")
    private String out = null;

    @Argument
    private String input = null;


    public static void main(String[] args) throws IOException {
        new TransposeLauncher().parserArgs(args);
    }

    private void parserArgs(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
            if (input.isEmpty()) throw new IOException();

        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar transpose.jar [-a num] [-t] [-r] [-o ofile] [file]");
            parser.printUsage(System.err);
            return;
        }

        TransposeKt.transpose(a, t, r, out, input);
    }
}