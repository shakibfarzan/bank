/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

/**
 *
 * @author Hooman
 */

public class MergeSort<T extends Comparable<T>> implements SortAlgorithm<T>
{
    public void sort(T[] items)
    {
        mergeSort(items,0,items.length-1);
    }
    
    private void mergeSort(T[] items, int left, int right)
    {
        if (right <= left) 
            return;
        int mid = (left+right)/2;
        mergeSort(items, left, mid);
        mergeSort(items, mid+1, right);
        merge(items, left, mid, right);
    }
    
    private void merge(T[] items, int left, int mid, int right)
    {
        int leftLength = mid - left + 1;
        int rightLength = right - mid;

        T[] leftArray = (T[]) new Comparable[leftLength];
        T[] rightArray = (T[]) new Comparable[rightLength];

        for (int i = 0; i < leftLength; i++)
            leftArray[i] = items[left+i];
        for (int i = 0; i < rightLength; i++)
            rightArray[i] = items[mid+i+1];

        int leftIndex = 0;
        int rightIndex = 0;

        for (int i = left; i < right + 1; i++) 
        {
            if (leftIndex < leftLength && rightIndex < rightLength) 
            {
                if (leftArray[leftIndex].compareTo(rightArray[rightIndex])<0) 
                {
                    items[i] = leftArray[leftIndex];
                    leftIndex++;
                }
                else 
                {
                    items[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            }
            else if (leftIndex < leftLength) 
            {
                items[i] = leftArray[leftIndex];
                leftIndex++;
            }
            else if (rightIndex < rightLength) 
            {
                items[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }
} 
