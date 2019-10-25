package com.asgard.hasher.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatChecker {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static boolean checkMailAddress(String mailAddress) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mailAddress);
        return matcher.find();

    }

    public static List<String> validateMailsArray(List<String> arrToParse) {
        ArrayList<String> validatedList = new ArrayList<>();
        ArrayList<String> trimmedList = new ArrayList<>();
        for (String item :
                arrToParse) {
            trimmedList.add(item.trim().toLowerCase());
        }
        for (String item :
                trimmedList) {
            if (checkMailAddress(item))
                validatedList.add(item);
        }
        return validatedList;
    }


}
