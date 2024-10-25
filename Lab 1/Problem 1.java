/*
За дадена низа од случајни броеви кои се внесуваат од стандарден влез, да се направи преместување на сите нули на почеток на низата. На стандарден излез да се испечати трансформираната низа.

/

For a given array of random numbers given from standard input, perform a shift of all zeros at the beginning of the sequence. Print the transformed array to standard output.
 */

import java.util.Scanner;

public class PushZero {
    static void pushZerosToBeginning(int arr[], int n) {
        int countNonZero = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] != 0) {
                arr[countNonZero--] = arr[i];
            }
        }
        while (countNonZero >= 0) {
            arr[countNonZero--] = 0;
        }
        System.out.println("Transformiranata niza e:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        scanner.close();
        pushZerosToBeginning(arr, n);
    }
}
