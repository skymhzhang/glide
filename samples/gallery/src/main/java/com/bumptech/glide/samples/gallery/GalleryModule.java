package com.bumptech.glide.samples.gallery;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.AppGlideModule;
import java.io.File;

/**
 * Ensures that Glide's generated API is created for the Gallery sample.
 */
@GlideModule
public final class GalleryModule extends AppGlideModule {
    // Intentionally empty.
    public static final int DISK_CACHE_SIZE = 500 * 1024 * 1024;

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
        String path = context.getExternalFilesDir("glide").getAbsolutePath();
        Log.e("zmh123", "path=" + path + " 路径是否存在" + new File(path).exists());
        builder.setDiskCache(new DiskLruCacheFactory(path, DISK_CACHE_SIZE));
    }
}
