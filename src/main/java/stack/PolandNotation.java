package stack;

import java.util.ArrayList;
import java.util.List;

public class PolandNotation {
    //Test result
    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpression = toInfixExpression(expression);
        System.out.println(infixExpression);
        List<String> suffixExpression = toSuffixExpression(infixExpression);
        System.out.println(suffixExpression);
    }
    //Transfer input string into string list
    public static List<String> toInfixExpression(String s){
        List<String> sl = new ArrayList<String>();
        int i = 0;
        String str = "";

    }

    public static List<String> toSuffixExpression(List<String> sl) {

    }
}
// Get the priority level of different operators
class Priority{
    private static int add = 1;
    private static int sub = 1;
    private static int mul = 2;
    private static int div = 2;

    public static int getValue(String operator){
        int result = 0;
        switch (operator){
            case "+":
                result = add;
                break;
            case "-":
                result = sub;
                break;
            case "*":
                result = mul;
                break;
            case "/":
                result = div;
                break;
            default:
                System.out.println("Operator does not exist");
        }
        return result;
    }
}