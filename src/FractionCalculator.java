/**
 * Fraction Calculator
 * This class implements a fraction calculator where the user selects an
 * operation (+, -, *, / or =) and then inputs two fractions. The user can select
 * "q" to quit the program.
 * @author: Scott Sandman
 * @version: 1.0
 */

import java.util.Scanner;

public class FractionCalculator {
    
    //Scanner required for user input
    public static Scanner input = new Scanner(System.in);
    
    /**
     * Method used to introduce user to calculator.
     */
    public static void intro() {
        System.out.println("Welcome to Fraction Calculator!");
        System.out.println("This program will add, subtract, multiply and "
                + "divide fractions until you type Q to quit.");
        System.out.println("Please enter fractions in form a/b, where a and b "
                + "are integers.");
        System.out.println("==================================================");
    }
    
    /**
     * Method to allow user to select an operation.
     * + addition, - subtraction, * multiplication, / division, = equal to
     * @return
     */
    public static String getOperation() {
        System.out.println("Please enter an operation (+, -, *, /, = or Q to "
                + "quit): ");
        String operator = input.nextLine();
        int loop = 1;
        while (loop == 1) {
            if (operator.equalsIgnoreCase("q")) {
                System.exit(0);
            } else if (operator.equals("+") || operator.equals("-")
                    || operator.equals("*") || operator.equals("/")
                    || operator.equals("=")) {
                loop--;
            } else {
                System.out.println("Invalid input. Enter (+, -, *, /, = or Q to "
                        + "quit): ");
                operator = input.nextLine();
            }
        }
        return operator;
    }
    
    /**
     * Method determines if user input is a valid fraction.
     * Input must have an entry. Entry cannot be a symbol (except /) or alpha
     * character.
     * Denominator cannot be negative (ex. 1/-2) or zero.
     * @param a
     * @return
     */
    public static boolean validFraction(String a) {
        if (a.isEmpty()) {
            return false;
        }
        if (a.contains("/")) {
            int i = a.indexOf("/");
            String num = a.substring(0, i);
            String den = a.substring(i+1);
            if (num.charAt(0) == '-' || den.isEmpty() || den.equals("0")) {
                return false;
            } else {
                try {
                    int intA = Integer.parseInt(num);
                    int intB = Integer.parseInt(den);
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        } else {
            try {
                int intA = Integer.parseInt(a);
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Method requests fraction from user.
     * Utilizes isFraction() to determine if user input is valid.
     * @return
     */
    public static Fraction getFraction() {
        System.out.println("Please enter a fraction (a/b) or integer (a): ");
        String userInput = input.nextLine();
        while (!validFraction(userInput)) {
            System.out.println("Please input valid fraction. (a/b or a): ");
            userInput = input.nextLine();
        }
        
        if (userInput.contains("/")) {
            String[] arrayA = userInput.split("/", 2);
            int numerator = Integer.parseInt(arrayA[0]);
            int denominator = Integer.parseInt(arrayA[1]);
            Fraction userFraction = new Fraction(numerator, denominator);
            return userFraction;
        } else {
            int numerator = Integer.parseInt(userInput);
            Fraction userFraction = new Fraction(numerator);
            return userFraction;
        }
    }
    
    /**
     * Main method implementing the fraction calculator
     * @param args
     */
    public static void main(String[] args) {
        intro();
        
        //calculator will continue until user selects q to quit. System.exit(0)
        while (true) {
            String operator = getOperation();
            Fraction fractionA = getFraction();
            Fraction fractionB = getFraction();
            
            Fraction result = new Fraction(1,1);
            
            switch(operator) {
                case "+":
                    result = fractionA.add(fractionB);
                    break;
                    
                case "-":
                    result = fractionA.subtract(fractionB);
                    break;
                    
                case "*":
                    result = fractionA.multiply(fractionB);
                    break;
                    
                case "/":
                    result = fractionA.divide(fractionB);
                    break;
                    
                case "=":
                    System.out.println(fractionA+ " = " +fractionB+ " is "
                            +fractionA.equals(fractionB));
                    break;
            }
            
            if (operator != "=") {
                System.out.println(fractionA+ " " +operator+ " " +fractionB+
                        " = " +result.toString());
            }
        }
    }
}
