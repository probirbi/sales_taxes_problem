package com.itemis;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class PrintReceiptTest {

    @Test
    public void testGetReceiptSingleInput() {
        PrintReceipt inputOutput= new PrintReceipt();

        String[] items = new String[1];
        items[0]="1 book at 12.49";


        String output = inputOutput.getReceipt(items);
        assertEquals("1 book: 12.49\n" + "Sales Tax: 0.00\n" + "Total Amount: 12.49", output);

    }

    @Test
    public void testGetReceiptMultipleInput() {
        PrintReceipt inputOutput= new PrintReceipt();

        String[] items = new String[3];
        items[0]="1 book at 12.49";
        items[1]="1 music CD at 14.99";
        items[2]="1 chocolate bar at 0.85";

        String output = inputOutput.getReceipt(items);
        assertEquals("1 book: 12.49\n" + "1 music CD: 16.49\n" +
                "1 chocolate bar: 0.85\n" +
                "Sales Tax: 1.50\n" +
                "Total Amount: 29.83", output);

    }
}