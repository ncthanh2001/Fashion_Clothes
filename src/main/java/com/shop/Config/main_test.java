package com.shop.Config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class main_test {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Jane");
        Optional<List<String>> listOptional = Optional.of(names);
        List<String> nameList = listOptional.get();
        System.out.println("Names: " +  listOptional.get().get(1));
    }
}
