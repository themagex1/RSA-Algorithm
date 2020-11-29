package com.company;

import java.math.BigInteger;

public class PrivateKey
{
    private final BigInteger D;
    private final BigInteger N;

    public PrivateKey(BigInteger d, BigInteger n)
    {
        D = d;
        N = n;
    }

    public BigInteger getD()
    {
        return D;
    }
    public BigInteger getN()
    {
        return N;
    }
}
