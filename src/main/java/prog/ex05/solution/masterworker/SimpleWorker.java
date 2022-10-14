package prog.ex05.solution.masterworker;


import java.util.concurrent.ConcurrentLinkedQueue;
import prog.ex05.exercise.masterworker.Task;
import prog.ex05.exercise.masterworker.Worker;

/**
 * Simple and straight-forward implementation of the Worker interface.
 */
public class SimpleWorker extends Thread implements Worker {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimpleWorker.class);

  ConcurrentLinkedQueue<Task> tasks;
  String name;

  public SimpleWorker(String name) {

    this.name = name;
  }

  @Override
  public void setQueue(final ConcurrentLinkedQueue<Task> queue) {

    tasks = queue;

  }



  @Override
  public void terminate() {

  }
}
