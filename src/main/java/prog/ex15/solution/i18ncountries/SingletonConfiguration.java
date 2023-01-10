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
    this.locale = newLocale;
    logger.info("proberty changed to " + locale.toString());
    propertyChangeSupport.firePropertyChange("newLocale", oldLocal, locale);

  }

  @Override
  public ResourceBundle getTypicalBundle() {
    infos = ResourceBundle.getBundle("prog.ex15.solution.i18ncountries.InfoBundle", getLocale());

    return infos;
  }

  @Override
  public ResourceBundle getMessageBundle() {
    ResourceBundle messages = ResourceBundle.getBundle("bundles/i18ncountries", getLocale());

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