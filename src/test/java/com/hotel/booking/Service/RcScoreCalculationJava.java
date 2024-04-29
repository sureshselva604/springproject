package com.example.jwt_authentication.Service.RcScoreCalculationJava;

import java.util.*;
import java.util.regex.*;

public class RcScoreCalculationJava {
    public static void main(String[] args) {
        // Example usage
        Map<Integer, Double> questionValues = new HashMap<>();
        questionValues.put(1, 10.0);
        questionValues.put(2, 20.0);
        questionValues.put(3, 30.0);
        String formula = "5*Sum(1,3,5)-Sum(1,3,Product(1,2,3))*(25/100)+Product(2,6,4)";
        String result = replaceQuestionIdsWithValues(questionValues, formula);
        System.out.println("Result: " + result);
    }

    public static String replaceQuestionIdsWithValues(Map<Integer, Double> questionValues, String formula) {
        // Pattern to match operations, question IDs, and variables in the formula
        Pattern pattern = Pattern.compile("\\b([a-zA-Z]+)\\((.*?)\\)|\\b([a-zA-Z]+)\\b|\\b([0-9]+\\.?[0-9]*)\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(formula);

        // Replace each operation with its evaluated value
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String operation = matcher.group(1);
            String variable = matcher.group(3);
            String operand = matcher.group(4);
            String[] tokens = null;
            if (matcher.group(2) != null) {
                tokens = parseTokens(matcher.group(2));
            }
            double result = 0;

            if (operation != null) {
                double intermediateResult = 0;
                if (tokens != null && tokens.length > 0) { // Add a null and length check
                    intermediateResult = evaluateExpression(questionValues, String.join("", tokens));
                }
                switch (operation.toLowerCase()) {
                    case "sum":
                        result = calculateSum(questionValues, tokens);
                        break;
                    case "avg":
                        result = calculateAverage(questionValues, tokens);
                        break;
                    case "product":
                        result = calculateProduct(questionValues, tokens);
                        break;
                    case "mean":
                        result = calculateMean(questionValues, tokens);
                        break;
                    case "median":
                        result = calculateMedian(questionValues, tokens);
                        break;
                    case "mode":
                        result = calculateMode(questionValues, tokens);
                        break;
                    
                    
                    default:
                        throw new IllegalArgumentException("Unknown operation: " + operation);
                }
            } else if (variable != null) {
                // Check if the variable is present in the scoresMap
                if (questionValues.containsKey(Integer.parseInt(variable))) {
                    result = questionValues.get(Integer.parseInt(variable));
                } else {
                    throw new IllegalArgumentException("Unknown variable: " + variable);
                }
            } else if (operand != null) {
                // Parse the operand and push it to the values stack
                result = Double.parseDouble(operand);
            }

            matcher.appendReplacement(sb, String.valueOf(result));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String[] parseTokens(String tokenString) {
        List<String> tokens = new ArrayList<>();
        StringBuilder tokenBuilder = new StringBuilder();
        int nestedLevel = 0;

        for (char c : tokenString.toCharArray()) {
            if (c == ',' && nestedLevel == 0) {
                tokens.add(tokenBuilder.toString());
                tokenBuilder.setLength(0); // Clear the token builder
            } else {
                tokenBuilder.append(c);
                if (c == '(') {
                    nestedLevel++;
                } else if (c == ')') {
                    nestedLevel--;
                }
            }
        }

        // Add the last token
        tokens.add(tokenBuilder.toString());

        return tokens.toArray(new String[0]);
    }

    // Implement other methods like evaluateExpression, calculateSum, etc. here
    public static double evaluateExpression(Map<Integer, Double> questionValues, String expression) {
        System.out.println("Evaluating expression: " + expression); // Debugging statement
        char[] tokens = expression.toCharArray();

        // Stacks to store operands and operators
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        // Iterate through each character in the expression
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ')
                continue;

            // If the character is a digit or a decimal point, parse the number
            if ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.') {
                StringBuilder sb = new StringBuilder();
                // Continue collecting digits and the decimal point to form a number
                while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.')) {
                    sb.append(tokens[i]);
                    i++;
                }
                // Parse the collected number and push it to the values stack
                values.push(Double.parseDouble(sb.toString()));
                i--; // Decrement i to account for the extra increment in the loop
            } else if (tokens[i] == '(') {
                // If the character is '(', push it to the operators stack
                operators.push(tokens[i]);
            } else if (tokens[i] == ')') {
                // If the character is ')', pop and apply operators until '(' is encountered
                while (operators.peek() != '(') {
                    if (values.size() < 2) {
                        throw new IllegalArgumentException("Invalid expression: Not enough operands for operator");
                    }
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop(); // Pop the '('
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                // If the character is an operator, pop and apply operators with higher precedence
                while (!operators.isEmpty() && hasPrecedence(tokens[i], operators.peek())) {
                    if (values.size() < 2) {
                        throw new IllegalArgumentException("Invalid expression: Not enough operands for operator");
                    }
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                // Push the current operator to the operators stack
                operators.push(tokens[i]);
            }
        }

        // Process any remaining operators in the stack
        while (!operators.isEmpty()) {
            if (values.size() < 2) {
                throw new IllegalArgumentException("Invalid expression: Not enough operands for operator");
            }
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        // The result is the only remaining element in the values stack
        return values.pop();
    }

    private static double applyOperator(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private static boolean hasPrecedence(char op1, char op2) {
        return (op2 != '(' && op2 != ')') && ((op1 == '*' || op1 == '/') || (op2 == '+' || op2 == '-'));
    }

    public static double calculateSum(Map<Integer, Double> questionValues, String[] tokens) {
        double sum = 0;
        for (String token : tokens) {
        	System.out.println("Tokem is:"+token);
            sum += questionValues.get(Integer.parseInt(token));
        }
        return sum;
    }

    public static double calculateProduct(Map<Integer, Double> questionValues, String[] tokens) {
        double product = 1;
        for (String token : tokens) {
            product *= Double.parseDouble(token);
        }
        return product;
    }
    
    public static double calculateMean(Map<Integer, Double> questionValues, String[] tokens) {
        double sum = 0;
        for (String token : tokens) {
            sum += questionValues.get(Integer.parseInt(token));
        }
        return sum / tokens.length;
    }

    public static double calculateMedian(Map<Integer, Double> questionValues, String[] tokens) {
        double[] values = new double[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            values[i] = questionValues.get(Integer.parseInt(tokens[i]));
        }
        Arrays.sort(values);
        if (values.length % 2 == 0) {
            return (values[values.length / 2] + values[values.length / 2 - 1]) / 2;
        } else {
            return values[values.length / 2];
        }
    }

    public static double calculateMode(Map<Integer, Double> questionValues, String[] tokens) {
        Map<Double, Integer> frequencyMap = new HashMap<>();
        for (String token : tokens) {
            double value = questionValues.get(Integer.parseInt(token));
            frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
        }
        double mode = 0;
        int maxFrequency = 0;
        for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                mode = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }
        return mode;
    }

    public static double calculateAverage(Map<Integer, Double> questionValues, String[] tokens) {
        double sum = 0;
        for (String token : tokens) {
            sum += questionValues.get(Integer.parseInt(token));
        }
        return sum / tokens.length;
    }

    
}
