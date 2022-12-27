package prog.ex15.solution.i18countries;

import static prog.ex15.exercise.i18ncountries.TypicalCountry.MOST_FAMOUS_MEAL;

import java.util.Locale;
import java.util.ResourceBundle;
import prog.ex15.exercise.i18ncountries.Category;
import prog.ex15.exercise.i18ncountries.CountryKnowledgeContainer;
import prog.ex15.exercise.i18ncountries.KnowledgeGenerator;

/**
 * Simple, straight-forward implementation of the KnowledgeGenerator interface for multiple
 * countries.
 */
public class I18nKnowledgeGenerator implements KnowledgeGenerator {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(I18nKnowledgeGenerator.class);

  SingletonConfiguration singletonConfiguration = SingletonConfiguration.getInstance();
  @Override
  public CountryKnowledgeContainer fillContainer() {
    CountryKnowledgeContainer container = new CountryKnowledgeContainer();
    singletonConfiguration.setLocale(Locale.UK);

    //ResourceBundle bundle = new InfoBundle();
    //bundle.getObject(MOST_FAMOUS_MEAL);

    //container.addKnowledge(Category.FOOD,
    //    (String) singletonConfiguration.getTypicalBundle().getObject(MOST_FAMOUS_MEAL));
    container.addKnowledge(Category.FOOD, "(String) bundle.getObject(MOST_FAMOUS_MEAL)");


    //container.addKnowledge(Category.TRAFFIC, "Maximum speed on highways is 70 mph.");
    //container.addKnowledge(Category.FOOD, "Our most prominent food is Fish and Chips.");
    //container.addKnowledge(Category.HOLIDAYS,
    //    "Our most important holiday is  Brexit Day (Joke) on January, the 1, 2022.");
    //container.addKnowledge(Category.STATISTICS, "Our population is 66.500.000");
    return container;
  }
}
