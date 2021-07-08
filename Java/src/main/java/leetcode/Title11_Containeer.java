package leetcode;

public class Title11_Containeer {

    public static void main(String[] args) {
        int []height = new int[] {1, 4, 2, 10, 8, 7, 5};
        int ans = new Title11_Containeer().maxArea(height);
        System.out.println(ans);
    }

    public int maxArea(int[] height) {
        int len = height.length;
        int left = 0;
        int right = len - 1;
        int ans = 0;
        while (left < right) {
            int high = Math.min(height[left], height[right]);
            ans = Math.max(ans, (right - left) * high);
            if(height[left] > height[right]) {
                right--;
            }else {
                left++;
            }
        }
        return ans;
    }
}
