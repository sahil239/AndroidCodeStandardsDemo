package com.androidimptchanges.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androidimptchanges.R;
import com.androidimptchanges.base.BaseActivity;
import com.androidimptchanges.common_classes.Constants;
import com.androidimptchanges.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        binding.retrofitWithOneParam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RetroFitExample.class);
                intent.putExtra(Constants.ACTIVITY_KEY, Constants.SINGLE_PARAM);
                startActivity(intent);
            }
        });

        binding.retrofitUploadSingleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RetroFitExample.class);
                intent.putExtra(Constants.ACTIVITY_KEY, Constants.MULTI_PART_SINGLE_IMAGE);
                startActivity(intent);
            }
        });

        binding.retrofitWithMultipleParam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RetroFitExample.class);
                intent.putExtra(Constants.ACTIVITY_KEY, Constants.MULTIPLE_PARAM);
                startActivity(intent);
            }
        });

        binding.retrofitUploadMultipleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RetroFitExample.class);
                intent.putExtra(Constants.ACTIVITY_KEY, Constants.MULTI_PART_MULTIPLE_IMAGE);
                startActivity(intent);
            }
        });

        daggersDemo();

       // reactiveXDemo();

       // asyncUsingReactiveX();

        asyncReactiveXByKaushik();
        
    }


    void daggersDemo() {

        Log.d("DEBUG_TAG", "Before >" + sp.getInt("Number", 0));
        sp.edit().putInt("Number", 6).apply();
        Log.d("DEBUG_TAG", "After >" + sp.getInt("Number", 0));

        binding.daggerRes.setBackgroundColor(res.getColor(android.R.color.holo_red_light));
        binding.daggerRes.setTextColor(res.getColor(android.R.color.white));
    }

    //BASICS
    void reactiveXDemo() {

        Observable<String> myObservable
                = Observable.just("Hello"); // Emits "Hello"

        Observer<String> myObserver = new Observer<String>() {
            @Override
            public void onCompleted() {
                // Called when the observable has no more data to emit
                Log.d("RX_RESULT>", "No more data to emit");
            }

            @Override
            public void onError(Throwable e) {
                // Called when the observable encounters an error
                Log.d("RX_RESULT>", "Error trying data to emit");
            }

            @Override
            public void onNext(String s) {
                // Called each time the observable emits data
                Log.d("RX_RESULT>", "First Observable" + s);
            }
        };

        Action1<String> action1 = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("RX_RESULT>", "From Action > " + s);
            }
        };

        Subscription subscription1 = myObservable.subscribe(myObserver);

        subscription1.unsubscribe();

        Subscription subscription2 = myObservable.subscribe(action1);

        subscription2.unsubscribe();

        Observable<String> myArrayObservable
                = Observable.from(new String[]{"HI", "How", "ARE", "You", "??", ":)"}); // Emits each item of the array, one at a time

        myArrayObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String i) {
                Log.d("RX_RESULT>", i); // Prints the n
            }
        });

        Observable<Integer> myIntArrayObservable
                = Observable.from(new Integer[]{1, 2, 3, 4, 5, 6}); // Emits each item of the array, one at a time

        myIntArrayObservable.map(new Func1<Integer, Integer>() {//Input output both integer

            @Override
            public Integer call(Integer integer) {
                return integer * integer;//Square the Number
            }
        })
                .skip(2) //Skips first two array items
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {//Ignore odd numbers
                        return integer % 2 == 0;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d("RX_RESULT>", integer + "");
            }
        });


    }

    void asyncUsingReactiveX(){

        Observable<String[]> fetchFromGoogle = Observable.create(new Observable.OnSubscribe<String[]>() {
            @Override
            public void call(Subscriber<? super String[]> subscriber) {
                try {
                    String data = fetchData("http://www.google.com");
                    subscriber.onNext(new String[]{"from Google",data}); // Emit the contents of the URL
                    subscriber.onCompleted(); // Nothing more to emit
                }catch(Exception e){
                    subscriber.onError(e); // In case there are network errors
                }
            }
        });

        Observable<String[]> fetchFromYahoo = Observable.create(new Observable.OnSubscribe<String[]>() {
            @Override
            public void call(Subscriber<? super String[]> subscriber) {
                try {
                    String data = fetchData("http://www.yahoo.com");
                    subscriber.onNext(new String[]{"from Yahoo",data}); // Emit the contents of the URL
                    subscriber.onCompleted(); // Nothing more to emit
                }catch(Exception e){
                    subscriber.onError(e); // In case there are network errors
                }
            }
        });

     /*
       //Single Calls

       fetchFromGoogle
                .subscribeOn(Schedulers.newThread()) // Create a new Thread
                .observeOn(AndroidSchedulers.mainThread()) // Use the UI thread
                .subscribe(new Action1<String[]>() {
                    @Override
                    public void call(String[] s) {
                        Log.d("RX_RESULT>", s[0]+" : Result lenght" + s[1]); // Change a View
                    }
                });

        fetchFromYahoo
                .subscribeOn(Schedulers.newThread()) // Create a new Thread
                .observeOn(AndroidSchedulers.mainThread()) // Use the UI thread
                .subscribe(new Action1<String[]>() {
                    @Override
                    public void call(String[] s) {
                        Log.d("RX_RESULT>", s[0]+" : Result lenght" + s[1]); // Change a View
                    }
                });*/

        //Two web calls simultaneously USing Zip operator

        Observable<String[]> zipped = Observable.zip(fetchFromGoogle, fetchFromYahoo, new Func2<String[], String[], String[]>() {
            @Override
            public String[] call(String[] google, String[] yahoo) {
                return new String[]{google[0],google[1],yahoo[0],yahoo[1]};
            }
        });

        zipped.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String[]>() {
            @Override
            public void call(String[] googlePlusYahoo) {
                Log.d("RX_RESULT>",googlePlusYahoo[0]+", Result : "+googlePlusYahoo[1]);
                Log.d("RX_RESULT>",googlePlusYahoo[2]+", Result : "+googlePlusYahoo[3]);

            }
        });


    }

    void asyncReactiveXByKaushik(){

    }
    public String fetchData(String url) throws IOException{

        BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openStream()));

        String inputLine;

        String  result = "";

        while ((inputLine = in.readLine())!= null)
            result += inputLine;

        return result.length()+"";
    }

}
