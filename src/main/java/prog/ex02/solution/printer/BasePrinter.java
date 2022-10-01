package prog.ex02.solution.printer;

import prog.ex02.exercise.printer.Document;
import prog.ex02.exercise.printer.Printer;

/**
 * Realizes a basic Printer implementation.
 */
public abstract class BasePrinter  implements Printer {


  int paper = 0;
  boolean printerDuplex;

  @Override
  public boolean print(final Document document, final boolean duplex) {

    boolean returnValue = true;
    int paperUsed = 0;


    //if document is duplex and printer can print duplex
    //and pages of document is even
    //remove only half of document pages from paper tray
    //else if document is duplex and printer can print duplex
    //and pages of document is odd
    // remove only half of document pages from paper tray
    //and add 1
    //if (duplex && hasDuplex() && document.getPages() % 2 == 0) {
    //  paperUsed = document.getPages() / 2;
    //  paper = paper - paperUsed;
    //} else if (duplex && hasDuplex() && document.getPages() % 2 != 0) {
    //  paperUsed = document.getPages() / 2 + 1;
    //  paper = paper - paperUsed;
    //}

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


    //if (getNumberOfSheetsOfPaper() < paperUsed) {
    //  returnValue = false;
    //  System.out.println("bla");
    //  System.out.println(getNumberOfSheetsOfPaper());
    //  System.out.println(paperUsed);
    //} else {
    //  paper = paper - paperUsed;
    //}

    //if (duplex && !hasDuplex()) {
    //  returnValue = false;
    //  //System.out.println("bla");
    //}
    //if (document.isColor() && !hasColor()) {
    //  returnValue = false;
    //  //System.out.println("bla");
    //}

    if ((getNumberOfSheetsOfPaper() < paperUsed) //not enough paper OR
        || (duplex && !hasDuplex()) // printer cant print duplex, but document is OR
        || (document.isColor() && !hasColor())) { //document is color, printer cant print color
      returnValue = false;
    } else {
      paper = paper - paperUsed; //if printer is able to print, remove used paper from paper tray
    }

    return returnValue;
  }

  @Override
  public boolean hasDuplex() {
    return printerDuplex;
  }

  @Override
  public String getName() {
    return null;
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
