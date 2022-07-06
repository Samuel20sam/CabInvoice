package com.bridgelabz.cabinvoice;

import java.util.LinkedHashMap;
import java.util.Map;

public class CabInvoice {

        int costPerMin;
        double minCostPerKm;
        double minFare;

    public double calculateFare(double distance, int time) {
        double totalFare = distance * minCostPerKm + time * costPerMin;
        return Math.max(totalFare, minFare);
    }

    public double calculateFare(Ride[] rides){
        double  totalFare = 0;
        for (Ride ride : rides){
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return totalFare;
    }

    public Invoice generateInvoice(Ride[] rides){
        double totalFare = calculateFare(rides);
        int numOfRides = rides.length;
        double avgFare = totalFare/numOfRides;

        return new Invoice (totalFare,numOfRides, avgFare);
    }

    public Invoice generateUserBasedInvoice(int userId) {
        Map<Integer, Ride[]> userMap = new LinkedHashMap<>();

        Ride[] userOne = {new Ride(2.0, 5), new Ride(0.1, 1), new Ride(20.5,35)};
        Ride[] userTwo = {new Ride(5.0, 9), new Ride(1, 1), new Ride(15.0,20)};
        Ride[] userThree = {new Ride(8.0, 10), new Ride(3, 10), new Ride(10.0,10)};
        userMap.put(1, userOne);
        userMap.put(2, userTwo);
        userMap.put(3, userThree);
        
        for (Map.Entry<Integer, Ride[]> entry : userMap.entrySet()) {
            if (userId == entry.getKey()) {
                Ride[] userRides = entry.getValue();
                return generateInvoice(userRides);
            }
        }
        return null;
    }
}