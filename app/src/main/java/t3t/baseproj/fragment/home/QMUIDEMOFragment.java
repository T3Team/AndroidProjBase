package t3t.baseproj.fragment.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.io.File;
import java.io.InputStream;

import base.t3t.baseprojlib.utils.AppUtils;
import base.t3t.baseprojlib.utils.DevUtil;
import base.t3t.baseprojlib.utils.FileUtil;
import base.t3t.companybusinesslib.base.BaseFragment;
import base.t3t.companybusinesslib.constant.AppArouterParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import t3t.baseproj.R;
import t3t.baseproj.demo.User;
import t3t.baseproj.demo.viewmodel.TestViewModel;
import t3t.baseproj.lifecycle.TestLifecycleObserver;

@Route(path = AppArouterParams.fragmentQMUIDEMO)
public class QMUIDEMOFragment extends BaseFragment {
    TestLifecycleObserver mTestLifecycleObserver;
    @BindView(R.id.openOrInstall)
    Button mOpenOrInstallApp;
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    private boolean isInstall;
    private String packName = "com.qmuiteam.qmuidemo";
    //测试ViewModel
    @BindView(R.id.tv_test_name)
    TextView mTvTest;
    @BindView(R.id.btn_modUserName)
    Button mBtnModUser;
    private int mClickTimes = 0;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected View onCreateView() {
        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_qmui_demo, null);
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
        mTestLifecycleObserver = new TestLifecycleObserver();
        getLifecycle().addObserver(mTestLifecycleObserver);
        final TestViewModel testViewModel = ViewModelProviders.of(QMUIDEMOFragment.this).get(TestViewModel.class);
        testViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                mTvTest.setText(String.format("id is %1$s,name is %2$s", user.getId(), user.getUserName()));
            }
        });
        mBtnModUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickTimes++;
                User user = new User();
                user.setId(String.valueOf(mClickTimes));
                user.setUserName("Change");
                testViewModel.setUser(user);
            }
        });
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