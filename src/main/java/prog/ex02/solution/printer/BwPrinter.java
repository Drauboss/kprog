package prog.ex02.solution.printer;

/**
 * Realizes a Black-And-White Printer.
 */
public class BwPrinter extends BasePrinter {

  public BwPrinter(final String string, final boolean b) {
    this.printerDuplex = b;
    this.printerName = string;
  }

  @Override
  public boolean hasColor() {
    return false;
  }
}
