package com.asgard.hasher.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Preparator {

    private Preparator() {
    }

    public static List stringToArray(String dataS) {
        String[] splited = dataS.split("\\r?\\n");
        return new ArrayList<>(Arrays.asList(splited));
    }

    public static String resultPrep(List<String> encoded) {
        StringBuilder result = new StringBuilder();
        for (String encItem :
                encoded) {
            result.append(encItem);
            result.append("\n");
        }
        return result.toString();

    }
}
