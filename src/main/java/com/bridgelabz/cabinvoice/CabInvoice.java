package com.bridgelabz.cabinvoice;

public class CabInvoice {
    final int COST_PER_MIN = 1;
    final double MIN_COST_PER_KM = 10;
    final double MIN_FARE = 5;
    public double calculateFare(double distance, int time) {
        double totalFare = distance * MIN_COST_PER_KM + time * COST_PER_MIN;
        return Math.max(totalFare, MIN_FARE);
    }
    public double calculateFare(Ride[] rides){
        double  totalFare = 0;
        for (Ride ride : rides){
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return totalFare;
    }

    public Invoice generateInvoice(Ride[] rides){
        double  totalFare = 0;
        int numOfRides = 0;
        double avgFare = 0;

        for (Ride ride : rides){
            totalFare += this.calculateFare(ride.distance, ride.time);
            numOfRides = rides.length;
            avgFare = totalFare/numOfRides;
        }
        return new Invoice (totalFare,numOfRides, avgFare);
    }
}