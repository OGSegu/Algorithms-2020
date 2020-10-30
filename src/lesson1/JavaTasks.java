package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     * <p>
     * Пример:
     * <p>
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    // Эффективность - O(nlogn)
    // Ресурсы - O(n)
    static public void sortAddresses(String inputName, String outputName) throws IOException {
        Map<String, TreeSet<String>> treeMap = new TreeMap<>((s1, s2) -> {
            String[] info1 = s1.split(" ");
            String[] info2 = s2.split(" ");
            if (!info1[0].equals(info2[0])) {
                return info1[0].compareTo(info2[0]);
            } else {
                int firstNumber = Integer.parseInt(info1[1]);
                int secondNumber = Integer.parseInt(info2[1]);
                return Integer.compare(firstNumber, secondNumber);
            }
        });
        BufferedReader br = new BufferedReader(new FileReader(inputName, StandardCharsets.UTF_8));
        String line = br.readLine();
        while (line != null) {
            String[] infoArray = line.split(" - ");
            if (!treeMap.containsKey(infoArray[1])) {
                treeMap.put(infoArray[1], new TreeSet<>());
            }
            treeMap.get(infoArray[1]).add(infoArray[0]);
            line = br.readLine();
        }
        String[] keys = treeMap.keySet().toArray(new String[0]);
        try (FileWriter fileWriter = new FileWriter(new File(outputName), StandardCharsets.UTF_8)) {
            for (String key : keys) {
                StringBuilder sb = new StringBuilder(key).append(" - ");
                for (String name : treeMap.get(key)) {
                    sb.append(name);
                    if (!treeMap.get(key).last().equals(name)) {
                        sb.append(", ");
                    } else {
                        sb.append("\n");
                    }
                }
                fileWriter.write(sb.toString());
            }
        }
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    // Эффективность - O(n + k)
    // Ресурсы - O(n)
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputName, StandardCharsets.UTF_8));
        String line = br.readLine();
        List<Integer> tempList = new ArrayList<>();
        final int MIN_VALUE = 273 * 10;
        int max = Integer.MIN_VALUE;
        while (line != null) {
            int number = (int) ((Double.parseDouble(line) * 10 + MIN_VALUE));
            if (number >= max) max = number;
            tempList.add(number);
            line = br.readLine();
        }
        int[] sortedArray = Sorts.countingSort(tempList.stream().mapToInt(e -> e).toArray(), max);
        try (FileWriter fileWriter = new FileWriter(new File(outputName), StandardCharsets.UTF_8)) {
            for (int j : sortedArray) {
                double output = (j - MIN_VALUE) / 10.0;
                fileWriter.write(output + "\n");
            }
        }
    }

    /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
