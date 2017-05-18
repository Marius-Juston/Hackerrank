import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

final class MorseCode {

    public static void main(final String... args) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder(1000);

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            bufferedReader.lines().forEach(s ->
            {
                String s3 = s;
                if (s3.equals("END OF TRANSMISSION"))
                    stringBuilder.append("END OF TRANSMISSION\n");
                else {
                    s3 = s3.toLowerCase();

                    if (isEnglish(s3)) {
                        StringJoiner stringJoiner1 = new StringJoiner("_______");

                        getAllWords(s3).forEach(s1 -> {
                            StringJoiner stringJoiner = new StringJoiner("___");

                            getAllLetters(s1).forEach(letter -> stringJoiner.add(toMorse(letter)));

                            stringJoiner1.add(stringJoiner.toString());
                        });
                        stringBuilder.append(stringJoiner1.toString());
                    } else {
                        getAllWordsMorse(s3).forEach(s1 -> {
                            getAllLettersMorse(s1).forEach(s2 -> stringBuilder.append(toLetter(s2)));
                            stringBuilder.append(' ');
                        });
                    }

                    stringBuilder.append('\n');
                }
            });
        }

        System.out.println(stringBuilder);
    }

    private static boolean isEnglish(final String message) {
        return !message.contains("=");
    }

    private static Iterable<String> getAllWords(final String message) {
        return Arrays.asList(message.split(" "));
    }

    private static IntStream getAllLetters(final CharSequence message) {
        return message.chars();
    }

    private static final Pattern word = Pattern.compile("_{7}");
private static final Pattern  letter = Pattern.compile("_{3}");


    private static Iterable<String> getAllWordsMorse(final CharSequence message) {
        return Arrays.asList(word.split(message));
    }

    private static Iterable<String> getAllLettersMorse(final CharSequence message) {
        return Arrays.asList(letter.split(message));
    }

    private static final String[] morse = {
            "=_===", "===_=_=_=", "===_=_===_=", "===_=_=", "=", "=_=_===_=",
            "===_===_=", "=_=_=_=", "=_=", "=_===_===_===", "===_=_===", // end k
            "=_===_=_=", "===_===", "===_=", "===_===_===", "=_===_===_=", // end p
            "===_===_=_===", "=_===_=", "=_=_=", "===", "=_=_===", "=_=_=_===", // und v
            "=_===_===", "===_=_=_===", "===_=_===_===", "===_===_=_=" // end z
    };

    private static final String[] nMorse = {
            "===_===_===_===_===", "=_===_===_===_===", "=_=_===_===_===", "=_=_=_===_===", // end 3
            "=_=_=_=_===", "=_=_=_=_=", "===_=_=_=_=", "===_===_=_=_=", "===_===_===_=_=_=", // end 8
            "===_===_===_===_="
    };

    private static char toLetter(final String message) {

        for(int i = 0; i < morse.length; i++)
            if(message.equals(morse[i]))
                return (char)(i + 97);


        for(int i = 0; i < nMorse.length; i++)
            if(message.equals(nMorse[i]))
                return (char)(i + 48);

        return ' ';
    }

    private static CharSequence toMorse(final int letter) {
        try {
            return morse[letter - 97];
        }
        catch(final IndexOutOfBoundsException ignored) {

            return nMorse[letter - 48];
        }
    }
}