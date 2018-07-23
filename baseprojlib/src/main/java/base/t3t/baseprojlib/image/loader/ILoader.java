package base.t3t.baseprojlib.image.loader;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.MemoryCategory;

import base.t3t.baseprojlib.image.config.SingleConfig;
import base.t3t.baseprojlib.image.utils.DownLoadImageService;

/**
 * Created by dale on 2018/7/18.
 */
public interface ILoader {
    void init(Context context, int cacheSizeInM, MemoryCategory memoryCategory, boolean isInternalCD);

    void request(SingleConfig config);

    void pause();

    void resume();

    void clearDiskCache();

    void clearMomoryCache(View view);

    void clearMomory();

    boolean isCached(String url);

    void trimMemory(int level);

    void clearAllMemoryCaches();

    void saveImageIntoGallery(DownLoadImageService downLoadImageService);
}
