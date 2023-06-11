package com.shop.Config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class entity {
    private int gia;
    private int soluong;

    private  int total;
    private int cacaltotal(){
        this.total = this.gia * this.soluong;
        return this.total;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
        cacaltotal();
    }

    public void setGia(int gia) {
        this.gia = gia;
        cacaltotal();
    }

    public entity(int gia, int soluong) {
        this.gia = gia;
        this.soluong = soluong;
        cacaltotal();
    }
}
