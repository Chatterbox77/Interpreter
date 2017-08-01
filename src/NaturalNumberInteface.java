public class NaturalNumberInteface implements NaturalNumberInterface {
	private StringBuffer value;
	boolean leadingZero = false;

	public NaturalNumberInteface(NaturalNumberInteface src) {
		this.value = new StringBuffer(src.value);
	}

	public NaturalNumberInteface() {

		this.value = new StringBuffer("0");// default value for NaturalNumber
	}
	public NaturalNumberInteface(int i) {

		this.value = new StringBuffer();
		value.append(i);
	}

	public NaturalNumberInteface(char c) {

		this.value = new StringBuffer();
		value.append(c);
	}

	public void append(char c) throws APException {
		if (value.charAt(0) == '0' && c == '0' && leadingZero) {

			throw new APException("Err. Leading zeros are not allowed");

		}
		if (c == '0' && value.charAt(0) == '0' && !leadingZero) {
			leadingZero = true;
			return;
		}

		if (!Character.isDigit(c)
				|| Integer.parseInt(Character.toString(c)) < 0) {
			throw new APException(
					"Err. Digits in the NaturalNumber can not be < 0");
		}
		if (value.charAt(0) == '0') {
			value = new StringBuffer();// remove default value
		}
		value.append(c);
	}

	private int compare(NaturalNumberInteface n) {

		if (value.length() > n.value.length())
			return 1;
		if (value.length() < n.value.length())
			return -1;
		else {
			for (int i = 0; i < n.value.length(); i++) {

				if (Integer.parseInt(Character.toString(value.charAt(i))) > Integer
						.parseInt(Character.toString(n.value.charAt(i)))) {
					return 1;
				}
				if (Integer.parseInt(Character.toString(value.charAt(i))) < Integer
						.parseInt(Character.toString(n.value.charAt(i)))) {
					return -1;
				}

			}
		}

		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return value.toString();
	}

	@Override
	public int compareTo(NaturalNumberInterface n) {

		if (this.compare((NaturalNumberInteface) n) == 0)
			return 0;
		if (this.compare((NaturalNumberInteface) n) < 0)
			return -1;
		return 1;
	}

	@Override
	public boolean equals(NaturalNumberInterface n) {
		if (n == null)
			return false;
		NaturalNumberInteface nn = (NaturalNumberInteface) n;
		return this.compareTo(nn) == 0;
	}

	@Override
	public NaturalNumberInteface clone() {
		// TODO Auto-generated method stub
		return new NaturalNumberInteface(this);
	}

}
