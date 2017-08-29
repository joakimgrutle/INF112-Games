package logicClasses;

public class Pin {

	private int[] pinkey = new int[3];
	private int[] pin = new int[3];
	private int index = 0;

	public Pin() {
		pinkey[0] = 1;
		pinkey[1] = 3;
		pinkey[2] = 4;
		pin[0] = -1;
		pin[1] = -1;
		pin[2] = -1;
	}

	public void reset() {
		pin[0] = -1;
		pin[1] = -1;
		pin[2] = -1;
		index = 0;
	}

	public String getnumber(int index) {
		if (pin[index] == -1)
			return "*";
		else
			return new Integer(pin[index]).toString();
	}

	public void input(int input) {
		if (index > 2) {
			index = 0;
			reset();
		}
		pin[index] = input;
		index++;
	}

	public boolean isFinish() {
		boolean check = true;
		for (int i = 0; i < pin.length; i++) {
			if (pin[i] != pinkey[i])
				check = false;
		}
		return check;
	}

}
