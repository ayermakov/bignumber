package com.math;

import java.util.Arrays;

public class BigInt {
    // bigInt stores decimal values in ASC order (value 123 is stored as {3,2,1}).
    private short[] value;
    private StringBuffer lastResult = new StringBuffer();
    private int indexOfNextPart;
    private int indexOfHasNextDigit = 0;

    public BigInt(String bigIntInString) {
        value = new short[bigIntInString.length()];
        for(int i = value.length - 1, j = 0; i >= 0 && j < bigIntInString.length(); i--, j++) {
            value[i] = Short.parseShort(String.valueOf(bigIntInString.charAt(j)));
        }

        indexOfNextPart = value.length - 1;
    }

    private BigInt(short[] newValue) {
        value = newValue;
    }

    public BigInt add(BigInt addedValue) {
        short[] value2 = addedValue.getValue();

        if(value.length >= value2.length)
            return calculateAdd(value, value2);
        else
            return calculateAdd(value2, value);
    }

    /**
     * This method add two big numbers.
     * First argument must be a larger array than the second one.
     *
     * @param value1
     * @param value2
     * @return
     */
    private BigInt calculateAdd(short[] value1, short[] value2) {
        int j = 0;
        boolean pernose = false;
        short[] result = new short[value1.length];
        for (int i = 0; i < value1.length; i++) {
            int sum = 0;

            if(j >= value2.length)
                sum = value1[i];
            else
                sum = value1[i] + value2[j++];

            if(pernose)
                sum++;

            if(sum >= 10) {
                sum %= 10;
                pernose = true;
            } else
                pernose = false;

            result[i] = (short) sum;
        }

        if(pernose) {
            result = Arrays.copyOf(result, result.length + 1);
            result[result.length - 1] = 1;
        }


        return new BigInt(result);
    }

    public BigInt distract(BigInt addedValue) {
        return this;
    }

    public BigInt multiply(BigInt addedValue) {
        return this;
    }

    public BigInt divide(BigInt addedValue) {
        boolean completed = false;
        lastResult = new StringBuffer();
        while(!completed) {

            completed = true;
        }

        return this;
    }

    /**public int getNextPart() {

    }**/

    public String getLastResult() {
        return lastResult.toString();
    }

    public short[] getValue() {
        return value;
    }

    public boolean hasNext() {
        if(indexOfHasNextDigit < value.length)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        for(int i = value.length - 1; i >= 0; i--) {
            result.append(value[i]);
        }

        return result.toString();
    }
}
