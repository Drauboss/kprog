package prog.ex15.solution.i18ncountries;

import java.text.MessageFormat;
import prog.ex15.exercise.i18ncountries.Category;
import prog.ex15.exercise.i18ncountries.CountryKnowledgeContainer;
import prog.ex15.exercise.i18ncountries.KnowledgeGenerator;
import prog.ex15.exercise.i18ncountries.TypicalCountry;

/**
 * Simple, straight-forward implementation of the KnowledgeGenerator interface for multiple
 * countries.
 */
public class I18nKnowledgeGenerator implements KnowledgeGenerator {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(I18nKnowledgeGenerator.class);


  SingletonConfiguration singletonConfiguration = SingletonConfiguration.getInstance();
  CountryKnowledgeContainer container = new CountryKnowledgeContainer();


  /**
   * method to get the message string with 2 params.
   *
   * @param bundleKey   the bundle key
   * @param firstParam  the first param
   * @param secondParam the second param
   * @return the formatted messages string.
   */
  private String printMessage(final String bundleKey, String firstParam, String secondParam) {
    return MessageFormat.format(singletonConfiguration.getMessageBundle().getString(bundleKey),
        firstParam, secondParam);
  }

  /**
   * method to get the message string with 1 param.
   *
   * @param bundleKey  the bundle key
   * @param firstParam the first param
   * @return the formatted messages string.
   */
  private String printMessage(final String bundleKey, String firstParam) {
    return MessageFormat.format(singletonConfiguration.getMessageBundle().getString(bundleKey),
        firstParam);
  }


  /**
   * add knowledge to container.
   */
  public void addKnowledge() {
    System.out.println("add knowledge");

    logger.info(String.valueOf(singletonConfiguration.getLocale()));
    String velocity = singletonConfiguration.getTypicalBundle().getString(TypicalCountry.VELOCITY);
    String velocityUnit = singletonConfiguration.getTypicalBundle()
        .getString(TypicalCountry.VELOCITY_UNIT);
    String population = singletonConfiguration.getTypicalBundle()
        .getString(TypicalCountry.POPULATION);
    String mostImportantHolidayDate = singletonConfiguration.getTypicalBundle()
        .getString(TypicalCountry.MOST_IMPORTANT_HOLIDAY_DATE);
    String mostImportantHolidayName = singletonConfiguration.getTypicalBundle()
        .getString(TypicalCountry.MOST_IMPORTANT_HOLIDAY_NAME);
    String mostFamousMeal = singletonConfiguration.getTypicalBundle()
        .getString(TypicalCountry.MOST_FAMOUS_MEAL);

    container.addKnowledge(Category.TRAFFIC,
        printMessage("traffic.maximum.speed.highways", velocity, velocityUnit));
    container.addKnowledge(Category.FOOD,
        printMessage("food.most.prominent.food", mostFamousMeal));
    container.addKnowledge(Category.HOLIDAYS,
        printMessage("holiday.most.important.holiday", mostImportantHolidayName,
            mostImportantHolidayDate));
    container.addKnowledge(Category.STATISTICS,
        printMessage("statistics.population", population));

  }


  @Override
  public CountryKnowledgeContainer fillContainer() {
    container.clear();
    addKnowledge();
    return container;
  }
}