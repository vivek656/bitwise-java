package org.latwal.vivek;

import java.util.random.RandomGenerator;

public class BitwiseHelper {

    static final int numBitsInInt = 32;
    public static String integerToBitsAsString(int integer) {
        StringBuilder s = new StringBuilder();
        int n = integer;
        while(n > 0) {
            int remainder = n % 2;
            s.insert(0,remainder);
            n >>= 1;
        }
        return s.toString();
    }

    public static int countNumberOfBits(int integer){
        int bitsCounter =  0;
        while((1 << bitsCounter) <= integer){
            bitsCounter++;
        }
        return bitsCounter;
    }

    /**
     * count bit set using Brian Kernighan’s algorithm
     */
    public static int countSetBits(int integer){
        int count  = 0;
        int i = integer;
        while(i > 0){
            i &= (i -1);
            count ++;
        }
        return count;
    }

    public static boolean isEven(int integer){
        return (integer & 1) == 0;
    }

    public static boolean isPowerOfTwo(int integer){
        return (integer & (integer - 1)) == 0;
    }

    public static int binaryStringToInt(String s){
        if(s.length() > 32) {
            throw new IllegalArgumentException("Cannot parse binary string with length greater than 32bits.");
        }
        Long.parseLong(s);
        int res = 0;
        int bitLen = s.length()-1;
        for (int i = 0; i <= bitLen; i++) {
            int intAti = Integer.parseInt(String.valueOf(s.charAt(bitLen - i)));
            assert (intAti <= 1);
            res += (intAti << i );
        }
        return res;
    }

    public static int negativeOf(int integer) {
        return (1 + ~integer);
    }


    public static int[] swap(int a, int b){
        a = a ^ b;
        b = b ^ a;
        a = a ^ b;
        return new int[]{a, b};
    }
    // 11100 k = 2 , a >> 2 == 00111 & 1 = 1 = true
    public static boolean isKPositionSet(int n , int k) {
        return ((n >> k) & 1) == 1;
    }

    public static int firstSetPosition(int n){
        if (n == 0) return -1;
        int pos = 0;
        while ((1 << pos) < n) {
            if((n & (1 << pos)) != 0){
                return pos;
            }
            pos++;
        }
        return pos;
    }


    public static byte parity(int integer) {
        byte result = 0;
        while(integer != 0){
            result ^= 1;
            integer &= (integer-1);
        }
        return result;
    }

    public static int swapBits(int integer , int i , int j){
        assert i <= 32;
        assert j <= 32;
        if(((integer >> i) & 1) != ((integer >> j) & 1)){
            int bitMask = (i << i) | (1 << j);
            integer ^= bitMask;
        }
        return integer;
    }


    public static int reverseBits(int integer){
        int res = 0;
        while(integer!=0){
            int bit = integer & 1;
            res = (res << 1) | bit;
            integer >>= 1;
        }
        return res;
    }

    public static int closestIntSameBitCount(int integer){
        for (int i = 0; i < numBitsInInt -2; i++) {
            if(((integer >>> i) & 1) != (integer >>> (i+1) & 1)){
                integer ^= ((1 << i) | (1 << (i +1)));
                return integer;
            }
        }
        throw new IllegalArgumentException(String.format("All bits in %s are 0 or 1" , integer));
    }

    public static int add (int a, int b){
        int sum = 0, carry = 0, k =1 , tempA = a, tempB = b;
        while(tempA != 0 || tempB != 0){
            int ak  = a & k , bk = b & k;
            int carryOut = (ak & bk) | (ak & carry) | (bk & carry);
            sum |= (ak ^ bk ^ carry);
            carry = carryOut << 1;
            k <<= 1;
            tempA >>>=1;
            tempB >>>= 1;
        }
        return sum | carry;
    }

    public static int multiply(int a, int b){
        int sum = 0;
        while(a != 0){
            if((a&1) != 0){
                sum = add(sum ,b);
            }
            a >>>= 1;
            b <<=1;
        }
        return sum;
    }

    public static int divide(int a, int b){
        int result = 0;
        int power = numBitsInInt;
        long bPower = (long) b << power;
        while(a >= b){
            while(bPower > a){
                bPower >>>= 1;
                --power;
            }
            result += (1 << power);
            a -= (int) bPower;
        }
        return result;
    }

    /**
     * @see <a href="https://leetcode.com/problems/powx-n/solutions/1337794/java-c-simple-o-log-n-easy-faster-than-100-explained/">Leet code explanation</a>
     */
    public static double power(int x, int y){
        double result = 1;
        double xx = x;
        long power = y;
        if(y < 0){
            power = -power;
            xx = (1.0 / x);
        }
        while(power != 0){
            if((power & 1) != 0){
                result *= xx;
            }
            xx *=xx ;
            power >>>=1;
        }
        return result;
    }

    public static int reverse(int integer){
        int result = 0;
        int remaining = Math.abs(integer);
        while (remaining != 0){
            result  = (result*10) + (remaining % 10);
            remaining/=10;
        }
        return integer <0 ? -result : result;
    }


    public static boolean isPalindrome(int x){
        if(x < 0) return false;

        final int numDigits = (int) (Math.floor(Math.log10(x)) +1);
        int msdMask = (int) Math.pow(10,numDigits-1); // most significant digit mask
        for (int i = 0; i < (numDigits / 2); i++) {
            if(x / msdMask != x %10){
                return false;
            }
            x %= msdMask;
            x /= 10;
            msdMask /= 100;
        }
        return true;
    }

    public static int uniformRandom(int lower , int upper){
        int numOfOutcome = upper - lower +1 , result;
        do {
            result = 0;
            for (int i = 0; (1 << i) < numOfOutcome; i++) {
                result = (result << 1) | Math.abs(RandomGenerator.getDefault().nextInt() % 2);
            }
        }while (result >= numOfOutcome);
        return result + lower;
    }

    public static int turnOffRightMostSetBit(int integer) {
        return integer & (integer - 1);
    }

    public static int turnOnRightMostZero(int integer){
        return integer | (integer +1);
    }

    public static int turnOffAllTrailingOne(int integer){
        return integer & (integer + 1);
    }
}

