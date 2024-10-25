/*
 * Потребно е да се симулира најава на еден систем. Притоа корисникот внесува корисничко име и лозинка. Доколку корисничкото име одговара со лозинката тогаш се печати Najaven, доколку не одговара се печати Nenajaven и на корисникот му се дава повторна шанса на корисникот да внесе корисничко име и лозинка. Во моментот кога корисникот ќе биде најавен престануваат обидите за најава.

Влез: Прво се дава број N на кориснички имиња и лозинки кои ќе бидат внесени во системот. Во наредните N реда се дадени корисничките имиња и лозинки разделени со едно празно место. Потоа се даваат редови со кориснички имиња и лозинки на корисници кои се обидувата да се најават (Пр. ana banana) За означување на крај на обидите во редицата се дава зборот KRAJ

Излез: За секој од влезовите кои се обид за најава се печати Nenajaven се додека не дoбиеме Najaven или додека имаме обиди за најава.

Пример. Влез: 3 ana banana pero zdero trpe trpi ana ana ana banana trpe trpi KRAJ

Излез: Nenajaven Najaven

Забелешка: Работете со хеш табела со затворени кофички. Самите решавате за големината на хеш табела, а хеш функцијата ви е дадена.

Име на класа: Lozinki

--------

You are supposed to simulate a log in system. The user enters username and password. If such user exists in the system you are supposed to print "Najaven" to the standard output, else print "Nenajaven" and give the user another prompt for credentials. This stops when the user will enter username and password that match, a certain user credentials in the system.

Input: In the first line of the input you are given a single integer N. In the following N lines you are given usernames and passwords separated with one white space. These are the users that exist in the system. After this you are supposed to read usernames and passwords from the standard input until, a user can be successfully logged in.

Output: Print "Nenajaven" to the standard output for every failed log in try, until we get a successful log in. Then you have to print "Najaven"

Example. Input: 3 ana banana pero zdero trpe trpi ana ana ana banana trpe trpi KRAJ

Output: Nenajaven Najaven

Note: Use closed bucket hash table. Figure out the hash table size by yourself. The hash function has already been given to you.

Class name: Lozinki
 */

 import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class Lozinki {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Hashtable<String, String> hashTable = new Hashtable<>();

        for(int i=0;i<n;i++){
            String imelozinka = br.readLine();
            String[] pom = imelozinka.split(" ");
            hashTable.put(pom[0],pom[1]);
        }

        for(;;){
            String line = br.readLine();
            if (line.equals("KRAJ")) break;

            String [] data = line.split(" ");
            if (
                    hashTable.containsKey(data[0]) && 
                    hashTable.get(data[0]).equals(data[1])
            ){
                System.out.println("Najaven");
                break;
            }
            System.out.println("Nenajaven");
        }

    }
}
