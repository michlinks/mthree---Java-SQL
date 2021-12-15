/*
 * @michaelalinks
 * */

package com.company.dto;

public enum Coins {
    POUND(100),FIFTY(50),TWENTY(20),TEN(10),FIVE(5),TWO(2),ONE(1),
    TWOEURO(200);

    private int value;

    Coins(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
