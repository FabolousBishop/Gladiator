package com.codecool.gladiator.util;

import java.util.List;
import java.util.Random;

public class RandomUtils {

    private static final Random RANDOM = new Random();

    public static int number(int from) {
        return RANDOM.nextInt(from);
    }

    public static int number(int from, int to) {
        return from + RANDOM.nextInt(to - from);
    }

    public static <T> T choseOneFromList(List<T> list) {
        if (list == null || list.isEmpty())
            throw new IllegalArgumentException("Given list shouldn't be an empty object");

        return list.get(RANDOM.nextInt(list.size()));
    }

    public static boolean percentageSuccess(int chance){
        return RANDOM.nextInt(100) < chance;
    }

}
