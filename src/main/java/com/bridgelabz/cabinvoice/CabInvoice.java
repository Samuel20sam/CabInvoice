package com.bridgelabz.cabinvoice;

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
        RideRepo rp = new RideRepo();
        for (Map.Entry<Integer, Ride[]> entry : rp.userMap.entrySet()) {
            if (userId == entry.getKey()) {
                Ride[] userRides = entry.getValue();
                return generateInvoice(userRides);
            }
        }
        return null;
    }
}