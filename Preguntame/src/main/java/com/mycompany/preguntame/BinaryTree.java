package com.mycompany.preguntame;


import com.mycompany.preguntame.BinaryNodeTree;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryTree {

    private BinaryNodeTree root;
    private BinaryNodeTree currentNode;

    public BinaryTree() {
        try {
            loadTree("src/main/resources/files/preguntas.txt", "src/main/resources/files/respuestas.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentNode = root;
    }

    private void loadTree(String questionsFilePath, String answersFilePath) throws IOException {
        BufferedReader questionReader = new BufferedReader(new FileReader(questionsFilePath));
        String question;
        Map<Integer, String> questions = new HashMap<>();
        int index = 0;
        while ((question = questionReader.readLine()) != null) {
            questions.put(index++, question);
        }
        questionReader.close();

        BufferedReader answerReader = new BufferedReader(new FileReader(answersFilePath));
        String answerLine;
        root = new BinaryNodeTree(questions.get(0)); // Empezamos en la primera pregunta
        while ((answerLine = answerReader.readLine()) != null) {
            String[] parts = answerLine.split(" ");
            String animal = parts[0];
            BinaryNodeTree current = root;
            for (int i = 1; i < parts.length; i++) { // Navegamos a través de las respuestas
                if (parts[i].equalsIgnoreCase("Si")) {
                    if (current.getYesNode() == null) {
                        if (i == parts.length - 1) { // Si es la última respuesta, debe ser un nodo hoja
                            current.setYesNode(new BinaryNodeTree(null));
                            current.getYesNode().setAnswer(animal);
                        } else {
                            current.setYesNode(new BinaryNodeTree(questions.get(i)));
                        }
                    }
                    current = current.getYesNode();
                } else {
                    if (current.getNoNode() == null) {
                        if (i == parts.length - 1) { // Si es la última respuesta, debe ser un nodo hoja
                            current.setNoNode(new BinaryNodeTree(null));
                            current.getNoNode().setAnswer(animal);
                        } else {
                            current.setNoNode(new BinaryNodeTree(questions.get(i)));
                        }
                    }
                    current = current.getNoNode();
                }
            }
        }
        answerReader.close();
    }

    public String askQuestion() {
        if (currentNode == null || currentNode.getQuestion() == null) {
            return "No se pudo determinar una pregunta.";
        }
        return currentNode.getQuestion();
    }

    public void processAnswer(boolean answer) {
        if (currentNode != null) {
            if (answer) {
                if (currentNode.getYesNode() != null) {
                    currentNode = currentNode.getYesNode();
                } else {
                    System.out.println("No hay más nodos para 'Sí', debería ser un nodo hoja.");
                    currentNode = null;
                }
            } else {
                if (currentNode.getNoNode() != null) {
                    currentNode = currentNode.getNoNode();
                } else {
                    System.out.println("No hay más nodos para 'No', debería ser un nodo hoja.");
                    currentNode = null;
                }
            }
        } else {
            System.out.println("Nodo actual es nulo.");
        }
    }

    public boolean isGameOver() {
        return currentNode == null || currentNode.isLeaf();
    }

    //Agregar aqui tu parte Geovanny
    public String getPossibleAnswers() {
        List<String> possibleAnimals = getPossibleAnimals();

        if (possibleAnimals.size() == 1) {
            return "El animal es: " + possibleAnimals.get(0);
        } else if (possibleAnimals.size() > 1) {
            return "No se pudo determinar el animal exacto, pero podría ser uno de los siguientes: " + String.join(", ", possibleAnimals);
        }

        return "No se pudo determinar el animal.";
    }

    // Método auxiliar para recolectar respuestas posibles
    private String collectPossibleAnswers(BinaryNodeTree node) {
        StringBuilder animals = new StringBuilder();
        collectAnimalsFromNode(node, animals);
        return animals.toString();
    }

    private void collectAnimalsFromNode(BinaryNodeTree node, StringBuilder animals) {
        if (node == null) return;
        if (node.isLeaf() && node.getAnswer() != null) {
            if (animals.length() > 0) {
                animals.append(", ");
            }
            animals.append(node.getAnswer());
        } else {
            collectAnimalsFromNode(node.getYesNode(), animals);
            collectAnimalsFromNode(node.getNoNode(), animals);
        }
    }
    
    // Método para obtener las respuestas posibles (animales) cuando no se ha podido determinar uno solo.
    public List<String> getPossibleAnimals() {
        List<String> possibleAnimals = new ArrayList<>();
        collectPossibleAnimals(currentNode, possibleAnimals);
        return possibleAnimals;
    }

    // Recolecta los posibles animales desde los nodos hijos.
    private void collectPossibleAnimals(BinaryNodeTree node, List<String> animals) {
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            animals.add(node.getAnswer());  // Aquí se asume que el método getAnswer() devuelve el nombre del animal.
        } else {
            collectPossibleAnimals(node.getYesNode(), animals);
            collectPossibleAnimals(node.getNoNode(), animals);
        }
    }
}
