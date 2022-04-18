package cmsc256;

import bridges.base.BinTreeElement;
import bridges.connect.Bridges;

import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.util.Stack;

public class Project5 {
    public static void main(String[] args){
        String ex1 = "( ( 7 + 3 ) * ( 5 - 2 ) )";
        BinTreeElement<String> parseTree1 = buildParseTree(ex1);
        double answer1 = evaluate(parseTree1);
        System.out.println(answer1);
        System.out.println(getEquation(parseTree1));

        String ex2 = "( ( 10 + 5 ) * 3 )";
        BinTreeElement<String>  parseTree2 = buildParseTree(ex2);
        double answer2 = evaluate(parseTree2);
        System.out.println(answer2);
        System.out.println(getEquation(parseTree2));

        String ex3 = "( ( ( ( ( 2 * 12 ) / 6 ) + 3 ) - 17 ) + ( 2 * 0 ) )";
        BinTreeElement<String>  parseTree3 = buildParseTree(ex3);
        double answer3 = evaluate(parseTree3);
        System.out.println(answer3);
        System.out.println(getEquation(parseTree3));

        String ex4 = "( 3 + ( 4 * 5 ) )";
        BinTreeElement<String>  parseTree4 = buildParseTree(ex4);
        double answer4 = evaluate(parseTree4);
        System.out.println(answer4);
        System.out.println(getEquation(parseTree4));

        /* Initialize a Bridges connection */
        Bridges bridges = new Bridges(3,"hoangth844" , "497417069475" );

        /* Set an assignment title */
        bridges.setTitle("Arithmetic Parse Tree Project - Debra Duke");
        bridges.setDescription("CMSC 256, Spring 2022");

        try {
            /* Tell BRIDGES which data structure to visualize */
            bridges.setDataStructure(parseTree1);
            /* Visualize the Array */
            bridges.visualize();

            /* Tell BRIDGES which data structure to visualize */
            bridges.setDataStructure(parseTree2);
            /* Visualize the Array */
            bridges.visualize();

            /* Tell BRIDGES which data structure to visualize */
            bridges.setDataStructure(parseTree3);
            /* Visualize the Array */
            bridges.visualize();

            /* Tell BRIDGES which data structure to visualize */
            bridges.setDataStructure(parseTree4);
            /* Visualize the Array */
            bridges.visualize();
        }
        catch(Exception ex){
            System.out.println("Error connecting to Bridges server.");
        }
    }

    //    Build a parse tree from a fully parenthesized mathematical expression.
//    The method accepts a mathematical expression as a string parameter and returns the root of the parse tree.
//    Each token in the mathematical expression is separated by a white-space character, for example, “( ( 7 + 3 ) * ( 5 – 2 ) )”
    public static bridges.base.BinTreeElement<String> buildParseTree(String expression){
        if(expression == null || expression.length() == 0){
            throw new IllegalArgumentException("Expression is invalid");
        }
        String[] tokens = expression.split(" ");
        BinTreeElement<String> parseTree = new BinTreeElement<>("root","");
        BinTreeElement<String> curr = parseTree;
        Stack<BinTreeElement<String>> parentStorage = new Stack<>();

        for (String token : tokens) {
            //case of (
            if ("(".equals(token)) {
                curr.setLeft(new BinTreeElement<String>("void", ""));
                parentStorage.push(curr);
                curr = curr.getLeft();

                //case of operator
            } else if ("+".equals(token) || "-".equals(token) || "/".equals(token) || "*".equals(token)) {
                curr.setLabel(token);
                curr.setRight(new BinTreeElement<String>("void", ""));
                parentStorage.push(curr);
                curr = curr.getRight(); //make the curr the right child

                //case of "}"
            } else if (")".equals(token)) {
                if (!parentStorage.isEmpty()) {
                    curr = parentStorage.pop(); //get next operator
                }

                //case of a number
            } else if(checkIsNumeric(token)) {
                curr.setLabel(token);
                curr = parentStorage.pop(); //get the parent back
            }
        }
        return parseTree;

    }

    //    Evaluate the expression stored in a parse tree.
//    This method evaluates a parse tree by recursively evaluating each subtree.
    public static double evaluate(bridges.base.BinTreeElement<String> tree){
        double firstEval;
        double secondEval;
        if(tree == null){
            return Double.NaN;
        }
        if(tree.getLeft() == null && tree.getRight() == null){ //check if leaf
            return Double.parseDouble(tree.getLabel()); //get number in double form
        }
        firstEval = evaluate(tree.getLeft()); //recursion to get to far left
        secondEval = evaluate(tree.getRight());

        if(tree.getLabel().equals("+")) {
            return firstEval + secondEval;
        }
        else if(tree.getLabel().equals("-")){
            return firstEval - secondEval;
        }
        else if(tree.getLabel().equals("*")){
            return firstEval * secondEval;
        }else if(tree.getLabel().equals("/")){
            if (secondEval == 0){
                throw new ArithmeticException("Cannot divide by 0");
            }
            return firstEval / secondEval;
        }
        return 0;
    }

    //    Recover the original mathematical expression from a parse tree.
//    The method accepts the root of the parse tree parameter and returns a mathematical expression as a string.
    public static String getEquation(bridges.base.BinTreeElement<String> tree){
        String result = "";
        if(tree.getLeft() == null && tree.getRight() == null){
            return tree.getLabel(); //get the numbers
        }
        result += "( ";
        result += getEquation(tree.getLeft()); //recursively add open bracket

        result += " " + tree.getLabel() + " ";// get operators
        result += getEquation(tree.getRight());
        result += " )";


        return result;

    }

    private static boolean checkIsNumeric(String val){
        if (val == null) {
            return false;
        }
        try{
            double temp = Double.parseDouble(val);
        }
        catch(Exception e){
            throw new IllegalArgumentException("token is invalid");
        }
        return true;
    }
}
