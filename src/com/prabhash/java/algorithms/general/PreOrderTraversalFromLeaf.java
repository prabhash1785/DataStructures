/**
 * Amazon Online Interview question for SDE 3 Position.
 * Logic: We can find preorder traversal by using two properties.
 *     1.In BST all nodes left to root are smaller and right to root are greater then root
 *     2.Preorder traversal is Node -> Left -> Right
 *  Link: http://www.geeksforgeeks.org/contribute/temp/
 * 
 */

package com.prabhash.java.algorithms.general;

public class PreOrderTraversalFromLeaf {
	
	public static void findPreOrder(String[] input){
        
        //Edge case 1
        if(input == null || input.length < 1){
            System.out.println("Wrong Input");
            return;
        }
         
        int size = input.length;
        char[] rootArray = input[size-1].toCharArray(); //this will be root
         
        //Edge case 2 : Validating to make sure last element has one element as this is root.
        if(rootArray.length!=1){
            System.out.println("Wrong Input");
            return;
        } 
         
        //Edge case 3
        if(input.length == 1){
            System.out.println("PreOrder of Tree is :: " + input[0]);
            return;
        }
             
        // Root element of main tree
        char root = rootArray[0];
         
        // First string in input must be leaf's of original tree.
        String leafs = input[0];
         
        //This will left and right subtree's leafs
        int index = getIndex(root,leafs);
         
        String leftTree =  leafs.substring(0, index);
        String rightTree = leafs.substring(index);
         
        for(int i =1; i<input.length-1;i++){
            String newleaf = input[i];
            for(int j =0;j<newleaf.length();j++){
                char newChar = newleaf.charAt(j);
                if(newChar <= root){
                    leftTree = newChar + leftTree;
                } else {
                    rightTree = newChar + rightTree;
                }
            }
        }
         
        String preOrder = root + leftTree + rightTree;
         
        System.out.println("PreOrder of Tree is :: " + preOrder);
         
    }
     
 
    //Helper function to divide left and right leaves
    public static int getIndex(char root, String leafArray){
        int index = leafArray.length();
         
        for(int i=0;i<leafArray.length();i++){
             
            if(leafArray.charAt(i) > root){
                index = i;
                break;
            }       
        }   
        return index;   
    }
     
 
      
    public static void main(String args[]){
         
        String[] input = null;
        findPreOrder(input);
         
        String[] input1 = {"K"};
        findPreOrder(input1);
         
        String[] input2 = {"A","B","C"};
        findPreOrder(input2);
         
        String[] input3 = {"BDHPY","CM","GQ","K"};
        findPreOrder(input3);
         
        String[] input4 = {"ACFH","BE","G","D"};
        findPreOrder(input4);
        
        
    }
         
}
