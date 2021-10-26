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

public class InsertionSort<T extends Comparable<T>> implements SortAlgorithm<T>
{
    public void sort(T[] items)
    {
        for (int i = 1; i < items.length; i++) 
        {
            T current = items[i];
            int j = i - 1;
            while(j >= 0 && current.compareTo(items[j])<0) 
            {
                items[j+1] = items[j];
                j--;
            }
            // j is either -1 or it's the index of the first element 
            //where current >= a[j]
            items[j+1] = current;
        }
    }
} 
