/**package com.mycompany.preguntame;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
*/

package com.mycompany.preguntame;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;

/**
 *
 * @author Hackzll
 */

public class PrimaryController {

    @FXML
    private TextField txtMaxQuestions;
    
    @FXML
    private ImageView imgTitle;

    @FXML
    public void initialize() {
        // Cargar la imagen en el ImageView
        Image image = new Image("file:src/main/resources/assets/Portada.jpg");
        imgTitle.setImage(image);
    }

    @FXML
    private void startGame() throws IOException {
        String maxQuestions = txtMaxQuestions.getText();
        if (!maxQuestions.isEmpty()) {
            // Pasar el número de preguntas al SecondaryController
            SecondaryController.setMaxQuestions(Integer.parseInt(maxQuestions));
            App.setRoot("secondary");
        } else {
            showAlert("Error", "Debe especificar el número máximo de preguntas.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

