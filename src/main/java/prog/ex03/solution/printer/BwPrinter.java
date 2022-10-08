package prog.ex03.solution.printer;

/**
 * A BwPrinter represents a black-and-white printer.
 */
public class BwPrinter extends BasePrinter {

  /**
   * Constructor for BwPrinter.
   * Save param string in var printerName.
   * Save param b in var printerDuplex.
   *
   * @param b var whether printer is duplex or not.
   * @param string name of printer.
   */
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
