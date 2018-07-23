package t3t.baseproj.demo.ImageLoader.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.io.File;

import base.t3t.baseprojlib.image.callback.ImageDownLoadCallBack;
import base.t3t.baseprojlib.image.config.PriorityMode;
import base.t3t.baseprojlib.image.config.ScaleMode;
import base.t3t.baseprojlib.image.loader.ImageLoader;
import base.t3t.baseprojlib.image.utils.DownLoadImageService;
import base.t3t.companybusinesslib.base.BaseFragment;
import base.t3t.companybusinesslib.constant.AppArouterParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import t3t.baseproj.R;

import static base.t3t.baseprojlib.image.config.Contants.ANDROID_RESOURCE;
import static base.t3t.baseprojlib.image.config.Contants.ASSERTS_PATH;
import static base.t3t.baseprojlib.image.config.Contants.FOREWARD_SLASH;
import static base.t3t.baseprojlib.image.config.Contants.RAW;

@Route(path = AppArouterParams.fragmentImagerLoaderSimpleDemo)
public class SimpleLoaderDemoFragment extends BaseFragment {
    private static final String TAG = SimpleLoaderDemoFragment.class.getSimpleName();

    String URL1 = "http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490944508&di=671845045c66356487c1a539c4ed0717&imgtype=jpg&er=1&src=http%3A%2F%2Fattach.bbs.letv.com%2Fforum%2F201606%2F27%2F185306g84m4gsxztvzxjt5.jpg";
    String URL2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532339324681&di=c7da42ec1572891c7d6805229e25c5bc&imgtype=0&src=http%3A%2F%2Fpic.jia360.com%2Fueditor%2Fjsp%2Fupload%2F201601%2F19%2F29971453174173871.jpg";
    String URL3 = "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2796659031,1466769776&fm=80&w=179&h=119&img.JPEG";
    String URL4 = "https://isparta.github.io/compare-webp/image/gif_webp/gif/1.gif";
    String URL5 = "https://p.upyun.com/docs/cloud/demo.jpg!/format/webp";

    String IMG_NAME = "SHARE_IMG2.PNG";
    String IMG_NAME_C = "jpg_test.jpeg";

    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.iv_test1)
    ImageView iv_test1;
    @BindView(R.id.iv_test2)
    ImageView iv_test2;
    @BindView(R.id.iv_test3)
    ImageView iv_test3;
    @BindView(R.id.iv_test4)
    ImageView iv_test4;
    @BindView(R.id.iv_test5)
    ImageView iv_test5;
    @BindView(R.id.iv_test6)
    ImageView iv_test6;
    @BindView(R.id.iv_test7)
    ImageView iv_test7;
    @BindView(R.id.iv_test8)
    ImageView iv_test8;
    @BindView(R.id.iv_test9)
    ImageView iv_test9;
    @BindView(R.id.iv_test10)
    ImageView iv_test10;

    @BindView(R.id.iv_test11)
    ImageView iv_test11;
    @BindView(R.id.iv_test12)
    ImageView iv_test12;
    @BindView(R.id.iv_test13)
    ImageView iv_test13;
    @BindView(R.id.iv_test14)
    ImageView iv_test14;
    @BindView(R.id.iv_test15)
    ImageView iv_test15;

    @Override
    protected View onCreateView() {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_imageloader_simple_layout, null);
        ButterKnife.bind(this, layout);
        mTopBar.setTitle("图片加载demo");
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        load(getContext());
    }


    private void load(Context context) {

        ImageLoader.with(getActivity())
                .url(URL2)
                .placeHolder(R.mipmap.ic_launcher)
                .scale(ScaleMode.CENTER_CROP)
                .blur(50)
                .into(iv_test1);

        ImageLoader.with(context)
                .url(URL1)
                .placeHolder(R.mipmap.ic_launcher)
                .scale(ScaleMode.FIT_CENTER)
                .into(iv_test2);

        ImageLoader.with(context)
                .res(R.drawable.gif_test)
                .placeHolder(R.mipmap.ic_launcher)
                .scale(ScaleMode.FIT_CENTER)
                .into(iv_test3);


        ImageLoader.with(context)
                .url(URL4)
                .placeHolder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .scale(ScaleMode.FIT_CENTER)
                .into(iv_test4);

        ImageLoader.with(context)
                .url(URL3)
                .placeHolder(R.mipmap.ic_launcher)
                .scale(ScaleMode.FIT_CENTER)
                .into(iv_test5);

        ImageLoader.with(context)
                .url(URL5)
                .placeHolder(R.mipmap.ic_launcher)
                .scale(ScaleMode.FIT_CENTER)
                .into(iv_test6);

        ImageLoader.with(context)
                .res(R.drawable.gif_test)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeHolder(R.mipmap.ic_launcher)
                .scale(ScaleMode.FIT_CENTER)
                .into(iv_test7);


        ImageLoader.with(context)
                .res(R.drawable.b000)
                .vignetteFilter()
                .priority(PriorityMode.PRIORITY_NORMAL)
                .placeHolder(R.mipmap.ic_launcher)
                .scale(ScaleMode.FIT_CENTER)
                .into(iv_test9);

        ImageLoader.with(context)
                .res(R.drawable.b000)
                .sketchFilter()
                .placeHolder(R.mipmap.ic_launcher)
                .scale(ScaleMode.FIT_CENTER)
                .into(iv_test10);


        ImageLoader.with(context)
                .file("file://" + Environment.getExternalStorageDirectory().getPath() + FOREWARD_SLASH + IMG_NAME)
                .placeHolder(R.mipmap.ic_launcher)
                .scale(ScaleMode.FIT_CENTER)
                .into(iv_test11);


        ImageLoader.with(context)
                .file(new File(getActivity().getFilesDir(), IMG_NAME_C))
                .placeHolder(R.mipmap.ic_launcher)
                .scale(ScaleMode.FIT_CENTER)
                .into(iv_test12);

        ImageLoader.with(context)
                .asserts(ASSERTS_PATH + IMG_NAME_C)
                .placeHolder(R.mipmap.ic_launcher)
                .scale(ScaleMode.FIT_CENTER)
                .rectRoundCorner(50)
                .into(iv_test13);

        ImageLoader.with(context)
                .raw(ANDROID_RESOURCE + getActivity().getPackageName() + RAW + R.raw.jpg_test)
                .scale(ScaleMode.FIT_CENTER)
                .asCircle()
                .into(iv_test14);

        ImageLoader.with(context)
                .raw(ANDROID_RESOURCE + getActivity().getPackageName() + RAW + R.raw.jpg_test)
                .placeHolder(R.mipmap.ic_launcher)
                .scale(ScaleMode.FIT_CENTER)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .asSquare()
                .into(iv_test15);

        ImageLoader.saveImageIntoGallery(new DownLoadImageService(context, URL3, true, "lala", new ImageDownLoadCallBack() {

            @Override
            public void onDownLoadSuccess(Bitmap bitmap) {
                Log.e(TAG, "下载图片成功");
            }

            @Override
            public void onDownLoadFailed() {
                Log.e(TAG, "下载图片失败");
            }

        }));
    }


    @Override
    protected boolean canDragBack() {
        return true;
    }


}