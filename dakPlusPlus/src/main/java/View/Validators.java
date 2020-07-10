package View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validators {
	protected static String requestOptionalStringInput() {
		Scanner scanner = new Scanner(System.in);
		String input = null;
		do {
			System.out.println("Enter a text hier: ");
			input = scanner.nextLine();
			if (input.isEmpty() || input.isBlank())
				input= "No text entered";
		} while (input.isEmpty() || input.isBlank());

		return input;
	}
	protected static int requestIntInput(int lower, int upper) {
		Scanner scanner = new Scanner(System.in);
		int input = -1;
		do {
			try {
				System.out.print("Make a choice: ");
				input = scanner.nextInt();
			} catch (InputMismatchException e) {
				input = -1;
			}
			scanner.nextLine();
			if (input < lower || input > upper)
				System.out.println("That's not a valid number!");
		} while (input < lower || input > upper);

		return input;
	}

	protected static double requestPriceInput() {

		Scanner scanner = new Scanner(System.in);
		double input = 0;
		do {

			System.out.print("Enter a price hier: ");
			input = scanner.nextDouble();
			if (input < 0)
				System.out.println("The price must be > 0");
		} while (input < 0);

		return input;
	}

	protected static double requestSalaryInput() {

		Scanner scanner = new Scanner(System.in);
		double input = 0;
		do {

			System.out.print("Enter a salary hier: ");
			input = scanner.nextDouble();
			if (input < 1)
				System.out.println("That's not a valid salary!");
		} while (input < 1);

		return input;
	}

	protected static String requestPhoneInput() {
		Scanner scanner = new Scanner(System.in);
		String input = null;
		boolean match = false;
		do {
			System.out.println("Enter a phone number hier: ");
			input = scanner.nextLine();
			match = Pattern.matches("^(0[1-9][0-9]{8})$", input);
			if (!match) {
				System.out.println("That's not a valid name!");
			}
		} while (!match);

		return input;
	}

	protected static String requestStringInput() {
		Scanner scanner = new Scanner(System.in);
		String input = null;
		do {
			System.out.println("Enter a text hier: ");
			input = scanner.nextLine();
			if (input.isEmpty() || input.isBlank())
				System.out.println("That's not a valid name!");
		} while (input.isEmpty() || input.isBlank());

		return input;
	}

	protected static LocalDate requestAdultInput() {
		Scanner scanner = new Scanner(System.in);
		LocalDate dateOfBirth = null;
		String input = null;
		boolean match = false;
		do {
			System.out.println("Enter a date of birth hier: (dd/mm/yyyy) ");
			input = scanner.nextLine();
			match = Pattern.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\\\d\\\\d)", input);
			if (!match) {
				System.out.println("That's not a valid date!");

			} else {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				dateOfBirth = LocalDate.parse(input, formatter);
				LocalDate now = LocalDate.now();
				long years = java.time.temporal.ChronoUnit.YEARS.between(dateOfBirth, now);
				if (years < 18) {
					System.out.println("We don't hire children !");
					match = false;
				}
			}
		} while (!match);
		return dateOfBirth;

	}

	protected static LocalDate requestDateInput() {
		Scanner scanner = new Scanner(System.in);
		String input = null;
		boolean match = false;
		do {
			System.out.println("Enter a date hier: (dd/mm/yyyy) ");
			input = scanner.nextLine();
			match = Pattern.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)", input);
			if (!match) {
				System.out.println("That's not a valid date!");

			}
		} while (!match);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(input, formatter);
	}

	protected static LocalDate requestStartDateNotInThePastInput() {
		Scanner scanner = new Scanner(System.in);
		String input = null;
		boolean match = false;
		LocalDate startDate = null;
		do {
			System.out.println("Enter a date hier: (dd/mm/yyyy) ");
			input = scanner.nextLine();
			match = Pattern.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)", input);
			if (!match) {
				System.out.println("That's not a valid date!");

			} else {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				startDate = LocalDate.parse(input, formatter);
				if (startDate.isBefore(LocalDate.now())) {
					System.out.println("Start date must not be in the past! ");
					match = false;
				}
			}
		} while (!match);
		return startDate;
	}
	
	protected static LocalDate requestEndDateNotInThePastInput(LocalDate startDate) {
		Scanner scanner = new Scanner(System.in);
		String input = null;
		boolean match = false;
		LocalDate endDate = null;
		do {
			System.out.println("Enter a date hier: (dd/mm/yyyy) ");
			input = scanner.nextLine();
			match = Pattern.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)", input);
			if (!match) {
				System.out.println("That's not a valid date!");

			} else {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				endDate = LocalDate.parse(input, formatter);
				if (endDate.isBefore(startDate)) {
					System.out.println("End date must not be before start date! ");
					match = false;
				}
			}
		} while (!match);
		return endDate;
	}

}
