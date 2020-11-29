package com.company;

import java.math.BigInteger;

public class PublicKey
{
    private final BigInteger E;
    private final BigInteger N;


    public PublicKey(BigInteger e, BigInteger n)
    {
        E = e;
        N = n;
    }
    public BigInteger getE() {
        return E;
    }

    public BigInteger getN() {
        return N;
    }


}
