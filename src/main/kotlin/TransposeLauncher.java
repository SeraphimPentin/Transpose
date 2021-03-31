
import org.kohsuke.args4j.*;

import java.util.ArrayList;
import java.util.List;

public class TransposeLauncher {

    @Option(name = "-a", usage = "Each word takes up num characters")
    private boolean a = false;

    @Option(name = "-t", usage = "Crop to the right size")
    private boolean t = false;

    @Option(name = "-r", usage = "Align the word to the right border")
    private boolean r = false;

//    @Option(name = "-o", metaVar = "OutputName", usage = "Output file name")
//    private String out;

    @Argument
    private List<String> arguments = new ArrayList<String>();

    public TransposeLauncher() {
    }

//    @Argument(metaVar = "InputName", index = 1, usage = "Input file name")
//    private String inputFile;


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
            throw new IllegalArgumentException("");

        }
        int num = 0;
        System.out.println(arguments.get(0));
        if (a) {
            try {
                num = Integer.parseInt(arguments.get(1));
            } catch (NumberFormatException e) {
                System.out.println("Wrong it's not a number");
            }
        } else if (t || r) num = 10;

        String input = arguments.get(arguments.size() - 1);// input
        String out = arguments.get(arguments.size() - 2);// input
        System.out.println(arguments.get(0) + " 000");
        System.out.println(arguments.get(1) + " 111");
//        System.out.println(arguments.get(2) + " 222");
//        System.out.println(arguments.get(3) + " 333");
        System.out.println(input);


        TransposeKt.transpose(num, t, r, out, input);
    }
}