package examples.i18n.listresourcebundles;

import java.util.ListResourceBundle;

public class StatsBundle_en_CA extends ListResourceBundle {
  private Object[][] contents = {
          {"GDP", 24400},
          {"Population", 28802671},
          {"Literacy", 0.97},
  };

  public Object[][] getContents() {
    return contents;
  }
}

