package com.company;
import java.math.BigInteger;
import java.util.Random;


public class KeyGenerator
{
    private static final int bits = 2048;
    private Random rand = new Random();

    private BigInteger pN = BigInteger.ZERO;
    private BigInteger pPhi = BigInteger.ZERO;

    private BigInteger coE = BigInteger.ZERO;
    private BigInteger D = BigInteger.ZERO;

    public KeyGenerator()
    {
        while (pN.compareTo(BigInteger.TWO.pow(bits))<0)
        {
            BigInteger primeP = generatePrime();
            BigInteger primeQ = generatePrime();
            while(primeP.compareTo(primeQ) == 0)
                primeQ = generatePrime();

            pN = primeP.multiply(primeQ);
            pPhi = (primeP.subtract(BigInteger.ONE)).multiply((primeQ.subtract(BigInteger.ONE)));

        }
        coE = generateCoPrime(pPhi);
        D = generateD(coE, pPhi);

    }

    private BigInteger generateD(BigInteger l, BigInteger modulo)
    {
        BigInteger a = l;
        BigInteger mods = modulo;

        BigInteger y = BigInteger.ZERO;
        BigInteger x = BigInteger.ONE;

        if(mods.compareTo(BigInteger.ONE) == 0) return BigInteger.ZERO;

        while (a.compareTo(BigInteger.ONE) > 0)
        {
            BigInteger quotient = a.divide(mods);
            BigInteger temp = mods;

            mods = a.mod(mods);
            a = temp;
            temp = y;

            y = x.subtract(quotient.multiply(y));
            x = temp;
        }

        return x;
    }

    private BigInteger generateCoPrime(BigInteger pPhi) //generate E
    {
        BigInteger result = new BigInteger(bits, rand);

        while (result.gcd(pPhi).compareTo(BigInteger.ONE)!=0)
        {
            result = new BigInteger(bits,rand);
            if (result.compareTo(BigInteger.ONE) == 0)
                result = result.add(BigInteger.ONE);
        }
        return result;
    }

    private BigInteger generatePrime()  //generate D
    {
        BigInteger prime = BigInteger.probablePrime(bits, rand);

        while (!prime.isProbablePrime(100))
        {
            prime = BigInteger.probablePrime(bits,rand);
        }
        return prime;
    }

    public PrivateKey getPrivateKey()
    {
        return new PrivateKey(D,pN);
    }

    public PublicKey getPublicKey()
    {
        return new PublicKey(coE, pN);
    }


}
