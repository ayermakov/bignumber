package com.math;
import static org.assertj.core.api.Assertions.*;

public class Main {
    public static void main(String[] args) {
        assertThat(new BigInt("5348").add(new BigInt("99")))
                .isEqualTo(new BigInt("5447"));

        assertThat(new BigInt("1223").add(new BigInt("2138778347287428")))
                .isEqualTo(new BigInt("2138778347288651"));

        assertThat(new BigInt("9999").add(new BigInt("9999")))
                .isEqualTo(new BigInt("19998"));
    }
}
