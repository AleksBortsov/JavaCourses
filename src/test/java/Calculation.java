import com.fathzer.soft.javaluator.DoubleEvaluator;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculation {
    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(System.in);
        VerifyExpression verifyExpression = new VerifyExpression();
        DataBaseOperation dataBaseOperation = new DataBaseOperation();

        System.out.println("Greeting, here you can enter yor mathematics expression to be calculated and saved \r\n" +
                "Enter your expression or type Help for more option \r\n" +
                "Type Exit to close program");

        String input = scanner.nextLine();

        Pattern pattern = Pattern.compile("[0-9]");
        Matcher verifyInput = pattern.matcher(input);

        if (verifyInput.find()){
            verifyExpression.verifyForEmptyParentheses(input);
            verifyExpression.verifyExtraMathSigns(input);
            verifyExpression.verifyForParenthesesSymmetry(input);
            verifyExpression.verifyNumberOfParentheses(input);
            Double result = new DoubleEvaluator().evaluate(input);
            dataBaseOperation.executeInsertQuery(input, result);
            System.out.println("Result is: " + result + "\r\n" +
                    "Your expression and result were added to data base");
        }
        else if (input.equalsIgnoreCase("Exit")) {
            System.exit(0);
        }
        else if (input.equalsIgnoreCase("Help")) {
            System.out.println("To get all record from data base type Get \r\n" +
                    "To get records by result type Result \r\n" +
                    "To update record in table type Update \r\n" +
                    "Type Exit to close program");
            String ifInput = scanner.nextLine();
            if (ifInput.equalsIgnoreCase("Exit")) {
                System.exit(0);
            }
            else if (ifInput.equalsIgnoreCase("Get")){
                dataBaseOperation.printAllRecordsFromDataBase();
            }
            else if (ifInput.equalsIgnoreCase("Result")) {
                System.out.println("Enter result to search of, only numeric values");
                double result = Double.parseDouble(scanner.nextLine());
                dataBaseOperation.printRecordByExpressionResult(result);
            }
        }
        else if (input.equalsIgnoreCase("Get")) {
            dataBaseOperation.printAllRecordsFromDataBase();
        }
        else if (input.equalsIgnoreCase("Result")) {
            System.out.println("Enter result to search of, only numeric values");
            double result = Double.parseDouble(scanner.nextLine());
            dataBaseOperation.printRecordByExpressionResult(result);
        }
        else if (input.equalsIgnoreCase("Update")) {
            System.out.println("Enter new expression, press enter and then enter ID of record you want to update \r\n" +
                    "If new expression will pass all validations record will be updated");
            String newExpression = scanner.nextLine();
            int recordId = scanner.nextInt();
            verifyExpression.verifyForEmptyParentheses(newExpression);
            verifyExpression.verifyExtraMathSigns(newExpression);
            verifyExpression.verifyForParenthesesSymmetry(newExpression);
            verifyExpression.verifyNumberOfParentheses(newExpression);
            Double newResult = new DoubleEvaluator().evaluate(newExpression);
            dataBaseOperation.updateRecordById(recordId, newExpression, newResult);
            System.out.println("Record with ID: " + "\'" + recordId + "\'" +
                    " Was updated with expression: " + "\'" + newExpression + "\'" +
                    " And result: " + "\'" + newResult + "\'");
        }
        verifyExpression.countNumbersInExpression(input);
    }
}
