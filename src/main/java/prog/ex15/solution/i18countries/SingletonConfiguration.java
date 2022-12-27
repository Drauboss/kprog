package prog.ex15.solution.i18countries;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeListenerProxy;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import prog.ex10.solution.javafx4pizzadelivery.gui.SingletonAttributeStore;
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

  @Override
  public Locale getLocale() {
    return locale;
  }

  @Override
  public void setLocale(final Locale locale) {

    this.locale = locale;
  }

  @Override
  public ResourceBundle getTypicalBundle() {

    ResourceBundle infos = ResourceBundle.getBundle("prog.ex15.solution.i18countries.InfoBundle", getLocale());
    return infos;
  }

  @Override
  public ResourceBundle getMessageBundle() {

    return null;
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

  }

  @Override
  public void removePropertyChangeListener(final PropertyChangeListener listener) {
  }
}
