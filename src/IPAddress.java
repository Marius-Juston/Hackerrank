import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IPAddress {

    public static void main(String[] args) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            bufferedReader.lines().forEach(ip ->
            {
                int start = Integer.parseInt(ip.substring(0, 8), 2);
                System.out.println(start + "." + Integer.parseInt(ip.substring(8, 16), 2) + "." + Integer.parseInt(ip.substring(16, 24), 2) + "." + Integer.parseInt(ip.substring(24, 32), 2) +" [CLASS " + (start >= 240 ? "E" : start >= 224 ? "D" : start >= 192 ? "C" : start >= 128 ? "B" : "A") + "]");
            });
        }
    }
}