package prog.ex15.solution.i18ncountries;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ListResourceBundle;
import java.util.Locale;
import prog.ex15.exercise.i18ncountries.TypicalCountry;

public class InfoBundle_de_DE extends ListResourceBundle implements TypicalCountry {


  private int velocity;
  private String velocityUnit;
  private String population;
  private String mostImportantHolidayDate;
  private String mostImportantHolidayName;
  private String mostFamousMeal;
  //Object[][] contents;
  Locale locale;




  Object[][] contents = {
      {VELOCITY, velocity},
      {VELOCITY_UNIT, velocityUnit},
      {POPULATION, population},
      {MOST_IMPORTANT_HOLIDAY_DATE, mostImportantHolidayDate},
      {MOST_IMPORTANT_HOLIDAY_NAME, mostImportantHolidayName},
      {MOST_FAMOUS_MEAL, mostFamousMeal}
  };





  public InfoBundle_de_DE() {
    setVelocity(130, "km/h");
    setPopulation(83200000);
    setMostFamousMeal("Eisbein mit Sauerkraut");
    setMostImportantHoliday(LocalDate.parse("2022-10-03"), "Tag der Deutschen Einheit");
    locale = Locale.GERMANY;

    //getContents()[0][0] = 70;

  }

  @Override
  public Locale getLocale() {
    return locale;
  }

  /**
   * Setter for the maximum velocity on streets.
   *
   * @param velocity maximum allowed speed. If there is no maximum, the recommended velocity should
   *                 be used.
   * @param unit     unit for the velocity, e.g. "km/h" in Europe, "mph" in USA
   */
  @Override
  public void setVelocity(int velocity, String unit) {
    this.velocity = velocity;
    velocityUnit = unit;

    getContents()[0][1] = String.valueOf(this.velocity);
    getContents()[1][1] = unit;
  }

  /**
   * Number of people living in this country.
   *
   * @param population number of people
   */
  @Override
  public void setPopulation(int population) {
    NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.UK);
    this.population = numberFormat.format(population);

    getContents()[2][1] = this.population;

  }

  /**
   * Most famous meal the country is known for.
   *
   * @param mostFamousMeal Name of the most famous meal
   */
  @Override
  public void setMostFamousMeal(String mostFamousMeal) {

    this.mostFamousMeal = mostFamousMeal;
    getContents()[5][1] = this.mostFamousMeal;
  }

  /**
   * Most important holiday in this country.
   *
   * @param date        date of the current year the holiday takes place
   * @param holidayName Name of the holiday
   */
  @Override
  public void setMostImportantHoliday(LocalDate date, String holidayName) {

    DateTimeFormatter dtf =
        DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.GERMANY);

    mostImportantHolidayDate = dtf.format(date);
    mostImportantHolidayName = holidayName;


    getContents()[3][1] = mostImportantHolidayDate;
    getContents()[4][1] = mostImportantHolidayName;


  }






  /**
   * Returns an array in which each item is a pair of objects in an
   * <code>Object</code> array. The first element of each pair is
   * the key, which must be a <code>String</code>, and the second element is the value associated
   * with that key.  See the class description for details.
   *
   * @return an array of an <code>Object</code> array representing a key-value pair.
   */
  @Override
  public Object[][] getContents() {
    return contents;
  }
}
