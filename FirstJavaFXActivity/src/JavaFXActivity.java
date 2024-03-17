import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
// these imports are used for the First JavaFX Activity
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;


public class JavaFXActivity extends Application {
    @Override
    public void start(final Stage stage) {
        // update this method definition to complete the First JavaFX Activity
        Label label1 = new Label("    The key to making programs fast\n" +
                "    is to make them do practically nothing.\n" +
                "    -- Mike Haertel");

        Circle circle1 = new Circle(160, 120, 30);

        Polygon polygon1 = new Polygon(160, 120, 200, 220, 120, 220);

        Group group = new Group(label1, circle1, polygon1);
        Scene scene = new Scene(group,320,240);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("CS400: The Key");
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
