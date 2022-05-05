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
    }
}
