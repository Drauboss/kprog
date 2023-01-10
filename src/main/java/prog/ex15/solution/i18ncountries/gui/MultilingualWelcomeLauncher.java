package prog.ex15.solution.i18ncountries.gui;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import prog.ex15.exercise.i18ncountries.Country;
import prog.ex15.exercise.i18ncountries.CountryKnowledgeContainer;
import prog.ex15.solution.i18ncountries.I18nKnowledgeGenerator;
import prog.ex15.solution.i18ncountries.SingletonConfiguration;

/**
 * Main to launch the WelcomeToMyCountry content in a separate application.
 */
public class MultilingualWelcomeLauncher extends Application {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MultilingualWelcomeLauncher.class);

  ChoiceBox choiceBox = new ChoiceBox<>();
  List<String> countries;
  //ObservableList<Country> countriesObservable;
  ObservableList<String> countriesObservable;
  ObservableList<Country> countriesObservableCountry;
  SingletonConfiguration singletonConfiguration;


  @Override
  public void start(final Stage stage) throws Exception {

    Locale.setDefault(Locale.UK);
    singletonConfiguration = SingletonConfiguration.getInstance();

    // set default/initial locale
    singletonConfiguration.setLocale(
        singletonConfiguration.getCountry2LocaleMap().get(Country.GERMANY));

    CountryKnowledgeContainer container = new I18nKnowledgeGenerator().fillContainer();
    final FxKnowledgePresenter presenter = new FxKnowledgePresenter(container);
    final VBox vBox = new VBox();

    //countries choicebox
    choiceBox = new ChoiceBox();
    List<Country> countries = new ArrayList<>(EnumSet.allOf(Country.class));
    countriesObservableCountry = FXCollections.observableList(
        countries);
    choiceBox.setItems(countriesObservableCountry);
    choiceBox.getSelectionModel().select(0);
    choiceBox.getSelectionModel().selectedIndexProperty().addListener(
        (observable, oldValue, newValue) -> {

          switch (newValue.intValue()) {
            case 0:
              singletonConfiguration.setLocale(Locale.GERMANY);
              break;
            case 1:
              singletonConfiguration.setLocale(Locale.UK);
              break;
            case 2:
              singletonConfiguration.setLocale(new Locale("nl", "NL"));
              break;
            case 3:
              singletonConfiguration.setLocale(new Locale("dk", "DK"));
              break;
            default:
              logger.info("wrong locale");
              break;
          }
        }
    );

    vBox.getChildren().addAll(choiceBox, presenter);
    stage.setScene(new Scene(vBox, 400, 300));
    stage.show();
  }
}
