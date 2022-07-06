package com.bridgelabz.cabinvoice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals(25.0, fare);
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
        Invoice expectedFare = new Invoice(270.0, 3,90.0);
        Invoice actualFare = cabInvoice.generateInvoice(rides);
        Assertions.assertEquals(actualFare, expectedFare);
    }

    @Test
    public void givenMultipleRides_ShouldReturnUsedBasedInvoiceSummary() {
        CabInvoice cabInvoice = new CabInvoice();
        cabInvoice.minFare = 5;
        cabInvoice.costPerMin = 1;
        cabInvoice.minCostPerKm = 10;
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

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummaryBasedOnType() {
        CabInvoice cabInvoice = new CabInvoice();

        String type = "Premium";

        if (type.equals("Normal")){
            cabInvoice.minCostPerKm = 10;
            cabInvoice.costPerMin = 1;
            cabInvoice.minFare = 5;
        } else {
            cabInvoice.minCostPerKm = 15;
            cabInvoice.costPerMin = 2;
            cabInvoice.minFare = 20;
        }

        Invoice actualFareUserOne = cabInvoice.generateUserBasedInvoice(1);
//        Invoice expectedNormalFareUserOne = new Invoice(270.0, 3,90.0);
//        Assertions.assertEquals(actualFareUserOne, expectedNormalFareUserOne);
        Invoice expectedPremiumFareUserOne = new Invoice(437.5, 3,437.5 / 3);
        Assertions.assertEquals(actualFareUserOne, expectedPremiumFareUserOne);

        Invoice actualFareUserTwo = cabInvoice.generateUserBasedInvoice(2);
//        Invoice expectedNormalFareUserTwo = new Invoice(240.0, 3,80.0);
//        Assertions.assertEquals(actualFareUserTwo, expectedNormalFareUserTwo);
        Invoice expectedPremiumFareUserTwo = new Invoice(378.0, 3,378.0 / 3);
        Assertions.assertEquals(actualFareUserTwo, expectedPremiumFareUserTwo);

        Invoice actualFareUserThree = cabInvoice.generateUserBasedInvoice(3);
//        Invoice expectedNormalFareUserThree = new Invoice(240.0, 3,80.0);
//        Assertions.assertEquals(actualFareUserThree, expectedNormalFareUserThree);
        Invoice expectedPremiumFareUserThree = new Invoice(375.0, 3,375.0 / 3);
        Assertions.assertEquals(actualFareUserThree, expectedPremiumFareUserThree);
    }
}