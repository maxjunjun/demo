package com.max.demo.thread;

import com.max.demo.ThreadApplicationTests;
import org.junit.Test;

/**
 * Created by max on 2017/11/1.
 */
public class ThreadTest extends ThreadApplicationTests {

    @Test
    public void primeGeneratorTest() {
        try {
            PrimeGenerator.aSecondOfPrimes();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void PrimeProducerTest() {
        PrimeProducer primeProducer = new PrimeProducer();

    }
}
