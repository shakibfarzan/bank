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

public class HeapSort<T extends Comparable<T>> implements SortAlgorithm<T>
{
    public void sort(T[] items)
    {
        if (items.length == 0) 
            return;
        // from the first non-leaf to the root
        for (int i = items.length / 2-1; i >= 0; i--)
            heapify(items, items.length, i);

        for (int i = items.length-1; i >= 0; i--) 
        {
            T temp = items[0];
            items[0] = items[i];
            items[i] = temp;
            heapify(items, i, 0);
        }
    }
    
    private void heapify(T[] array, int length, int i)
    {
        int leftChild = 2*i+1;
        int rightChild = 2*i+2;
        int largest = i;

        // if the left child is larger than parent
        if (leftChild < length && array[leftChild].compareTo(array[largest])>0)
        {
            largest = leftChild;
        }

        // if the right child is larger than parent
        if (rightChild < length && array[rightChild].compareTo(array[largest])>0) 
        {
            largest = rightChild;
        }

        // if a swap needs to occur
        if (largest != i) 
        {
            T temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            heapify(array, length, largest);
        }
    }
} 
