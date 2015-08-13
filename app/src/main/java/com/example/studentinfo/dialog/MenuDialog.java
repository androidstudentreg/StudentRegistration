package com.example.studentinfo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.studentinfo.R;

/**
 * Created by vinay on 28/6/15.
 */
public class MenuDialog extends Dialog implements View.OnClickListener {

    public static final int DIALOG_EDIT = 1;
    public static final int DIALOG_DELETE = 2;

    OnDialogItemClick onDialogItemClick;

    public MenuDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_menu);
        findViewById(R.id.tv_edit).setOnClickListener(this);
        findViewById(R.id.tv_delete).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (onDialogItemClick != null) {
            int clickCode = 0;
            switch (view.getId()) {
                case R.id.tv_edit:
                    clickCode = DIALOG_EDIT;
                    break;

                case R.id.tv_delete:
                    clickCode = DIALOG_DELETE;
                    break;
            }

            onDialogItemClick.onClick(clickCode);
        }
        dismiss();
    }

    public void setOnDialogItemClick(OnDialogItemClick onDialogItemClick) {
        this.onDialogItemClick = onDialogItemClick;
    }

    public interface OnDialogItemClick {
        void onClick(int clickCode);
    }
}
