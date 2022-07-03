package zmh.glide.demo.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;
import zmh.glide.demo.thread.ThreadManager;

public class ToastUtils {
  public static void show(Context context, String info) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      showInner(context,info);
    }else {
      ThreadManager.runOnMainThread(() -> showInner(context,info));
    }
  }

  private static void showInner(Context context, String info){
    Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
  }
}
