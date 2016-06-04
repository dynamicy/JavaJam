package com.dev.java.chris;

public class Randomizer
{
    static final int m = 29319;
    static final int n = 1834;
    long seed = 1;

    // Constructor
    public Randomizer(long seed)
    {
        this.seed = seed;
    }

    public double randomDouble()
    {
        seed = (seed * n) % m;
        return (float)seed / (float)m;
    }

    public int randomInt()
    {
        seed = (seed * n) % m;
        return Math.round(seed);
    }
}
