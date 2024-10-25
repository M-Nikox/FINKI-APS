/*
 * <p>Да се напише алгоритам кој ќе врши евалуација на израз во постфикс нотација.</p>

<p>На влез се чита низа од знаци за изразот (стринг), а на излез се печати вредноста на изразот по евалуацијата.</p>

<p>Име на класата (Java): PostFixEvaluation</p>

----------

<p>Write an algorithm that will evaluate an expression in postfix notation.</p>

<p>A sequence of characters for the expression (string) is read at the input, and the value of the expression after evaluation is printed at the output.</p>

<p>Class Name (Java): PostFixEvaluation</p>
 */

 import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek ();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    // Go prazni stekot.

    public void push (E x);
    // Go dodava x na vrvot na stekot.

    public E pop ();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }


    public void clear () {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class PostFixEvaluation {

    static int evaluatePostfix(char [] izraz, int n)  {
        Stack<Integer> stack = new ArrayStack<>(n);
        StringBuilder number = new StringBuilder();

        for (int i=0; i<n; i++){
            char current=izraz[i];

            if (Character.isDigit(current)){
                number.append(current);
            } else if (current == ' ' && number.length() > 0){
                stack.push(Integer.parseInt(number.toString()));
                number.setLength(0);
            } else if (current == '+' || current == '*' || current == '-' || current == '/'){
                if (!stack.isEmpty()){
                    int val1=stack.pop();
                    if (!stack.isEmpty()){
                        int val2=stack.pop();
                        switch (current){
                            case '+': stack.push(val1+val2);
                            break;
                            case '*': stack.push(val1*val2);
                            break;
                            case '-': stack.push(val2-val1);
                            break;
                            case '/': stack.push(val2/val1);
                            break;
                        }
                    }
                }
            }
        }
        if (number.length() > 0) {
            stack.push(Integer.parseInt(number.toString()));
        }

        return stack.pop();
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = evaluatePostfix(exp, exp.length);
        System.out.println(rez);

        br.close();

    }

}