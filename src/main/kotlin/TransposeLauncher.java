
import org.kohsuke.args4j.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransposeLauncher {

    @Option(name = "-a", usage = "Each word takes up num characters")
    private boolean a = false;

    @Option(name = "-t", usage = "Crop the word to the desired size")
    private boolean t = false;

    @Option(name = "-r", usage = "Align the word to the right border")
    private boolean r = false;

    @Option(name = "-o", metaVar = "OutputName", usage = "Output file name")
    private String out = "no";

    @Argument
    private List<String> arguments = new ArrayList<String>();


    public static void main(String[] args) throws IOException {
        new TransposeLauncher().parserArgs(args);
    }

    private void parserArgs(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
            if(arguments.isEmpty() || !arguments.get(0).equals("transpose")){
                System.err.println("Error entering arguments");
                parser.printUsage(System.err);
                System.err.println("transpose [-a num] [-t] [-r] [-o ofile] [file]");
                throw new IllegalArgumentException();}
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("transpose [-a num] [-t] [-r] [-o ofile] [file]");
            parser.printUsage(System.err);
            throw new IllegalArgumentException("");
        }

        int num = 0;
        if (a) {
            try {
                num = Integer.parseInt(arguments.get(1));
            } catch (NumberFormatException e) {
                System.out.println("Wrong it's not a number");
            }
        } else if (t || r) num = 10;

        String input = "";
        try {
            input = arguments.get(arguments.size() - 1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            throw new IOException();
        }

        TransposeKt.transpose(num, t, r, out, input);
    }
}