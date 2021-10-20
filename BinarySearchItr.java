class BinarySearchItr
{
	// Function to determine if a `target` exists in the sorted array `nums`
	// or not using a binary search algorithm
	public static int binarySearch(int[] nums, int target)
	{
		int left = 0, right = nums.length - 1;

		while (left <= right)
		{
			int mid = (left + right) / 2;

			if (target == nums[mid]) {
				return target;
			}
			else if (target < nums[mid]) {
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args)
	{
		int[] nums = {2, 5, 5, 5, 6, 6, 8, 9, 9, 9 };
		int target = 5;

		int counter = binarySearch(nums, target);

		if (counter != 0) {
			System.out.println("Target"+target+" occurs "+ counter+" times");
		}
		else {
			System.out.println("Element not found in the array");
		}
	}
}