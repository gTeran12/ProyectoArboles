package com.mycompany.preguntame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPaPruebas {

    public static void main(String[] args) {
        
        BinaryTree<String> bt = createTree("C:/Users/Geova/NeatBeansProjects/ProyectoArboles/Preguntame/src/main/resources/files/preguntas.txt", 0);
        bt.recorrerEnPreOrden();
    }
    
    public static BinaryTree<String> createTree(String fileRoute, int n){
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
                addQuestionToTree(questionsTree,question);
            }
        }
        return questionsTree;
    }
    
    
    public static void addQuestionToTree(BinaryTree<String> tree, String question){
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

}
