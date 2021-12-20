package com.ibaoge.user;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class QuickSort {

    public static void main(String[] args) {
        int[] nums = {3, 2, 6, 8, 1, 7, 9 };
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    public static void quickSort(int[] arr, int l, int r){
        if(l >= r) {
            return;
        }
        int pivot = parition(arr, l, r);
        quickSort(arr, l, pivot);
        quickSort(arr, pivot + 1, r);
    }

    private static int parition(int[] a, int l, int r){
        int pivot = l + ((int) (Math.random() * (r - l + 1)));
//        int pivot = r;
        int pivotVal = a[pivot];
        while (l <= r){
            while (a[l] < pivotVal) {
                l++;
            }
            while (a[r] > pivotVal) {
                r--;
            }
            if(l == r){
                break;
            }
            if(l < r){
                int temp = a[l];
                a[l] = a[r];
                a[r] = temp;
                l++;
                r--;
            }
        }
        return r;
    }



}
