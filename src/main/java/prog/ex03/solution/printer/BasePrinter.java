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


    //if document is null, throw IllegalArgumentException
    if (document == null) {
      throw  new IllegalArgumentException("Document object is null");
    }

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

    //not enough paper, throw NotEnoughPaperException
    if ((getNumberOfSheetsOfPaper() < paperUsed)) {
      throw new NotEnoughPaperException("Not enough Paper", paperUsed - getNumberOfSheetsOfPaper());
    }

    // printer cant print duplex, but document is
    //throw NoDuplexPrinterException
    if ((duplex && !hasDuplex())) {
      throw new NoDuplexPrinterException("Printer cant print duplex");
    }

    //document is color, printer cant print color
    //throw NoColorPrinterException
    if ((document.isColor() && !hasColor())) {
      throw new NoColorPrinterException("Printer cant print color");
    }

    // when printing is possible, no exception is thrown so this code is executed
    paper = paper - paperUsed; //remove used paper from paper tray

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
      throw new IllegalArgumentException("Cant add less paper than 0");
    }
  }

  @Override
  public int getNumberOfSheetsOfPaper() {
    return paper;
  }
}
