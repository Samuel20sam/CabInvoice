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

    @Test
    public void givenLessDistanceAndFare_ShouldReturnMinFare() {
        CabInvoice inVoiceGenerator = new CabInvoice();
        double distance = 0.1;
        int time = 1;
        double fare = inVoiceGenerator.calculateFare(distance, time);
        Assertions.assertEquals(5.0, fare);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(2.0, 5), new Ride(0.1, 1), new Ride(20.5, 35)};
        CabInvoice inVoiceGenerator = new CabInvoice();
        double fare = inVoiceGenerator.calculateFare(rides);
        Assertions.assertEquals(270.0, fare);
    }
}