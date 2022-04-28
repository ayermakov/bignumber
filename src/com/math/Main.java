package com.math;
import static org.assertj.core.api.Assertions.*;

public class Main {
    public static void main(String[] args) {
        assertThat(new BigInt("5348", false).add(new BigInt("99", false)))
                .isEqualTo(new BigInt("5447", false));

        assertThat(new BigInt("1223", false).add(new BigInt("2138778347287428", false)))
                .isEqualTo(new BigInt("2138778347288651", false));

        assertThat(new BigInt("9999", false).add(new BigInt("9999", false)))
                .isEqualTo(new BigInt("19998", false));
    }
}
