package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Title15_ThreeSum {

    public static void main(String[] args) {
        int [] nums = new int[]{0, 0, 0, 0};
        System.out.print(threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if(nums.length < 2) {
            return list;
        }
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++) {
            if(nums[i] > 0) return list;

            if(i > 0 && nums[i] == nums[i-1]) continue;
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                if(nums[i] + nums[j] + nums[k] == 0) {
                    if(nums[j] == nums[j + 1] && (j + 1) < k) {
                        j++;
                    }else if(nums[k - 1] == nums[k] && (k- 1) > j){
                        k--;
                    }else {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        list.add(temp);
                        j++;
                        k--;
                    }
                }else if(nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                }else {
                    j++;
                }
            }
        }
        return list;
    }
}
