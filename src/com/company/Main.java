package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {

    private static KeyGenerator generator = new KeyGenerator();


    private static String readFile(String name)
    {
        try{
            List<String> lines = Files.readAllLines(Paths.get(name), StandardCharsets.UTF_8);
            String text = lines.stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining());
            return text;
        }
        catch (IOException e){ e.printStackTrace();}
        return "";
    }

    private static void bytesToFile(byte[] bytes, String name)
    {
        try
        {
            Files.write(Paths.get(name),bytes);
        }
        catch (IOException e){e.printStackTrace();}
    }

    private static void encryptAndDecrypt(String name)
    {
        byte[] text = readFile(name).getBytes();
        String[] nameAndType = splits(name);

        byte[] encrypted = Encrypt.encrypt(generator.getPublicKey(),text);
        String encryptedName = nameAndType[0] + "_encrypted." + nameAndType[1];
        bytesToFile(encrypted, encryptedName);

        byte[] decrypted = Descrypt.decrypt(generator.getPrivateKey(),encrypted);
        String decryptedName = nameAndType[0] + "_decrypted." + nameAndType[1];
        bytesToFile(decrypted,decryptedName);

    }

    private static String[] splits(String name)
    {
        String[] result = name.split("\\.");
        return result;
    }
    public static void main(String[] args) {

        encryptAndDecrypt("C:\\Users\\damia\\OneDrive\\Pulpit\\Programming Projects\\RSAalgorithm\\szyfrowanko.txt");


    }
}
