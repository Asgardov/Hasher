package com.asgard.hasher.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Preparator {

    public static List stringToArray (String dataS){
        String[] splited = dataS.split("\\r?\\n");
        List<String> dataL = new ArrayList<>(Arrays.asList(splited));
        return dataL;
    }
}
