package com.kinvobul.math;

import java.util.Arrays;

public class BigNumber {
    // BigNumber stores decimal values in reverse order (value 123 is stored as {3,2,1}).
    private short[] value;
    private boolean isNegative;
    private short[] floatingPart;
    private boolean hasFloatingPart;

    public BigNumber(String bigIntInString) {
        bigIntInString = checkAndReturnTheString(bigIntInString);
        init(bigIntInString);
    }

    private BigNumber(short[] newValue) {
        this.value = newValue;
        this.isNegative = false;
        this.hasFloatingPart = false;
    }

    private BigNumber(short[] newValue, short[] newFloatingPart) {
        this.value = newValue;
        this.isNegative = false;
        this.floatingPart = newFloatingPart;
        this.hasFloatingPart = true;
    }

    private BigNumber(short[] newValue, boolean signNegative) {
        this.value = newValue;
        this.isNegative = signNegative;
        this.hasFloatingPart = false;
    }

    private BigNumber(short[] newValue, short[] newFloatingPart, boolean signNegative) {
        this.value = newValue;
        this.isNegative = signNegative;
        this.floatingPart = newFloatingPart;
        this.hasFloatingPart = true;
    }

    public BigNumber add(BigNumber addedValue) {
        short[] value2 = addedValue.getValue();
        short[] value2floating = addedValue.getFloatingPart();

        if(this.isNegative) {
            if (addedValue.isNegative()) {
                if (this.isGreaterThanWithoutSign(addedValue))
                    return calculateAdd(this.value, value2, this.floatingPart, value2floating, true); //with neg flag
                else
                    return calculateAdd(value2, this.value, value2floating, this.floatingPart, true); //with neg flag
            } else {
                if (this.isGreaterThanWithoutSign(addedValue))
                    return calculateSubtract(this.value, value2, this.floatingPart, value2floating,true);//with neg flag
                else
                    return calculateSubtract(value2, this.value, value2floating, this.floatingPart, false);//with plus flag
            }
        } else {
            if (addedValue.isNegative()) {
                if (this.isGreaterThanWithoutSign(addedValue))
                    return calculateSubtract(this.value, value2, this.floatingPart, value2floating, false); //with plus flag
                else
                    return calculateSubtract(value2, this.value, value2floating, this.floatingPart, true); //with neg flag
            } else {
                if (this.isGreaterThanWithoutSign(addedValue))
                    return calculateAdd(this.value, value2, this.floatingPart, value2floating, false);//with plus flag
                else
                    return calculateAdd(value2, this.value, value2floating, this.floatingPart, false);//with plus flag
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
    private BigNumber calculateAdd(short[] value1, short[] value2, short[] value1floating, short[] value2floating, boolean signNegative) {
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

        this.value = result;
        this.isNegative = signNegative;
        return this;
    }

    public BigNumber subtract(BigNumber subtractedValue) {
        short[] value2 = subtractedValue.getValue();
        short[] value2floating = subtractedValue.getFloatingPart();

        if(this.isNegative) {
            if(subtractedValue.isNegative()) {
                if(this.isGreaterThanWithoutSign(subtractedValue))
                    return calculateSubtract(this.value, value2, this.floatingPart, value2floating,true); //with neg flag
                else
                    return calculateSubtract(value2, this.value, value2floating, this.floatingPart,false); //with plus flag
            } else {
                if(this.isGreaterThanWithoutSign(subtractedValue))
                    return calculateAdd(this.value, value2, this.floatingPart, value2floating,true);//with neg flag
                else
                    return calculateAdd(value2, this.value, value2floating, this.floatingPart,true);//with neg flag
            }
        } else {
            if(subtractedValue.isNegative()) {
                if(this.isGreaterThanWithoutSign(subtractedValue))
                    return calculateAdd(this.value, value2, this.floatingPart, value2floating,false); //with plus flag
                else
                    return calculateAdd(value2, this.value, value2floating, this.floatingPart,false); //with plus flag
            } else {
                if(this.isGreaterThanWithoutSign(subtractedValue))
                    return calculateSubtract(this.value, value2, this.floatingPart, value2floating,false);//with plus flag
                else
                    return calculateSubtract(value2, this.value, value2floating, this.floatingPart,true);//with neg flag
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
    private BigNumber calculateSubtract(short[] value1, short[] value2, short[] value1floating, short[] value2floating, boolean signNegative) {
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

        this.value = result;
        this.isNegative = signNegative;
        return this;
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
            for(int j = 0; j < this.value.length; j++) {
                int one = this.value[j] * value2[i];
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
        return this.value;
    }

    public short[] getFloatingPart() {
        return this.floatingPart;
    }

    public boolean isNegative() {
        return this.isNegative;
    }

    public boolean hasFloatingPart() {
        return this.hasFloatingPart;
    }

    public void setNegative(boolean isNegative) {
        this.isNegative = isNegative;
    }

    public boolean isGreaterThan(BigNumber anotherNumber) {
        if(this.isNegative) {
            if (!anotherNumber.isNegative())
                return false;
        } else if(anotherNumber.isNegative())
            return true;

        return isGreaterThanWithoutSign(anotherNumber);
    }

    public boolean isLessThan(int i) {
        if(this.isNegative) {
            if(i >= 0)
                return true;
        } else if(i < 0)
            return false;

        BigNumber than = new BigNumber(String.valueOf(i));
        return isLessThanWithoutSign(than);
    }

    @Override
    public String toString() {
        if(this.value[0] == 0 && !hasFloatingPart())
            this.isNegative = false;

        StringBuilder result = new StringBuilder(this.isNegative ? "-" : "");
        for(int i = this.value.length - 1; i >= 0; i--)
            result.append(this.value[i]);

        if(this.hasFloatingPart) {
            result.append('.');
            for(int i = this.floatingPart.length - 1; i >= 0; i--)
                result.append(this.floatingPart[i]);
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

        if(this.isNegative() != bigInt.isNegative())
            return false;

        if(    ( this.hasFloatingPart() && !bigInt.hasFloatingPart() )
            || (!this.hasFloatingPart() && bigInt.hasFloatingPart()  ) )
                return false;

        short[] values = bigInt.getValue();
        if(this.value.length != values.length)
            return false;

        for(int i = 0; i < this.value.length; i++)
            if(this.value[i] != values[i])
                return false;

        if(this.hasFloatingPart() && bigInt.hasFloatingPart()) {
            short[] floating = bigInt.getFloatingPart();
            if (this.floatingPart.length != floating.length)
                return false;

            for (int i = 0; i < this.floatingPart.length; i++) {
                if (this.floatingPart[i] != floating[i])
                    return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int sum = 0;
        for (short element : this.value)
            sum += (int) element;

        if(this.hasFloatingPart)
            for (short item : this.floatingPart)
                sum += (int) item;

        return sum;
    }

    private String checkAndReturnTheString(String toCheck) {
        if(toCheck == null || toCheck.length() == 0)
            return "0";
        else
            return toCheck;
    }

    private void init(String bigIntInString) {
        if(bigIntInString.charAt(0) == '-') {
            this.isNegative = true;
            bigIntInString = String.copyValueOf(bigIntInString.toCharArray(), 1, bigIntInString.length() - 1);
        } else if(bigIntInString.charAt(0) == '+') {
            this.isNegative = false;
            bigIntInString = String.copyValueOf(bigIntInString.toCharArray(), 1, bigIntInString.length() - 1);
        } else
            this.isNegative = false;

        boolean dotFound = false;
        int dotIndex = 0;
        for(int i = 0; i < bigIntInString.length(); i++) {
            switch(bigIntInString.charAt(i)) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    break;
                case '+':
                    throw new IllegalArgumentException("Plus sign found in wrong place with index " + i + ".");
                case '-':
                    throw new IllegalArgumentException("Minus sign found in wrong place with index " + i + ".");
                case '.':
                    if(dotFound)
                        throw new IllegalArgumentException("Found more than a one dot.");
                    dotFound = true;
                    this.hasFloatingPart = true;
                    dotIndex = i;
                    break;
                default:
                    throw new IllegalArgumentException("Found wrong symbol " + bigIntInString.charAt(i) + ".");
            }
        }

        if(dotFound) {
            if (dotIndex == 0)
                this.value = new short[1];
            else
                this.value = new short[dotIndex];
        } else
            this.value = new short[bigIntInString.length()];

        for(int i = this.value.length - 1, j = 0; i >= 0 && j < bigIntInString.length(); i--, j++) {
            if(j == 0 && bigIntInString.charAt(j) == '.') {
                this.value[i] = 0;
                break;
            }

            if(bigIntInString.charAt(j) == '.')
                break;

            this.value[i] = Short.parseShort(String.valueOf(bigIntInString.charAt(j)));
        }

        if(dotFound)
            this.floatingPart = new short[bigIntInString.length() - dotIndex - 1];
        else
            this.floatingPart = new short[]{0};

        for(int i = dotIndex + 1, j = this.floatingPart.length - 1; i < bigIntInString.length() && j >= 0; i++, j--)
            this.floatingPart[j] = Short.parseShort(String.valueOf(bigIntInString.charAt(i)));
    }

    private boolean isGreaterThanWithoutSign(BigNumber anotherNumber) {
        return isGreaterThan(anotherNumber.getValue());
    }

    private boolean isLessThanWithoutSign(BigNumber anotherNumber) {
        return isLessThan(anotherNumber.getValue());
    }

    private boolean isGreaterThan(short[] anotherValue) {
        if(this.value.length > anotherValue.length)
            return true;
        else if(anotherValue.length > this.value.length)
            return false;

        for(int i = this.value.length - 1; i >= 0; i--) {
            if(this.value[i] > anotherValue[i])
                return true;
            else if(anotherValue[i] > this.value[i])
                return false;
        }

        return false;
    }

    private boolean isLessThan(short[] anotherValue) {
        if(this.value.length > anotherValue.length)
            return false;
        else if(anotherValue.length > this.value.length)
            return true;

        for(int i = this.value.length - 1; i >= 0; i--) {
            if(this.value[i] > anotherValue[i])
                return false;
            else if(anotherValue[i] > value[i])
                return true;
        }

        return false;
    }

    private void append(int times, short toAppend) {
        final int newLength = this.value.length + times;
        short[] result = new short[newLength];

        for(int i = 0; i < times; i++)
            result[i] = toAppend;

        for(int i = 0; i < this.value.length; i++)
            result[i + times] = this.value[i];

        this.value = result;
    }

    private String append(String value, int times, short toAppend) {
        StringBuilder builder = new StringBuilder(value);
        for(int i = 0; i < times; i++)
            builder.append(toAppend);

        return builder.toString();
    }

    private String append(String value, BigNumber times, short toAppend) {
        StringBuilder builder = new StringBuilder(value);
        for(BigNumber i = new BigNumber("0"); i.isLessThanWithoutSign(times); i.increment())
            builder.append(toAppend);

        return builder.toString();
    }
}
