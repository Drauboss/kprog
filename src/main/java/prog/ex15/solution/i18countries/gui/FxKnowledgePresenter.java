package prog.ex15.solution.i18countries.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Locale;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import prog.ex15.exercise.i18ncountries.Category;
import prog.ex15.exercise.i18ncountries.CountryKnowledgeContainer;
import prog.ex15.solution.i18countries.I18nKnowledgeGenerator;
import prog.ex15.solution.i18countries.SingletonConfiguration;

/**
 * JavaFX component presenting the content of a CountryKnowledgeContainer.
 */
public class FxKnowledgePresenter extends Accordion implements PropertyChangeListener {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(FxKnowledgePresenter.class);

  CountryKnowledgeContainer countryKnowledgeContainer;
  SingletonConfiguration singletonConfiguration = SingletonConfiguration.getInstance();
  I18nKnowledgeGenerator generator;
  public FxKnowledgePresenter(final CountryKnowledgeContainer countryKnowledgeContainer) {
    this.countryKnowledgeContainer = countryKnowledgeContainer;
    singletonConfiguration.addPropertyChangeListener(this::propertyChange);

    generator = new I18nKnowledgeGenerator();
    fillAccordion();
  }

  private void fillAccordion() {
    this.getPanes().clear();
    for (Category category : Category.values()) {
      TitledPane titledPane = new TitledPane();
      titledPane.setText(category.toString());
      List<String> knowledgeList = countryKnowledgeContainer.getKnowledge(category);
      VBox box = new VBox();
      for (String string : knowledgeList) {
        box.getChildren().add(new Label(string));
        logger.info("Adding label " + string);
      }
      titledPane.setContent(box);
      this.getPanes().add(titledPane);
    }
  }

  /**
   * This method gets called when a bound property is changed.
   *
   * @param evt A PropertyChangeEvent object describing the event source and the property that has
   *            changed.
   */
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    System.out.println("fillAccordion()");


    this.countryKnowledgeContainer = generator.changeContainerTo((Locale) evt.getNewValue());
    fillAccordion();
  }
}
