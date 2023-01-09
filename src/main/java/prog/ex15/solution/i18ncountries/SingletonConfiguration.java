package prog.ex15.solution.i18ncountries;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import prog.ex15.exercise.i18ncountries.Configuration;
import prog.ex15.exercise.i18ncountries.Country;

/**
 * Singleton-based implementation of the Configuration interface.
 */
public class SingletonConfiguration implements Configuration {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SingletonConfiguration.class);

  private static final SingletonConfiguration self = new SingletonConfiguration();
  public static SingletonConfiguration getInstance() {
    return self;
  }

  Map<Country, Locale> countryLocaleMap = new HashMap<>();
  Locale locale;
  PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

  ResourceBundle infos;
  @Override
  public Locale getLocale() {
    return locale;
  }

  @Override
  public void setLocale(final Locale locale) {
    Locale oldLocal = this.locale;
    Locale newLocale = locale;
    this.locale = locale;
    logger.info("proberty changed to " + locale.toString());
    propertyChangeSupport.firePropertyChange("newLocale", oldLocal, locale);
    //System.out.println("proberty changed to " + locale.toString());
  }

  @Override
  public ResourceBundle getTypicalBundle() {



    switch (getLocale().getLanguage()) {
      case "en":
        infos = ResourceBundle.getBundle("prog.ex15.solution.i18ncountries.InfoBundle", Locale.UK);
        break;
      case "de":
        infos = ResourceBundle.getBundle("prog.ex15.solution.i18ncountries.InfoBundle", Locale.GERMANY);
        break;
      case "dk":
        infos = ResourceBundle.getBundle("prog.ex15.solution.i18ncountries.InfoBundle", getCountry2LocaleMap().get(Country.DENMARK));
        break;
      case "nl":
        infos = ResourceBundle.getBundle("prog.ex15.solution.i18ncountries.InfoBundle", getCountry2LocaleMap().get(Country.NETHERLANDS));
        break;

      default:
        logger.info("fgasgdasgdsafgasggdsaqg");
    }
    //ResourceBundle infos = ResourceBundle.getBundle("prog.ex15.solution.i18countries.InfoBundle", Locale.GERMANY);
    //ResourceBundle infos = ResourceBundle.getBundle("prog/ex15/solution/i18countries/InfoBundle", Locale.UK);
    //ResourceBundle infos = ResourceBundle.getBundle("src/main/java/prog/ex15/solution/i18countries/infoBundle2.java", Locale.UK);
    System.out.println(infos);
    return infos;
  }

  @Override
  public ResourceBundle getMessageBundle() {

    ResourceBundle messages = ResourceBundle.getBundle("bundles/i18ncountries", getLocale());
    logger.info(String.valueOf(messages));

    return messages;
  }

  @Override
  public Map<Country, Locale> getCountry2LocaleMap() {
    countryLocaleMap.put(Country.DENMARK, new Locale("dk", "DK"));
    countryLocaleMap.put(Country.GERMANY, Locale.GERMANY);
    countryLocaleMap.put(Country.NETHERLANDS, new Locale("nl", "NL"));
    countryLocaleMap.put(Country.ENGLAND, Locale.UK);
    return countryLocaleMap;
  }

  @Override
  public void addPropertyChangeListener(final PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  @Override
  public void removePropertyChangeListener(final PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }
}
