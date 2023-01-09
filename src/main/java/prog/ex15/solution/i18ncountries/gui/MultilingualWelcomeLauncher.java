package prog.ex15.solution.i18ncountries.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
public class MultilingualWelcomeLauncher extends Application implements PropertyChangeListener {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MultilingualWelcomeLauncher.class);

  ChoiceBox choiceBox = new ChoiceBox<>();
  List<String> countries;
  //ObservableList<Country> countriesObservable;
  ObservableList<String> countriesObservable;
  ObservableList<Country> countriesObservableCountry;
  SingletonConfiguration singletonConfiguration;

  public void changeChoiceBoxLanguage() {
    choiceBox = new ChoiceBox<>();
    countries.clear();
    logger.info("changeChoiceBoxLang" + singletonConfiguration.getLocale());
    countries.add(singletonConfiguration.getMessageBundle().getString("country.GERMANY"));
    countries.add(singletonConfiguration.getMessageBundle().getString("country.ENGLAND"));
    countries.add(singletonConfiguration.getMessageBundle().getString("country.NETHERLANDS"));
    countries.add(singletonConfiguration.getMessageBundle().getString("country.DENMARK"));

    countriesObservable = FXCollections.observableList(
        countries);
    choiceBox.setItems(countriesObservable);
    //choiceBox.getSelectionModel().select(0);

    choiceBox.getSelectionModel().selectedIndexProperty().addListener(
        (observable, oldValue, newValue) -> {

          logger.info(String.valueOf(newValue.intValue()));

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
              logger.info("wrong");
              break;
          }

        }
    );

  }

  @Override
  public void start(final Stage stage) throws Exception {

    Locale.setDefault(Locale.UK);
    singletonConfiguration = SingletonConfiguration.getInstance();
    //default locale
    singletonConfiguration.setLocale(
        singletonConfiguration.getCountry2LocaleMap().get(Country.GERMANY));
    System.out.println(singletonConfiguration.getMessageBundle().getString("country.GERMANY"));

    //countries = new ArrayList<>();

    singletonConfiguration.addPropertyChangeListener(this);

    CountryKnowledgeContainer container = new I18nKnowledgeGenerator().fillContainer();
    FxKnowledgePresenter presenter = new FxKnowledgePresenter(container);
    VBox vBox = new VBox();

    //System.out.println(singletonConfiguration.getTypicalBundle().getObject(TypicalCountry.VELOCITY));
    //System.out.println(singletonConfiguration.getTypicalBundle().getString(TypicalCountry.VELOCITY));
    //System.out.println(singletonConfiguration.getTypicalBundle().getString(TypicalCountry.VELOCITY_UNIT));
    //System.out.println(singletonConfiguration.getTypicalBundle().getString(TypicalCountry.MOST_FAMOUS_MEAL));
    //System.out.println(singletonConfiguration.getTypicalBundle().getString(TypicalCountry.MOST_IMPORTANT_HOLIDAY_DATE));
    //System.out.println(singletonConfiguration.getTypicalBundle().getString(TypicalCountry.MOST_IMPORTANT_HOLIDAY_NAME));

    //changeChoiceBoxLanguage();

    //List<String>
    //countries2.add(singletonConfiguration.getMessageBundle().getString("country.GERMANY"));
    //countries2Observable = FXCollections.observableList(
    //    countries2);
//
    choiceBox = new ChoiceBox();
//
    List<Country> countries = new ArrayList<>(EnumSet.allOf(Country.class));
//
    countriesObservableCountry = FXCollections.observableList(
        countries);
    choiceBox.setItems(countriesObservableCountry);

    //List<String> locales = new ArrayList<>();
    //locales.add(Locale.UK.getDisplayCountry());
    //locales.add(Locale.GERMANY.getDisplayCountry());
    //locales.add(new Locale("dk", "DK").getDisplayCountry());
    //locales.add(new Locale("nl", "NL").getDisplayCountry());
    //ObservableList<String> localesObservable = FXCollections.observableList(
    //    locales);
    //choiceBox.setItems(localesObservable);

    choiceBox.getSelectionModel().select(0);
    choiceBox.getSelectionModel().selectedIndexProperty().addListener(
        (observable, oldValue, newValue) -> {

          logger.info(String.valueOf(newValue.intValue()));

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
              logger.info("wrong");
              break;
          }

        }
    );

    vBox.getChildren().addAll(choiceBox, presenter);
    stage.setScene(new Scene(vBox, 400, 300));
    stage.show();
  }

  /**
   * This method gets called when a bound property is changed.
   *
   * @param evt A PropertyChangeEvent object describing the event source and the property that has
   *            changed.
   */
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    //changeChoiceBoxLanguage();
    logger.info("in der main prob change");
  }
}
