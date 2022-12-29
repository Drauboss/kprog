package prog.ex15.solution.i18countries.gui;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import livesession.snake.javafx.BoardSizes;
import prog.ex15.exercise.i18ncountries.Country;
import prog.ex15.exercise.i18ncountries.CountryKnowledgeContainer;
import prog.ex15.exercise.i18ncountries.TypicalCountry;
import prog.ex15.solution.i18countries.I18nKnowledgeGenerator;
import prog.ex15.solution.i18countries.SingletonConfiguration;

/**
 * Main to launch the WelcomeToMyCountry content in a separate application.
 */
public class MultilingualWelcomeLauncher extends Application {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(MultilingualWelcomeLauncher.class);

  @Override
  public void start(final Stage stage) throws Exception {
    SingletonConfiguration singletonConfiguration = SingletonConfiguration.getInstance();
    //default locale
    singletonConfiguration.setLocale(Locale.GERMANY);

    CountryKnowledgeContainer container = new I18nKnowledgeGenerator().fillContainer();
    FxKnowledgePresenter presenter = new FxKnowledgePresenter(container);
    VBox vBox = new VBox();



    //System.out.println(singletonConfiguration.getTypicalBundle().getObject(TypicalCountry.VELOCITY));
    //System.out.println(singletonConfiguration.getTypicalBundle().getString(TypicalCountry.VELOCITY));
    //System.out.println(singletonConfiguration.getTypicalBundle().getString(TypicalCountry.VELOCITY_UNIT));
    //System.out.println(singletonConfiguration.getTypicalBundle().getString(TypicalCountry.MOST_FAMOUS_MEAL));
    //System.out.println(singletonConfiguration.getTypicalBundle().getString(TypicalCountry.MOST_IMPORTANT_HOLIDAY_DATE));
    //System.out.println(singletonConfiguration.getTypicalBundle().getString(TypicalCountry.MOST_IMPORTANT_HOLIDAY_NAME));





    ChoiceBox choiceBox = new ChoiceBox();


    List<Country> countries = new ArrayList<>(EnumSet.allOf(Country.class));
    ObservableList<Country> countriesObservable = FXCollections.observableList(
        countries);
    choiceBox.setItems(countriesObservable);

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
        new ChangeListener<Number>() {
          @Override
          public void changed(ObservableValue<? extends Number> observable, Number oldValue,
              Number newValue) {

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
            }

          }
        }
    );


    vBox.getChildren().addAll(choiceBox, presenter);
    stage.setScene(new Scene(vBox, 400, 300));
    stage.show();
  }
}
