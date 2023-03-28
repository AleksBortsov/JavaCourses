import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyExpression {
    final double EPS = 1e-12;

    void verifyExtraMathSigns(String input) throws Exception {
        input = input.replaceAll("[(]","");
        input = input.replaceAll("[)]","");
        Pattern pattern = Pattern.compile("[+*/-][+*/]");
        Matcher check = pattern.matcher(input);
        if (check.find() || input.contains("---")) {
            throw new Exception("Extra math symbols are not allowed!");
        }
    }

    void verifyNumberOfParentheses(String input) throws Exception {
        long countOpen = input.chars().filter(ch -> ch == '(').count();
        long countClose = input.chars().filter(ch -> ch == ')').count();
        if (countOpen > countClose) {
            throw new Exception("You're missing closing parentheses");
        }
        else if (countOpen < countClose) {
            throw new Exception("You're missing opening parentheses");
        }
    }

    void verifyForEmptyParentheses(String input) throws Exception {
        if (input.contains("()")) {
            throw new Exception("There is an empty parentheses in expression");
        }
    }

    void verifyForParenthesesSymmetry(String input) throws Exception {
        if (input.contains(")") || input.contains("(")) {
            if ((input.indexOf(")") > input.indexOf("("))) {
                while ((input.indexOf(")") > input.indexOf("("))) {
                    StringBuilder stringBuilder = new StringBuilder(input);
                    stringBuilder.setCharAt(input.indexOf("("), ' ');
                    stringBuilder.setCharAt(input.indexOf(")"), ' ');
                    input = stringBuilder.toString();
                    if ((input.indexOf(")") < input.indexOf("("))) {
                        throw new Exception("There is closing parentheses before opening one");
                    }
                }
            } else {
                throw new Exception("There is closing parentheses before opening one");
            }
        }
    }

    void countNumbersInExpression(String input) {
        String[] massiveOfNumbers = input.split("[+*/)(-]");
        System.out.println("Count of numbers = " + massiveOfNumbers.length);
    }
}
