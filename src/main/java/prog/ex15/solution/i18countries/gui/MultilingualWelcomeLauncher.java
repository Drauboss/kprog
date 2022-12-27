package prog.ex15.solution.i18countries.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import prog.ex15.exercise.i18ncountries.CountryKnowledgeContainer;
import prog.ex15.solution.i18countries.I18nKnowledgeGenerator;

/**
 * Main to launch the WelcomeToMyCountry content in a separate application.
 */
public class MultilingualWelcomeLauncher extends Application {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(MultilingualWelcomeLauncher.class);

  @Override
  public void start(final Stage stage) throws Exception {
    CountryKnowledgeContainer container = new I18nKnowledgeGenerator().fillContainer();
    FxKnowledgePresenter presenter = new FxKnowledgePresenter(container);
    VBox vBox = new VBox();
    ChoiceBox choiceBox = new ChoiceBox();
    vBox.getChildren().addAll(choiceBox,presenter);
    stage.setScene(new Scene(vBox, 400, 300));
    stage.show();
  }
}
