package prog.ex07.solution.javafx4palindrome;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import prog.ex07.exercise.javafx4palindrome.Constants;
import prog.ex07.exercise.javafx4palindrome.PalindromeChecker;

/**
 * JavaFX component to wrap around a given PalindromeChecker.
 */
public class PalindromeCheckerGui extends FlowPane {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PalindromeCheckerGui.class);

  private Label label;
  private String palindrom;
  private Button btn;
  private TextField txtField;
  private Background redBackground;
  private Background greenBackground;


  /**
   * PalindromCheckerGui.
   */
  public PalindromeCheckerGui(PalindromeChecker palindromeChecker) {

    btn = new Button();
    txtField = new TextField();
    redBackground = new Background(
        new BackgroundFill(Color.rgb(255, 0, 0, 1), null, null));
    greenBackground = new Background(
        new BackgroundFill(Color.rgb(0, 255, 0, 1), null, null));

    btn.setText("Prüfe Palindrom");

    label = new Label();
    label.setAlignment(Pos.CENTER);
    label.setFont(new Font(30));
    setAlignment(Pos.CENTER);

    setHgap(25);

    //wenn textfield fokussiert ist und enter gedrückt wird
    txtField.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ENTER) {
        logger.info(txtField.getText());

        palindrom = txtField.getText();

        if (palindromeChecker.isPalindrome(palindrom)) {
          label.setText(Constants.SUCCESS);
          setBackground(greenBackground);
        } else {
          label.setText(Constants.FAILURE);
          setBackground(redBackground);
        }
      }
      event.consume();
    });

    //wenn button fokussiert ist und enter gedrückt wird
    btn.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ENTER) {
        logger.info(txtField.getText());

        palindrom = txtField.getText();

        if (palindromeChecker.isPalindrome(palindrom)) {
          label.setText(Constants.SUCCESS);
          setBackground(greenBackground);
        } else {
          label.setText(Constants.FAILURE);
          setBackground(redBackground);
        }
      }
      event.consume();
    });

    //wenn button gedrückt wird
    btn.setOnAction(event -> {
      logger.info(txtField.getText());

      palindrom = txtField.getText();

      if (palindromeChecker.isPalindrome(palindrom)) {
        label.setText(Constants.SUCCESS);
        setBackground(greenBackground);
      } else {
        label.setText(Constants.FAILURE);
        setBackground(redBackground);
      }

      event.consume();
    });

    getChildren().addAll(btn, txtField, label);

  }
}
