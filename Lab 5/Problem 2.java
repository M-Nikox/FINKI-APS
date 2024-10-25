/*
 * Дадена е низа со N природни броеви. Треба да се сортира низата со помош на таканареченото shaker (cocktail) сортирање. Ова сортирање е варијација на сортирањето со меурчиња (bubble sort) со тоа што во секоја итерација низата се изминува два пати. Во првото поминување најмалиот елемент се поместува на почетокот на низата, а при второто најголемиот елемент се поместува на крајот. 

Во првиот ред од влезот даден е бројот на елементи во низата N, а во вториот ред се дадени броевите. На излез треба да се испечати низата по секое изминување во посебен ред.

Име на класата: ShakerSort

-------------------

Given a sequence of N natural numbers. The array should be sorted using the so-called shaker (cocktail) sort. This sort is a variation of the bubble sort in that in each iteration the array is traversed twice. In the first pass, the smallest element is moved to the beginning of the array, and in the second pass, the largest element is moved to the end.

In the first line of the input, the number of elements in the array N is given, and in the second line, the numbers are given. The output should print the string after each pass in a separate line.

Class Name: ShakerSort
 */

 import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShakerSort {

    static void print(int a[], int n){
        for (int i=0; i<n; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }
    static void shakerSort(int a[], int n)
    {
        boolean isSwapped=true;
        int start=0;
        int end = n;

        while (isSwapped) {
            isSwapped = false;

            for (int i = start; i < end - 1; ++i) {
                if (a[i] > a[i + 1]) {
                    int tmp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = tmp;
                    isSwapped = true;
                }
            }

            print(a, n);

            if (!isSwapped) {
                break;
            } else {
                isSwapped = false;
                end = end - 1;

                for (int i = end - 1; i >= start; i--) {
                    if (a[i] > a[i + 1]) {
                        int tmp = a[i];
                        a[i] = a[i + 1];
                        a[i + 1] = tmp;
                        isSwapped = true;
                    }
                }
                start = start + 1;
            }

            print(a, n);
        }
    }

    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        shakerSort(a,n);
    }
}