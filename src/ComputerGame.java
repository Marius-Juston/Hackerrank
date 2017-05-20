import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ComputerGame {

    private static int greatestCommonDenominator(int z, int y) {
        int a = Math.max(z, y);
        int b = Math.min(z, y);

        while (a % b != 0)
            b = a % (a = b);

        return b;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            bufferedReader.readLine();

            ArrayList<Integer> a = new ArrayList<>();
            for (String number : bufferedReader.readLine().split(" "))
                a.add(Integer.parseInt(number));

            ArrayList<Integer> b = new ArrayList<>();
            for (String number : bufferedReader.readLine().split(" "))
                b.add(Integer.parseInt(number));

            Thread[] threads = new Thread[a.size()];
            int q = 0;
            final int[] sum = {0};

            for (int num : a) {
                threads[q] = new Thread(() -> {
                    for (int i = 0; i < b.size(); i++)
                        if (greatestCommonDenominator(num, b.get(Math.min(b.size(), i))) != 1) {
                            sum[0]++;

                            b.remove((Integer) num);

                            break;
                        }
                });
                threads[q++].start();
            }


            for (Thread thread : threads) {
                thread.join();

            }
            System.out.println(sum[0]);
        }
    }
}