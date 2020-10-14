package com.github.josmilan.myapplication.fragment.dailog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.github.josmilan.myapplication.R;
import com.github.josmilan.myapplication.utils.PreferenceUtils;

public class AddEmailDialogFragment extends DialogFragment {

    Button btSave;
    Button btCancel;
    EditText etMail;
    private OnEmailAddedListener mListener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Fragment fragment = getParentFragment();
        try {
            mListener = (OnEmailAddedListener) fragment;
        } catch (ClassCastException e) {

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int style = DialogFragment.STYLE_NORMAL, theme = android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth;
        setStyle(style, theme);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        setCancelable(false);
        return dialog;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_email_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initListener();

    }

    private void initListener() {
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valideEmail()) {
                    PreferenceUtils.setEmailId(etMail.getText().toString().trim());
                    mListener.onEmailAdded();
                    dismiss();
                }
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] i = PreferenceUtils.getEmailIdList();
                if (i != null && i.length > 0 && i[0] != "") {
                    dismiss();
                }else {
                    Toast.makeText(getContext(), getResources().getString(R.string.txt_please_add_email_to_continue), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean valideEmail() {
        if (!Patterns.EMAIL_ADDRESS.matcher(etMail.getText().toString().trim()).matches()) {
            Toast.makeText(getContext(), getResources().getString(R.string.txt_invalid_email), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initView(View view) {
        btSave = view.findViewById(R.id.btSave);
        btCancel = view.findViewById(R.id.btCancel);
        etMail = view.findViewById(R.id.etMail);
    }

    public interface OnEmailAddedListener {
        public void onEmailAdded();
    }
}
