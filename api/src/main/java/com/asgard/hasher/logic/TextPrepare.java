package com.asgard.hasher.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextPrepare {

    private TextPrepare() {
    }

    public static List<String> stringToArray(String dataS) {
        String[] arrayOfStrings = dataS.split("\\r?\\n");
        return new ArrayList<>(Arrays.asList(arrayOfStrings));
    }

    public static String resultPrepForOutput(List<String> encoded) {
        StringBuilder result = new StringBuilder();
        for (String encItem :
                encoded) {
            result.append(encItem);
            result.append("\n");
        }
        return result.toString();

    }
}
