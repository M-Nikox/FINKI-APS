/*За даден збор кој се внесува од стандарден влез, да се испечати истиот превртен. На влез во првиот ред се дава број на зборови кои ќе се внесуваат. Во наредните линии се внесуваат самите зборови.

For a given word entered from standard input, print it reversed. On input in the first line, the number of words that will be entered is given. In the following lines, the words are entered.
 */

 import java.util.Scanner;

public class ReverseWord {
    public static void printReversed(String word) {
        String revWord=""; //also mozhe so StringBuilder str = new Stringbuilder(word).reverse, sout str
        for (int i=word.length()-1; i>=0; i--){
            revWord+=word.charAt(i);
        }
        System.out.println(revWord);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            String word = scanner.nextLine();
            printReversed(word);
        }
        scanner.close();
    }
}