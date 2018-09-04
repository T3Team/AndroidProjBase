package base.t3t.companybusinesslib.base;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import base.t3t.baseprojlib.base.LibUIFragment;

public abstract class BaseFragment extends LibUIFragment {


    public BaseFragment() {
    }

    @Override
    protected int backViewInitOffset() {
        return QMUIDisplayHelper.dp2px(getContext(), 100);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

}
