package leetcode;

import utils.ArrayUtil;

import java.util.HashMap;
import java.util.Map;

public class Title1_Twonum_Sum {

    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 7, 10, 12};
        ArrayUtil.printArray(findTarget(nums, nums.length, 8));
    }

    private static int[] findTarget(int[] nums, int n, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0 ; i < n; i++) {
            Integer index;
            if((index = map.get(target - nums[i])) != null) {
                return new int[] {index, i};
            }else {
                 map.put(nums[i], i);
            }
        }
        return null;
    }
}
