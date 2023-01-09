package prog.ex15.solution.i18ncountries;

import java.text.MessageFormat;
import java.util.Locale;
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
  Locale locale;
  CountryKnowledgeContainer container = new CountryKnowledgeContainer();

  public I18nKnowledgeGenerator() {


  }

  private String printMessage(final String bundleKey, String firstParam, String secondParam) {
    return MessageFormat.format(singletonConfiguration.getMessageBundle().getString(bundleKey), firstParam, secondParam);
  }
  private String printMessage(final String bundleKey, String firstParam) {
    return MessageFormat.format(singletonConfiguration.getMessageBundle().getString(bundleKey), firstParam);
  }

  public void addUkKnowledge() {

    System.out.println("add uk knowledge");
    //System.out.println(singletonConfiguration.getLocale());
    //System.out.println(Locale.getDefault());
    //Locale.setDefault(Locale.US);
    //System.out.println(Locale.getDefault());
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
        printMessage("holiday.most.important.holiday", mostImportantHolidayName, mostImportantHolidayDate));
    container.addKnowledge(Category.STATISTICS,
        printMessage("statistics.population", population));

  }

  public void addGermanyKnowledge() {
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
        printMessage("holiday.most.important.holiday", mostImportantHolidayName, mostImportantHolidayDate));
    container.addKnowledge(Category.STATISTICS,
        printMessage("statistics.population", population));
  }

  public void addDenmarkKnowledge() {
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
        printMessage("holiday.most.important.holiday", mostImportantHolidayName, mostImportantHolidayDate));
    container.addKnowledge(Category.STATISTICS,
        printMessage("statistics.population", population));
  }

  public void addNetherlandsKnowledge() {
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
        printMessage("holiday.most.important.holiday", mostImportantHolidayName, mostImportantHolidayDate));
    container.addKnowledge(Category.STATISTICS,
        printMessage("statistics.population", population));
  }

  public CountryKnowledgeContainer changeContainerTo(Locale locale) {

    container.clear();
    logger.info(String.valueOf(locale));
    logger.info(String.valueOf(locale.getCountry()));
    switch (locale.getCountry()) {
      case "GB":
        addUkKnowledge();
        break;
      case "DE":
        addGermanyKnowledge();
        break;
      case "NL":
        addNetherlandsKnowledge();
        break;
      case "DK":
        addDenmarkKnowledge();
        break;
      default:
        logger.info("wrong locale switch case");

    }

    return container;
  }

  @Override
  public CountryKnowledgeContainer fillContainer() {

    addGermanyKnowledge();
    //addUkKnowledge();
    //initial UK knowledge

    //singletonConfiguration.addPropertyChangeListener(this::propertyChange);

    //System.out.println("contaeiner filled");
    //locale = singletonConfiguration.getLocale();

    //container.addKnowledge(Category.FOOD, locale.getDisplayCountry());

    //ResourceBundle bundle = new InfoBundle();
    //bundle.getObject(MOST_FAMOUS_MEAL);

    //container.addKnowledge(Category.FOOD,
    //    (String) singletonConfiguration.getTypicalBundle().getObject(MOST_FAMOUS_MEAL));
    //container.addKnowledge(Category.FOOD, "(String) bundle.getObject(MOST_FAMOUS_MEAL)");

    //container.addKnowledge(Category.TRAFFIC, "Maximum speed on highways is 70 mph.");
    //container.addKnowledge(Category.FOOD, "Our most prominent food is Fish and Chips.");
    //container.addKnowledge(Category.HOLIDAYS,
    //    "Our most important holiday is  Brexit Day (Joke) on January, the 1, 2022.");
    //container.addKnowledge(Category.STATISTICS, "Our population is 66.500.000");
    return container;
  }

  /**
   * This method gets called when a bound property is changed.
   *
   * @param evt A PropertyChangeEvent object describing the event source and the property that has
   *            changed.
   */
  //@Override
  //public void propertyChange(PropertyChangeEvent evt) {
  //  System.out.println("dadadsdsaq");
  //  evt.getNewValue();
  //  this.locale = (Locale) evt.getNewValue();
  //  System.out.println("fsafdafds" + this.locale);
//
//
  //}
}
