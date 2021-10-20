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

public interface SortAlgorithm<T extends Comparable<T>>
{
    void sort(T[] items);
} 
