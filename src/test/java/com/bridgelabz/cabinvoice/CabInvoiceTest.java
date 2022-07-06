package com.bridgelabz.cabinvoice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CabInvoiceTest {
    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        CabInvoice inVoiceGenerator = new CabInvoice();
        double distance = 2.0;
        int time = 1;
        double fare = inVoiceGenerator.calculateFare(distance, time);
        Assertions.assertEquals(25.0, fare);
    }

    @Test
    public void givenLessDistanceAndFare_ShouldReturnMinFare() {
        CabInvoice cabInvoice = new CabInvoice();
        double distance = 0.1;
        int time = 1;
        double fare = cabInvoice.calculateFare(distance, time);
        Assertions.assertEquals(5.0, fare);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoice() {
        Ride[] rides = {new Ride(2.0, 5), new Ride(0.1, 1), new Ride(20.5, 35)};
        CabInvoice cabInvoice = new CabInvoice();
        double fare = cabInvoice.calculateFare(rides);
        Assertions.assertEquals(270.0, fare);
    }
    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(2.0, 5), new Ride(0.1, 1), new Ride(20.5, 35)};
        CabInvoice cabInvoice = new CabInvoice();
        Invoice expectedFare = new Invoice(270.0, 3,90.0);
        Invoice actualFare = cabInvoice.generateInvoice(rides);
        Assertions.assertEquals(actualFare, expectedFare);
    }

    @Test
    public void givenMultipleRides_ShouldReturnUsedBasedInvoiceSummary() {
        CabInvoice cabInvoice = new CabInvoice();

        Invoice actualFareUserOne = cabInvoice.generateUserBasedInvoice(1);
        Invoice expectedFareUserOne = new Invoice(270.0, 3,90.0);
        Assertions.assertEquals(actualFareUserOne, expectedFareUserOne);

        Invoice actualFareUserTwo = cabInvoice.generateUserBasedInvoice(2);
        Invoice expectedFareUserTwo = new Invoice(240.0, 3,80.0);
        Assertions.assertEquals(actualFareUserTwo, expectedFareUserTwo);

        Invoice actualFareUserThree = cabInvoice.generateUserBasedInvoice(3);
        Invoice expectedFareUserThree = new Invoice(240.0, 3,80.0);
        Assertions.assertEquals(actualFareUserThree, expectedFareUserThree);
    }
}