package com.company;

import java.math.BigInteger;

public class Encrypt
{
    public static byte[] encrypt(PublicKey key, byte text[])
    {
        BigInteger value = new BigInteger(text);
        BigInteger result = value.modPow(key.getE(), key.getN());

        return result.toByteArray();
    }
}
