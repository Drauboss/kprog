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

    while (!isShutdown) {

      Task t;
      synchronized (tasks) {
        t = tasks.poll();
      }

      if (t == null) {
        try {
          this.wait();
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






    //try {
    //  currentTask.getRunnable().run();
    //  currentTask.setState(TaskState.SUCCEEDED);
    //} catch (RuntimeException e) {
    //  currentTask.crashed(e);
    //  currentTask.setState(TaskState.SUCCEEDED);
    //}

    //System.out.println(Thread.currentThread());
    //System.out.println("running from simpleworker class");
  }

  @Override
  public void setQueue(final ConcurrentLinkedQueue<Task> queue) {

    //if (queue.isEmpty()) {
    //  try {
    //    Thread.sleep(WAIT_EMPTY_QUEUE);
    //  } catch (InterruptedException e) {
    //    Thread.currentThread().interrupt();
    //  }
    //  setQueue(queue);
    //}


    this.tasks = queue;

    //currentTask = queue.element();
    //currentTask.setState(TaskState.RUNNING);


    //tasks = queue;

  }



  @Override
  public void terminate() {

    try {
      join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    isShutdown = true;

    //interrupt();

  }
}
