/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SortAlgorithms;

import SearchStructures.Item;

/**
 *
 * @author Hooman
 */

public class QuickSort<T extends Comparable<T>> implements SortAlgorithm<T>
{
    public void sort(T[] items)
    {
        quickSort(items,0,items.length-1);
    }
    
    private int partition(T[] items, int begin, int end)
    {
        int pivot = end;
        int counter = begin;
        for (int i = begin; i < end; i++) 
        {
            if (items[i].compareTo(items[pivot])<0)
            {
                T temp = items[counter];
                items[counter] = items[i];
                items[i] = temp;
                counter++;
            }
        }
        
        T temp = items[pivot];
        items[pivot] = items[counter];
        items[counter] = temp;

        return counter;
    }

    private void quickSort(T[] items, int begin, int end)
    {
        if (end <= begin) 
            return;
        int pivot = partition(items, begin, end);
        quickSort(items, begin, pivot-1);
        quickSort(items, pivot+1, end);
    }
} 
