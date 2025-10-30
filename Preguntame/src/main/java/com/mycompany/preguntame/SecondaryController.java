package com.mycompany.preguntame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.io.IOException;
import java.util.List;
import javafx.scene.control.Button;

public class SecondaryController {

    private static final String PRIMARY_VIEW = "primary";  // ✅ constant for view name

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
    @FXML
    private Button btnYes;
    @FXML
    private Button btnNo;

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
            try {
                App.setRoot(PRIMARY_VIEW);  // ✅ use constant
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            processAnswer(false);
            currentQuestionCount++;
        }
    }



