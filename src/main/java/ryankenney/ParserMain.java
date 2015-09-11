package ryankenney;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

import com.google.common.base.Throwables;

public class ParserMain {

	private static final String INPUT_FILE_OPTION = "-i";
	private static final String OUTPUT_FILE_OPTION = "-o";
	
	public static void main(String[] args) throws Exception {
		LinkedList<String> argList = new LinkedList<String>(Arrays.asList(args));
		File inputFile = null;
		File outputFile = null;
		try {
			while (!argList.isEmpty()) {
				String arg = getRequiredArg(argList);
				switch (arg) {
				case INPUT_FILE_OPTION:
					inputFile = new File(getRequiredArg(argList));
					break;
				case OUTPUT_FILE_OPTION:
					outputFile = new File(getRequiredArg(argList));
					break;
				default:
					printUsage();
					System.exit(1);
				}
			}
		} catch (UsageException e) {
			printUsage();
			System.exit(1);
		}

		if (inputFile == null) {
			throw new RuntimeException("Missing required arg: "+INPUT_FILE_OPTION);
		}
		if (outputFile == null) {
			throw new RuntimeException("Missing required arg: "+OUTPUT_FILE_OPTION);
		}

		new Parser(inputFile, outputFile).run();
	}
	
	private static String getRequiredArg(LinkedList<String> argList) {
		if (argList.isEmpty()) {
			throw new UsageException();
		}
		return argList.removeFirst();
	}

	private static void printUsage() {
		try (ByteArrayOutputStream  stream = new ByteArrayOutputStream(); PrintWriter writer = new PrintWriter(stream)) {
			writer.printf("%nUsage: %n");
			writer.printf("%n");
			writer.printf("-i <file>: Specify the input txt file (format defaults to ActualTests.com pdf export via pdftotext)%n");
			writer.printf("-o <file>: Specify the output txt file (format defaults to anki html)%n");
			System.out.println(stream.toString());
		} catch (IOException e) {
			Throwables.propagate(e);
		}
	}
	
}
