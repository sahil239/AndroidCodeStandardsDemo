package com.androidimptchanges.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;




import java.io.IOException;

public class BaseActivity extends FragmentActivity implements View.OnClickListener {

    protected String deviceId;
    public SharedPreferences sp;
    public SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        initData();
    }

    protected void initData() {

        sp = PreferenceManager.getDefaultSharedPreferences(BaseActivity.this);
        editor = sp.edit();

        deviceId = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);


    }
    protected void initUi() {
        //setDefaultFont();
    }

    public void showCustomMessage(String message) {

      /*  final Dialog dialog = new Dialog(BaseActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);
        ((TextView) dialog.findViewById(R.id.dialog_title)).setText(pTitle);
        ((TextView) dialog.findViewById(R.id.dialog_message)).setText(pMsg);
        ((Button) dialog.findViewById(R.id.ok)).setText("Ok");
        ((Button) dialog.findViewById(R.id.cancel))
                .setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });
        ((Button) dialog.findViewById(R.id.ok))
                .setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });
        dialog.show();*/


    }

    protected void onOkPressed(){

    }

    protected void showProgressDialog(String message)
    {

       /* if(customProgress != null && customProgress.isShowing())
        {
            customProgress.dismiss();
        }

        customProgress = customProgress.show(this, message, true, false, new DialogInterface.OnCancelListener()
        {
            @Override
            public void onCancel(DialogInterface dialog)
            {
                customProgress.dismiss();
            }
        });*/
    }

    protected void cancelProgressDialog()
    {

        /*if(customProgress != null && customProgress.isShowing())
        {
            customProgress.dismiss();
        }*/
    }

    @Override
    public void onClick(View v) {
        
    }



  /*  protected void goToLoginActivity(){
        Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_right_to_left, R.anim.slide_out_from_right_to_left);
    }

    protected void goToSignUpActivity(){
        Intent intent = new Intent(BaseActivity.this, SignUpActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_right_to_left, R.anim.slide_out_from_right_to_left);
    }*/







}
