package com.asgard.hasher.logic;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

public class Encoder {

    private Encoder(){

    }

    public static List<String> encodeListToMd5(List<String> validMailList) {
        ArrayList<String> encodedMailList = new ArrayList<>();
        for (String mailItem :
                validMailList) {
            encodedMailList.add(DigestUtils.md5Hex(mailItem));
        }
        return encodedMailList;
    }

    public static List<String> encodeListToSHA256(List<String> validMailList) {
        ArrayList<String> encodedMailList = new ArrayList<>();
        for (String listItem :
                validMailList) {
            encodedMailList.add(DigestUtils.sha256Hex(listItem));
        }
        return encodedMailList;
    }
}
