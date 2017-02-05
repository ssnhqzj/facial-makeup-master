package com.qzj.facial.widget;

import android.content.Context;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.chinamobile.streetlight.R;
import com.qzj.facial.base.BaseDialog;

/**
 * 提示信息dialog
 */
@SuppressWarnings("unused")
public class PromptDialog extends BaseDialog implements View.OnClickListener {

    // 是否禁止物理返回键
    protected boolean isForbidReturn;

    private View view;
    private TextView title;
    private TextView contentTv;
    private TextView cancel;
    private TextView sure;

    public OnPromptDialogClickListener listener;
    public Object tag;
    public Object obj;

    public PromptDialog(Context context) {
        super(context);
    }

    @Override
    protected View getDialogView() {
        if (view == null){
            view = View.inflate(context, R.layout.dialog_prompt, null);
            title = (TextView) view.findViewById(R.id.dialog_prompt_title);
            contentTv = (TextView) view.findViewById(R.id.dialog_prompt_content);
            cancel = (TextView) view.findViewById(R.id.dialog_prompt_cancel);
            sure = (TextView) view.findViewById(R.id.dialog_prompt_sure);

            contentTv.setMovementMethod(ScrollingMovementMethod.getInstance());
            cancel.setOnClickListener(this);
            sure.setOnClickListener(this);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_prompt_cancel:
                this.dismiss();
                break;

            case R.id.dialog_prompt_sure:
                if (listener != null){
                    listener.confirmClicked(tag,obj);
                }

                if (!isForbidReturn) {
                    this.dismiss();
                }

                break;
        }
    }

    /**
     * 设置title
     */
    public void setTitle(String titleText){
        if (titleText != null && !"".equals(titleText))
        title.setText(titleText);
    }

    /**
     * 设置内容，支持html
     */
    public void setContent(String htmlContent) {
        if (htmlContent != null && !"".equals(htmlContent)){
            contentTv.setText(Html.fromHtml(htmlContent));
        }
    }

    public void setSureBtnEnabled(boolean enabled){
        if (sure != null) {
            sure.setEnabled(enabled);
        }
    }

    public void setCancelBtnEnabled(boolean enabled) {
        if (cancel != null) {
            cancel.setEnabled(enabled);
        }
    }

    public void setForbidReturn(boolean forbidReturn) {
        isForbidReturn = forbidReturn;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public void setListener(OnPromptDialogClickListener listener) {
        this.listener = listener;
    }

    public interface OnPromptDialogClickListener {
       void confirmClicked(Object tag,Object obj);
   }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return isForbidReturn && keyCode == KeyEvent.KEYCODE_BACK || super.onKeyDown(keyCode, event);
    }
}
