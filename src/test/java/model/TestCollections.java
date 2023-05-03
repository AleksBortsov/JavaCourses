package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TestCollections {

    // 1 --------------------------------
    @Test
    void testPrintList() {
        list.forEach(System.out::println);
    }

    // 2 --------------------------------
    @Test
    void testChangeWeightOfFirstByOne() {
        //todo Изменить вес первой коробки на 1.
        list.get(0).setWeight(list.get(0).getWeight() + 1);
        assertEquals(new HeavyBox(1, 2, 3, 5), list.get(0));
    }

    // 3 --------------------------------
    @Test
    void testDeletePreLast() {
        //todo Удалить предпоследнюю коробку.
        HeavyBox element = list.get(list.size() - 2);
        list.remove(list.size() - 2);
        assertNotEquals(list.get(list.size() - 1), element);

    }

    // 4 --------------------------------
    @Test
    void testConvertToArray() {
        //todo Получить массив содержащий коробки из коллекции тремя способами и вывести на консоль.
        HeavyBox[] arrayListToArray1 = list.toArray(new HeavyBox[0]);
        for (HeavyBox array : arrayListToArray1
        ) {
            System.out.println(array);
        }
        assertArrayEquals(new HeavyBox[]{
                new HeavyBox(1, 2, 3, 4),
                new HeavyBox(3, 3, 3, 4),
                new HeavyBox(2, 6, 5, 3),
                new HeavyBox(2, 3, 4, 7),
                new HeavyBox(1, 3, 3, 4),
                new HeavyBox(1, 2, 3, 4),
                new HeavyBox(1, 1, 1, 1)
        }, arrayListToArray1);

        HeavyBox[] arrayListToArray2 = new HeavyBox[list.size()];
        arrayListToArray2 = list.toArray(arrayListToArray2);
        System.out.println("-----------------------------");
        for (HeavyBox array2 : arrayListToArray2
        ) {
            System.out.println(array2);
        }

        assertArrayEquals(new HeavyBox[]{
                new HeavyBox(1, 2, 3, 4),
                new HeavyBox(3, 3, 3, 4),
                new HeavyBox(2, 6, 5, 3),
                new HeavyBox(2, 3, 4, 7),
                new HeavyBox(1, 3, 3, 4),
                new HeavyBox(1, 2, 3, 4),
                new HeavyBox(1, 1, 1, 1)
        }, arrayListToArray2);

        HeavyBox[] arrayListToArray3 = new HeavyBox[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arrayListToArray3[i] = list.get(i);
        }
        System.out.println("-----------------------------");
        for (HeavyBox array3 : arrayListToArray3
        ) {
            System.out.println(array3);
        }

        assertArrayEquals(new HeavyBox[]{
                new HeavyBox(1, 2, 3, 4),
                new HeavyBox(3, 3, 3, 4),
                new HeavyBox(2, 6, 5, 3),
                new HeavyBox(2, 3, 4, 7),
                new HeavyBox(1, 3, 3, 4),
                new HeavyBox(1, 2, 3, 4),
                new HeavyBox(1, 1, 1, 1)
        }, arrayListToArray3);
    }

    // 5 --------------------------------
    @Test
    void testDeleteBoxesByWeight() {
        // todo удалить все коробки, которые весят 4
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getWeight() == 4) {
                list.remove(i);
                i = 0;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getWeight() == 4) {
                list.remove(i);
                i = 0;
            }
        }
        assertEquals(3, list.size());
    }

    // 6 --------------------------------
    @Test
    void testSortBoxesByWeight() {
        // отсортировать коробки по возрастанию веса. При одинаковом весе - по возрастанию объема
        Collections.sort(list, new Comparator<HeavyBox>() {
            @Override
            public int compare(HeavyBox weight1, HeavyBox weight2) {
                if (weight1.getWeight() >= weight2.getWeight())
                    return 1;

                if (weight1.getWeight() < weight2.getWeight()) {
                    return -1;
                }
                return 0;
            }
        });
        Collections.sort(list, new Comparator<HeavyBox>() {
            @Override
            public int compare(HeavyBox weight1, HeavyBox weight2) {
                if (weight1.getWeight() == weight2.getWeight())
                    if (weight1.getVolume() >= weight2.getVolume())
                        return 1;
                    else return -1;
                return 0;
            }
        });
        assertEquals(new HeavyBox(1, 1, 1, 1), list.get(0));
        assertEquals(new HeavyBox(2, 3, 4, 7), list.get(6));
        assertEquals(new HeavyBox(1, 3, 3, 4), list.get(4));
        assertEquals(new HeavyBox(1, 2, 3, 4), list.get(3));
    }

    // 7 --------------------------------
    @Test
    void testClearList() {
        //todo Удалить все коробки.
        list.clear();
        assertTrue(list.isEmpty());
    }

    // 8 --------------------------------
    @Test
    void testReadAllLinesFromFileToList() throws IOException {
        // todo Прочитать все строки в коллекцию
        List<String> lines = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        assertEquals(19, lines.size());
        assertEquals("", lines.get(8));
    }

    // 9 --------------------------------
    @Test
    void testReadAllWordsFromFileToList() throws IOException {
        // todo прочитать все строки, разбить на слова и записать в коллекцию
        List<String> words = readAllWordsFromFileToList();
        assertEquals(257, words.size());
    }

    List<String> readAllWordsFromFileToList() throws IOException {
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] arr = line.split(REGEXP);
            Collections.addAll(lines, arr);
        }
        while (lines.contains("")) {
            lines.remove("");
        }
        return lines;
    }

    // 10 -------------------------------
    @Test
    void testFindLongestWord() throws IOException {
        // todo Найти самое длинное слово

        assertEquals("conversations", findLongestWord());
    }

    private String findLongestWord() throws IOException {
        String longestWord = "";
        String word = "";
        String line;
        while ((line = reader.readLine()) != null) {
            longestWord = Arrays.stream(line.split(REGEXP)).max(Comparator.comparingInt(String::length)).orElse(null);
            if (word.length() < longestWord.length())
                word = longestWord;
        }
        return word;
    }

    // 11 -------------------------------
    @Test
    void testAllWordsByAlphabetWithoutRepeat() throws Exception {
        // todo Получить список всех слов по алфавиту без повторов
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] arr = line.split(REGEXP);
            Collections.addAll(lines, arr);
        }
        List<String> result1 = lines.stream().map(String::toLowerCase).distinct().collect(Collectors.toList());
        result1.remove("");
        List<String> result = result1.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());

        assertEquals("alice", result.get(5));
        assertEquals("all", result.get(6));
        assertEquals("without", result.get(134));
        assertEquals(138, result.size());
    }

    // 12 -------------------------------
    @Test
    void testFindMostFrequentWord() throws Exception {
        // todo Найти самое часто вcтречающееся слово
        assertEquals("the", mostFrequentWord());
    }

    private String mostFrequentWord() throws Exception {
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] arr = line.split(REGEXP);
            Collections.addAll(lines, arr);
        }
        String mostFrequency = "";
        int count = 0;
        int maxCount = 0;

        for (String name : lines) {
            count = 1;
            for (String innerName : lines) {
                if (name.equals(innerName)) {
                    count++;
                }
                if (count > maxCount) {
                    maxCount = count;
                    mostFrequency = name;
                }
            }
        }
        return mostFrequency;
    }

    // 13 -------------------------------
    @Test
    void testFindWordsByLengthInAlphabetOrder() throws IOException {
        // todo получить список слов, длиной не более 5 символов, переведенных в нижний регистр, в порядке алфавита, без повторов
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] arr = line.split(REGEXP);
            Collections.addAll(lines, arr);
        }
        int k = 5;
        List<String> result1 = lines.stream().map(String::toLowerCase).distinct().collect(Collectors.toList());
        while (result1.contains("")) {
            result1.remove("");
        }
        List<String> result = result1.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        List<String> numberWords = new ArrayList<>();
        for (String str : result
        ) {
            if (str.length() <= k) {
                numberWords.add(str);
            }
        }
        List<String> strings = numberWords;
        assertEquals(94, strings.size());
        assertEquals("a", strings.get(0));
        assertEquals("alice", strings.get(2));
        assertEquals("would", strings.get(strings.size() - 1));

    }


    List<HeavyBox> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>(List.of(
                new HeavyBox(1, 2, 3, 4),//4     6
                new HeavyBox(3, 3, 3, 4),//7     27
                new HeavyBox(2, 6, 5, 3),//2
                new HeavyBox(2, 3, 4, 7),//6     24
                new HeavyBox(1, 3, 3, 4),//5     9
                new HeavyBox(1, 2, 3, 4),//3     6
                new HeavyBox(1, 1, 1, 1) //1
        ));
    }

    static final String REGEXP = "\\W+"; // for splitting into words
    static final String SPACE = "\\S+"; // for splitting into words

    private BufferedReader reader;

    @BeforeEach
    public void setUpBufferedReader() throws IOException {
        reader = Files.newBufferedReader(
                Paths.get("Text.txt"), StandardCharsets.UTF_8);
    }

    @AfterEach
    public void closeBufferedReader() throws IOException {
        reader.close();
    }
}