package com.kinvobul.math;

import static org.assertj.core.api.Assertions.*;

public class Main {
    public static void main(String[] args) {
        assertThat(new BigNumber("5348").add(new BigNumber("99")))
                .isEqualTo(new BigNumber("5447"));

        assertThat(new BigNumber("1223").add(new BigNumber("2138778347287428")))
                .isEqualTo(new BigNumber("2138778347288651"));

        assertThat(new BigNumber("9999").add(new BigNumber("9999")))
                .isEqualTo(new BigNumber("19998"));

        assertThat(new BigNumber("5348").add(new BigNumber("+99")))
                .isEqualTo(new BigNumber("+5447"));

        assertThat(new BigNumber("+1223").add(new BigNumber("+2138778347287428")))
                .isEqualTo(new BigNumber("2138778347288651"));

        assertThat(new BigNumber("+9999").add(new BigNumber("9999")))
                .isEqualTo(new BigNumber("19998"));

        assertThat(new BigNumber("9999").subtract(new BigNumber("9999")))
                .isEqualTo(new BigNumber("0"));

        assertThat(new BigNumber("9998").subtract(new BigNumber("9999")))
                .isEqualTo(new BigNumber("-1"));

        assertThat(new BigNumber("99").subtract(new BigNumber("98")))
                .isEqualTo(new BigNumber("1"));

        assertThat(new BigNumber("+9999").subtract(new BigNumber("-1")))
                .isEqualTo(new BigNumber("10000"));

        assertThat(new BigNumber("999").subtract(new BigNumber("+8")))
                .isEqualTo(new BigNumber("991"));

        // two negative
        assertThat(new BigNumber("-9").add(new BigNumber("-8")))
                .isEqualTo(new BigNumber("-17"));

        assertThat(new BigNumber("-8").add(new BigNumber("-9")))
                .isEqualTo(new BigNumber("-17"));

        assertThat(new BigNumber("-8").add(new BigNumber("-8")))
                .isEqualTo(new BigNumber("-16"));

        assertThat(new BigNumber("-92").add(new BigNumber("-8")))
                .isEqualTo(new BigNumber("-100"));

        assertThat(new BigNumber("-9").add(new BigNumber("-81")))
                .isEqualTo(new BigNumber("-90"));

        // first negative second positive
        assertThat(new BigNumber("-9").add(new BigNumber("8")))
                .isEqualTo(new BigNumber("-1"));

        assertThat(new BigNumber("-8").add(new BigNumber("9")))
                .isEqualTo(new BigNumber("1"));

        assertThat(new BigNumber("-8").add(new BigNumber("8")))
                .isEqualTo(new BigNumber("0"));

        assertThat(new BigNumber("-92").add(new BigNumber("8")))
                .isEqualTo(new BigNumber("-84"));

        assertThat(new BigNumber("-9").add(new BigNumber("81")))
                .isEqualTo(new BigNumber("72"));

        //first positive second negative
        assertThat(new BigNumber("9").add(new BigNumber("-8")))
                .isEqualTo(new BigNumber("1"));

        assertThat(new BigNumber("8").add(new BigNumber("-9")))
                .isEqualTo(new BigNumber("-1"));

        assertThat(new BigNumber("8").add(new BigNumber("-8")))
                .isEqualTo(new BigNumber("0"));

        assertThat(new BigNumber("92").add(new BigNumber("-8")))
                .isEqualTo(new BigNumber("84"));

        assertThat(new BigNumber("9").add(new BigNumber("-81")))
                .isEqualTo(new BigNumber("-72"));

        //two positive
        assertThat(new BigNumber("9").add(new BigNumber("8")))
                .isEqualTo(new BigNumber("17"));

        assertThat(new BigNumber("8").add(new BigNumber("9")))
                .isEqualTo(new BigNumber("17"));

        assertThat(new BigNumber("92").add(new BigNumber("8")))
                .isEqualTo(new BigNumber("100"));

        assertThat(new BigNumber("9").add(new BigNumber("81")))
                .isEqualTo(new BigNumber("90"));

        // two negative
        assertThat(new BigNumber("-9").subtract(new BigNumber("-8")))
                .isEqualTo(new BigNumber("-1"));

        assertThat(new BigNumber("-8").subtract(new BigNumber("-9")))
                .isEqualTo(new BigNumber("1"));

        assertThat(new BigNumber("-8").subtract(new BigNumber("-8")))
                .isEqualTo(new BigNumber("0"));

        assertThat(new BigNumber("-92").subtract(new BigNumber("-8")))
                .isEqualTo(new BigNumber("-84"));

        assertThat(new BigNumber("-9").subtract(new BigNumber("-81")))
                .isEqualTo(new BigNumber("72"));

        // first negative second positive
        assertThat(new BigNumber("-9").subtract(new BigNumber("8")))
                .isEqualTo(new BigNumber("-17"));

        assertThat(new BigNumber("-8").subtract(new BigNumber("9")))
                .isEqualTo(new BigNumber("-17"));

        assertThat(new BigNumber("-8").subtract(new BigNumber("8")))
                .isEqualTo(new BigNumber("-16"));

        assertThat(new BigNumber("-92").subtract(new BigNumber("8")))
                .isEqualTo(new BigNumber("-100"));

        assertThat(new BigNumber("-9").subtract(new BigNumber("81")))
                .isEqualTo(new BigNumber("-90"));

        //first positive second negative
        assertThat(new BigNumber("9").subtract(new BigNumber("-8")))
                .isEqualTo(new BigNumber("17"));

        assertThat(new BigNumber("8").subtract(new BigNumber("-9")))
                .isEqualTo(new BigNumber("17"));

        assertThat(new BigNumber("8").subtract(new BigNumber("-8")))
                .isEqualTo(new BigNumber("16"));

        assertThat(new BigNumber("92").subtract(new BigNumber("-8")))
                .isEqualTo(new BigNumber("100"));

        assertThat(new BigNumber("9").subtract(new BigNumber("-81")))
                .isEqualTo(new BigNumber("90"));

        //two positive
        assertThat(new BigNumber("9").subtract(new BigNumber("8")))
                .isEqualTo(new BigNumber("1"));

        assertThat(new BigNumber("8").subtract(new BigNumber("9")))
                .isEqualTo(new BigNumber("-1"));

        assertThat(new BigNumber("92").subtract(new BigNumber("8")))
                .isEqualTo(new BigNumber("84"));

        assertThat(new BigNumber("9").subtract(new BigNumber("81")))
                .isEqualTo(new BigNumber("-72"));

        //power of ten
        assertThat(new BigNumber("1").multiplyByPowerOfTen(1))
                .isEqualTo(new BigNumber("10"));

        assertThat(new BigNumber("-2").multiplyByPowerOfTen(2))
                .isEqualTo(new BigNumber("-200"));

        assertThat(new BigNumber("+123").multiplyByPowerOfTen(4))
                .isEqualTo(new BigNumber("1230000"));

        //multiply
        assertThat(new BigNumber("-23").multiply(new BigNumber("9")))
                .isEqualTo(new BigNumber("-207"));

        assertThat(new BigNumber("99").multiply(new BigNumber("99")))
                .isEqualTo(new BigNumber("9801"));

        assertThat(new BigNumber("99").multiply(new BigNumber("-999")))
                .isEqualTo(new BigNumber("-98901"));

        //increment
        assertThat(new BigNumber("0").increment())
                .isEqualTo(new BigNumber("1"));

        assertThat(new BigNumber("-1").increment())
                .isEqualTo(new BigNumber("0"));

        assertThat(new BigNumber("-20").increment())
                .isEqualTo(new BigNumber("-19"));

        assertThat(new BigNumber("9").increment())
                .isEqualTo(new BigNumber("10"));

        assertThat(new BigNumber("28").increment())
                .isEqualTo(new BigNumber("29"));

        assertThat(new BigNumber("99").increment())
                .isEqualTo(new BigNumber("100"));
    }
}

