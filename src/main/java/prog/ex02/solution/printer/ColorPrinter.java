package prog.ex02.solution.printer;

/**
 * Realizes a color Printer.
 */
public class ColorPrinter extends BasePrinter {

  public ColorPrinter(String string, boolean b) {
    this.printerDuplex = b;
  }

  @Override
  public boolean hasColor() {
    return true;
  }
}
