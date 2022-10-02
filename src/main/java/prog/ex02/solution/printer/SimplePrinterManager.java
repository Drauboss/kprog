package prog.ex02.solution.printer;

import java.util.ArrayList;
import java.util.List;
import prog.ex02.exercise.printer.Printer;
import prog.ex02.exercise.printer.PrinterManager;


/**
 * Realizes a simple PrintManager.
 */
public class SimplePrinterManager implements PrinterManager {


  List<Printer> printerList = new ArrayList<>();


  @Override
  public Printer getPrinter(final String name) {

    Printer printer = null;
    // iterate trough printerList. If one name of the list entrys matches
    // the name from the param, save in printer variable and return it
    for (Printer value : printerList) {
      if (value.getName().equals(name)) {
        printer = value;
      }
    }
    return printer;
  }

  @Override
  public List<Printer> getAllPrinters() {
    return printerList;
  }

  @Override
  public boolean addPrinter(final Printer printer) {

    boolean returnValue = true;

    //Iterate trough printerList.
    //If one of the names of the printers matches name of printer from param
    //set returnValue to false
    for (Printer value : printerList) {
      if (value.getName().equals(printer.getName())) {
        returnValue = false;
      }
    }
    //if param printer is null reference or the name contains
    //non-printable characters set returnValue to false
    if (printer == null
        || printer.getName().contains("[^\\x00-\\xFF]")) { // try with "\\p{C}"
      returnValue = false;
    }
    //if printer is addable, add it to list
    if (returnValue) {
      printerList.add(printer);
    }

    return returnValue;
  }

  @Override
  public boolean removePrinter(final String name) {

    boolean returnValue = true;

    for (Printer printer : printerList) {
      if (!(printer.getName().equals(name))) {
        returnValue = false;
      }
    }

    if (name == null) {
      returnValue = false;
    }

    if (returnValue) {
      printerList.remove(getPrinter(name));
    }

    return returnValue;
  }

  @Override
  public int getNumberOfColorPrinters() {
    int tmp = 0;
    for (Printer printer : printerList) {
      if (printer.hasColor()) {
        tmp++;
      }
    }
    return tmp;
  }

  @Override
  public int getNumberOfBwPrinters() {
    int tmp = 0;
    for (Printer printer : printerList) {
      if (!(printer.hasColor())) {
        tmp++;
      }
    }
    return tmp;
  }
}
