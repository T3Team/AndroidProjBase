package t3t.baseproj.fragment.home;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qmuiteam.qmui.widget.QMUITopBar;

import base.t3t.companybusinesslib.constant.NetDemoArouterParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import t3t.baseproj.R;
import t3t.baseproj.decorator.GridDividerItemDecoration;

public abstract class HomeController extends FrameLayout {

    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;


    public HomeController(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.home_layout, this);
        ButterKnife.bind(this);
        initTopBar();
    }


    protected abstract String getTitle();

    private void initTopBar() {
        mTopBar.setTitle(getTitle());

        mTopBar.addRightImageButton(R.mipmap.icon_topbar_about, R.id.topbar_right_about_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(NetDemoArouterParams.jumpToNetDemoMain).navigation();
            }
        });
    }

    private void initRecyclerView() {
        // mRecyclerView.setAdapter(mItemAdapter);
        int spanCount = 3;
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        mRecyclerView.addItemDecoration(new GridDividerItemDecoration(getContext(), spanCount));
    }


}
