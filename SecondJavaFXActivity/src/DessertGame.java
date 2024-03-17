import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.geometry.Pos;
import java.util.Random;

public class DessertGame extends Application {
    private int score = 0;
    public void increaseScore() {
        this.score++;
    }
    public void decreaseScore() {
        this.score--;
    }

    @Override
    public void start(final Stage stage) {
        // Step 3 & 4
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 640, 480);
        stage.setTitle("Dessert in the Desert JavaFX Game");

        // Step 5
        Label scoreLabel = new Label("Score: " + score);
        borderPane.setTop(scoreLabel);
        BorderPane.setAlignment(scoreLabel, Pos.TOP_LEFT);

        Button exitButton = new Button("Exit");
        exitButton.requestFocus();
        exitButton.setOnAction(event -> {
            Platform.exit();
        });

        borderPane.setBottom(exitButton);
        BorderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);

        // Step 6 & 7
        Button dessert = new Button("Dessert");
        Button desert1 = new Button("Desert");
        Button desert2 = new Button("Desert");
        Button desert3 = new Button("Desert");
        Button desert4 = new Button("Desert");
        Button desert5 = new Button("Desert");
        Button desert6 = new Button("Desert");
        Button desert7 = new Button("Desert");

        Button[] buttonArray = new Button[8];
        buttonArray[0] = desert1;
        buttonArray[1] = desert2;
        buttonArray[2] = desert3;
        buttonArray[3] = desert4;
        buttonArray[4] = desert5;
        buttonArray[5] = desert6;
        buttonArray[6] = desert7;
        buttonArray[7] = dessert;

        Random RNG = new Random();

        randomizeButtonPositions(RNG, buttonArray);

        dessert.setOnAction(event -> {
            increaseScore();
            randomizeButtonPositions(RNG, buttonArray);
            Label scoreLabel1 = new Label("Score: " + score);
            borderPane.setTop(scoreLabel1);
            BorderPane.setAlignment(scoreLabel1, Pos.TOP_LEFT);
            exitButton.requestFocus();
        });
        desert1.setOnAction(event -> {
            decreaseScore();
            randomizeButtonPositions(RNG, buttonArray);
            Label scoreLabel2 = new Label("Score: " + score);
            borderPane.setTop(scoreLabel2);
            BorderPane.setAlignment(scoreLabel2, Pos.TOP_LEFT);
            exitButton.requestFocus();
        });
        desert2.setOnAction(event -> {
            decreaseScore();
            randomizeButtonPositions(RNG, buttonArray);
            Label scoreLabel3 = new Label("Score: " + score);
            borderPane.setTop(scoreLabel3);
            BorderPane.setAlignment(scoreLabel3, Pos.TOP_LEFT);
            exitButton.requestFocus();
        });
        desert3.setOnAction(event -> {
            decreaseScore();
            randomizeButtonPositions(RNG, buttonArray);
            Label scoreLabel4 = new Label("Score: " + score);
            borderPane.setTop(scoreLabel4);
            BorderPane.setAlignment(scoreLabel4, Pos.TOP_LEFT);
            exitButton.requestFocus();
        });
        desert4.setOnAction(event -> {
            decreaseScore();
            randomizeButtonPositions(RNG, buttonArray);
            Label scoreLabel5 = new Label("Score: " + score);
            borderPane.setTop(scoreLabel5);
            BorderPane.setAlignment(scoreLabel5, Pos.TOP_LEFT);
            exitButton.requestFocus();
        });
        desert5.setOnAction(event -> {
            decreaseScore();
            randomizeButtonPositions(RNG, buttonArray);
            Label scoreLabel6 = new Label("Score: " + score);
            borderPane.setTop(scoreLabel6);
            BorderPane.setAlignment(scoreLabel6, Pos.TOP_LEFT);
            exitButton.requestFocus();
        });
        desert6.setOnAction(event -> {
            decreaseScore();
            randomizeButtonPositions(RNG, buttonArray);
            Label scoreLabel7 = new Label("Score: " + score);
            borderPane.setTop(scoreLabel7);
            BorderPane.setAlignment(scoreLabel7, Pos.TOP_LEFT);
            exitButton.requestFocus();
        });
        desert7.setOnAction(event -> {
            decreaseScore();
            randomizeButtonPositions(RNG, buttonArray);
            Label scoreLabel8 = new Label("Score: " + score);
            borderPane.setTop(scoreLabel8);
            BorderPane.setAlignment(scoreLabel8, Pos.TOP_LEFT);
            exitButton.requestFocus();
        });

        Pane pane = new Pane(dessert, desert1, desert2, desert3, desert4, desert5, desert6,
                desert7);
        borderPane.setCenter(pane);
        BorderPane.setAlignment(pane, Pos.CENTER);

        stage.setScene(scene);
        stage.show();
    }

    // Step 8
    private void randomizeButtonPositions(java.util.Random RNG, Button[] buttonArray) {
        for (int i = 0; i < buttonArray.length; i++) {
            buttonArray[i].setLayoutX(RNG.nextInt(601));
            buttonArray[i].setLayoutY(RNG.nextInt(401));
        }
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
