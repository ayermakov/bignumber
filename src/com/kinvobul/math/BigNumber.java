package com.kinvobul.math;

import java.util.Arrays;

public class BigNumber {
    // BigNumber stores decimal values in reverse order (value 123 is stored as {3,2,1}).
    private short[] value;
    private boolean isNegative;

    public BigNumber(String bigIntInString) {
        bigIntInString = checkAndReturnTheString(bigIntInString);
        init(bigIntInString);
    }

    private BigNumber(short[] newValue) {
        value = newValue;
    }

    private String checkAndReturnTheString(String toCheck) {
        if(toCheck == null || toCheck.length() == 0)
            return "0";
        else
            return toCheck;
    }

    private void init(String bigIntInString) {
        if(bigIntInString.charAt(0) == '-')
            isNegative = true;
        else if(bigIntInString.charAt(0) == '+')
            isNegative = false;
        else
            isNegative = false;

        value = new short[bigIntInString.length()];
        for(int i = value.length - 1, j = 0; i >= 0 && j < bigIntInString.length(); i--, j++) {
            if(j == 0 && (bigIntInString.charAt(j) == '-' || bigIntInString.charAt(j) == '+')) {
                value[i] = 0;
                continue;
            }
            value[i] = Short.parseShort(String.valueOf(bigIntInString.charAt(j)));
        }

        if(value[value.length - 1] == 0)
            value = Arrays.copyOfRange(value, 0, value.length - 1);
    }

    public BigNumber add(BigNumber addedValue) {
        short[] value2 = addedValue.getValue();

        if(isNegative) {
            if (addedValue.isNegative()) {
                if (this.isGreaterThanWithoutSign(addedValue))
                    return calculateAdd(value, value2); //with neg flag
                else
                    return calculateAdd(value2, value); //with neg flag
            } else {
                if (this.isGreaterThanWithoutSign(addedValue))
                    return calculateSubtract(value, value2);//with neg flag
                else
                    return calculateSubtract(value2, value);//with plus flag
            }
        } else {
            if (addedValue.isNegative()) {
                if (this.isGreaterThanWithoutSign(addedValue))
                    return calculateSubtract(value, value2); //with plus flag
                else
                    return calculateSubtract(value2, value); //with neg flag
            } else {
                if (this.isGreaterThanWithoutSign(addedValue))
                    return calculateAdd(value, value2);//with plus flag
                else
                    return calculateAdd(value2, value);//with plus flag
            }
        }
    }

    /**
     * This method adds two big numbers.
     * First argument must be a larger array (number) than the second one.
     *
     * @param value1 is the biggest one
     * @param value2 is the less one
     * @return new BigNumber
     */
    private BigNumber calculateAdd(short[] value1, short[] value2) {
        int j = 0;
        boolean pernose = false;
        short[] result = new short[value1.length];
        for (int i = 0; i < value1.length; i++) {
            int sum;

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

        return new BigNumber(result);
    }

    public BigNumber subtract(BigNumber subtractedValue) {
        short[] value2 = subtractedValue.getValue();

        if(isNegative) {
            if(subtractedValue.isNegative()) {
                if(this.isGreaterThanWithoutSign(subtractedValue))
                    return calculateSubtract(value, value2); //with neg flag
                else
                    return calculateSubtract(value2, value); //with plus flag
            } else {
                if(this.isGreaterThanWithoutSign(subtractedValue))
                    return calculateAdd(value, value2);//with neg flag
                else
                    return calculateAdd(value2, value);//with neg flag
            }
        } else {
            if(subtractedValue.isNegative()) {
                if(this.isGreaterThanWithoutSign(subtractedValue))
                    return calculateAdd(value, value2); //with plus flag
                else
                    return calculateAdd(value2, value); //with plus flag
            } else {
                if(this.isGreaterThanWithoutSign(subtractedValue))
                    return calculateSubtract(value, value2);//with plus flag
                else
                    return calculateSubtract(value2, value);//with neg flag
            }
        }
    }

    private BigNumber calculateSubtract(short[] value1, short[] value2) {
        return this;
    }

    public BigNumber multiply(BigNumber addedValue) {
        return this;
    }

    public BigNumber divide(BigNumber addedValue) {
        return this;
    }

    public short[] getValue() {
        return value;
    }

    public boolean isNegative() {
        return isNegative;
    }

    public boolean isGreaterThan(BigNumber anotherNumber) {
        if(isNegative)
            if(!anotherNumber.isNegative())
                return false;
        else if(anotherNumber.isNegative())
            return true;

        return isGreaterThanWithoutSign(anotherNumber);
    }

    private boolean isGreaterThanWithoutSign(BigNumber anotherNumber) {
        var anotherValue = anotherNumber.getValue();
        return isGreaterThan(anotherValue);
    }

    private boolean isGreaterThan(short[] anotherValue) {
        if(value.length > anotherValue.length)
            return true;
        else if(anotherValue.length > value.length)
            return false;

        for(int i = value.length - 1; i >= 0; i--) {
            if(value[i] > anotherValue[i])
                return true;
            else if(anotherValue[i] > value[i])
                return false;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(isNegative ? "-" : "");
        for(int i = value.length - 1; i >= 0; i--) {
            result.append(value[i]);
        }

        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        BigNumber bigInt = (BigNumber) o;

        if(isNegative != bigInt.isNegative())
            return false;

        var values = bigInt.getValue();

        if(value.length != values.length)
            return false;

        for(int i = 0; i < value.length; i++)
            if(value[i] != values[i])
                return false;

        return true;
    }

    @Override
    public int hashCode() {
        int sum = 0;
        for(int i = 0; i < value.length; ++i)
            sum += (int) value[i];
        return sum;
    }
}
