package com.mycompany.preguntame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Hackzll
 */
public class SecondaryController {

    @FXML
    private Label lblQuestion;
    @FXML
    private Label lblResult;
    @FXML
    private Label lblPlayAgain;
    @FXML
    private Label lblConfirmExit;
    @FXML
    private HBox hboxPlayAgain;
    @FXML
    private HBox hboxConfirmExit;
    @FXML
    private HBox hboxImages;

    private static int maxQuestions;
    private BinaryTree decisionTree;
    private int currentQuestionCount = 0;

    @FXML
    public void initialize() {
        decisionTree = new BinaryTree();
        lblQuestion.setText("Piense en un animal y luego presione Sí para iniciar el juego o presione No para regresar.");
        lblResult.setWrapText(true);
    }

    public static void setMaxQuestions(int max) {
        maxQuestions = max;
    }

    @FXML
    private void handleYes() {
        if (currentQuestionCount == 0) {
            lblQuestion.setText(decisionTree.askQuestion());
        } else {
            processAnswer(true);
        }
        currentQuestionCount++;
    }

    @FXML
    private void handleNo() {
        if (currentQuestionCount == 0) {
            // Volver a la ventana primary si el juego no ha comenzado
            try {
                App.setRoot("primary");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Procesar la respuesta "No" durante el juego
            processAnswer(false);
            currentQuestionCount++;
        }
    }

    private void processAnswer(boolean answer) {
        decisionTree.processAnswer(answer);

        if (decisionTree.isGameOver() || currentQuestionCount >= maxQuestions) {
            showResult();
        } else {
            lblQuestion.setText(decisionTree.askQuestion());
        }
    }

    private void showResult() {
        lblResult.setVisible(true);
        String result = decisionTree.getPossibleAnswers();
        lblResult.setText(result);

        // Mostrar las imágenes de los posibles animales
        showAnimalImages(decisionTree.getPossibleAnimals());

        lblQuestion.setVisible(false);
        hboxPlayAgain.setVisible(true);
        lblPlayAgain.setVisible(true);
    }

    private void showAnimalImages(List<String> animals) {
        hboxImages.getChildren().clear();  // Limpiar imágenes previas
        hboxImages.setVisible(true);

        for (String animal : animals) {
            String imagePath = "file:src/main/resources/assets/" + animal.toLowerCase() + ".jpg";
            ImageView imageView = new ImageView(new Image(imagePath));
            imageView.setFitHeight(100);  // Ajusta el tamaño según sea necesario
            imageView.setFitWidth(100);   // Ajusta el tamaño según sea necesario
            hboxImages.getChildren().add(imageView);
        }
    }

    @FXML
    private void handlePlayAgainYes() {
        // Volver a la ventana primary
        try {
            App.setRoot("primary");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePlayAgainNo() {
        hboxPlayAgain.setVisible(false);
        lblPlayAgain.setVisible(false);
        hboxConfirmExit.setVisible(true);
        lblConfirmExit.setVisible(true);
    }

    @FXML
    private void handleExitYes() {
        // Cerrar la aplicación
        System.exit(0);
    }

    @FXML
    private void handleExitNo() {
        // Volver a la ventana primary
        try {
            App.setRoot("primary");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


