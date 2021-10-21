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
public class Item <K,V>
{
    public K key;
    public V data;
    public Item()
    {
        //key=0;
        //data=0;
    }
    public Item(K key, V data)
    {
        this.key=key;
        this.data=data;
    }
    public String toString()
    {
        return key+" -> "+data+" , ";
    }
}
