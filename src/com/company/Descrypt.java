package com.company;

import java.math.BigInteger;

public class Descrypt
{
    public static byte[] decrypt(PrivateKey key, byte cryptogram[])
    {
        BigInteger value = new BigInteger(cryptogram);
        BigInteger result = value.modPow(key.getD(), key.getN());

        return result.toByteArray();
    }
}
