import java.util.concurrent.TimeUnit;

public class TimeWatch {    
  long starts;

  public static TimeWatch start() {
      return new TimeWatch();
  }

  private TimeWatch() {
      reset();
  }

  public TimeWatch reset() {
      starts = System.currentTimeMillis();
      return this;
  }

  public long time() {
      long ends = System.currentTimeMillis();
      return ends - starts;
  }

  public long time(TimeUnit unit) {
      return unit.convert(time(), TimeUnit.MILLISECONDS);
  }

  public static void main(String[] args)
  {
    TimeWatch watch = TimeWatch.start();
    for (int i = 0; i < 1000000; i++)
    {
      try
      {
          Thread.sleep(1000);
      }
      catch (InterruptedException e) {}
      long passedTimeInSeconds = watch.time(TimeUnit.SECONDS);
      System.out.println("Time elapsed: " + passedTimeInSeconds);
    }
  }
}