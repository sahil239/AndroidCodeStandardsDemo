package com.androidimptchanges.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.androidimptchanges.R;
import com.androidimptchanges.base.BaseActivity;
import com.androidimptchanges.common_classes.Constants;
import com.androidimptchanges.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        binding.retrofitWithOneParam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RetroFitExample.class);
                intent.putExtra(Constants.ACTIVITY_KEY,Constants.SINGLE_PARAM);
                startActivity(intent);
            }
        });

        binding.retrofitUploadSingleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RetroFitExample.class);
                intent.putExtra(Constants.ACTIVITY_KEY,Constants.MULTI_PART_SINGLE_IMAGE);
                startActivity(intent);
            }
        });

        binding.retrofitWithMultipleParam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RetroFitExample.class);
                intent.putExtra(Constants.ACTIVITY_KEY,Constants.MULTIPLE_PARAM);
                startActivity(intent);
            }
        });

        binding.retrofitUploadMultipleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RetroFitExample.class);
                intent.putExtra(Constants.ACTIVITY_KEY,Constants.MULTI_PART_MULTIPLE_IMAGE);
                startActivity(intent);
            }
        });



    }

}
