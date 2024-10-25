/* Даден е некој аритметички израз. Аритметичкиот израз е во облик (A+B) или (A-B) каде што А и B истовремено се други аритметички изрази или цифри од 0-9. Потребно е да го евалуирате дадениот израз.

Име на класата (Java): ArithmeticExpression
=================================================================

An arithmetic expression is given. An arithmetic expression is of the form (A+B) or (A-B) where A and B are both other arithmetic expressions or digits from 0-9. You need to evaluate the given expression.

Name of Java class: ArithmeticExpression
 */

 import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArithmeticExpression {
    
    static int presmetaj(char c[], int l, int r) {
        if(l == r) {
            return toInt(c[l]);
        }

        int cnt = 0, index = -1;
        for(int i = l; i < r; i++) {
            if(c[i] == '(') cnt++;
            else if(c[i] == ')') cnt--;
            if((c[i] == '+' || c[i] == '-') && cnt == 0) index = i;
        }

        if(index == -1) return presmetaj(c, l + 1, r - 1);

        if(c[index] == '+')
            return presmetaj(c, l, index - 1) + presmetaj(c, index + 1, r);
        else if(c[index] == '-')
            return presmetaj(c, l, index - 1) - presmetaj(c, index + 1, r);

        return 0;
    }

    static int toInt(char c) {
        return Integer.parseInt(String.valueOf(c));
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

        br.close();

    }

}