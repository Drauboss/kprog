package examples.ch14.newsfeedv1g;

/**
 * Demonstrates the AddressBook with a graphical user interface.
 */
public class AddressBookDemoLauncher {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(AddressBookDemoLauncher.class);

  public static void main(String[] args) {
    AddressBookDemo demo = new AddressBookDemo();
    demo.showInterface();
  }
}
