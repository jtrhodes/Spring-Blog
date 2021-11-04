package com.codeup.springblog.models;

public class Dice {
    private int d100;
    private int d20;
    private int d12;
    private int d10;
    private int d8;
    private int d6;
    private int d4;
public Dice(){

}
    public Dice(int d100, int d20, int d12, int d10, int d8, int d6, int d4) {
        this.d100 = d100;
        this.d20 = d20;
        this.d12 = d12;
        this.d10 = d10;
        this.d8 = d8;
        this.d6 = d6;
        this.d4 = d4;
    }

    public int getD100() {
        return d100;
    }

    public void setD100(int d100) {
        this.d100 = d100;
    }

    public int getD20() {
        return d20;
    }

    public void setD20(int d20) {
        this.d20 = d20;
    }

    public int getD12() {
        return d12;
    }

    public void setD12(int d12) {
        this.d12 = d12;
    }

    public int getD10() {
        return d10;
    }

    public void setD10(int d10) {
        this.d10 = d10;
    }

    public int getD8() {
        return d8;
    }

    public void setD8(int d8) {
        this.d8 = d8;
    }

    public int getD6() {
        return d6;
    }

    public void setD6(int d6) {
        this.d6 = d6;
    }

    public int getD4() {
        return d4;
    }

    public void setD4(int d4) {
        this.d4 = d4;
    }
}
