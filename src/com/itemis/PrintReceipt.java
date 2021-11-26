package com.itemis;

import java.text.DecimalFormat;
import java.util.Scanner;

public class PrintReceipt {
    private final DecimalFormat df = new DecimalFormat("0.00");
    private final Integer salesTaxPercentage = 10;
    private final Integer importedTaxPercentage = 5;

    private Double totalTaxAmount = 0.0;
    private Double totalAmount = 0.0;

    private final String exemptedItems = "books, chocolates, pills";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the number of items you want to enter: ");

        int numberOfInputs = sc.nextInt();

        String[] items = new String[numberOfInputs];

        sc.nextLine();
        for (int i = 0; i < items.length; i++) {
            items[i] = sc.nextLine();
        }

        System.out.println("\n The inputs is: ");

        for (String str : items) {
            System.out.println(str);
        }

        PrintReceipt pr = new PrintReceipt();
        //pr.getReceipt(items);
        System.out.println("\nThe output is: ");

        System.out.println(pr.getReceipt(items));
    }

    public String getReceipt(String[] items) {

        String output = "";
        for (String item : items) {
            if (output.equals("")) {
                output = calculatePrice(item);
            } else {
                output = output + "\n" + calculatePrice(item);
            }
            //    System.out.println(output);
        }
        // totalTaxAmount = Math.round(totalTaxAmount * 20) / 20d;
        // totalAmount = Math.round(totalAmount * 20) / 20d;
        output = output + "\nSales Tax: " + df.format(totalTaxAmount);
        output = output + "\nTotal Amount: " + df.format(totalAmount);
        //System.out.println("Sales Tax: " + df.format(totalTaxAmount));
        //System.out.println("Total Amount: " + df.format(totalAmount));

        return output;

    }

    private String calculatePrice(String item) {

        String output = "";
        if (item.indexOf("at") == -1) {
            output = "Input string bad format";
        } else {
            try {
                String[] itemDetails = item.split(" ");
                Integer quantity = Integer.parseInt(itemDetails[0]);
                Double price = Double.parseDouble(itemDetails[itemDetails.length - 1]);
                String itemName = "";
                Double salesTax = 0.0;
                Double importedTax = 0.0;
                Double calculatedPrice = 0.0;
                for (int i = 1; i < itemDetails.length - 2; i++) {
                    if (itemName.equals("")) {
                        itemName = itemDetails[i];
                    } else {
                        itemName = itemName + " " + itemDetails[i];
                    }
                }
                boolean isImported = item.contains("imported");
                if (isImported) {
                    itemName = itemName.replace("imported", "").trim();
                }
                boolean isExemptedItem = checkExemptedItem(exemptedItems, itemName);
                // System.out.println("Quantity is " + quantity);
                // System.out.println("Price is " + price);
                // System.out.println("Item name is " + itemName);
                if (isExemptedItem) {
                    if (isImported) {
                        importedTax = (quantity * price * importedTaxPercentage / 100);
                        totalAmount = totalAmount + quantity * price + importedTax;
                        // System.out.println(totalAmount);
                        calculatedPrice = quantity * price + importedTax;
                    } else {
                        totalAmount = totalAmount + quantity * price;
                        // System.out.println(totalAmount);
                        calculatedPrice = quantity * price;
                    }
                } else {
                    if (isImported) {
                        salesTax = (quantity * price * salesTaxPercentage / 100);
                        // System.out.println(salesTax);
                        importedTax = (quantity * price * importedTaxPercentage / 100);
                        // System.out.println(importedTax);
                        totalTaxAmount = totalTaxAmount + salesTax + importedTax;
                        // System.out.println(totalTaxAmount);
                        totalAmount = totalAmount + (quantity * price) + salesTax + importedTax;
                        // System.out.println(totalAmount);
                        calculatedPrice = (quantity * price) + salesTax + importedTax;
                    } else {
                        salesTax = (quantity * price * salesTaxPercentage / 100);
                        // System.out.println(salesTax);
                        totalTaxAmount = totalTaxAmount + salesTax;
                        // System.out.println(totalTaxAmount);
                        totalAmount = totalAmount + (quantity * price) + salesTax;
                        // System.out.println(totalAmount);
                        calculatedPrice = (quantity * price) + salesTax;
                    }
                }
                // System.out.println("Calculated Price is " + calculatedPrice);
                if (isImported) {
                    output = quantity + " imported " + itemName + ": " + df.format(calculatedPrice);
                } else {
                    output = quantity + " " + itemName + ": " + df.format(calculatedPrice);
                }

            } catch (Exception e) {
                e.printStackTrace();
                output = "Input string bad format";
            }
        }
        return output;

    }

    private boolean checkExemptedItem(String exemptedItems, String itemName) {
        boolean found = false;
        String[] itemNames = itemName.split(" ");
        for (String str : itemNames) {
            found = exemptedItems.contains(str);
            if (found) {
                return found;
            }
        }
        return found;
    }
}

