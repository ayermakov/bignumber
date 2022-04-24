package com.math;
import static org.assertj.core.api.Assertions.*;

public class Main {
    public static void main(String[] args) {
        assertThat(new BigInt("5348").add(new BigInt("99")).toString())
                .isEqualTo(new BigInt("5447").toString());

        assertThat(new BigInt("1223").add(new BigInt("2138778347287428")).toString())
                .isEqualTo(new BigInt("2138778347288651").toString());

        assertThat(new BigInt("9999").add(new BigInt("9999")).toString())
                .isEqualTo(new BigInt("19998").toString());
    }
}
