import java.util.Scanner;

class BreakingTheRecords
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        int max = s.nextInt();
        int countM = 0;
        int min = max;
        int countMin = 0;

        while(n-- > 1)
        {
            int num = s.nextInt();

            if(num > max) {
                countM++;
                max = num;
            }
            if(num < min) {
                countMin++;
                min = num;
            }
        }

        System.out.printf("%d %d", countM, countMin);
    }
}
