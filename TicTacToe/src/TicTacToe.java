import java.util.Scanner;

public class TicTacToe {
	private static boolean checkWinner(char[] chars, char c) {
		if (chars[0] == c && chars[1] == c && chars[2] == c) {
			return  true;
		} else if (chars[3] == c && chars[4] == c && chars[5] == c) {
			return true;
		} else if (chars[6] == c && chars[7] == c && chars[8] == c) {
			return true;
		} else if (chars[0] == c && chars[3] == c && chars[6] == c) {
			return true;
		} else if (chars[1] == c && chars[4] == c && chars[7] == c) {
			return true;
		} else if (chars[2] == c && chars[5] == c && chars[8] == c) {
			return true;
		} else if (chars[0] == c && chars[4] == c && chars[8] == c) {
			return true;
		} else return chars[2] == c && chars[4] == c && chars[6] == c;
	}
	private static int countChar(char[] chars, char c) {
		int count = 0;
		int i = 0;
		while (i < chars.length) {
			if (chars[i] == c) {
				++count;
			}
			++i;
		}
		return count;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		char[] symbols = line.toCharArray();
		System.out.println("Enter cells: " + line);
		System.out.println("---------");
		int i = 0;
		while (i < 9) {
			if (i % 3 == 0) {
				System.out.print("| ");
			}
			System.out.print(symbols[i] + " ");
			if ((i + 1) % 3 == 0) {
				System.out.println("|");
			}
			++i;
		}
		System.out.println("---------");
		// continue
		int countX = countChar(symbols, 'X');
		int countO = countChar(symbols, 'O');
		boolean winX = checkWinner(symbols, 'X');
		boolean winO = checkWinner(symbols, 'O');
//		System.out.println(countX + " " + countO);
		if (Math.abs(countX - countO) > 1 || winX && winO) {
			System.out.println("Impossible");
		} else {
			if (winX) {
				System.out.println("X wins");
			} else if (winO) {
				System.out.println("O wins");
			} else if (countChar(symbols, '_') == 0) {
				System.out.println("Draw");
			} else {
				System.out.println("Game not finished");
			}
		}
	}
}
