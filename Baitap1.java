package multiThread;
import java.util.Scanner;

public class Baitap1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input N: ");
        int N = scanner.nextInt();

        PrintThread evenThread = new PrintThread(true, N);
        PrintThread oddThread = new PrintThread(false, N);

        evenThread.start();
        oddThread.start();

        scanner.close();
    }

    private static class PrintThread extends Thread {
        private final boolean isEvenThread;
        private final int maxCount;

        public PrintThread(boolean isEvenThread, int N) {
            this.isEvenThread = isEvenThread;
            this.maxCount = N;
        }

        @Override
        public void run() {
            int num = isEvenThread ? 0 : 1;
            while (num < maxCount) {
                if ((num % 2 == 0 && isEvenThread) || (num % 2 != 0 && !isEvenThread)) {
                    System.out.println(Thread.currentThread().getName() + ": " + num);
                    num += 2;
                }
            }
        }
    }
}
