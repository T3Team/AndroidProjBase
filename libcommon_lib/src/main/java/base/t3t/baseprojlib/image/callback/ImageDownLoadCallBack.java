package base.t3t.baseprojlib.image.callback;

import android.graphics.Bitmap;

/**
 * Created by dale on 2018/7/19.
 */
public interface ImageDownLoadCallBack {
    void onDownLoadSuccess(Bitmap bitmap);

    void onDownLoadFailed();
}
