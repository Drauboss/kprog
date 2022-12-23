package examples.i18n.listresourcebundles;

import java.util.ListResourceBundle;

public class StatsBundle_fr_FR extends ListResourceBundle {
  private Object[][] contents = {
          {"GDP", 20200},
          {"Population", 58317450},
          {"Literacy", 0.99},
  };

  public Object[][] getContents() {
    return contents;
  }
}

