module kprog.main {
  requires java.desktop;
  requires org.slf4j;
  requires javafx.controls;
  requires javafx.fxml;
  requires java.rmi;
  exports environment.testprograms;
  exports examples.javafx.bidirectional;
  exports examples.javafx.firststeps;
  exports examples.javafx.fxml;
  exports examples.javafx.listcell;
  exports examples.javafx.misc;
  exports examples.javafx.modal;
  exports examples.javafx.mvvm;
  exports examples.javafx.observable;
  exports examples.javafx.addressbook;
  exports examples.io;
  exports examples.i18n;
  exports examples.dateandtime;
  exports examples.designpattern.observer;
  exports examples.designpattern.singleton;
  exports prog.javafx.main;
  exports prog.ex10.solution.javafx4pizzadelivery.gui;
  exports prog.ex15.monolingual.gui;
<<<<<<< HEAD
  exports prog.ex15.solution.i18ncountries.gui;
  exports prog.ex15.solution.i18ncountries;
  exports prog.ex15.exercise.i18ncountries;
=======
  exports examples.rmi.shapes;
>>>>>>> d5d50a3192d7b103eb2036422e6be285303a1560
  opens examples.javafx.fxml to javafx.fxml;
  opens prog.ex10.solution.javafx4pizzadelivery.gui to javafx.fxml;
  exports livesession.snake.javafx;
  opens livesession.snake.javafx to javafx.fxml;
  exports prog.ex15.solution.i18ncountries.gui;
  exports prog.ex15.solution.i18ncountries;
  exports prog.ex15.exercise.i18ncountries;
}
