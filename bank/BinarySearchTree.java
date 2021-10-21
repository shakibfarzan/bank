/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Hooman
 */
public class BinarySearchTree<K extends Comparable<K>, V> extends SearchStructure<K, V>
{
   public class Node
    {
        private Item<K, V> item;
        private Node left;
        private Node right;
        
	    public Node(K key,V data)
        {
            item=new Item<>(key,data);
            left = null;
            right = null;
	    }

        public Item<K, V> getItem() {
            return item;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }
    public Node root;
    public BinarySearchTree()
    {
        this.root = null;
    }
    public V search(K key)
    {
        Node current = root;
        while(current!=null)
        {
            if(current.item.key.equals(key))
            {
                return current.item.data;
            }
            else if(current.item.key.compareTo(key)>0)
            {
                current = current.left;
            }
            else
            {
                current = current.right;
            }
        }
        return null;
    }
    public boolean delete(K key)
    {
        Node parent = root;
        Node current = root;
        boolean isLeftChild = false;
        while(!current.item.key.equals(key))
        {
            parent = current;
            if(current.item.key.compareTo(key)>0)
            {
                isLeftChild = true;
                current = current.left;
            }
            else
            {
                isLeftChild = false;
                current = current.right;
            }
            if(current ==null)
            {
                return false;
            }
        }
        //If program pointer is here that means we have found the node
        //Case 1: if node to be deleted has no children
        if(current.left==null && current.right==null)
        {
            if(current==root)
            {
                root = null;
            }
            if(isLeftChild)
            {
                parent.left = null;
            }
            else
            {
                parent.right = null;
            }
        }
        //Case 2 : if node to be deleted has only one child
        else if(current.right==null)
        {
            if(current==root)
            {
                root = current.left;
            }
            else if(isLeftChild)
            {
                parent.left = current.left;
            }
            else
            {
                parent.right = current.left;
            }
        }
        else if(current.left==null)
        {
            if(current==root)
            {
                root = current.right;
            }
            else if(isLeftChild)
            {
                parent.left = current.right;
            }
            else
            {
                parent.right = current.right;
            }
        }
        else {
            //now we have found the minimum element in the right sub-tree
            Node successor= getSuccessor(current);
            if(current==root)
            {
                root = successor;
            }
            else if(isLeftChild)
            {
               parent.left = successor;
            }
            else
            {
                parent.right = successor;
            }			
            successor.left = current.left;
        }		
        return true;		
    }
    public Node getSuccessor(Node deleleNode)
    {
        Node successsor=null;
        Node successsorParent=null;
        Node current = deleleNode.right;
        while(current!=null)
        {
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }
        // Check if successor has the right child, it cannot have left child for sure
        // if it does have the right child, add it to the left of successorParent.
        // successsorParent
        if(successsor!=deleleNode.right)
        {
            successsorParent.left = successsor.right;
            successsor.right = deleleNode.right;
        }
        return successsor;
    }
    public boolean insert(K key,V data)
    {
//        if(search(key)!=null)
//            return false;
        Node currentNode = root;
        while(currentNode!=null)
        {
            if(currentNode.item.key.equals(key))
            {
                currentNode.item.data = data;
                return true;
            }
            else if(currentNode.item.key.compareTo(key)>0)
            {
                currentNode = currentNode.left;
            }
            else
            {
                currentNode = currentNode.right;
            }
        }
        Node newNode = new Node(key,data);
        if(root==null)
        {
            root = newNode;
            return true;
        }
        Node current = root;
        Node parent = null;
        while(true)
        {
            parent = current;
            if(current.item.key.compareTo(key)>0)
            {				
                current=current.left;
                if(current==null)
                {
                    parent.left = newNode;
                    return true;
                }
            }
            else
            {
                current = current.right;
                if(current==null)
                {
                    parent.right = newNode;
                    return true;
                }
            }    
	}
    }

    public List<Item<K, V>> convertToList(){
        List<Item<K,V>> list = new LinkedList<>();
        convertToList(root, list);
        return list;
    }

    private void convertToList(Node root, List<Item<K, V>> result){
        if(root != null){
            convertToList(root.left, result);
            result.add(root.item);
            convertToList(root.right, result);
        }
    }

    public void display(Node root)
    {
        if(root!=null)
        {
            display(root.left);
            System.out.print(root.item);
            display(root.right);
        }        
    }
    public void print()
    {
        if(root!=null)
        {
            display(root);
            System.out.println();
        }
        else
        {
            System.out.println("Empty Tree");
        }
    }  
} 
