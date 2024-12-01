package org.latwal.vivek;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MiscBitsAlgorithms {


    /**
     * Minimum flips required for individual bits of a and b to become c
     */
    public static int minFlipsNeededToMakeOrOperationEqual(int a, int b , int c){

        int ans = 0;
        for(int i =0 ; i < 32 ; i++){
            int cBitAtIPosition = ((c >> i) & 1);
            int aBitAtIPosition = ((a >> i) & 1);
            int bBitAtIPosition = ((b >> i) & 1);

            if((aBitAtIPosition | bBitAtIPosition) != cBitAtIPosition){
                //if c bit is 0 than we need both a nd b's bit to be 0
                if(cBitAtIPosition == 0){
                   if(aBitAtIPosition == 1 && bBitAtIPosition == 1) {
                       ans+=2; //need to flip both a and b bit
                   } else {
                       ans +=1; // can flip only one bit;
                   }
                } else {
                    ans +=1 ; //c bit == 1 , can be achieved by only flipping any one bit
                }
            }
        }
        return ans;
    }

    public static boolean signsAreOpposite(int a, int b){
        /**
         * explanantion
         * -v2 number left more bit = 1
         * +ve number left most bit = 0
         *
         * so if c = a ^ b is -ve
         * is only possible if c is -v3 i.e. its left most bit in 1
         * which in xor only possible if either
         * one of a or b has 1 as left most bit and 0
         * for other.
         * i.e. one is +ve and other -ve
         */
        return (a ^ b) < 0;
    }

    /**
     * function tel how many bit positions are different in a and b
     * eg a = 111
     * b = 101
     * res = 1 (at place 1)
     */
    public static int noOfDifferentBitPositions(int a, int b){
        int xor = a ^ b;
        //a ^ b is 1 only where there is a diff , so count set bit of a ^ b will give diff bit number.
        return BitwiseHelper.countSetBits(xor);
    }

    public static List<List<Integer>> powerSet(int[] nums){
        int n = nums.length;
        int c = 0;
        List<List<Integer>> res = new ArrayList<>();
        int numSubSets = (int) Math.pow(2,n);
        for (int i = 0; i < numSubSets; i++) {
            List<Integer> subSet = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                if(BitwiseHelper.isKPositionSet(i,j)){
                    subSet.add(nums[j]);
                }
            }
            res.add(subSet);
        }
        return res;
    }
}
