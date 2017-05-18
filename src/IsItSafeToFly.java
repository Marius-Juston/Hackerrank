import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

public class IsItSafeToFly {

    public static void main(String[] args) throws IOException {
        class Part {
            private final String name;
            private final int life;
            private final int usedLife;
            private final int plannedUsage;

            private Part(String name, int life, int usedLife, int plannedUsage) {
                this.name = name;
                this.life = life;
                this.usedLife = usedLife;
                this.plannedUsage = plannedUsage;
            }

            private boolean isOk() {
                return life - usedLife > plannedUsage;
            }

            public String toString() {
                return name + ": " + (isOk() ? "GO" : "NOGO");
            }
        }

        List<Part> parts = new LinkedList<>();

        final Pattern pattern = Pattern.compile(" ");

        try( BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            bufferedReader.lines().forEach(s -> {
                String[] stuff = pattern.split(s.replaceAll("[,]", ""));

                parts.add(new Part(stuff[0], Integer.parseInt(stuff[1]), Integer.parseInt(stuff[3]), Integer.parseInt(stuff[4])));
            });
        }

        parts.sort(Comparator.comparing(x -> x.name));

        parts.forEach(System.out::println);
    }
}