/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package improvedcalculator;

import java.util.Scanner;

/**
 *
 * @author shanm
 */
public class ImprovedCalculator {

        private static final String[] op = new String[]{"{", "}", "(", ")", "^", "*", "/", "+", "-"};
        /**
         * private constructor to prevent user from making objects of this class
         */
        private ImprovedCalculator() {}
        /**
         * Method that preps the inputted string and calculates the expression using other helper methods
         * @param s, a mathematical equation that needs to be solved
         * @return a double which is the answer to the equation
         */
        public static double calculate(String s) {
            String str = "";
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (operator(c)) {
                    str += " " + c + " ";
                } else {
                    str += c;
                }
            }
            str = str.trim();
            String output = postFix(str);
            double result = evaluatePostfix(output);
            return result;
        }
        /**
         * Helper method which checks if the parameter is an operator
         * @param c, the character being checked if its an operator or not
         * @return true if the character is an operator
         */
        private static boolean operator(char c) {
            for (String operator : op) {
                if (operator.charAt(0) == c) {
                    return true;
                }
            }
            return false;
        }
        /**
         * Helper method that converts inFix notation to postFix notation
         * @param str which represents the infix notation
         * @return a String which represents the postfix notation
         */   
        private static String postFix(String str) {
            MyStack<String> operators = new MyStack<>();
            String output = "";
            Scanner sc = new Scanner(str);

            while (sc.hasNext()) {
                String nxt = sc.next().trim();

                if (isNumber(nxt)) {
                    output += nxt + " ";
                } else if (nxt.equals("(")) {
                    operators.push(nxt);
                } else if (nxt.equals(")")) {
                    while (!operators.isEmpty() && !operators.peek().equals("(")) {
                        output += operators.pop() + " ";
                    }
                    if (!operators.isEmpty()) {
                        operators.pop();
                    }
                } else if (!nxt.isEmpty() && operator(nxt.charAt(0))) {
                    while (!operators.isEmpty() && comparePrecedence(operators.peek(), nxt)) {
                        output += operators.pop() + " ";
                    }
                    operators.push(nxt);
                }
            }

            while (!operators.isEmpty()) {
                output += operators.pop() + " ";
            }

            return output;
        }
        /**
         * Helper method which checks if a string is a number
         * @param s, the string being checked for a number
         * @return true if the string represents a number
         */
        private static boolean isNumber(String s) {
            if (s == null || s.isEmpty()) {
                return false;
            }
            boolean deci = false;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '.') {
                    deci = true;
                }
                else if (Character.isDigit(s.charAt(i)) == false) {
                    return false;
                }
            }
            return true;
        }
        /**
         * A helper method that compares the precedence of two operators
         * @param top which is the last operator on the stack
         * @param current is the current operator being evaluated 
         * @return 
         */
        private static boolean comparePrecedence(String top, String current) {
            if (top.equals("^") && current.equals("^")) {
                return false;
            }
            if (top.equals("^")) {
                return true;
            }
            if (top.equals("*") || top.equals("/")) {
                if (current.equals("+") || current.equals("-")) {
                    return true;
                }
                if (current.equals("*") || current.equals("/")) {
                    return true;
                }
            }
            if (top.equals("+") || top.equals("-")) {
                if (current.equals("+") || current.equals("-")) {
                    return true;
                }
            }
            return false;
        }
        /**
         * Helper method which solves the postFix equation returned by the postFix method
         * @param postfix, A string that represents a postfix expression
         * @return a double which represents the calculated value of the postFix expression
         */
        private static double evaluatePostfix(String postfix) {
            MyStack<Double> stack = new MyStack<>();
            Scanner sc = new Scanner(postfix);

            while (sc.hasNext()) {
                    String operator = sc.next();
                    if (isNumber(operator)) {
                        stack.push(Double.parseDouble(operator));
                    } else {
                        double b = stack.pop();
                        double a = stack.pop();

                        if (operator.equals("+")) {
                            stack.push(a + b);
                        } else if (operator.equals("-")) {
                            stack.push(a - b);
                        } else if (operator.equals("*")) {
                            stack.push(a * b);
                        } else if (operator.equals("/")) {
                            stack.push(a / b);
                        } else if (operator.equals("^")) {
                            stack.push(Math.pow(a, b));
                        }
                    }
                }
                return stack.pop();
            }
        }
