package com.ma.test.Stream;

import org.apache.poi.ss.formula.functions.T;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {
    private static Path path = new File(TestStream.class.getResource("/").getPath() + "alice30.txt").toPath();

    public static void main(String[] args) throws IOException {
        modifyCollectionWhenGenStream();
    }

    public static <T> void show(String title, Stream<T> stream) {
        final int SIZE = 10;
        List<T> firstElements = stream
                .limit(SIZE + 1)
                .collect(Collectors.toList());
        System.out.print(title + ": ");
        for (int i = 0; i < firstElements.size(); i++) {
            if (i > 0) System.out.print(", ");
            if (i < SIZE) System.out.print(firstElements.get(i));
            else System.out.print("...");
        }
        System.out.println();
    }


    public static void TestReturnStreamInMap() throws IOException {
        Stream<String> words = getStringTestStream();
        Stream<String> result = words.flatMap(w -> letters(w));
        result.peek(System.out::println).toArray();
        Object[] powers = Stream.iterate(1.0, p -> p * 2).peek(System.out::println).limit(20).toArray();

    }

    public static void TestCollect() throws IOException {
        Stream<String> words = getStringTestStream();

        //System.out.println(words.collect(Collectors.joining(", ")));
        Optional<String> startsWithQ = words.parallel().filter(s ->
                s.startsWith("Q")).findAny();

    }

    private static Stream<String> getStringTestStream() throws IOException {
        String contents = new String(Files.readAllBytes(path),
                StandardCharsets.UTF_8);
        return Stream.of(contents.split("\\PL+"));
    }

    private static void TestVariousStramCreation() throws IOException {
        String contents = new String(Files.readAllBytes(path),
                StandardCharsets.UTF_8);

        Stream<String> words = Stream.of(contents.split("\\PL+"));
        show("words", words);
        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        show("song", song);
        Stream<String> silence = Stream.empty();
        show("silence", silence);

        Stream<String> echos = Stream.generate(() -> "Echo");
        show("echos", echos);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE,
                n -> n.add(BigInteger.ONE));
        show("integers", integers);

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(
                contents);
        show("wordsAnotherWay", wordsAnotherWay);

        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            show("lines", lines);
        }
        Collection l=null;
        l.stream();
    }

    public static Stream<String> letters(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            result.add(s.substring(i, i + 1));
        }
        return result.stream();
    }
    public static Stream<String> createStream( ) {
        Stream<String> echos = Stream.generate(() -> "Echo");
        Stream<String> stringStream = echos.map(str -> {
            System.out.println(str);
            return str;
        });
        System.out.println("============");
        Stream<String> limit = stringStream.limit(10);
        System.out.println("============");
        limit.peek(System.out::println).count();
        System.out.println();
        Object[] powers = Stream.iterate(1.0, p -> p * 2)
                .peek(e -> System.out.println("Fetching " + e))
                .limit(20).toArray();
        return echos;
    }
    public static long modifyCollectionWhenGenStream( ) throws IOException {
        String contents = new String(Files.readAllBytes(path),
                StandardCharsets.UTF_8);
        String[] split1 = contents.split("\\PL+");
        List<String> strings = new ArrayList<>(Arrays.asList(split1));
        Stream<String> stream = strings.stream();
        strings.add("1");
        long count = stream.distinct().count();
        System.out.println(count);
        return count;
    }
}
