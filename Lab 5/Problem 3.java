/*
 * Дадена е една двојно поврзана листa и со N јазли кои во себе содржат по еден природен број. Треба да се сортира листата со помош на сортирањето со меурчиња (bubble sort). 

Во првиот ред од влезот е даден бројот на јазли во листата, а потоа во вториот ред се дадени јазлите од кои е составена. На излез треба да се испечатат јазлите на сортираната листа.

Име на класата: BubbleSortDLL

Забелешка: При реализација на задачата МОРА да се користи дадената структура, а не да користат помошни структури како низи и сл.

-----------------

Given is a doubly linked list with N nodes that each contain a natural number. The list should be sorted using bubble sort.

In the first line of the input, the number of nodes in the list is given, and then in the second line, the nodes of which it is composed are given. The output should print the nodes of the sorted list

Class Name: BubbleSortDLL

Note: When performing the task, the given structure MUST be used, and not auxiliary structures such as arrays, etc.
 */

 import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BubbleSortDLL {

    static class DLL{
        Node head;

        public void push(int data) {
            Node newNode = new Node(data);

            newNode.next = head;
            newNode.prev=null;

            if (head!=null){
                head.prev=newNode;
            }

            head=newNode;
        }

        static class Node{
            int data;
            Node prev;
            Node next;

            Node(int data){
                this.data=data;
            }
        }
    }

    public static void sort(DLL dll, int n){
        if (dll.head == null || dll.head.next == null){
            return;
        }

        for (int i=0; i<n; i++){
            DLL.Node current = dll.head;
            DLL.Node next = dll.head.next;
            DLL.Node prev = null;

            while (next != null){
                if (current.data > next.data){
                    current.next = next.next;
                    next.prev=current.prev;

                    if (current.next!=null){
                        current.next.prev=current;
                    }

                    if (prev != null){
                        prev.next=next;
                    } else {
                        dll.head = next;
                    }

                    next.next=current;
                    current.prev = next;
                    prev = next;
                    next=current.next;
                } else {
                    prev = current;
                    current = next;
                    next = next.next;
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        DLL dll = new DLL();

        String[] line = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int data = Integer.parseInt(line[i]);
            dll.push(data);
        }

        sort(dll, n);

        DLL.Node node = dll.head;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }
}
