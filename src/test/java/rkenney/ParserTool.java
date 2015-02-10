package rkenney;

import static org.junit.Assert.assertTrue;
import jacle.common.io.FilesExt;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.google.common.base.Splitter;

public class ParserTool {
	
	@Test
	public void test() throws Exception {

		File exportedFile = new File("/home/rkenney/Downloads/VBoxShared/SY0-401.txt"); 
		File outputFile = new File("/home/rkenney/Downloads/VBoxShared/SY0-401.anki.txt"); 
		
		String raw = FilesExt.toString(exportedFile, StandardCharsets.UTF_8);
		String withoutPageBreaks = removePageBreaks(raw);
		String ankiFormat = toAnkiHtmlFormat(withoutPageBreaks);
		FilesExt.write(ankiFormat, outputFile, StandardCharsets.UTF_8);
	}
	
	private String toAnkiHtmlFormat(String string) {
		StringBuilder ankiFormat = new StringBuilder();
		LinkedList<String> lines = toQueueOfLines(string);
		while (!lines.isEmpty()) {
			// Find question start
			while (!lines.isEmpty()) {
				if (lines.get(0).startsWith("QUESTION NO")) {
					break;
				}
				lines.pollFirst();
			}
			if (lines.isEmpty()) {
				break;
			}
			// Append question
			while (!lines.isEmpty()) {
				if (lines.get(0).startsWith("Answer")) {
					break;
				} else {
					ankiFormat.append("<div>").append(lines.pollFirst()).append("</div>");
				}
			}
			// Append answer
			if (lines.isEmpty()) {
				throw new RuntimeException("Missing answer for question");
			}
			ankiFormat.append("\t").append("<div>").append(lines.pollFirst()).append("</div>").append(System.lineSeparator());
		}
		return ankiFormat.toString();
	}

	private LinkedList<String> toQueueOfLines(String withoutPageBreaks) {
		Iterable<String> lines = Splitter.on(Pattern.compile("\r?\n")).split(withoutPageBreaks);
		LinkedList<String> stack = new LinkedList<String>();
		for (String line : lines) { stack.add(line); }
		return stack;
	}

	private static String removePageBreaks(String pdfSource) {
		Pattern pageBreak = Pattern.compile("(\r?\n)*\"Pass Any Exam. Any Time.\" - www.actualtests.com(\r?\n)*\\d*(\r?\n)*\\fCompTIA SY0-401 Exam", Pattern.DOTALL);
		Matcher matcher = pageBreak.matcher(pdfSource);
		assertTrue(matcher.find());
		return matcher.replaceAll("");
	}
}
