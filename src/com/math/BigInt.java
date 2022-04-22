package com.math;

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

    public BigInt add(BigInt addedValue) {
        short[] value2 = addedValue.getValue();
        int j = 0;
        if(value.length >= value2.length) {
            for (int i = 0; i < value.length; i++) {
                int sum = value[i] + value2[j++];
            }
        } else {

        }

        return this;
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
