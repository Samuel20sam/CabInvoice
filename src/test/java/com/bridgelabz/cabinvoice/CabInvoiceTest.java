package com.bridgelabz.cabinvoice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class CabInvoiceTest {
    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        CabInvoice cabInvoice = new CabInvoice();
        cabInvoice.minFare = 5;
        cabInvoice.costPerMin = 1;
        cabInvoice.minCostPerKm = 10;

        double distance = 2.0;
        int time = 1;
        double fare = cabInvoice.calculateFare(distance, time);
        Assertions.assertEquals(21.0, fare);
    }

    @Test
    public void givenLessDistanceAndFare_ShouldReturnMinFare() {
        CabInvoice cabInvoice = new CabInvoice();
        cabInvoice.minFare = 5;
        cabInvoice.costPerMin = 1;
        cabInvoice.minCostPerKm = 10;

        double distance = 0.1;
        int time = 1;
        double fare = cabInvoice.calculateFare(distance, time);
        Assertions.assertEquals(5.0, fare);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoice() {
        Ride[] rides = {new Ride(2.0, 5), new Ride(0.1, 1), new Ride(20.5, 35)};

        CabInvoice cabInvoice = new CabInvoice();
        cabInvoice.minFare = 5;
        cabInvoice.costPerMin = 1;
        cabInvoice.minCostPerKm = 10;

        double fare = cabInvoice.calculateFare(rides);
        Assertions.assertEquals(270.0, fare);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(2.0, 5), new Ride(0.1, 1), new Ride(20.5, 35)};

        CabInvoice cabInvoice = new CabInvoice();
        cabInvoice.minFare = 5;
        cabInvoice.costPerMin = 1;
        cabInvoice.minCostPerKm = 10;

        Invoice expectedFare = new Invoice(270.0, 3, 90.0);
        Invoice actualFare = cabInvoice.generateInvoice(rides);
        Assertions.assertEquals(actualFare, expectedFare);
    }

    @Test
    public void givenMultipleRides_ShouldReturnUsedBasedInvoiceSummaryForNormalFare() {
        CabInvoice cabInvoice = new CabInvoice();
        RideRepo rp = new RideRepo();

        cabInvoice.minFare = 5;
        cabInvoice.costPerMin = 1;
        cabInvoice.minCostPerKm = 10;

        for (Map.Entry<Integer, Ride[]> entry : rp.userMap.entrySet()) {
            Invoice actualFareOfUser = cabInvoice.generateUserBasedInvoice(entry.getKey());

            for (Map.Entry<Integer, Invoice> invoiceEntry : rp.expectedNormalFare.entrySet()) {
                Invoice expectedFareOfUser = invoiceEntry.getValue();

                Assertions.assertEquals(actualFareOfUser, expectedFareOfUser);
            }
        }
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummaryForPremiumFare() {
        CabInvoice cabInvoice = new CabInvoice();
        RideRepo rp = new RideRepo();

        cabInvoice.minCostPerKm = 15;
        cabInvoice.costPerMin = 2;
        cabInvoice.minFare = 20;

        for (Map.Entry<Integer, Ride[]> entry : rp.userMap.entrySet()) {
            Invoice actualFareOfUser = cabInvoice.generateUserBasedInvoice(entry.getKey());

            for (Map.Entry<Integer, Invoice> invoiceEntry : rp.expectedPremiumFare.entrySet()) {
                Invoice expectedFareOfUser = invoiceEntry.getValue();

                Assertions.assertEquals(actualFareOfUser, expectedFareOfUser);
            }
        }
    }
}