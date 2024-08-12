package com.mycompany.preguntame;

import java.util.*;

class BinaryNodeTree<E,K> {
    private E content;
    private K key;
    private BinaryTree<E,K> left;
    private BinaryTree<E,K> right;

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public BinaryTree<E, K> getLeft() {
        return left;
    }

    public void setLeft(BinaryTree<E, K> left) {
        this.left = left;
    }

    public BinaryTree<E, K> getRight() {
        return right;
    }

    public void setRight(BinaryTree<E, K> right) {
        this.right = right;
    }

    
    
}
