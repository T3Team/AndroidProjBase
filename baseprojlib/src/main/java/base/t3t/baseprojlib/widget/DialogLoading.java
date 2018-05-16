package base.t3t.baseprojlib.widget;

import android.app.Dialog;
import android.content.Context;

/**
 * 需要根据业务定制需要的loading 框
 */
public class DialogLoading extends Dialog {
    private static DialogLoading customProgressDialog = null;

    public DialogLoading(Context context, int theme) {
        super(context, theme);
    }

    public static DialogLoading createDialog(final Context context) {
//        customProgressDialog = new DialogLoading(context, R.style.CustomProgressDialog);
//        customProgressDialog.setContentView(R.layout.layout_loading);
//        customProgressDialog.setCancelable(true);
//        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
//        customProgressDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
//        customProgressDialog.setOnCancelListener(new OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//
//            }
//        });
//        View view = customProgressDialog.getWindow().getDecorView();
//        ImageView iv = (ImageView) view.findViewById(R.id.iv);
//       // ImageLoader.loadImage(context, R.raw.loading, iv);
        return customProgressDialog;
    }

    public void close() {
        try {
            this.dismiss();
        } catch (Exception ex) {

        }
    }
}
