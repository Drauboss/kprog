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


  public PalindromeCheckerGui(PalindromeChecker palindromeChecker) {


    Button btn = new Button();
    TextField txtField = new TextField();

    btn.setText("Prüfe Palindrom");


    label = new Label();

    //wenn textfield fokussiert ist und enter gedrückt wird
    txtField.setOnKeyPressed(event -> {
      if( event.getCode() == KeyCode.ENTER ) {
        logger.info(txtField.getText());

        palindrom = txtField.getText();

        if (palindromeChecker.isPalindrome(palindrom)) {
          label.setText(Constants.SUCCESS);
        } else {
          label.setText(Constants.FAILURE);
        }
      }
      event.consume();
    } );

    //wenn button fokussiert ist und enter gedrückt wird
    btn.setOnKeyPressed(event -> {
      if( event.getCode() == KeyCode.ENTER ) {
        logger.info(txtField.getText());

        palindrom = txtField.getText();

        if (palindromeChecker.isPalindrome(palindrom)) {
          label.setText(Constants.SUCCESS);
        } else {
          label.setText(Constants.FAILURE);
        }
      }
      event.consume();
    } );

    //wenn button gedrückt wird
    btn.setOnAction(event -> {
      logger.info(txtField.getText());

      palindrom = txtField.getText();

      if (palindromeChecker.isPalindrome(palindrom)) {
        label.setText(Constants.SUCCESS);
      } else {
        label.setText(Constants.FAILURE);
      }

      event.consume();
    });






    getChildren().addAll(btn, txtField, label);

  }
}
