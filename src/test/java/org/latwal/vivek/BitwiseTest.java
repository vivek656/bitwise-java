package org.latwal.vivek;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class BitwiseTest {

    private final Logger logger = Logger.getLogger(BitwiseTest.class.getName());

    @Test
    void bitwise_integer_to_bits_test(){
        int i = 125;
        String integerAsBits = BitwiseHelper.integerToBitsAsString(i);
        logger.info(String.format("Integer %s as bits %s" , i , integerAsBits));
    }

    @Test
    void bitwise_bit_count(){
        int i = 1789;
        int noOfBits = BitwiseHelper.countNumberOfBits(i);
        logger.info(String.format("No of bits in %s is %s" , i , noOfBits));
    }

    @Test
    void bitwise_is_even(){
        int i = 1524;
        boolean isEven = BitwiseHelper.isEven(i);
        logger.info(String.format("Number %s is %s" , i , isEven ? "even" : "odd"));
    }

    @Test
    void bitwise_is_power_of_two(){
        int i = 4094;
        boolean is2sPower = BitwiseHelper.isPowerOfTwo(i);
        logger.info(String.format("Number %s is %s power of two" , i , is2sPower ? "a" : "not a"));
    }

    @Test
    void bitwise_or_min_flips_required(){
        String a = "100101";
        String b = "111010";
        String c = "010100";
        String multiLineString = """
                Number of flips required by 
                a: %032d
                b: %032d
                c: %032d
                is %d
                """;
        int minFlips = MiscBitsAlgorithms.minFlipsNeededToMakeOrOperationEqual(
                BitwiseHelper.binaryStringToInt(a),
                BitwiseHelper.binaryStringToInt(b),
                BitwiseHelper.binaryStringToInt(c)
        );
        logger.info(String.format(multiLineString, Integer.parseInt(a) , Integer.parseInt(b) , Integer.parseInt(c) , minFlips));
    }

    @Test
    void binaryStringToInt(){
        String binaryAsString = "10000";
        int integer = BitwiseHelper.binaryStringToInt(binaryAsString);
        logger.info(String.format("Binary %s is %s as integer" , binaryAsString, integer));
    }

    @Test
    void negativeOfANumber(){
        int number = 1000;
        logger.info(String.format("negative of %s is %s " , number, BitwiseHelper.negativeOf(number)));
    }

    @Test
    void signAreOpposite(){
        int a = 1000;
        int b = -500;
        boolean oppositeSign = MiscBitsAlgorithms.signsAreOpposite(a,b);
        logger.info(String.format("%s and %s are of %s sign" , a,b , oppositeSign ? "opposite" : "same"));
    }

    @Test
    void hammingDistance(){
        int a = 5;
        int b = 4;
        int dist = MiscBitsAlgorithms.noOfDifferentBitPositions(a,b);
        logger.info(String.format("Hamming distance between %s and %s is %s" , a,b,dist));
    }

    @Test
    void missingValueInASeq(){
        int[] nums = {9, 6, 3, 2, 8, 5, 7, 0, 1};
        logger.info(String.format("missing number in %s is %s" , Arrays.toString(nums) , missingValueInASeq(nums)));
    }

    int missingValueInASeq(int[] nums){
        int xor = 0;
        for (int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
            xor ^= i;
        }
        xor ^= nums.length;
        return xor;
    }

    @Test
    void isKSet(){
        int a = BitwiseHelper.binaryStringToInt("001000");
        int k = 3;
        boolean isSet = BitwiseHelper.isKPositionSet(a,k);
        logger.info(String.format("%sth position of %s is %s",k,a,isSet ? "set" : "unset"));
    }

    @Test
    void powerSet(){
        int[] nums =  {1,2,3,4};
        List<List<Integer>> powerSet = MiscBitsAlgorithms.powerSet(nums);
        String multiline = """
                 powerSet of 
                 %s 
                 is
                 %s
                """;
        logger.info(String.format(multiline, Arrays.toString(nums) , powerSet));
    }

    @Test
    void firstSetPosition(){
        int a = BitwiseHelper.binaryStringToInt("10000000000000000");
        int firstSetPos = BitwiseHelper.firstSetPosition(a);
        logger.info(String.format(
                "first set position of %s is %s"
         , a , firstSetPos));
    }




}
