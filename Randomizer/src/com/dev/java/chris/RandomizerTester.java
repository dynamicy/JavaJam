package com.dev.java.chris;

import java.util.Date;

public class RandomizerTester
{
    public static void main(String args[])
    {
        Randomizer randomizer = new Randomizer(new Date().getTime());

        for(int i=0; i<10; i++)
        {
            System.out.println(randomizer.randomDouble());
        }

        for(int i=0; i<10; i++)
        {
            System.out.println(randomizer.randomInt());
        }
    }
}
