

import java.util.ArrayList;

/**
 *
 * @author jacinto
 */
public class CircularPrimes extends Thread {

    int counter;
    int limit;
    boolean[] isPrime;
    ArrayList<Integer> list = new ArrayList<Integer>();

    public CircularPrimes(int c, int n, boolean[] isPrime) {
        counter = c;
        limit = n;
        this.isPrime = isPrime;
    }  
    
    
    public String printList() {
        return list.toString();
    }

    // si es primo circular aumenta el contador
    public void rotate(int number) {
        int start = number;

        int numdigits = (int) Math.log10((double) number);
        int multiplier = (int) Math.pow(10.0, (double) numdigits);

        boolean b = true;
        while (true) {
            int q = number / 10;
            int r = number % 10;
            number = number / 10;
            number = (int) (number + multiplier * r);
            if (number > 1000000 || !isPrime[number]) {
                b = false;
                break;
            }
            if (number == start) {
                break;
            }
        }
        if (b) {
            counter++;
            list.add(start);
        }
    }

    public void run() {
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                rotate(i);
            }
        }
    }

    public static void main(String args[]) throws Exception {
        

        //Criba de Eratóstenes
        int N = 1000000;
        boolean[] isPrime = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i <= N; i++) {

            if (isPrime[i]) {
                for (int j = i; i * j <= N; j++) {
                    isPrime[i * j] = false;
                }
            }
        }

        CircularPrimes t1;
        t1 = new CircularPrimes(0,N,isPrime);
        t1.start();
        t1.join();

        System.out.println("Cantidad de primos circulares de 1 a 1000000= " + t1.counter);
        System.out.println(t1.printList());
    }
}
