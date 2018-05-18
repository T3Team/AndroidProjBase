package t3t.baseproj.fragment.home;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qmuiteam.qmui.widget.QMUITopBar;

import base.t3t.baseprojlib.base.BaseRecyclerAdapter;
import base.t3t.companybusinesslib.base.BaseFragment;
import base.t3t.companybusinesslib.base.BaseFragmentActivity;
import base.t3t.companybusinesslib.constant.NetDemoArouterParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import t3t.baseproj.R;
import t3t.baseproj.adapter.ItemAdapter;
import t3t.baseproj.decorator.GridDividerItemDecoration;
import t3t.baseproj.model.ItemDescription;

public abstract class HomeController extends FrameLayout {

    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private ItemAdapter mItemAdapter;
    private HomeControlListener mHomeControlListener;

    public HomeController(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.home_layout, this);
        ButterKnife.bind(this);
        initTopBar();
        initRecyclerView();
    }

    protected void startFragment(BaseFragment fragment) {
        if (mHomeControlListener != null) {
            mHomeControlListener.startFragment(fragment);
        }
    }

    protected void startActivity(BaseFragmentActivity activity) {
        if (mHomeControlListener != null) {
            mHomeControlListener.startAct(activity);
        }
    }

    public void setHomeControlListener(HomeControlListener homeControlListener) {
        mHomeControlListener = homeControlListener;
    }

    protected abstract String getTitle();

    private void initTopBar() {
        mTopBar.setTitle(getTitle());

//        mTopBar.addRightImageButton(R.mipmap.icon_topbar_about, R.id.topbar_right_about_button).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ARouter.getInstance().build(NetDemoArouterParams.jumpToNetDemoMain).navigation();
//            }
//        });
    }

    abstract ItemAdapter getItemAdapter();

    public interface HomeControlListener {
        void startFragment(BaseFragment fragment);

        void startAct(BaseFragmentActivity activity);
    }

    private void initRecyclerView() {
        mItemAdapter = getItemAdapter();
        mItemAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                ItemDescription item = mItemAdapter.getItem(pos);
                try {
                    if (item.getmKitDemoClass() != null) {
                        BaseFragment fragment = item.getmKitDemoClass();
                        startFragment(fragment);
                    } else {
                        BaseFragmentActivity activity = item.getmKitDemoActClass();
                        startActivity(activity);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        mRecyclerView.setAdapter(mItemAdapter);
        int spanCount = 3;
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        mRecyclerView.addItemDecoration(new GridDividerItemDecoration(getContext(), spanCount));

    }


}
