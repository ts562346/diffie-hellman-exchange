import java.util.Random;
import java.util.Scanner;

public class Main {

    /*
        Method to generate prime numbers in the range 1 to 1000.
     */
    public static int generatePrime(){
        int num = 0;
        Random rand = new Random();
        num = rand.nextInt(1000) + 1;

        while (!isPrime(num)) {
            num = rand.nextInt(1000) + 1;
        }

        return num;
    }

    /*
        Method to check whether the generated random number is prime.
     */
    public static boolean isPrime(int n) {
        if (n == 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n)+1; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /*
        Method to perform modular exponentiation.
     */
    public static int modExpon(int x, int y, int p) {
        int res = 1;

        while (y > 0) {
            if(y % 2 ==1){
                res = (res * x) % p;
            }

            x = (x * x )%p;
            y = y/2;
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter numbers p (followed by space) and g: ");

        // read input p and g
        int p = sc.nextInt();
        int g = sc.nextInt();

        // create a random prime number for Alice and Bob
        int sA = generatePrime();
        int sB = generatePrime();

        System.out.println("\nAlice's secret key is " + sA);
        System.out.println("Bob's secret key is " + sB);

        // calculate the key that Alice and Bob share with each other
        int tA = modExpon(g, sA, p);
        int tB = modExpon(g, sB, p);

        System.out.println("\nThe key Alice sends to Bob is " + tA);
        System.out.println("The key Bob sends to Alice is " + tB);

        // calculate the Diffie-Hellman key which should be the same for Alice and Bob
        int dKeyA = modExpon(tB, sA, p);
        int dKeyB = modExpon(tA, sB, p);

        if(dKeyA == dKeyB) {
            System.out.println("\nThe Diffie Hellman secret key is " + dKeyA);
        } else {
            System.out.println("Oops...something went wrong.");
        }

    }
}