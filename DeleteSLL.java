/*
 * 

Дадена е еднострана поврзана листа од броеви со дупликати. За дадена вредност на info поле во листата, да се обезбеди бројот на повторувања на тој број да биде парен. Доколку за дадената вредност на инфо полето, бројот на елементи со таа вредност е непарен, треба да се додаде истиот елемент пред првото појавување на елементот со таа вредност  во листата.

Забелешка: 0 е парен број. 

Влез:

Во првиот ред се внесуваат бројот на елементи во листата, па потоа самите елементи, а на крај се внесува бројот (вредноста) кој ќе се осигураме дека се појавува парен број на пати.

Излез:

Листата со парен број на дупликати за дадената вредност

Примери:

Влез:
6
1 2 5 7 9 12
7

Излез:
1 2 5 7 7 9 12

Влез:
8
7 1 2 7 5 9 7 12
7

Излез:
7 7 1 2 7 5 9 7 12

/

A single linked list of duplicate numbers is given. For a given value of an info field in the list, ensure that the number of repetitions (duplicates) of that number is even. If for the given value of the info field, the number of elements with that value is odd, an element with the same value should be added before the first appearance of the value.
 */

 import java.util.Scanner;


class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "->" + tmp;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == before) {
                this.insertFirst(o);
                return;
            }
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode<E> ins = new SLLNode<E>(o, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.element != o && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element == o) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return first;
    }
    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while(tmp != null){
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }
    }
}

public class DeleteSLL {

    public static void change(SLL<Integer> list, int br) {
        int cnt=0;
        SLLNode<Integer> current = list.getFirst();
        while (current != null){
            if (current.element.equals(br)){
                cnt++;
            }
            current = current.succ;
        }

        if (cnt%2!=0){
            list.insertBefore(br, list.find(br));
        } else return;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);
        int n, broj;
        SLL<Integer> list1 = new SLL<Integer>();
        n = scan.nextInt();
        for(int i = 0; i<n; i++) {
            list1.insertLast(scan.nextInt());
        }
        int br = scan.nextInt();
        change(list1,br);
        System.out.println(list1);


    }
}
