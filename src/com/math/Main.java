package com.math;

public class Main {
    public static void main(String[] args) {
	    BigInt decimal = new BigInt("5348");
        System.out.println(decimal);

        System.out.println(new BigInt("5348").add(new BigInt("99")));

        System.out.println(new BigInt("2138778347287428").add(new BigInt("1223")));

        System.out.println(new BigInt("9999").add(new BigInt("9999")));
    }
}
