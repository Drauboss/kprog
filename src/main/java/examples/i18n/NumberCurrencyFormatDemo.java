package examples.i18n;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberCurrencyFormatDemo {

  static public void displayInteger(Locale currentLocale) {

    Integer quantity = 123456;
    Double amount = 345987.246;

    NumberFormat numberFormatter = NumberFormat.getIntegerInstance(currentLocale);
    String quantityFormatted = numberFormatter.format(quantity);
    String amountFormatted = numberFormatter.format(amount);
    System.out.println(quantityFormatted + "   " + currentLocale.toString());
    System.out.println(amountFormatted + "   " + currentLocale.toString());
  }


  static public void displayNumber(Locale currentLocale) {

    Integer quantity = 123456;
    Double amount = 345987.246;

    NumberFormat numberFormatter = NumberFormat.getNumberInstance(currentLocale);
    String quantityFormatted = numberFormatter.format(quantity);
    String amountFormatted = numberFormatter.format(amount);
    System.out.println(quantityFormatted + "   " + currentLocale.toString());
    System.out.println(amountFormatted + "   " + currentLocale.toString());
  }

  static public void displayCurrency(Locale currentLocale) {

    Double currency = 9876543.21;

    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
    String currencyFormatted = currencyFormatter.format(currency);
    System.out.println(currencyFormatted + "   " + currentLocale.toString());
  }

  static public void displayPercent(Locale currentLocale) {

    Double percent = 0.75;

    NumberFormat percentFormatter = NumberFormat.getPercentInstance(currentLocale);
    String percentFormatted = percentFormatter.format(percent);
    System.out.println(percentFormatted + "   " + currentLocale.toString());
  }

  static public void main(String[] args) {

    Locale[] locales = {
            new Locale("en", "US"),
            new Locale("de", "DE"),
            new Locale("fr", "FR")
    };

    for (int i = 0; i < locales.length; i++) {
      System.out.println();
      displayInteger(locales[i]);
      displayNumber(locales[i]);
      displayCurrency(locales[i]);
      displayPercent(locales[i]);
    }
  }

}
