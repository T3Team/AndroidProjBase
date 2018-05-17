package base.android.t3t.netrequestdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.QMUITopBar;

import base.android.t3t.netrequestdemo.http.MNetService;
import base.t3t.companybusinesslib.base.BaseFragment;
import base.t3t.companybusinesslib.http.BaseObserver;
import io.reactivex.disposables.Disposable;

public class NetFragment extends BaseFragment {

    private Button mSentRequest;
    private TextView mNetBackContent;
    private QMUITopBar mTopBar;

    @Override
    protected View onCreateView() {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_net_demo, null);
        mSentRequest = layout.findViewById(R.id.sentRequest);
        mTopBar = layout.findViewById(R.id.topbar);
        mNetBackContent = layout.findViewById(R.id.netBackContent);
        mTopBar.setTitle("网络请求demo");
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mSentRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MNetService.getTestContent().subscribe(new BaseObserver<String>(getContext()) {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        Toast.makeText(getContext(), "----", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(int code, String message) {
                        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        return layout;
    }

    @Override
    protected boolean canDragBack() {
        return false;
    }
}