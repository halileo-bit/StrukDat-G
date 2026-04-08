package Tugas.Modul3;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Stack;

public class ExpressionProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Task 1: Input
        System.out.print("Masukkan ekspresi infix: ");
        String infix = scanner.nextLine();

        // Standardize the input spacing (ensures spaces around all operators and parentheses)
        // This makes it much easier to split the string into individual tokens.
        String formattedInfix = infix.replaceAll("(?<=[-+*/()])|(?=[-+*/()])", " ").trim();
        String[] tokens = formattedInfix.split("\\s+");

        // Re-construct clean infix string for output
        String cleanInfix = String.join(" ", tokens);

        // Task 2: Infix to Postfix Conversion
        String postfix = infixToPostfix(tokens);

        // Task 3: Postfix Expression Evaluation
        double result = evaluatePostfix(postfix);

        // Formatting the result to remove trailing .0 if it's a whole number
        DecimalFormat df = new DecimalFormat("0.#");

        // Task 4: Display the Output
        System.out.println("Infix   : " + cleanInfix);
        System.out.println("Postfix : " + postfix);
        System.out.println("Result  : " + df.format(result));

        scanner.close();
    }

    /**
     * Converts an Infix expression array of tokens to a Postfix string
     * using the Shunting-yard algorithm.
     */
    private static String infixToPostfix(String[] tokens) {
        StringBuilder postfix = new StringBuilder();
        Stack<String> operators = new Stack<>();

        for (String token : tokens) {
            // If the token is a number, append it to the postfix output
            if (isNumeric(token)) {
                postfix.append(token).append(" ");
            } 
            // If the token is a left parenthesis, push it to the stack
            else if (token.equals("(")) {
                operators.push(token);
            } 
            // If the token is a right parenthesis
            else if (token.equals(")")) {
                // Pop operators from the stack to the output until '(' is encountered
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    postfix.append(operators.pop()).append(" ");
                }
                // Pop and discard the '('
                if (!operators.isEmpty() && operators.peek().equals("(")) {
                    operators.pop();
                }
            } 
            // If the token is an operator (+, -, *, /)
            else if (isOperator(token)) {
                // Check precedence and associativity
                while (!operators.isEmpty() && !operators.peek().equals("(") &&
                        getPrecedence(operators.peek()) >= getPrecedence(token)) {
                    postfix.append(operators.pop()).append(" ");
                }
                operators.push(token);
            }
        }

        // Pop all remaining operators in the stack
        while (!operators.isEmpty()) {
            postfix.append(operators.pop()).append(" ");
        }

        return postfix.toString().trim();
    }

    /**
     * Evaluates a Postfix expression string and returns the double result.
     */
    private static double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {
            // If the token is a number, push it onto the stack
            if (isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } 
            // If the token is an operator, pop the required operands and perform the operation
            else if (isOperator(token)) {
                double b = stack.pop(); // The second operand was pushed last
                double a = stack.pop(); // The first operand was pushed first
                double result = 0;

                switch (token) {
                    case "+": result = a + b; break;
                    case "-": result = a - b; break;
                    case "*": result = a * b; break;
                    case "/": result = a / b; break; // Uses double division for decimals
                }
                // Push the result back onto the stack
                stack.push(result);
            }
        }

        // The final result will be the last remaining element on the stack
        return stack.pop();
    }

    // Helper method to check if a string is a numeric value
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Helper method to check if a string is an operator
    private static boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }

    // Helper method to determine the precedence of operators
    private static int getPrecedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return -1;
        }
    }
}
