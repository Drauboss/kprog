package prog.ex03.solution.printer;

import prog.ex03.exercise.printer.Document;
import prog.ex03.exercise.printer.Printer;
import prog.ex03.exercise.printer.exceptions.NoColorPrinterException;
import prog.ex03.exercise.printer.exceptions.NoDuplexPrinterException;
import prog.ex03.exercise.printer.exceptions.NotEnoughPaperException;

/**
 * Abstract class for BwPrinter and ColorPrinter.
 */


public abstract class BasePrinter implements Printer {


  int paper = 0;
  boolean printerDuplex;
  String printerName;

  @Override
  public void print(final Document document, final boolean duplex) throws
          IllegalArgumentException, NotEnoughPaperException,
          NoDuplexPrinterException, NoColorPrinterException {

    int paperUsed = 0;
    boolean error = false;

    //if document is null, set returnValue to false
    //if not continue
    if (document == null) {
      throw  new IllegalArgumentException();
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


      if ((getNumberOfSheetsOfPaper() < paperUsed)) {
        throw new NotEnoughPaperException("Not enough Paper", paperUsed - getNumberOfSheetsOfPaper());
      }else{
        error = true;
      }
      if ((duplex && !hasDuplex())) {
        throw new NoDuplexPrinterException("Printer cant print duplex");
      }else{
        error = true;
      }
      if ((document.isColor() && !hasColor())) {
        throw new NoColorPrinterException("Printer cant print color");
      }else{
        error = true;
      }


      //if ((getNumberOfSheetsOfPaper() < paperUsed) //not enough paper OR
      //        || (duplex && !hasDuplex()) // printer cant print duplex, but document is OR
      //        || (document.isColor() && !hasColor())) { //document is color, printer cant print color
      //  returnValue = false;
      //} else {
      //  paper = paper - paperUsed; //if printer is able to print, remove used paper from paper tray
      //}

      if (!error) {
        paper = paper - paperUsed; //if printer is able to print, remove used paper from paper tray
      }

    }
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
  public void addPaper(final int numberOfSheets) throws IllegalArgumentException {

    if (numberOfSheets >= 0) {
      paper = paper + numberOfSheets;
    } else {
      throw new IllegalArgumentException("Illegal Argument!!!");
    }



  }

  @Override
  public int getNumberOfSheetsOfPaper() {
    return paper;
  }
}
