package com.customerrewardprogram;

import java.time.Instant;
import java.util.*;

public class CustomerPointService {

    public static int calculatePointsForTransaction(int purchaseAmount) {

//        int purchaseAmount = 120;
        int totalPoints = 0;
        if (purchaseAmount > 50 && purchaseAmount <= 100) {
            return purchaseAmount - 50;
        } else if (purchaseAmount > 100) {
            return totalPoints + 50 + ((purchaseAmount - 100) * 2);
        }
        return totalPoints;
    }

    public static int calculatePointsForTransactionForMonth(List<CustomerTransaction> customerTransactions) {
        int monthPoints = 0;
        List<CustomerMonthlyPoints> monthlyPointsList = new ArrayList<>();
        Map<String, Integer> customerMonthlyPoints = new HashMap<>();
        Map<String, Integer> customerTotalPoints = new HashMap<>();

        customerTransactions.forEach(customerTransaction -> {
            String customerMonthId = customerTransaction.getCustId() + "-" + customerTransaction.getDate().getMonth();
            customerMonthlyPoints.put(customerMonthId,
                    (customerMonthlyPoints.get(customerMonthId) == null ? 0 : customerMonthlyPoints.get(customerMonthId)) +
                            calculatePointsForTransaction(customerTransaction.getAmount()));
            customerTotalPoints.put(customerTransaction.getCustId(),
                    (customerTotalPoints.get(customerTransaction.getCustId()) == null ? 0 : customerTotalPoints.get(customerTransaction.getCustId())) +
                            calculatePointsForTransaction(customerTransaction.getAmount()));

        });
        customerMonthlyPoints.forEach((s, integer) -> {
            System.out.println("Cust Id: " + s.substring(0, s.indexOf("-")) + " Month: " + s.substring(s.lastIndexOf("-") + 1) + " Points: " + integer);
        });
        System.out.println("--------Total points------");
        customerTotalPoints.forEach((s, integer) -> {
            System.out.println("Cust Id: " + s + " Points: " + integer);
        });
        return monthPoints;
    }

    public static void starter(String[] args) {
//        System.out.println(calculatePointsForTransactionForMonth(120));
        List<CustomerTransaction> customerTransactions = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            CustomerTransaction transaction = new CustomerTransaction();
            transaction.setCustId("" + UUID.randomUUID());
            transaction.setAmount(i * 100);
            transaction.setTransID(i);
            transaction.setDate(Date.from(Instant.now()));
            customerTransactions.add(transaction);
        }
        calculatePointsForTransactionForMonth(customerTransactions);
    }
}
