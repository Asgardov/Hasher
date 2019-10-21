package com.asgard.hasher.mail;

import com.asgard.hasher.logic.FormatChecker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.asgard.hasher.logic.Encoder.encodeListToMd5;
import static com.asgard.hasher.logic.Encoder.encodeListToSHA256;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("source.txt");
        List<String> validMails = new FormatChecker().parseFile(file);
        List<String> encodedMailsMd5  = encodeListToMd5(validMails);
        List<String> encodedMailsSHA256  = encodeListToSHA256(validMails);

        FileWriter writer = new FileWriter("MD5_output"+System.currentTimeMillis()+".txt");
        for(String encodedMail : encodedMailsMd5){
            writer.write(encodedMail + System.getProperty("line.separator"));
        }
        writer.close();

        writer = new FileWriter("SHA256_output"+System.currentTimeMillis()+".txt");
        for(String encodedMail : encodedMailsSHA256){
            writer.write(encodedMail + System.getProperty("line.separator"));
        }
        writer.close();
    }
}
