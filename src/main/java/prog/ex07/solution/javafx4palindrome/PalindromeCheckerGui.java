package prog.ex07.solution.javafx4palindrome;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
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
  TextField txtField;

  public PalindromeCheckerGui(PalindromeChecker palindromeChecker) {

    //TODO: bei drücken der enter taste soll auch geprüft werden
    Button btn = new Button();
    TextField txtField = new TextField();

    btn.setText("Prüfe Palindrom");
    //btn.setOnAction(new ButtonClickHandler());

    txtField.setOnKeyPressed( event -> {
      if( event.getCode() == KeyCode.ENTER ) {
        logger.info(txtField.getText());

        palindrom = txtField.getText();

        if (palindromeChecker.isPalindrome(palindrom)) {
          label.setText(Constants.SUCCESS);
        } else {
          label.setText(Constants.FAILURE);
        }
      }} );


    btn.setOnAction((event) -> {
      logger.info(txtField.getText());

      palindrom = txtField.getText();

      if (palindromeChecker.isPalindrome(palindrom)) {
        label.setText(Constants.SUCCESS);
      } else {
        label.setText(Constants.FAILURE);
      }

      event.consume();
    });


    label = new Label();



    getChildren().addAll(btn, txtField, label);

  }
}
