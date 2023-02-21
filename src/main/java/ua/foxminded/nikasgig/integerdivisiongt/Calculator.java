package ua.foxminded.nikasgig.integerdivisiongt;

public class Calculator {

	private StringBuilder result = new StringBuilder();
	private StringBuilder quotient = new StringBuilder();
	private StringBuilder reminder = new StringBuilder();

	public String createLongDivision(int dividend, int divisor) {
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);
		if (isValidToDivision(dividend, divisor)) {
			return divisionExaption(dividend, divisor);
		}
		String[] digits = splitDividend(dividend);
		Integer reminderNumber;
		Integer multiplyResult;
		Integer mod;
		for (int i = 0; i < digits.length; i++) {
			reminder.append(digits[i]);
			reminderNumber = Integer.parseInt(reminder.toString());
			if (reminderNumber >= divisor) {
				mod = reminderNumber % divisor;
				multiplyResult = reminderNumber / divisor * divisor;
				String lastReminder = String.format("%" + (i + 2) + "s", "_" + reminderNumber.toString());
				result.append(lastReminder).append("\n");
				String multiply = String.format("%" + (i + 2) + "d", multiplyResult);
				result.append(multiply).append("\n");
				Integer tab = lastReminder.length() - calculateDigit(multiplyResult);
				result.append(createDivisor(multiplyResult, tab)).append("\n");
				quotient.append(reminderNumber / divisor);
				reminder.replace(0, reminder.length(), mod.toString());
				reminderNumber = Integer.parseInt(reminder.toString());
			}
			if (i == digits.length - 1) {
				result.append(String.format("%" + (i + 2) + "s", reminderNumber.toString())).append("\n");
			}
		}
		modifyResultView(dividend, divisor);
		String cloneResult = result.toString();
		cleanStringBuilderMemory();
		return cloneResult;
	}

	private boolean isValidToDivision(int dividend, int divisor) {
		return (divisor == 0 || dividend < divisor);
	}

	private String divisionExaption(int dividend, int divisor) {
		if (divisor == 0) {
			return "Divisor can't be 0";
		} else if (dividend < divisor) {
			return "Dividend can't be less than divisor";
		}
		return "Unknown error";
	}

	private String[] splitDividend(int dividend) {
		return String.valueOf(dividend).split("");
	}

	private String createDivisor(Integer multiplyResult, Integer tab) {
		return assemblyString(tab, ' ') + assemblyString(calculateDigit(multiplyResult), '-');
	}

	private int calculateDigit(int i) {
		return (int) Math.log10(i) + 1;
	}

	private String assemblyString(int numberOfSymbols, char symbol) {
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < numberOfSymbols; i++) {
			string.append(symbol);
		}
		return string.toString();
	}

	private void modifyResultView(Integer dividend, Integer divisor) {
		int[] index = new int[3];
		for (int i = 0, j = 0; i < result.length(); i++) {
			if (result.charAt(i) == '\n') {
				index[j] = i;
				j++;
			}
			if (j == 3) {
				break;
			}
		}
		int tab = calculateDigit(dividend) + 1 - index[0];
		result.insert(index[2], assemblyString(tab, ' ') + "│" + quotient.toString());
		result.insert(index[1], assemblyString(tab, ' ') + "│" + assemblyString(quotient.length(), '-'));
		result.insert(index[0], "│" + divisor);
		result.replace(1, index[0], dividend.toString());
	}

	private void cleanStringBuilderMemory() {
		result = new StringBuilder();
		quotient = new StringBuilder();
		reminder = new StringBuilder();
	}
}
