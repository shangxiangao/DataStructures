package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    //Test result
    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpression = toInfixExpression(expression);
        System.out.println(infixExpression);

        List<String> suffixExpression = toSuffixExpression(infixExpression);
        System.out.println(suffixExpression);

        System.out.println(PolandNotation.calculation(suffixExpression));
    }

    //Transfer input string into string list
    public static List<String> toInfixExpression(String s){
        List<String> sl = new ArrayList<String>();
        int i = 0;
        String str = "";
        char c = ' ';
        while(i < s.length()){
            //When s.charAt(i) is a operator or a bracket
            if((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                sl.add("" + c);
                i++;
            }else{
                //When s.charAt(i) is a number
                str = "";
                //Cannot separate a number larger than 9
                while(i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57){
                    str += c;
                    i++;
                }
                sl.add("" + str);
            }
        }
        return sl;
    }

    //Transfer infix expressions to suffix expressions
    public static List<String> toSuffixExpression(List<String> sl) {
        //1. Define two collections s1, s2
        //For operators
        Stack<String> s1 = new Stack<String>();
        //For final result
        List<String> s2 = new ArrayList<>();
        //2. Scan the infix expression(left to right)
        for (String item : sl) {
            //If there is a number, put it in s2
            if(item.matches("\\d+"))
                s2.add(item);
            //If there is a "(", put it in s1 directly
            else if(item.equals("("))
                s1.push(item);
            //if the item is a ")", pop the items in s1 and adding them into s2 until the top of s1 is a "("
            else if(item.equals(")")){
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();
            //If there is a operator
            }else{
            //(1) Compare the priority of the new item and the top item of s1
                while(!s1.isEmpty() && Priority.getValue(item) < Priority.getValue(s1.peek())){
                    //If the priority of the new item is smaller, continuously pop the items
                    //in s1 and push them into s2 until the the priority of the new item is
                    //larger or equals to the top item of s1
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (!s1.empty()){
            s2.add(s1.pop());
        }
        return s2;
    }

    // Calculation the value of a suffix expression
    public static int calculation(List<String> sl){
        Stack<String> s = new Stack<>();
        for (String item : sl) {
            if(item.matches("\\d+"))
                s.push(item);
            else{
                int num2 = Integer.parseInt(s.pop());
                int num1 = Integer.parseInt(s.pop());
                int result = 0;
                switch (item){
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException();
                }
                s.push(""+result);
            }
        }
        return Integer.parseInt(s.pop());
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