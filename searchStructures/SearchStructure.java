/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchStructures;

/**
 *
 * @author Hooman
 */
public abstract class SearchStructure<K, V>
{
    abstract public boolean insert(K key,V data);
    abstract public boolean delete(K key);// return success
    abstract public V search(K key);// return data
    abstract public void print();
} 
