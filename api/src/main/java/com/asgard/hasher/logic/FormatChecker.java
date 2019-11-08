package com.asgard.hasher.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatChecker {

    private FormatChecker() {
    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static boolean checkMailAddress(String mailAddress) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mailAddress);
        return matcher.find();

    }

    public static List<String> validateMailsArray(List<String> arrToParse) {
        ArrayList<String> validatedList = new ArrayList<>();
        for (String item :
                arrToParse) {
            String trimmedItem = item.trim().toLowerCase();
            if (trimmedItem.length() > 0 && checkMailAddress(trimmedItem))
                validatedList.add(trimmedItem);
        }
        return validatedList;
    }

    public static List<String> validatePhonesArray(List<String> arrToParse) {
        ArrayList<String> validatedList = new ArrayList<>();
        for (String item :
                arrToParse) {
            if (item.trim().length() > 0)
                validatedList.add(item.trim().replaceAll("[^\\d.]", ""));
        }
        return validatedList;
    }


}
