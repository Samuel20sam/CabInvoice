package com.bridgelabz.cabinvoice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CabInvoiceTest {
    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        CabInvoice inVoiceGenerator = new CabInvoice();
        double distance = 0.2;
        int time = 1;
        double fare = inVoiceGenerator.calculateFare(distance, time);
        Assertions.assertEquals(25.0, fare);
    }
}