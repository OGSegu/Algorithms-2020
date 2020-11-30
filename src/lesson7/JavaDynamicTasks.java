package lesson7;

import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    public static String longestCommonSubSequence(String first, String second) {
        int[][] d = new int[first.length() + 1][second.length() + 1];
        StringBuilder[][] sbArray = new StringBuilder[first.length() + 1][second.length() + 1];

        for (int i = 1; i <= first.length(); i++) {
            char firstChar = first.charAt(i - 1);
            for (int j = 1; j <= second.length(); j++) {
                char secondChar = second.charAt(j - 1);
                if (firstChar == secondChar) {
                    d[i][j] = 1 + d[i - 1][j - 1];
                    if (sbArray[i - 1][j - 1] == null)
                        sbArray[i - 1][j - 1] = new StringBuilder();
                    sbArray[i][j] = new StringBuilder(sbArray[i - 1][j - 1]).append(secondChar);
                } else {
                    if (d[i - 1][j] > d[i][j - 1]) {
                        d[i][j] = d[i - 1][j];
                        if (sbArray[i - 1][j] == null)
                            sbArray[i - 1][j] = new StringBuilder();
                        sbArray[i][j] = sbArray[i - 1][j];
                    } else {
                        d[i][j] = d[i][j - 1];
                        if (sbArray[i][j - 1] == null)
                            sbArray[i][j - 1] = new StringBuilder();
                        sbArray[i][j] = sbArray[i][j - 1];
                    }
                }
            }
        }
        return sbArray[first.length()][second.length()].toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        if (list.size() <= 1)
            return list;
        int[] d = new int[list.size()];
        int[] p = new int[list.size()];
        p[0] = -1;
        d[0] = 1;
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < i; j++) {
                if (list.get(j) < list.get(i) && d[j] + 1 > d[i]) {
                    d[i] = d[j] + 1;
                    p[i] = j;
                }
            }
        }
        int index = 0;
        int value = Integer.MIN_VALUE;
        for (int i = 0; i < d.length; i++) {
            if (d[i] > value) {
                value = d[i];
                index = i;
            }
        }
        List<Integer> resultList = new ArrayList<>();
        while (index != -1) {
            resultList.add(list.get(index));
            index = p[index];
        }
        Collections.reverse(resultList);
        return resultList;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
