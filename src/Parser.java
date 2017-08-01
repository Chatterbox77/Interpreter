import java.io.PrintStream;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Parser {
	Table<IdentifierInterface, NaturalNumberInterface> tb;
	PrintStream out;
	

	public Parser() {
		
		tb = new Table<IdentifierInterface, NaturalNumberInterface>();
		out = new PrintStream(System.out);

	}

	void start() {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {

			try {
				
				String input = in.nextLine();
				if (input.length() == 0) {
					throw new APException("Err. Line is empty");

				}
				Scanner inputScanner = new Scanner(input);
				statement(inputScanner);
			} catch (APException ape) {
				System.err.println(ape.getMessage());
				
				continue;

			}
		}
	}

	void removeWhiteSpaces(Scanner input) throws APException {
		while (nextCharIs(input, ' ')) {
			character(input, ' ');
		}
	}

	private void statement(Scanner input) throws APException {

		input.useDelimiter("");
		removeWhiteSpaces(input);

		if (nextCharIs(input, '?')) {
			print(input);

		} else if (nextCharIs(input, '/')) {

			character(input, '/');
			removeWhiteSpaces(input);
			comment(input);
			eol(input);
		} else if (nextCharIs(input, '!')) {

			character(input, '!');
			if (!input.hasNext()) {
				throw new APException("Err. Identifier is missing");
			}
			removeWhiteSpaces(input);
			Identifier key = readIdentifier(input);
			removeWhiteSpaces(input);
			eol(input);

			tb.remove(key);

		}

		else if (nextCharIs(input, '^')) {
			character(input, '^');
			if (input.hasNext())
				throw new APException("Err. Statement is not recognized");
			out.println("Terminated");
			System.exit(1);

		}

		else if (input.hasNext("[a-zA-Z]")) {

			assignmentStatement(input);

		} else {
			throw new APException(
					"Err. Input is invalid!Statement is not regonized");
		}
	}

	void assignmentStatement(Scanner input) throws APException {

		Identifier key = readIdentifier(input);

		removeWhiteSpaces(input);

		character(input, '=');
		removeWhiteSpaces(input);

		Set<NaturalNumberInterface> set = (Set<NaturalNumberInterface>) expression(input).clone();
		eol(input);
		tb.add(key, set);

	}

	Set<NaturalNumberInterface> expression(Scanner input) throws APException {

		Set<NaturalNumberInterface> set = term(input);
		removeWhiteSpaces(input);
		while (input.hasNext()) {

			if (nextCharIs(input, '+')) {
				character(input, '+');
				removeWhiteSpaces(input);
				Set<NaturalNumberInterface> set1 = term(input);
				set = (Set<NaturalNumberInterface>) set.union(set1);
				removeWhiteSpaces(input);
			} else if (nextCharIs(input, '-')) {
				character(input, '-');
				removeWhiteSpaces(input);
				set = (Set<NaturalNumberInterface>) set.difference(term(input));
				removeWhiteSpaces(input);

			} else if (nextCharIs(input, '|')) {
				character(input, '|');
				removeWhiteSpaces(input);
				set = (Set<NaturalNumberInterface>) set
						.symmetricDifference(term(input));
				removeWhiteSpaces(input);
			}

			 else if (nextCharIs(input, '(')) {
				character(input, '(');
				removeWhiteSpaces(input);
				set = expression(input);
				removeWhiteSpaces(input);
				character(input, ')');
			} else if (nextCharIs(input, ')'))
				break;
			else {

				throw new APException("Err. Input is incorrect");

			}

		}
		return set;
	}

	Set<NaturalNumberInterface> term(Scanner input) throws APException {
		removeWhiteSpaces(input);
		Set<NaturalNumberInterface> set = new Set<NaturalNumberInterface>();
		set = factor(input);
		removeWhiteSpaces(input);
		while (nextCharIs(input, '*')) {
			character(input, '*');
			removeWhiteSpaces(input);

			Set<NaturalNumberInterface> set1 = factor(input);

			removeWhiteSpaces(input);
			set = (Set<NaturalNumberInterface>) set.intersection(set1);
		}
		return set;
	}

	Set<NaturalNumberInterface> factor(Scanner input) throws APException {
		removeWhiteSpaces(input);
		if (nextCharIs(input, '{')) {

			character(input, '{');
			removeWhiteSpaces(input);

			Set<NaturalNumberInterface> set = naturalNumbers(input);

			removeWhiteSpaces(input);
			character(input, '}');

			return set;

		} else if (nextCharIsLetter(input)) {

			Identifier key = readIdentifier(input);
			removeWhiteSpaces(input);
			Set<NaturalNumberInterface> set = tb.getByKey(key);
			removeWhiteSpaces(input);
			return set;

		} else if (nextCharIs(input, '(')) {
			character(input, '(');
			removeWhiteSpaces(input);
			Set<NaturalNumberInterface> set = expression(input);
			removeWhiteSpaces(input);
			character(input, ')');
			removeWhiteSpaces(input);
			return set;

		} else {
			throw new APException("Err. Input is incorrect");
		}

	}

	boolean nextCharIsDigit(Scanner input) {
		return input.hasNext("[0-9]");
	}

	Set<NaturalNumberInterface> naturalNumbers(Scanner input)
			throws APException {
		if (!input.hasNext()) {
			throw new APException("Err. '}' is missing");
		}
		Set<NaturalNumberInterface> row = new Set<>();
		if (nextCharIsDigit(input)) {

			row.add(naturalNumber(input));
			while (nextCharIs(input, ',')) {
				character(input, ',');

				removeWhiteSpaces(input);
				row.add(naturalNumber(input));
				removeWhiteSpaces(input);
			}
		} else if (nextCharIs(input, '}'))
			return row;

		else {
			throw new APException(
					"Err. Invalid value,cannot create NaturalNumber from '"
							+ input.next() + "'");
		}

		return row;

	}

	NaturalNumberInteface naturalNumber(Scanner input) throws APException {
		NaturalNumberInteface n = new NaturalNumberInteface();
		int counter = 0;// if nothing would be added to n,the default value of n
						// would be returned,
						// to prevent this we use counter
		while (input.hasNext()) {

			if (!nextCharIsDigit(input)) {
				if (counter == 0)
					throw new APException("Err. NaturalNumber is not found");

				break;
			}

			n.append(input.next().charAt(0));
			counter++;

		}
		removeWhiteSpaces(input);
		return n;

	}

	public Identifier readIdentifier(Scanner input) throws APException {

		Identifier id = new Identifier(letter(input));
		while (!nextCharIs(input, ' ')) {
			if (input.hasNext("[|=+-[*])]") || booleanEndOfLine(input)) {
				break;
			}

			id.append(letter(input));
		}

		return id;

	}

	char letter(Scanner input) {
		String s = input.next();

		return s.charAt(0);
	}

	void print(Scanner input) throws APException {
		character(input, '?');
		removeWhiteSpaces(input);
		Set<NaturalNumberInterface> set = expression(input);
		eol(input);
		print(set);

	}

	void print(Set<NaturalNumberInterface> s) {

		for (NaturalNumberInterface n : s) {

			out.printf(n + " ");
		}
		out.println();

	}

	boolean nextCharIsLetter(Scanner in) {

		return in.hasNext("[a-zA-Z]");
	}

	void comment(Scanner input) {
		if (input.hasNext())
			input.nextLine();
	}

	char nextChar(Scanner input) {

		return input.next().charAt(0);
	}

	void character(Scanner input, char c) throws APException {

		if (!nextCharIs(input, c)) {
			throw new APException("Err. " + c + " is not found");
		}
		nextChar(input);
	}

	boolean booleanEndOfLine(Scanner input) throws APException {
		if (input.hasNext()) {
			return false;
		}
		return true;
	}

	void eol(Scanner input) throws APException {
		if (input.hasNext()) {
			throw new APException("Err. EOL is missing");
		}

	}

	boolean nextCharIs(Scanner in, char c) {
		return in.hasNext(Pattern.quote(c + ""));
	}

	public static void main(String[] args) {
		new Parser().start();
	}

}
