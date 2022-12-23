package examples.i18n.compountmessages;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Demonstrates compound messages in I18N.
 */
public class DemoI18NCompoundMessages {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(DemoI18NCompoundMessages.class);

  private final NumberFormat numberFormat;
  private final DateTimeFormatter dateTimeFormatter;
  private final ResourceBundle bundle;

  /**
   * Constructor.
   *
   * @param locale locale of the preferred language / country to control the generation of messages
   */
  public DemoI18NCompoundMessages(Locale locale) {
    bundle = ResourceBundle.getBundle("bundles/compoundMessages", locale);
    numberFormat = NumberFormat.getNumberInstance(locale);
    dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale);
  }

  private String printMessage(final String bundleKey, final int noPeople, final LocalDate date) {
    return MessageFormat.format(
            bundle.getString(bundleKey),
            numberFormat.format(noPeople),
            dateTimeFormatter.format(date));
  }

  /**
   * main.
   *
   * @param args no args expected
   */
  public static void main(String[] args) {
    int noPeople = 405390;
    LocalDate date = LocalDate.of(2009, 1, 1);


    DemoI18NCompoundMessages demoDE = new DemoI18NCompoundMessages(Locale.GERMANY);
    DemoI18NCompoundMessages demoUS = new DemoI18NCompoundMessages(Locale.US);

    System.out.println("US: "
            + demoUS.printMessage("people.have.seen.our.website.since", noPeople, date));
    System.out.println("Germany: "
            + demoDE.printMessage("people.have.seen.our.website.since", noPeople, date));
  }
}

