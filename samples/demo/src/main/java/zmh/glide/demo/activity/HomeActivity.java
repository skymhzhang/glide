package zmh.glide.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.zmh.glide.demo.R;
import zmh.glide.demo.thread.ThreadManager;
import zmh.glide.demo.utils.TestUrls;
import zmh.glide.demo.utils.ToastUtils;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.btn_load1).setOnClickListener(view -> loadImg1());
        findViewById(R.id.btn_load2).setOnClickListener(view -> loadImg2());
        findViewById(R.id.btn_clear).setOnClickListener(view -> clear());
    }

    private void clear() {
        ThreadManager.execute(() -> {
            final long time = System.currentTimeMillis();
            Glide.get(HomeActivity.this).clearDiskCache();
            ToastUtils.show(HomeActivity.this, "缓存清理完成" + (System.currentTimeMillis() - time));
        });
    }

    private void loadImg1() {
        ImageView imageView = findViewById(R.id.img1);
        Glide.with(this).load(TestUrls.URI_ICON[0]).into(imageView);
    }

    private void loadImg2() {
        ImageView imageView = findViewById(R.id.img2);
        Glide.with(this).load(TestUrls.URI_ICON[0]).into(imageView);
    }
}