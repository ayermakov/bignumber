package com.math;
import static org.assertj.core.api.Assertions.*;

public class Main {
    public static void main(String[] args) {
        assertThat(new BigNumber("5348", false).add(new BigNumber("99", false)))
                .isEqualTo(new BigNumber("5447", false));

        assertThat(new BigNumber("1223", false).add(new BigNumber("2138778347287428", false)))
                .isEqualTo(new BigNumber("2138778347288651", false));

        assertThat(new BigNumber("9999", false).add(new BigNumber("9999", false)))
                .isEqualTo(new BigNumber("19998", false));
    }
}
