package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Title16_ThreeSumClose {

    public static void main(String[] args) {
        int [] nums = new int[]{0, 1, 0, 0};
        System.out.print(threeSumClosest(nums, 2));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int ans = nums[0] + nums[1] + nums[2];
        for(int i = 0; i < nums.length - 2; i++) {
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if(Math.abs(target -ans) > Math.abs(target - sum)) {
                    ans = sum;
                }
                if(sum > target) {
                    k--;
                }else if(sum < target) {
                    j++;
                }else {
                    return ans;
                }
            }
        }
        return ans;
    }
}
