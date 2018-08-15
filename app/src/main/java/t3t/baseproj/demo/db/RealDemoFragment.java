package t3t.baseproj.demo.db;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mrgao.luckrecyclerview.LucklyRecyclerView;
import com.mrgao.luckrecyclerview.recyclerview.LRecyclerView;
import com.qmuiteam.qmui.widget.QMUITopBar;

import base.t3t.companybusinesslib.base.BaseFragment;
import base.t3t.companybusinesslib.constant.AppArouterParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import t3t.baseproj.R;
import t3t.baseproj.demo.db.adapter.CityAdapter;
import t3t.baseproj.demo.db.model.CityList;
import t3t.baseproj.demo.db.viewmodel.DbDemoVM;

@Route(path = AppArouterParams.fragmentRealmDbDemo)
public class RealDemoFragment extends BaseFragment implements LucklyRecyclerView.OnLoadMoreListener, LucklyRecyclerView.OnRefreshListener, View.OnClickListener {
    private static final String TAG = RealDemoFragment.class.getSimpleName();

    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.yRecyclerView)
    LucklyRecyclerView mLRecyclerView;
    private CityAdapter mCityAdapter;
    private DbDemoVM dbDemoVM;

    @Override
    protected View onCreateView() {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_realm_db_layout, null);
        ButterKnife.bind(this, layout);
        mTopBar.setTitle("realm db 演示demo");
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        initRecyview();

        return layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbDemoVM = ViewModelProviders.of(this).get(DbDemoVM.class);

        dbDemoVM.getObservable()
                .compose(this.<CityList>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CityList>() {
                    @Override
                    public void accept(CityList list) throws Exception {
                        if (list == null) {
                            return;
                        }
                        if (list.isRefreash()) {
                            mCityAdapter.swapData(list.getList());
                            mLRecyclerView.setRefreshComplete();
                        } else {
                            mCityAdapter.append(list.getList());
                            mLRecyclerView.setLoadingComplete();
                        }

                    }
                });
    }

    private void initRecyview() {
        //添加加载更多监听
        mLRecyclerView.setLoadMoreListener(this);
        //添加下拉刷新监听
        mLRecyclerView.setOnRefreshListener(this);
        //设置空视图/错误视图点击后是否刷新数据
        mLRecyclerView.setOnClickEmptyOrErrorToRefresh(true);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //封装好了的线性分割线,也可以使用setGridDivider()；使用以及封装好的网格式布局，在使用这句话之前，请先设置好LayoutManager
        mLRecyclerView.addLinearDivider(LRecyclerView.VERTICAL_LIST);
        // mLRecyclerView.addGridDivider();
        mLRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置下拉刷新的背景图片（可放广告图片）
        mLRecyclerView.setRefreshBackground(getResources().getDrawable(R.mipmap.head_back));
        //设置上拉加载部分设置背景图片（也可放广告图片）
        // mLRecyclerView.setFooterBackground(getResources().getDrawable(R.drawable.footerback));

        mLRecyclerView.setRefreshColor(getResources().getColor(R.color.app_color_blue));

        mCityAdapter = new CityAdapter(getLayoutInflater());


        mLRecyclerView.setAdapter(mCityAdapter);

        //设置下拉刷新的时长
        mLRecyclerView.setDuration(4000);
        //添加错误的View
        // mLRecyclerView.setErrorView(R.layout.error_view);
        //添加空View
        // mLRecyclerView.setEmptyView(R.layout.view_empty);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dbDemoVM.freashData();
    }

    @Override
    protected boolean canDragBack() {
        return true;
    }


    @Override
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.showEmpty:
//                // dataAdapter.clearAll();
//                break;
//            case R.id.showError:
//                mLRecyclerView.showError(true);
//                break;
//            case R.id.group:
//                // startActivity(new Intent(LoadingActivity.this, GroupActivity.class));
//                break;
//
//        }

    }

    @Override
    public void onLoadMore() {
        dbDemoVM.loadMoreData();
    }

    @Override
    public void onRefresh() {
        dbDemoVM.freashData();
    }
}