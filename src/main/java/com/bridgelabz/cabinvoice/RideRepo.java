package com.bridgelabz.cabinvoice;

import java.util.LinkedHashMap;
import java.util.Map;

public class RideRepo {
    Map<Integer, Ride[]> userMap = new LinkedHashMap<>();
    Map<Integer, Invoice> expectedNormalFare = new LinkedHashMap<>();
    Map<Integer, Invoice> expectedPremiumFare = new LinkedHashMap<>();

    public void rideRepo() {

        Ride[] userOne = {new Ride(2.0, 5), new Ride(0.1, 1), new Ride(20.5, 35)};
        Ride[] userTwo = {new Ride(5.0, 9), new Ride(1, 1), new Ride(15.0, 20)};
        Ride[] userThree = {new Ride(8.0, 10), new Ride(3, 10), new Ride(10.0, 10)};

        userMap.put(1, userOne);
        userMap.put(2, userTwo);
        userMap.put(3, userThree);

        Invoice expectedFareUserOne = new Invoice(270.0, 3, 90.0);
        Invoice expectedFareUserTwo = new Invoice(240.0, 3, 80.0);
        Invoice expectedFareUserThree = new Invoice(240.0, 3, 80.0);

        expectedNormalFare.put(1, expectedFareUserOne);
        expectedNormalFare.put(2, expectedFareUserTwo);
        expectedNormalFare.put(3, expectedFareUserThree);

        Invoice expectedPremiumFareUserOne = new Invoice(437.5, 3,437.5 / 3);
        Invoice expectedPremiumFareUserTwo = new Invoice(378.0, 3,378.0 / 3);
        Invoice expectedPremiumFareUserThree = new Invoice(375.0, 3,375.0 / 3);

        expectedPremiumFare.put(1, expectedPremiumFareUserOne);
        expectedPremiumFare.put(2, expectedPremiumFareUserTwo);
        expectedPremiumFare.put(3, expectedPremiumFareUserThree);
    }
}