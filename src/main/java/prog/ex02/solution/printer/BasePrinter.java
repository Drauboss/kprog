package prog.ex02.solution.printer;

import prog.ex02.exercise.printer.Document;
import prog.ex02.exercise.printer.Printer;

/**
 * Realizes a basic Printer implementation.
 */
public abstract class BasePrinter  implements Printer {


  int paper = 0;
  boolean printerDuplex;
  String printerName;

  @Override
  public boolean print(final Document document, final boolean duplex) {

    boolean returnValue = true;
    int paperUsed = 0;

    //if document is null, set returnValue to false
    //if not continue
    if (document == null) {
      returnValue = false;
    } else {

      //calculate amount of paper required for duplex printing
      if (duplex && hasDuplex()) {
        if (document.getPages() % 2 == 0) {
          paperUsed = document.getPages() / 2;
        }
        if (document.getPages() % 2 != 0) {
          paperUsed = document.getPages() / 2 + 1;
        }

      }

      //calculate amount of paper required for simplex printing
      if (!duplex) {
        paperUsed = document.getPages();
      }



      if ((getNumberOfSheetsOfPaper() < paperUsed) //not enough paper OR
          || (duplex && !hasDuplex()) // printer cant print duplex, but document is OR
          || (document.isColor() && !hasColor())) { //document is color, printer cant print color
        returnValue = false;
      } else {
        paper = paper - paperUsed; //if printer is able to print, remove used paper from paper tray
      }

    }



    return returnValue;
  }

  @Override
  public boolean hasDuplex() {
    return printerDuplex;
  }

  @Override
  public String getName() {

    return printerName;
  }

  @Override
  public boolean addPaper(final int numberOfSheets) {

    boolean returnValue = false;

    if (numberOfSheets >= 0) {
      paper = paper + numberOfSheets;
      returnValue = true;
    }


    return returnValue;
  }

  @Override
  public int getNumberOfSheetsOfPaper() {
    return paper;
  }
}
