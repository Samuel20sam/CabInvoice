package com.bridgelabz.cabinvoice;

import java.util.Objects;

public class Invoice {
    double totalFare;
    int numOfRides;
    double avgFare;

    public Invoice(double totalFare, int numOfRides, double avgFare) {
        this.totalFare = totalFare;
        this.numOfRides = numOfRides;
        this.avgFare = avgFare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Double.compare(invoice.totalFare, totalFare) == 0 && numOfRides == invoice.numOfRides && Double.compare(invoice.avgFare, avgFare) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalFare, numOfRides, avgFare);
    }
}