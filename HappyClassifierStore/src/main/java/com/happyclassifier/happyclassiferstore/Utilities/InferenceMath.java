package com.happyclassifier.happyclassiferstore.Utilities;

public class InferenceMath {
    public static int argMax(float[] nums){
        int result = 0;
        float previousMax = nums[0];
        for (int i = 1; i < nums.length; i++){
            if (nums[i] > previousMax){
                previousMax = nums[i];
                result = i;
            }
        }
        return result;
    }
}
