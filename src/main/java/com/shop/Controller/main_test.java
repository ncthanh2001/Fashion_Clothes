package com.shop.Controller;

import com.shop.Constant.Infor;

import java.time.LocalDate;

public class main_test {
    public static void main(String[] args) {
        LocalDate date =  LocalDate.now();
        System.out.println(date);

        System.out.println(date instanceof LocalDate);

        System.out.println("pageID: " + Infor.REGISTER.getContent());
        System.out.println("pageTitle: " + Infor.REGISTER.getTittle());
//        System.out.println("pageURL: " + Infor.PAGE_001.getPageURL());
    }
}
