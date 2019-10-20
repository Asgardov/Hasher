package com.asgard.hasher.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatChecker {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static List<String> brokenMailList = new ArrayList<>();

    private boolean checkMailAddress(String mailaddress) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mailaddress);
        return matcher.find();

    }

    public List<String> parseFile(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        ArrayList<String> mailList = new ArrayList<>();
        String mailItem;
        while ((mailItem = bufferedReader.readLine()) != null) {
            if (checkMailAddress(mailItem)) {
                mailList.add(mailItem);
            } else
                brokenMailList.add(mailItem);
        }
        bufferedReader.close();
        return mailList;
    }


}
