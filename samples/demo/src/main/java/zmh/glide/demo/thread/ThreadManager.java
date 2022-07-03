package zmh.glide.demo.thread;


import static java.util.concurrent.TimeUnit.SECONDS;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author skymhzhang
 */
public class ThreadManager {
  private static final ThreadPoolExecutor EXECUTOR;
  private static final Handler HANDLER = new Handler(Looper.getMainLooper());

  static {
    EXECUTOR = new ThreadPoolExecutor(2,
        10,
        0,
        SECONDS,
        new ArrayBlockingQueue<>(100),
        new DefaultThreadFactory());
  }

  public static void execute(Runnable runnable) {
    EXECUTOR.execute(runnable);
  }

  private static class DefaultThreadFactory implements ThreadFactory {
    private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    DefaultThreadFactory() {
      SecurityManager s = System.getSecurityManager();
      group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
      namePrefix = "ThreadManager-" + POOL_NUMBER.getAndIncrement() + "-thread-";
    }

    @Override
    public Thread newThread(@NonNull Runnable r) {
      Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
      if (t.isDaemon()) {
        t.setDaemon(false);
      }
      if (t.getPriority() != Thread.NORM_PRIORITY) {
        t.setPriority(Thread.NORM_PRIORITY);
      }
      return t;
    }
  }

  public static void postMainThread(Runnable runnable) {
    HANDLER.post(runnable);
  }

  public static void runOnMainThread(Runnable runnable) {
    if (Looper.getMainLooper() == Looper.myLooper()) {
      runnable.run();
    } else {
      postMainThread(runnable);
    }
  }

  public static void runOnMainThread(Runnable runnable, long delay) {
    HANDLER.postDelayed(runnable, delay);
  }
}
