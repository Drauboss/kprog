package prog.ex03.solution.printer;

/**
 * A ColorPrinter represents, as you may have guessed, a color printer.
 */
public class ColorPrinter extends BasePrinter {

  /**
   * Constructor for BwPrinter.
   * Save param string in var printerName.
   * Save param b in var printerDuplex.
   *
   * @param b var whether printer is duplex or not.
   * @param string name of printer.
   */
  public ColorPrinter(String string, boolean b) {
    this.printerDuplex = b;
    this.printerName = string;
    //super();
  }

  @Override
  public boolean hasColor() {
    return true;
  }
}
