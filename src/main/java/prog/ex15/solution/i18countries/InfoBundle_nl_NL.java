package prog.ex15.solution.i18countries;

import java.time.LocalDate;
import prog.ex15.exercise.i18ncountries.TypicalCountry;

public class InfoBundle_nl_NL implements TypicalCountry {

  /**
   * Setter for the maximum velocity on streets.
   *
   * @param velocity maximum allowed speed. If there is no maximum, the recommended velocity should
   *                 be used.
   * @param unit     unit for the velocity, e.g. "km/h" in Europe, "mph" in USA
   */
  @Override
  public void setVelocity(int velocity, String unit) {

  }

  /**
   * Number of people living in this country.
   *
   * @param population number of people
   */
  @Override
  public void setPopulation(int population) {

  }

  /**
   * Most famous meal the country is known for.
   *
   * @param mostFamousMeal Name of the most famous meal
   */
  @Override
  public void setMostFamousMeal(String mostFamousMeal) {

  }

  /**
   * Most important holiday in this country.
   *
   * @param date        date of the current year the holiday takes place
   * @param holidayName Name of the holiday
   */
  @Override
  public void setMostImportantHoliday(LocalDate date, String holidayName) {

  }
}
