package examples.javafx.misc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Demonstrates the consumption of events in stacked environments. Sources from
 * https://stackoverflow.com/questions/37813358/what-is-the-meaning-of-event-consumes-in-javafx
 */
public class EventConsumingDemo extends Application {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(EventConsumingDemo.class);

  @Override
  public void start(Stage primaryStage) {
    Rectangle rect = new Rectangle(50, 50);

    StackPane root = new StackPane(rect);

    rect.addEventFilter(MouseEvent.MOUSE_CLICKED, evt -> {
      System.out.println("rect click(filter)");
      //      evt.consume();
    });
    root.addEventFilter(MouseEvent.MOUSE_CLICKED, evt -> {
      System.out.println("root click(filter)");
      //        evt.consume();
    });

    root.setOnMouseClicked(evt -> {
      System.out.println("root click(handler)");
      //      evt.consume();
    });
    rect.setOnMouseClicked(evt -> {
      System.out.println("rect click(handler)");
      //      evt.consume();
    });

    Scene scene = new Scene(root, 200, 200);

    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
