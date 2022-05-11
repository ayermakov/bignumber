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
        isNegative = false;
    }

    private BigNumber(short[] newValue, boolean signNegative) {
        value = newValue;
        isNegative = signNegative;
    }

    public BigNumber add(BigNumber addedValue) {
        short[] value2 = addedValue.getValue();

        if(isNegative) {
            if (addedValue.isNegative()) {
                if (this.isGreaterThanWithoutSign(addedValue))
                    return calculateAdd(value, value2, true); //with neg flag
                else
                    return calculateAdd(value2, value, true); //with neg flag
            } else {
                if (this.isGreaterThanWithoutSign(addedValue))
                    return calculateSubtract(value, value2, true);//with neg flag
                else
                    return calculateSubtract(value2, value, false);//with plus flag
            }
        } else {
            if (addedValue.isNegative()) {
                if (this.isGreaterThanWithoutSign(addedValue))
                    return calculateSubtract(value, value2, false); //with plus flag
                else
                    return calculateSubtract(value2, value, true); //with neg flag
            } else {
                if (this.isGreaterThanWithoutSign(addedValue))
                    return calculateAdd(value, value2, false);//with plus flag
                else
                    return calculateAdd(value2, value, false);//with plus flag
            }
        }
    }

    /**
     * This method adds two big numbers.
     * First argument must be a larger array (number) than the second one.
     *
     * @param value1 is the biggest one
     * @param value2 is the less one
     * @param signNegative is negative or not
     * @return new BigNumber
     */
    private BigNumber calculateAdd(short[] value1, short[] value2, boolean signNegative) {
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

        value = result;
        isNegative = signNegative;
        return this;
    }

    public BigNumber subtract(BigNumber subtractedValue) {
        short[] value2 = subtractedValue.getValue();

        if(isNegative) {
            if(subtractedValue.isNegative()) {
                if(this.isGreaterThanWithoutSign(subtractedValue))
                    return calculateSubtract(value, value2, true); //with neg flag
                else
                    return calculateSubtract(value2, value, false); //with plus flag
            } else {
                if(this.isGreaterThanWithoutSign(subtractedValue))
                    return calculateAdd(value, value2, true);//with neg flag
                else
                    return calculateAdd(value2, value, true);//with neg flag
            }
        } else {
            if(subtractedValue.isNegative()) {
                if(this.isGreaterThanWithoutSign(subtractedValue))
                    return calculateAdd(value, value2, false); //with plus flag
                else
                    return calculateAdd(value2, value, false); //with plus flag
            } else {
                if(this.isGreaterThanWithoutSign(subtractedValue))
                    return calculateSubtract(value, value2, false);//with plus flag
                else
                    return calculateSubtract(value2, value, true);//with neg flag
            }
        }
    }

    /**
     * This method subtracts two big numbers.
     * First argument must be a larger array (number) than the second one.
     *
     * @param value1 is the biggest one
     * @param value2 is the less one
     * @param signNegative is negative or not
     * @return new BigNumber
     */
    private BigNumber calculateSubtract(short[] value1, short[] value2, boolean signNegative) {
        short[] result = new short[value1.length];
        boolean pernose = false;
        for(int i = 0, j = 0; i < value1.length; i++, j++) {
            short primaryNumber = value1[i];
            short secondNumber = j < value2.length ? value2[j] : 0;

            if(pernose) {
                primaryNumber--;
                pernose = false;
            }

            if(primaryNumber < secondNumber) {
                primaryNumber += 10;
                pernose = true;
            }

            result[i] = (short) (primaryNumber - secondNumber);
        }

        int indexOfZeros = result.length - 1;
        for(int i = result.length - 1; i >= 0; i--) {
            if(result[i] == 0 && i != 0)
                indexOfZeros--;
            else
                break;
        }

        if(indexOfZeros < result.length - 1)
            result = Arrays.copyOf(result, indexOfZeros + 1);

        if(result[0] == 0)
            signNegative = false;

        return new BigNumber(result, signNegative);
    }

    /**
     * Implements a school method of multiplication.
     *
     * @param addedValue
     * @return
     */
    public BigNumber multiply(BigNumber addedValue) {
        BigNumber sum = new BigNumber("0");
        short[] value2 = addedValue.getValue();

        for(int i = 0; i < value2.length; i++) {
            for(int j = 0; j < value.length; j++) {
                int one = value[j] * value2[i];
                String two = String.valueOf(one);
                String three = append(two, new BigNumber(String.valueOf(j)).add(new BigNumber(String.valueOf(i))), (short) 0);
                sum = sum.add(new BigNumber(three));
            }
        }

        boolean flag;
        if(isNegative()) {
            if(addedValue.isNegative())
                flag = false;
            else
                flag = true;
        } else {
            if(addedValue.isNegative())
                flag = true;
            else
                flag = false;
        }

        sum.setNegative(flag);

        return sum;
    }

    public BigNumber increment() {
        return add(new BigNumber("1"));
    }

    public BigNumber multiplyByPowerOfTen(int power) {
        append(power, (short) 0);
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

    public void setNegative(boolean isNegative) {
        this.isNegative = isNegative;
    }

    public boolean isGreaterThan(BigNumber anotherNumber) {
        if(isNegative) {
            if (!anotherNumber.isNegative())
                return false;
        } else if(anotherNumber.isNegative())
            return true;

        return isGreaterThanWithoutSign(anotherNumber);
    }

    public boolean isLessThan(BigNumber anotherNumber) {
        if(isNegative) {
            if (!anotherNumber.isNegative())
                return true;
        } else if(anotherNumber.isNegative())
            return false;

        return isLessThan(anotherNumber.getValue());
    }

    @Override
    public String toString() {
        if(value[0] == 0)
            isNegative = false;

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

        short[] values = bigInt.getValue();

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

        if(value[value.length - 1] == 0 && value.length > 1)
            value = Arrays.copyOfRange(value, 0, value.length - 1);
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

    private boolean isLessThan(int i) {
        BigNumber than = new BigNumber(String.valueOf(i));
        return isLessThan(than.getValue());
    }

    private boolean isLessThan(short[] anotherValue) {
        if(value.length > anotherValue.length)
            return false;
        else if(anotherValue.length > value.length)
            return true;

        for(int i = value.length - 1; i >= 0; i--) {
            if(value[i] > anotherValue[i])
                return false;
            else if(anotherValue[i] > value[i])
                return true;
        }

        return false;
    }

    private void append(int times, short toAppend) {
        final int newLength = value.length + times;
        short[] result = new short[newLength];

        for(int i = 0; i < times; i++)
            result[i] = toAppend;

        for(int i = 0; i < value.length; i++)
            result[i + times] = value[i];

        value = result;
    }

    private String append(String value, int times, short toAppend) {
        StringBuilder builder = new StringBuilder(value);
        for(int i = 0; i < times; i++) {
            builder.append(toAppend);
        }
        return builder.toString();
    }

    private String append(String value, BigNumber times, short toAppend) {
        StringBuilder builder = new StringBuilder(value);
        for(BigNumber i = new BigNumber("0"); i.isLessThan(times); i.increment()) {
            builder.append(toAppend);
        }
        return builder.toString();
    }
}
