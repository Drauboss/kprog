package prog.ex05.solution.masterworker;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import prog.ex05.exercise.masterworker.Master;
import prog.ex05.exercise.masterworker.Task;
import prog.ex05.exercise.masterworker.TaskState;

/**
 * Simple and straight-forward implementation of the Master interface.
 */
public class SimpleMaster implements Master {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimpleMaster.class);


  int numberOfWorkers = 0;

  ConcurrentLinkedQueue<Task> tasks;
  Task t;

  public SimpleMaster(int numberOfWorkers) {
    //TODO: hier worker anlegen
    for (int i = 0; i < numberOfWorkers; i++) {
      SimpleWorker worker = new SimpleWorker("worker" + (i + 1));
    }
    this.numberOfWorkers = numberOfWorkers;
  }

  @Override
  public Task addTask(final Runnable runnable) throws IllegalArgumentException {

    t = new Task(runnable);


    return t;
  }

  @Override
  public TaskState getTaskState(final int taskId) throws IllegalArgumentException {
    return getTask(taskId).getState();
  }

  @Override
  public Task getTask(final int taskId) throws IllegalArgumentException {
    return null;
  }

  @Override
  public int getNumberOfWorkers() {
    return numberOfWorkers;
  }

  @Override
  public List<String> getWorkerNames() {
    return null;
  }

  @Override
  public int getNumberOfQueuedTasks() {
    return 0;
  }

  @Override
  public void shutdown() {
  }
}
