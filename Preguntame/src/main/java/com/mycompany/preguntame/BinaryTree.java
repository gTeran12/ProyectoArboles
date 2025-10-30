package com.mycompany.preguntame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class BinaryTree {

    private static final Logger logger = Logger.getLogger(BinaryTree.class.getName());

    private BinaryNodeTree root;
    private BinaryNodeTree currentNode;

    public BinaryTree() {
        try {
            loadTree("src/main/resources/files/preguntas.txt", "src/main/resources/files/respuestas.txt");
        } catch (IOException e) {
            logger.severe("Error loading tree: " + e.getMessage());
        }
        currentNode = root;
    }

    private void loadTree(String questionsFilePath, String answersFilePath) throws IOException {
        Map<Integer, String> questions = readQuestions(questionsFilePath);
        root = new BinaryNodeTree(questions.get(0)); 
        buildTreeFromAnswers(answersFilePath, questions);
    }

    private Map<Integer, String> readQuestions(String filePath) throws IOException {
        Map<Integer, String> questions = new HashMap<>();
        try (BufferedReader questionReader = new BufferedReader(new FileReader(filePath))) {
            String question;
            int index = 0;
            while ((question = questionReader.readLine()) != null) {
                questions.put(index++, question);
            }
        }
        return questions;
    }

    private void buildTreeFromAnswers(String filePath, Map<Integer, String> questions) throws IOException {
        try (BufferedReader answerReader = new BufferedReader(new FileReader(filePath))) {
            String answerLine;
            while ((answerLine = answerReader.readLine()) != null) {
                processAnswerLine(answerLine, questions);
            }
        }
    }

    private void processAnswerLine(String answerLine, Map<Integer, String> questions) {
        String[] parts = answerLine.split(" ");
        String animal = parts[0];
        BinaryNodeTree current = root;

        for (int i = 1; i < parts.length; i++) {
            boolean isYes = parts[i].equalsIgnoreCase("Si");
            boolean isLast = (i == parts.length - 1);

            if (isYes) {
                current = createNode(current, true, isLast, animal, questions.get(i));
            } else {
                current = createNode(current, false, isLast, animal, questions.get(i));
            }
        }
    }

    private BinaryNodeTree createNode(BinaryNodeTree current, boolean yes, boolean isLast, String animal, String question) {
        BinaryNodeTree nextNode = yes ? current.getYesNode() : current.getNoNode();

        if (nextNode == null) {
            if (isLast) {
                nextNode = new BinaryNodeTree(null);
                nextNode.setAnswer(animal);
            } else {
                nextNode = new BinaryNodeTree(question);
            }

            if (yes) current.setYesNode(nextNode);
            else current.setNoNode(nextNode);
        }
        return nextNode;
    }

    public String askQuestion() {
        if (currentNode == null || currentNode.getQuestion() == null) {
            return "No se pudo determinar una pregunta.";
        }
        return currentNode.getQuestion();
    }

    public void processAnswer(boolean answer) {
        if (currentNode != null) {
            if (answer && currentNode.getYesNode() != null) {
                currentNode = currentNode.getYesNode();
            } else if (!answer && currentNode.getNoNode() != null) {
                currentNode = currentNode.getNoNode();
            } else {
                logger.info("No more nodes in this path. It should be a leaf node.");
                currentNode = null;
            }
        } else {
            logger.warning("Current node is null.");
        }
    }

    public boolean isGameOver() {
        return currentNode == null || currentNode.isLeaf();
    }

    public String getPossibleAnswers() {
        List<String> possibleAnimals = getPossibleAnimals();

        if (possibleAnimals.size() == 1) {
            return "El animal es: " + possibleAnimals.get(0);
        } else if (possibleAnimals.size() > 1) {
            return "No se pudo determinar el animal exacto, pero podría ser uno de los siguientes: " + String.join(", ", possibleAnimals);
        }

        return "No se pudo determinar el animal.";
    }

    public List<String> getPossibleAnimals() {
        List<String> possibleAnimals = new ArrayList<>();
        collectPossibleAnimals(currentNode, possibleAnimals);
        return possibleAnimals;
    }

    private void collectPossibleAnimals(BinaryNodeTree node, List<String> animals) {
        if (node == null) return;
        if (node.isLeaf()) {
            animals.add(node.getAnswer());
        } else {
            collectPossibleAnimals(node.getYesNode(), animals);
            collectPossibleAnimals(node.getNoNode(), animals);
        }
    }
}
