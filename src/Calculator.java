import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {

    private static double lastResult = 0;
    private static ArrayList<String> resultList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            displayMenu();

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                if (choice >= 1 && choice <= 8) {
                    System.out.print("Enter first number: ");
                    double num1 = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    double num2 = scanner.nextDouble();

                    String operation = "";
                    switch (choice) {
                        case 1:
                            lastResult = add(num1, num2);
                            operation = "Addition";
                            break;
                        case 2:
                            lastResult = subtract(num1, num2);
                            operation = "Subtraction";
                            break;
                        case 3:
                            lastResult = multiply(num1, num2);
                            operation = "Multiplication";
                            break;
                        case 4:
                            lastResult = divide(num1, num2);
                            operation = "Division";
                            break;
                        case 5:
                            lastResult = modulus(num1, num2);
                            operation = "Modulus";
                            break;
                        case 6:
                            lastResult = min(num1, num2);
                            operation = "Minimum";
                            break;
                        case 7:
                            lastResult = max(num1, num2);
                            operation = "Maximum";
                            break;
                        case 8:
                            lastResult = average(num1, num2);
                            operation = "Average";
                            break;
                    }
                        //to print the operation namr with the result
                    String resultEntry = operation + " of " + num1 + " and " + num2 + " = " + lastResult;
                    resultList.add(resultEntry);
                    System.out.println("Result: " + resultEntry);

                } else if (choice == 9) {
                    System.out.println("Last result: " + lastResult);
                } else if (choice == 10) {
                    System.out.println("All results:");
                    for (String result : resultList) {
                        System.out.println(result);
                    }
                } else if (choice != 0) {
                    System.out.println("Invalid choice, please try again.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
            }
        }

        System.out.println("Calculator exited.");
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\nCalculator Menu:");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Modulus");
        System.out.println("6. Minimum");
        System.out.println("7. Maximum");
        System.out.println("8. Average");
        System.out.println("9. Show Last Result");
        System.out.println("10. Show All Results");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static double add(double a, double b) {
        return a + b;
    }

    private static double subtract(double a, double b) {
        return a - b;
    }

    private static double multiply(double a, double b) {
        return a * b;
    }

    private static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Division by zero.");
            return 0;
        }
        return a / b;
    }

    private static double modulus(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Modulus by zero.");
            return 0;
        }
        return a % b;
    }

    private static double min(double a, double b) {
        return Math.min(a, b);
    }

    private static double max(double a, double b) {
        return Math.max(a, b);
    }

    private static double average(double a, double b) {
        return (a + b) / 2;
    }
}
