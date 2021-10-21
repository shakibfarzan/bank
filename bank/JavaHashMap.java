/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import bank.SearchStructure;

import java.util.HashMap;

/**
 *
 * @author Hooman
 */
class JavaHashMap<K, V> extends SearchStructure<K, V>
{
    HashMap<K,V> items= new HashMap<>();
    public boolean insert(K key,V data)
    {
        if (items.get(key)==null)
        {
            items.put(key, data);
            return true;
        }
        else
            return false;
    }
    public boolean delete(K key)
    {
       if(items.remove(key)!=null)
           return true;
       else
           return false;   
    }
    public V search(K key)
    {
        return items.get(key);
    }
    public void print()
    {
        System.out.println(items);
    }
}