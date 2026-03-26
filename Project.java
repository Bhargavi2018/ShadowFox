import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

class Project {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        boolean calci = true;

        while (calci) {
            System.out.println("\n===== CALCULATOR MENU =====");
            System.out.println("1.Addition");
            System.out.println("2.Subtraction");
            System.out.println("3.Multiplication");
            System.out.println("4.Division");
            System.out.println("5.Square root");
            System.out.println("6.Power");
            System.out.println("7.Temperature Conversion");
            System.out.println("8.Exit");

            try {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        add(sc);
                        break;
                    case 2:
                        subtract(sc);
                        break;
                    case 3:
                        multiply(sc);
                        break;
                    case 4:
                        divide(sc);
                        break;
                    case 5:
                        sqrt(sc);
                        break;
                    case 6:
                        power(sc);
                        break;
                    case 7:
                        tempConvert(sc);
                        break;
                    case 8:
                        calci = false;
                        System.out.println("Exiting Calculator...");
                        break;
                    default:
                        System.out.println("Invalid Choice!!");
                }

                if (calci) {
                    System.out.print("Do you want to continue? (yes/no): ");
                    String ch = sc.next();
                    if (!ch.equalsIgnoreCase("yes")) {
                        calci = false;
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid Input! Enter numbers only.");
                sc.next(); // clear invalid input
            }
        }
    }

    static void add(Scanner sc) {
        System.out.print("Enter first number: ");
        BigDecimal a = sc.nextBigDecimal();
        System.out.print("Enter second number: ");
        BigDecimal b = sc.nextBigDecimal();
        System.out.println("Result: " + a.add(b));
    }

    static void subtract(Scanner sc) {
        System.out.print("Enter first number: ");
        BigDecimal a = sc.nextBigDecimal();
        System.out.print("Enter second number: ");
        BigDecimal b = sc.nextBigDecimal();
        System.out.println("Result: " + a.subtract(b));
    }

    static void multiply(Scanner sc) {
        System.out.print("Enter first number: ");
        BigDecimal a = sc.nextBigDecimal();
        System.out.print("Enter second number: ");
        BigDecimal b = sc.nextBigDecimal();
        System.out.println("Result: " + a.multiply(b));
    }

    static void divide(Scanner sc) {
        System.out.print("Enter first number: ");
        BigDecimal a = sc.nextBigDecimal();

        System.out.print("Enter second number: ");
        BigDecimal b = sc.nextBigDecimal();

        if (b.equals(BigDecimal.ZERO)) {
            System.out.println("Cannot divide by zero!");
            return;
        }

        BigDecimal result = a.divide(b, 2, RoundingMode.HALF_UP);
        System.out.println("Result: " + result);
    }

    static void power(Scanner sc) {
        System.out.print("Enter base: ");
        BigDecimal a = sc.nextBigDecimal();

        System.out.print("Enter exponent: ");
        BigDecimal b = sc.nextBigDecimal();

        double result = Math.pow(a.doubleValue(), b.doubleValue());
        System.out.println("Result: " + result);
    }

    static void sqrt(Scanner sc) {
        System.out.print("Enter number: ");
        BigDecimal a = sc.nextBigDecimal();

        double result = Math.sqrt(a.doubleValue());
        System.out.println("Result: " + result);
    }

    static void tempConvert(Scanner sc) {
        System.out.println("1. Fahrenheit to Celsius");
        System.out.println("2. Celsius to Fahrenheit");
        System.out.print("Choose option: ");
        int opt = sc.nextInt();

        if (opt == 1) {
            System.out.print("Enter Fahrenheit: ");
            BigDecimal F = sc.nextBigDecimal();

            BigDecimal C = F.subtract(new BigDecimal("32"))
                    .multiply(new BigDecimal("5"))
                    .divide(new BigDecimal("9"), 2, RoundingMode.HALF_UP);

            System.out.println("Celsius: " + C);

        } else if (opt == 2) {
            System.out.print("Enter Celsius: ");
            BigDecimal C = sc.nextBigDecimal();

            BigDecimal F = C.multiply(new BigDecimal("9"))
                    .divide(new BigDecimal("5"), 2, RoundingMode.HALF_UP)
                    .add(new BigDecimal("32"));

            System.out.println("Fahrenheit: " + F);

        } else {
            System.out.println("Invalid Option!");
        }
    }
}