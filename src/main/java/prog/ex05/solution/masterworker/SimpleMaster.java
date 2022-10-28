package prog.ex05.solution.masterworker;

import java.util.ArrayList;
import java.util.HashMap;
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


  int numberOfWorkers;

  ConcurrentLinkedQueue<Task> tasks = new ConcurrentLinkedQueue<>();
  ConcurrentLinkedQueue<Task> allTasks = new ConcurrentLinkedQueue<>();

  HashMap<String, SimpleWorker> workerMap = new HashMap<>();


  /**
   * A Master is the coordinator in the master-worker design pattern.
   * The master receives runnables to be worked on by the worker threads. Depending on the
   * executing machine, the number of worker threads can be parameterized in the constructor
   * of an implementation of the Master interface.
   */
  public SimpleMaster(int numberOfWorkers) {


    if (numberOfWorkers < 1) {
      throw new IllegalArgumentException("numberOfWorkers has to be at min 1");
    }

    for (int i = 0; i < numberOfWorkers; i++) {
      SimpleWorker worker = new SimpleWorker("worker" + (i + 1));
      workerMap.put(worker.getName(), worker);
      worker.setQueue(tasks);  //
      worker.start();
    }
    this.numberOfWorkers = numberOfWorkers;
  }

  @Override
  public Task addTask(final Runnable runnable) throws IllegalArgumentException {

    if (runnable == null) {
      throw new IllegalArgumentException("runnable is null");
    }


    Task t;
    t = new Task(runnable);

    synchronized (tasks) {
      tasks.add(t);
      tasks.notifyAll();
    }


    allTasks.add(t);


    //t.setState(TaskState.SUCCEEDED);


    return t;


  }

  @Override
  public TaskState getTaskState(final int taskId) throws IllegalArgumentException {

    if (taskId < 1) {
      throw new IllegalArgumentException("task id has to be greater than or equal to 1");
    }



    for (Task value : allTasks) {
      if (value.getId() == taskId) {
        return value.getState();
      }
    }

    //if taskId is not found in for loop above, this exception is thrown
    //if taskId is found taskState in above for loop is returned and method ends there
    throw new IllegalArgumentException("taskId is not found");


  }

  @Override
  public Task getTask(final int taskId) throws IllegalArgumentException {

    if (taskId < 1) {
      throw new IllegalArgumentException("task id has to be greater than or equal to 1");
    }

    for (Task value : allTasks) {
      if (value.getId() == taskId) {
        return value;
      }
    }

    //if taskId is not found in for loop above, this exception is thrown
    //if taskId is found, Task in above for loop is returned and method ends there
    throw new IllegalArgumentException("taskId is not found");


  }

  @Override
  public int getNumberOfWorkers() {
    return numberOfWorkers;
  }

  @Override
  public List<String> getWorkerNames() {

    List<String> tmpList = new ArrayList<>();

    for (String i : workerMap.keySet()) {
      tmpList.add(i);
    }

    return tmpList;
  }

  @Override
  public int getNumberOfQueuedTasks() {

    int counter = 0;

    for (Task value : tasks) {
      counter++;
    }

    return counter;
  }

  @Override
  public void shutdown() {
    for (SimpleWorker value : workerMap.values()) {
      value.terminate();
    }
    tasks.clear();
  }

}
