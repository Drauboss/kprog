package prog.ex03.solution.printer;

/**
 * A ColorPrinter represents, as you may have guessed, a color printer.
 */
public class ColorPrinter extends BasePrinter {

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
