package com.shop.Controller;

import java.time.LocalDate;

public class main_test {
    public static void main(String[] args) {
        LocalDate date =  LocalDate.now();
        System.out.println(date);

        System.out.println(date instanceof LocalDate);
    }
}
