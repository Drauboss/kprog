package prog.ex03.solution.printer;

/**
 * A BwPrinter represents a black-and-white printer.
 */
public class BwPrinter extends BasePrinter {

  public BwPrinter(final String string, final boolean b) {
    this.printerDuplex = b;
    this.printerName = string;
    //super();
  }

  @Override
  public boolean hasColor() {
    return false;
  }
}
