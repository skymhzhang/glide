package zmh.glide.demo.test;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;

/**
 * 一个测试GlideApp的类，这是个扩展类，方便Glide链式调用的
 * 比如：GlideApp.with(fragment).load(url).zmhSquareThumb(thumbnailSize).into(imageView);
 */
@GlideExtension
public class MyAppExtension {

  private static final int MIN_THUMB_SIZE = 100;

  private static final RequestOptions DECODE_TYPE_BITMAP = RequestOptions.decodeTypeOf(Bitmap.class)
      .lock();

  private MyAppExtension() {
  }

  @NonNull
  @GlideOption
  public static BaseRequestOptions<?> zmhSquareThumb(BaseRequestOptions<?> requestOptions) {
    return requestOptions.centerCrop();
  }

  @NonNull
  @GlideOption
  public static BaseRequestOptions<?> zmhSquareMiniThumb(BaseRequestOptions<?> requestOptions) {
    return requestOptions.centerCrop();
  }

}