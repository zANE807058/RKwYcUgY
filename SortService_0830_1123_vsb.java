// 代码生成时间: 2025-08-30 11:23:36
package com.example.sort;

import java.util.Arrays;
import java.util.List;

public class SortService {

    /**
# 改进用户体验
     * Sorts a list of integers using the Bubble Sort algorithm.
     * 
# 改进用户体验
     * @param numbers List of integers to be sorted.
     * @return Sorted list of integers.
     */
    public List<Integer> bubbleSort(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Input list cannot be null or empty.");
        }
        
        boolean swapped;
        do {
# 优化算法效率
            swapped = false;
# NOTE: 重要实现细节
            for (int i = 1; i < numbers.size(); i++) {
                if (numbers.get(i - 1) > numbers.get(i)) {
                    // Swap numbers[i - 1] and numbers[i]
                    int temp = numbers.get(i - 1);
                    numbers.set(i - 1, numbers.get(i));
                    numbers.set(i, temp);
                    swapped = true;
                }
# 改进用户体验
            }
        } while (swapped);
# 改进用户体验
        
        return numbers;
    }

    /**
     * Sorts a list of integers using the Quick Sort algorithm.
     * 
     * @param numbers List of integers to be sorted.
# 改进用户体验
     * @return Sorted list of integers.
     */
    public List<Integer> quickSort(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Input list cannot be null or empty.");
        }
        
        return quickSort(numbers, 0, numbers.size() - 1);
# FIXME: 处理边界情况
    }
# 增强安全性

    private List<Integer> quickSort(List<Integer> numbers, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(numbers, low, high);
            quickSort(numbers, low, pivotIndex - 1);
            quickSort(numbers, pivotIndex + 1, high);
        }
# FIXME: 处理边界情况
        return numbers;
# NOTE: 重要实现细节
    }

    private int partition(List<Integer> numbers, int low, int high) {
        int pivot = numbers.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
# 添加错误处理
            if (numbers.get(j) < pivot) {
                i++;
                // Swap numbers[i] and numbers[j]
# 优化算法效率
                int temp = numbers.get(i);
                numbers.set(i, numbers.get(j));
                numbers.set(j, temp);
            }
# TODO: 优化性能
        }
        // Swap numbers[i + 1] and numbers[high] (or pivot)
        int temp = numbers.get(i + 1);
        numbers.set(i + 1, numbers.get(high));
        numbers.set(high, temp);
        return i + 1;
# 增强安全性
    }

    public static void main(String[] args) {
# FIXME: 处理边界情况
        SortService sortService = new SortService();
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
        System.out.println("Before sorting: " + numbers);
        System.out.println("After Bubble Sort: " + sortService.bubbleSort(new ArrayList<>(numbers)));
# TODO: 优化性能
        System.out.println("After Quick Sort: " + sortService.quickSort(new ArrayList<>(numbers)));
    }
}