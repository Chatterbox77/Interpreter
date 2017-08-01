public class Identifier implements IdentifierInterface {

	private StringBuffer value;

	public Identifier(Identifier src) {

		this.value = new StringBuffer(src.value);
	}



	public Identifier(char c) throws APException {
		
		if (!Character.isLetter((c))) {
			throw new APException(
					"Err. First char is not a letter,Identifier should start with a letter");
		}
		this.value = new StringBuffer();
		value.append(c);
	}

	@Override
	public IdentifierInterface clone() {

		return new Identifier(this);
	}

	@Override
	public void append(char c) throws APException {
	
		if (value.capacity() == 0 && !Character.isLetter(c))
			throw new APException("Err. Identifier should start with a letter");
		if (!new Character(c).toString().matches("^[a-zA-Z0-9]*$"))
			throw new APException("Err. Charset is not alphanumeric");
		if (Character.isDigit(c) || Character.isLetter(c)) {
			value.append(c);

		} 

	}

	@Override
	public char charAt(int index) throws APException {

		if (index >= value.capacity())
			throw new APException("Err. Index out of bounds");
		return value.charAt(index);
	}

	@Override
	public int compareTo(IdentifierInterface i) {
		if (value.toString().equals(((Identifier) i).value.toString()))
			return 0;
		if (value.toString().compareTo(((Identifier) i).value.toString()) < 0)
			return -1;
		return 1;
	}

	@Override
	public boolean equals(IdentifierInterface i) {
		return this.compareTo((Identifier) i) == 0;

	}

	@Override
	public String toString() {

		return value.toString();
	}

}
