/*
 * Најди ја најдолгата опаѓачка секвенца во една низа. Броевите во секвенцата не мора да се наоѓаат на последователни индекси во низата.

/

Find the longest decreasing sequence in an array. The numbers in the sequence don't need to be on consecutive indices in the array.
 */

 import java.util.Scanner;


public class LDS {


    private static int najdolgaOpagackaSekvenca(int[] a) {
        int n = a.length;
        int[] dp = new int[n]; // dp[i] stores the length of the longest descending subsequence ending at index i

        for (int i = 0; i < n; i++) {
            dp[i] = 1; // Initialize with 1, as every element is a subsequence of length 1 by itself
            for (int j = 0; j < i; j++) {
                if (a[j] > a[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLength) {
                maxLength = dp[i];
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }


}
