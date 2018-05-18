package t3t.baseproj.fragment.home;

import android.content.res.AssetManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.io.File;
import java.io.InputStream;

import base.t3t.baseprojlib.utils.AppUtils;
import base.t3t.baseprojlib.utils.FileUtil;
import base.t3t.companybusinesslib.base.BaseFragment;
import base.t3t.companybusinesslib.constant.AppArouterParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import t3t.baseproj.R;

import static base.t3t.baseprojlib.utils.FileUtil.writeStreamToFile;

@Route(path = AppArouterParams.fragmentQMUIDEMO)
public class QMUIDEMOFragment extends BaseFragment {

    @BindView(R.id.openOrInstall)
    Button mOpenOrInstallApp;
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    private boolean isInstall;
    private String packName = "com.qmuiteam.qmuidemo";

    @Override
    protected View onCreateView() {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_qmui_demo, null);
        ButterKnife.bind(this, layout);

        mOpenOrInstallApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //init
                if (isInstall) {
                    AppUtils.startApp(getContext(), packName);
                } else {
                    AssetManager assets = getActivity().getAssets();
                    try {
                        //获取assets资源目录下的himarket.mp3,实际上是himarket.apk,为了避免被编译压缩，修改后缀名。
                        InputStream stream = assets.open("qumi.mp3");
                        if (stream == null) {
                            return;
                        }
                        String folder = FileUtil.getCacheDir(getContext());
                        File f = new File(folder);
                        if (!f.exists()) {
                            f.mkdir();
                        }
                        String apkPath = new StringBuilder().append(folder).append(File.separator).append("temp.apk").toString();
                        File file = new File(apkPath);
                        if (file.exists()) {
                            file.delete();
                        }
                        file.createNewFile();
                        FileUtil.writeStreamToFile(stream, file);
                        AppUtils.installApp(getContext(), Uri.fromFile(file));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        mTopBar.addLeftBackImageButton().

                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popBackStack();
                    }
                });
        mTopBar.setTitle("QMUI Demo");

        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (AppUtils.isAppInstalled(getContext(), packName)) {
            mOpenOrInstallApp.setText("打开演示Demo");
            isInstall = true;
        } else {
            mOpenOrInstallApp.setText("安装演示Demo");
            isInstall = false;
        }
    }

    @Override
    protected boolean canDragBack() {
        return true;
    }
}