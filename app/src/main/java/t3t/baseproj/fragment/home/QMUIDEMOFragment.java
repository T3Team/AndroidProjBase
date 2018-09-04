package t3t.baseproj.fragment.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qmuiteam.qmui.widget.QMUITopBar;

import base.t3t.baseprojlib.utils.AppUtils;
import base.t3t.companybusinesslib.base.BaseFragment;
import base.t3t.companybusinesslib.constant.AppArouterParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import t3t.baseproj.R;

@Route(path = AppArouterParams.fragmentQMUIDEMO)
public class QMUIDEMOFragment extends BaseFragment {
    @BindView(R.id.openOrInstall)
    Button mOpenOrInstallApp;
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    private boolean isInstall;
    private String packName = "com.qmuiteam.qmuidemo";

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
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse("http://cdn.qmuiteam.com/download/android/latest");
                    intent.setData(content_url);
                    startActivity(intent);
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