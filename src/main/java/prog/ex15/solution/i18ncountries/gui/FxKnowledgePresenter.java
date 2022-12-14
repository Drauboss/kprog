package prog.ex15.solution.i18ncountries.gui;

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
import prog.ex15.solution.i18ncountries.I18nKnowledgeGenerator;
import prog.ex15.solution.i18ncountries.SingletonConfiguration;

/**
 * JavaFX component presenting the content of a CountryKnowledgeContainer.
 */
public class FxKnowledgePresenter extends Accordion implements PropertyChangeListener {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(FxKnowledgePresenter.class);

  CountryKnowledgeContainer countryKnowledgeContainer;
  SingletonConfiguration singletonConfiguration = SingletonConfiguration.getInstance();
  I18nKnowledgeGenerator generator;

  /**
   * fxKnowledgePresenter.
   *
   * @param countryKnowledgeContainer container.
   */
  public FxKnowledgePresenter(final CountryKnowledgeContainer countryKnowledgeContainer) {
    this.countryKnowledgeContainer = countryKnowledgeContainer;
    singletonConfiguration.addPropertyChangeListener(this);
    generator = new I18nKnowledgeGenerator();
    fillAccordion();
  }

  private void fillAccordion() {
    this.getPanes().clear();
    for (Category category : Category.values()) {
      TitledPane titledPane = new TitledPane();

      String categoryString = null;
      switch (category) {
        case TRAFFIC:
          categoryString = "categories.TRAFFIC";
          break;
        case FOOD:
          categoryString = "categories.FOOD";
          break;
        case HOLIDAYS:
          categoryString = "categories.HOLIDAYS";
          break;
        case STATISTICS:
          categoryString = "categories.STATISTICS";
          break;
        default:
          logger.info("wrong category");
      }

      titledPane.setText(singletonConfiguration.getMessageBundle().getString(categoryString));

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
   * This method gets called when a bound property is changed. takes the new locale and changes the
   * container to the corresponding container.
   *
   * @param evt A PropertyChangeEvent object describing the event source and the property that has
   *            changed.
   */
  @Override
  public void propertyChange(PropertyChangeEvent evt) {

    String loc = evt.getNewValue().toString();
    logger.info(loc);
    //this.countryKnowledgeContainer = generator.changeContainerTo((Locale) evt.getNewValue());
    this.countryKnowledgeContainer = generator.fillContainer();
    fillAccordion();
  }
}
