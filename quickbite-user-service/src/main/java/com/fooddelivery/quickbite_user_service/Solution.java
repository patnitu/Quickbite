package com.fooddelivery.quickbite_user_service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {

	public static void main(String args[]) {
		int[] num = { -2, 1, 1, 1 };
		// int[] num = {2,-3,0,-2,-5,-5,-4,1,2,-2,2,0,2,-4,5,5,-10};
		// Solution s = new Solution();
		// List<List<Integer>> list = s.threeSum(num);
		// System.out.println(list);
		// ListNode l1 = new ListNode(2,new ListNode(4,new ListNode(3)));
		// ListNode l2 = new ListNode(5,new ListNode(6,new ListNode(4)));
		// ListNode l3 = new Solution().addTwoNumbers(l1,l2);

		// while(l3 != null) {
		// System.out.println(l3.val);
		// l3 = l3.next;
		// }

		// new Solution().customIntegerParse("67543");
		/*Map<Character, Integer> map = new HashMap();
		for (int i = 0; i <= 127; i++) {
			map.put(new Character((char) i), 0);
		}

		System.out.println(map);
		map.put('c', 8);
		Integer c = map.get('c');
		System.out.println(c);*/
		
		System.out.println("Longestsubstring length "+new Solution().lengthOfLongestSubstring("abcdacdefg"));
	}

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> resultList = new ArrayList<>();
		Arrays.sort(nums); // Sort the array first

		int n = nums.length;

		for (int i = 0; i < n - 2; i++) {
			// Skip duplicate values for i
			if (i > 0 && nums[i] == nums[i - 1])
				continue;

			int l = i + 1;
			int r = n - 1;

			while (l < r) {
				int sum = nums[i] + nums[l] + nums[r];

				if (sum == 0) {
					resultList.add(Arrays.asList(nums[i], nums[l], nums[r]));

					// Move both pointers and skip duplicates
					l++;
					r--;
					while (l < r && nums[l] == nums[l - 1])
						l++;
					while (l < r && nums[r] == nums[r + 1])
						r--;

				} else if (sum < 0) {
					l++;
				} else {
					r--;
				}
			}
		}

		return resultList;
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		// 9,9,9,9,9,9,9
		// 9,9,9
		int carry = 0;
		ListNode l3 = null;
		ListNode tail = null;

		while (l1 != null || l2 != null) {
			int digit1 = 0;
			int digit2 = 0;

			if (l1 != null) {
				digit1 = l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				digit2 = l2.val;
				l2 = l2.next;
			}

			int newDigit = digit1 + digit2 + carry;
			System.out.println("new Digit " + newDigit);
			if (newDigit >= 10) {
				carry = newDigit / 10;
				newDigit = newDigit % 10;

				System.out.println("carry " + carry + " newDigit " + newDigit);
			} else {
				carry = 0;
			}

			if (l3 == null) {
				l3 = new ListNode(newDigit);
				tail = l3;
			} else {
				tail.next = new ListNode(newDigit);
				tail = tail.next;
			}

		}

		if (carry > 0) {
			tail.next = new ListNode(carry);
		}

		return l3;

	}

	public String multiply(String num1, String num2) {
		return null;
	}

	public int customIntegerParse(String num1) {
		// 123
		// '1'-'0'=1
		// '2'-'0'=2
		// '3'='0'=3
		int n = num1.length();
		int cur = n;
		int result = 0;
		for (int i = 0; i < n; i++) {

			int digit = num1.charAt(i) - '0';

			// Shift existing result by 1 decimal place, then add digit
			result = result * 10 + digit;

			System.out.println("After processing " + num1.charAt(i) + ", result = " + result);
		}
		System.out.println("result is " + result);
		return 0;
	}

	public int lengthOfLongestSubstring(String s) {
       
		int longestLen = 0;
		int start=0;
		Map<Character,Integer> charMap = new HashMap();
		//abcdacdefg
		for(int i=0 ; i<128 ; i++) {
			charMap.put((char) i, 0);
		}
		int currentLength =0;
		for(int i=0 ; i<s.length() ; i++) {
			i = start;
			char c = s.charAt(i);
			int count = charMap.get(c);
			if(count == 0) {
				charMap.put(c, count++);
				currentLength++;
			}else {
				longestLen = currentLength;
				currentLength = 0;
				for(int j=0 ; i<128 ; j++) {
					charMap.put((char) j, 0);
				}
				start =i;
			}
		}
		return longestLen;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}