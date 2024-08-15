package com.mycompany.preguntame;

import java.util.*;

class BinaryNodeTree<E> {
    private E content;
    private BinaryTree<E> left;
    private BinaryTree<E> right;

    public BinaryNodeTree(E content) {
        this.content = content;
        left = null;
        right = null;
    }
    
    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public BinaryTree<E> getLeft() {
        return left;
    }

    public void setLeft(BinaryTree<E> left) {
        this.left = left;
    }

    public BinaryTree<E> getRight() {
        return right;
    }

    public void setRight(BinaryTree<E> right) {
        this.right = right;
    }

    
    
}
