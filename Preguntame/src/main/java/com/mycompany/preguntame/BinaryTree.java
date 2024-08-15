package com.mycompany.preguntame;

import java.util.*;
import java.io.*;

/**
 *
 * @author Geovanny
 */

public class BinaryTree<E> {
    private BinaryNodeTree<E> root; 


    public BinaryTree(BinaryNodeTree<E> root) {
        this.root = root;
    }
    
    public BinaryTree(){
        root = null;
    }
    
    public BinaryTree<String> createTree(String fileRoute, int n){
        List<String> questions = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileRoute))){
            String line;
            while((line = br.readLine()) != null){
                questions.add(line);
            }
        }catch(IOException e){
            System.out.println("Could not read the file: " + fileRoute);
            e.printStackTrace();
        }
        
        BinaryTree<String> questionsTree = new BinaryTree<>();
        for (int i = 0; i < n ; i++){
            String question = questions.get(i);
            if(questionsTree.isEmpty()){
                questionsTree.setRoot(new BinaryNodeTree<>(question));
            }else{
                this.addQuestionToTree(questionsTree,question);
            }
        }
        return questionsTree;
    }
    
    
    public void addQuestionToTree(BinaryTree<String> tree, String question){
        List<BinaryNodeTree<String>> leafs = new ArrayList<>();
        if (!tree.isEmpty()){
            if (!tree.isLeaf()){
                if (tree.getRoot().getLeft() != null){
                    addQuestionToTree(tree.getRoot().getLeft(), question);
                }
                if (tree.getRoot().getRight() != null){
                    addQuestionToTree(tree.getRoot().getRight(), question);
                }
            }else leafs.add(tree.getRoot());
        }else System.out.println("The tree's empty");
        
        for (int i = 0; i < leafs.size(); i++){
            leafs.get(i).setLeft(new BinaryTree<>(new BinaryNodeTree<>(question)));
            leafs.get(i).setRight(new BinaryTree<>(new BinaryNodeTree<>(question)));
        }
    }
    
    
    
    public BinaryNodeTree<E> getRoot() {
        return root;
    }

    public void setRoot(BinaryNodeTree<E> root) {
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
        Stack<BinaryTree<E>> pila = new Stack();
        
        if(!this.isEmpty()){  
            pila.push(this);
            while(!pila.isEmpty()){
                BinaryTree<E> tree = pila.pop();
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
