import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {
		int i = 0;
		String mode = "enc";
		int key = 0;
		String data = null;
		String in = null;
		String out = null;
		String alg = "shift";
		while (i < args.length) {
			if ("-mode".equals(args[i])) {
				++i;
				if (i >= args.length) {
					System.out.println("Error: missed mode value");
					return;
				}
				if (!"dec".equals(args[i]) && !"enc".equals(args[i])) {
					System.out.println("Error: not correct mode value");
					return;
				}
				if ("dec".equals(args[i])) {
					mode = "dec";
				}
			} else if ("-key".equals(args[i])) {
				++i;
				if (i >= args.length) {
					System.out.println("Error: missed key value");
					return;
				}
				try {
					key = Integer.parseInt(args[i]);
				} catch (Exception e) {
					System.out.println("Error: not int key " + args[i]);
					return;
				}
			} else if ("-data".equals(args[i])) {
				++i;
				if (i >= args.length) {
					System.out.println("Error: missed data value");
					return;
				}
				data = args[i];
			} else if ("-in".equals(args[i])) {
				++i;
				if (i >= args.length) {
					System.out.println("Error: missed in value");
					return;
				}
				in = args[i];
			} else if ("-out".equals(args[i])) {
				++i;
				if (i >= args.length) {
					System.out.println("Error: missed out value");
					return;
				}
				out = args[i];
			} else if ("-alg".equals(args[i])) {
				++i;
				if (i >= args.length) {
					System.out.println("Error: missed alg value");
					return;
				}
				if (!"shift".equals(args[i]) && !"unicode".equals(args[i])) {
					System.out.println("Error: not correct alg value");
					return;
				}
				if ("unicode".equals(args[i])) {
					alg = "unicode";
				}
			} else {
				System.out.println("Error: not correct argument");
				return;
			}
			++i;
		}

		// input
		String line = "";
		if (data != null) {
			line = data;
		} else if (in != null) {
			try {
				line = Ridik.readFileAsString(in);
			} catch (Exception e) {
				System.out.println("Error: can not read from -in");
			}
		}

		// get new string
		LaunchAlgoritm launchAlgoritm = new LaunchAlgoritm(mode, alg);
		String newLine = launchAlgoritm.launch(line, key);

		// output
		if (out != null) {
			try (PrintWriter printWriter = new PrintWriter(out)) {
				printWriter.print(newLine);
			} catch (Exception e) {
				System.out.println("Error: can not write to -out " + e.getMessage());
			}
		} else
			System.out.println(newLine);
	}
}

// class for reading String from file
class Ridik {

	public static String readFileAsString(String fileName) throws IOException {
		return new String(Files.readAllBytes(Paths.get(fileName)));
	}
}

class LaunchAlgoritm {

	private Algoritm algoritm;

	public LaunchAlgoritm(String mode, String alg) {
		if ("unicode".equals(alg)) {
			if ("dec".equals(mode)) {
				this.algoritm = new UnicodeDec();
			} else {
				this.algoritm = new UnicodeEnc();
			}
		} else {
			if ("dec".equals(mode)) {
				this.algoritm = new ShiftDec();
			} else {
				this.algoritm = new ShiftEnc();
			}
		}
	}

	public String launch(String line, int key) {
		return String.valueOf(algoritm.work(line.toCharArray(), key));
	}
}

interface Algoritm {

	public char[] work(char[] line_chars, int key);
}

class ShiftEnc implements Algoritm {

	@Override
	public char[] work(char[] line_chars, int key) {
		int i = 0;
		while (i < line_chars.length) {
			if (line_chars[i] >= 'a' && line_chars[i] <= 'z') {
				line_chars[i] = (char) ('a' + (line_chars[i] - 'a' + key) % 26);
			} else if (line_chars[i] >= 'A' && line_chars[i] <= 'Z') {
				line_chars[i] = (char) ('A' + (line_chars[i] - 'A' + key) % 26);
			}
			++i;
		}
		return line_chars;
	}
}

class ShiftDec implements Algoritm {

	@Override
	public char[] work(char[] line_chars, int key) {
		int i = 0;
		while (i < line_chars.length) {
			if (line_chars[i] >= 'a' && line_chars[i] <= 'z') {
				int val = (line_chars[i] - 'a' - key) % 26;
				line_chars[i] = (char) (val < 0 ? 'z' + val + 1 : 'a' + val);
			} else if (line_chars[i] >= 'A' && line_chars[i] <= 'Z') {
				int val = (line_chars[i] - 'A' - key) % 26;
				line_chars[i] = (char) (val < 0 ? 'Z' + val + 1 : 'A' + val);
			}
			++i;
		}
		return line_chars;
	}
}

class UnicodeEnc implements Algoritm {

	@Override
	public char[] work(char[] line_chars, int key) {
		int i = 0;
		while (i < line_chars.length) {
			line_chars[i] = (char) (line_chars[i] + key);
			++i;
		}
		return line_chars;
	}
}

class UnicodeDec implements Algoritm {

	@Override
	public char[] work(char[] line_chars, int key) {
		int i = 0;
		while (i < line_chars.length) {
			line_chars[i] = (char) (line_chars[i] - key);
			++i;
		}
		return line_chars;
	}
}
