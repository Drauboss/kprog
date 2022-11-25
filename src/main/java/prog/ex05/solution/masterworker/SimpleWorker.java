package prog.ex05.solution.masterworker;


import java.util.concurrent.ConcurrentLinkedQueue;
import prog.ex05.exercise.masterworker.Task;
import prog.ex05.exercise.masterworker.TaskState;
import prog.ex05.exercise.masterworker.Worker;

/**
 * Simple and straight-forward implementation of the Worker interface.
 */
public class SimpleWorker extends Thread implements Worker {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimpleWorker.class);

  ConcurrentLinkedQueue<Task> tasks;
  Task currentTask;
  //String name;

  boolean isShutdown = false;

  /**
   * A Worker receives tasks to be executed using a queue. Since it is a non-blocking queue a
   * waiting time is defined to be used when the queue is empty.
   */
  public SimpleWorker(String name) {

    setName(name);
    //this.name = name;
  }

  @Override
  public void run() {

    //while (!isInterrupted()) {
    while (!isShutdown) {

      Task t;
      synchronized (tasks) {
        t = tasks.poll();
      }

      if (t == null) {
        try {
          synchronized (tasks) {

            tasks.wait();
          }
          //Thread.sleep(WAIT_EMPTY_QUEUE);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }

      } else {

        try {
          t.getRunnable().run();
          t.setState(TaskState.SUCCEEDED);
        } catch (RuntimeException e) {
          t.setState(TaskState.CRASHED);
          t.crashed(e);
        }
      }
    }
  }

  @Override
  public void setQueue(final ConcurrentLinkedQueue<Task> queue) {

    this.tasks = queue;

  }



  @Override
  public void terminate() {

    synchronized (tasks) {
      tasks.notifyAll();
    }


    isShutdown = true;

    //interrupt();

  }
}
