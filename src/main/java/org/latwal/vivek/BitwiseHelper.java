package org.latwal.vivek;

public class BitwiseHelper {

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
}

