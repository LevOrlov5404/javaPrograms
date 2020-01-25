import java.util.Scanner;

public class TicTacToe {
	static int coordX;
	static int coordY;
	static int iSymb;

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

	private static void readCoords(Scanner scanner, char[] symbols) {
		try {
			String coordinatesLine = scanner.nextLine();
			System.out.println("Enter the coordinates: " + coordinatesLine);
			String[] coordinatesStrings = coordinatesLine.split(" ");
			if (coordinatesStrings.length != 2) {
				System.out.println("You should enter two coordinates!");
				readCoords(scanner, symbols);
				return;
			}
			coordX = Integer.parseInt(coordinatesStrings[0]);
			coordY = Integer.parseInt(coordinatesStrings[1]);
			if (coordX < 1 || coordX > 3 || coordY < 1 || coordY > 3) {
				System.out.println("Coordinates should be from 1 to 3!");
				readCoords(scanner, symbols);
				return;
			}
			iSymb = coordX + coordY - (coordY == 3 ? 4 : 0) + (coordY == 1 ? 4 : 0);
			if (symbols[iSymb] != ' ') {
				System.out.println("This cell is occupied! Choose another one!");
				readCoords(scanner, symbols);
				return;
			}
		} catch (Exception e) {
			System.out.println("You should enter numbers!");
			readCoords(scanner, symbols);
		}
	}

	private static void printField(char[] symbols) {
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
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char[] symbols = "         ".toCharArray();
		printField(symbols);
		boolean movesX = true;
		boolean cycleToWork = true;
		while (cycleToWork) {
			readCoords(scanner, symbols);
			if (movesX) {
				symbols[iSymb] = 'X';
				movesX = false;
			} else {
				symbols[iSymb] = 'O';
				movesX = true;
			}
			printField(symbols);
			if (countChar(symbols, ' ') == 0) {
				System.out.println("Draw");
				cycleToWork = false;
			} else if (checkWinner(symbols, 'X')) {
				System.out.println("X wins");
				cycleToWork = false;
			} else if (checkWinner(symbols, 'O')) {
				System.out.println("O wins");
				cycleToWork = false;
			}
		}
	}
}
