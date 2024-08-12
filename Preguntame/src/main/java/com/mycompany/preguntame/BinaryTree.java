package com.mycompany.preguntame;

import java.util.*;

/**
 *
 * @author Geovanny
 */

public class BinaryTree<E,K> {
    private BinaryNodeTree<E,K> root; 
    private Comparator<K> cmp;

    public BinaryTree(BinaryNodeTree<E, K> root) {
        this.root = root;
    }
    
//    public BinaryTree<String,String> createTree(){
//        
//    }

    public BinaryNodeTree<E, K> getRoot() {
        return root;
    }

    public void setRoot(BinaryNodeTree<E, K> root) {
        this.root = root;
    }
    
    
    
    public boolean isEmpty(){
        return this.root == null;
    }
    
    public boolean isLeaf(){ //es hoja?
        if(!this.isEmpty()){
            return this.root.getLeft() == null && this.root.getRight() == null;
        }else return false;
    }
    
    public void recorrerEnPreOrden(){
        if(!this.isEmpty()){
            //1. imprimir a la raiz
            System.out.println(this.root.getContent());
            
            //2. recorrerEnPreOrden el hijo izquierdo
            if(this.root.getLeft() != null){
                this.root.getLeft().recorrerEnPreOrden();
            }
            
            //3. recorrerEnPreOrden el hijo derecho
            if(this.root.getRight() != null){
                this.root.getRight().recorrerEnPreOrden();
            }   
        }
    }
    
    public void recorrerEnPostOrden(){
        if(!this.isEmpty()){
            //1. recorrerEnPreOrden el hijo izquierdo
            if(this.root.getLeft() != null){
                this.root.getLeft().recorrerEnPreOrden();
            }
            
            //2. recorrerEnPreOrden el hijo derecho
            if(this.root.getRight() != null){
                this.root.getRight().recorrerEnPreOrden();
            }
            
            //3. imprimir a la raiz
            System.out.println(this.root.getContent());
        }
    }
    
    
    public void recorrerEnOrden(){
        if(!this.isEmpty()){
            //1. recorrerEnPreOrden el hijo izquierdo
            if(this.root.getLeft() != null){
                this.root.getLeft().recorrerEnPreOrden();
            }
            
            //2. imprimir a la raiz
            System.out.println(this.root.getContent());
            
            //3. recorrerEnPreOrden el hijo derecho
            if(this.root.getRight() != null){
                this.root.getRight().recorrerEnPreOrden();
            }  
        }
    }
    
    
    public int countLeaves(){ //contar hojas
        if(this.isEmpty()) return 0;
        else if(this.isLeaf()) return 1;
        else{
            int leftLeaves = 0;
            int rightLeaves = 0;
            if(this.root.getLeft() != null){
                leftLeaves = this.root.getLeft().countLeaves();
            }
            if(this.root.getRight() != null){
                rightLeaves = this.root.getRight().countLeaves();
            }
            return leftLeaves + rightLeaves;
        }
    }
    
    
    public int countLeavesIterative(){
        int leaves = 0; //contador
        Stack<BinaryTree<E,K>> pila = new Stack();
        
        if(!this.isEmpty()){  
            pila.push(this);
            while(!pila.isEmpty()){
                BinaryTree<E,K> tree = pila.pop();
                if(tree.isLeaf()) leaves++;
                if(tree.getRoot().getLeft() != null){
                    pila.push(tree.getRoot().getLeft());
                }
                if(tree.getRoot().getRight() != null){
                    pila.push(tree.getRoot().getRight());
                }
            }
        }
        return leaves;
    }


}
