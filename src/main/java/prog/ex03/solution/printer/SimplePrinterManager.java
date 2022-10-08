package prog.ex03.solution.printer;

import java.util.ArrayList;
import java.util.List;
import prog.ex03.exercise.printer.Printer;
import prog.ex03.exercise.printer.PrinterManager;
import prog.ex03.exercise.printer.exceptions.PrinterAlreadyRegisteredException;
import prog.ex03.exercise.printer.exceptions.PrinterNotRegisteredException;


/**
 * Implements the PrinterManager in a simple and straightforward way.
 */
public class SimplePrinterManager implements PrinterManager {


  List<Printer> printerList = new ArrayList<>();

  @Override
  public Printer getPrinter(final String name)
          throws IllegalArgumentException, PrinterNotRegisteredException {

    Printer printer = null;

    //if param name is null reference or the name contains
    //non-printable characters throw IllegalArgumentException
    if (name == null
        || name.isBlank()
        || name.contains("[^\\x00-\\xFF]")) {
      throw new IllegalArgumentException("Printername is null or contains non readable chars!");
    }


    // iterate trough printerList. If one name of the list entrys matches
    // the name from the param, save in printer variable and return it
    for (Printer value : printerList) {
      if (value.getName().equals(name)) {
        printer = value;
      }
    }

    //if printer is not registered and therefore not in printerList, variable printer is still
    // assigned to null, so PrinterNotRegisteredException is thrown
    if (printer == null) {
      throw new PrinterNotRegisteredException("Printer is not registered!");
    }


    return printer;
  }

  @Override
  public List<Printer> getAllPrinters() {
    return printerList;
  }

  @Override
  public void addPrinter(final Printer printer)
          throws IllegalArgumentException, PrinterAlreadyRegisteredException {


    //Iterate trough printerList.
    //If one of the names of the printers matches name of printer from param
    //throw new PrinterAlreadyRegisteredException
    for (Printer value : printerList) {
      if (value.getName().equals(printer.getName())) {
        throw new PrinterAlreadyRegisteredException("Printer with this name already registered!");
      }
    }

    //if param printer is null reference or the name contains
    //non-printable characters throw new IllegalArgumentException
    if (printer == null
        || printer.getName().isBlank()
        || printer.getName().contains("[^\\x00-\\xFF]")) { // try with "\\p{C}"
      throw new IllegalArgumentException("Printername is null or contains non readable chars!");
    }

    //when adding printing is possible, no exception is thrown so this code is executed

    printerList.add(printer); //if printer is addable, add it to list

  }

  @Override
  public void removePrinter(final String name)
          throws IllegalArgumentException, PrinterNotRegisteredException {

    boolean found = false;
    //Iterate trough printerList.
    //If the to be removed printer does not exist in list
    //throw new PrinterNotRegisteredException
    for (Printer printer : printerList) {
      if ((printer.getName().equals(name))) {
        printerList.remove(getPrinter(name));
        found = true;
      }
    }

    if (!found) {
      throw new PrinterNotRegisteredException("Printer to be removed does not exist");
    }

    //if param printer is null reference or the name contains
    //non-printable characters throw new IllegalArgumentException
    if (name == null
        || name.isBlank()
        || name.contains("[^\\x00-\\xFF]")) { // try with "\\p{C}"
      throw new IllegalArgumentException("Printername is null or contains non readable chars!");
    }

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
