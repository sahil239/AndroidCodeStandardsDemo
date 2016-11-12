package com.androidimptchanges.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.annotations.NonNull;
import com.androidimptchanges.R;
import com.androidimptchanges.adapter.BreedRecyclerAdapter;
import com.androidimptchanges.api.ApiClient;
import com.androidimptchanges.api.ApiInterface;
import com.androidimptchanges.common_classes.Constants;
import com.androidimptchanges.databinding.ActivityRetroFitExampleBinding;
import com.androidimptchanges.model.BreedModel;
import com.androidimptchanges.model.PetDetails;


import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.androidimptchanges.common_classes.Constants.ACTIVITY_KEY;
import static com.androidimptchanges.common_classes.Constants.MULTIPART_FORM_DATA;
import static com.androidimptchanges.common_classes.Constants.SINGLE_PARAM;


public class RetroFitExample extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private List<BreedModel.BreedDataBean> breedDataBeen = new ArrayList<>();
    ActivityRetroFitExampleBinding retroFitExampleBinding;


    BreedRecyclerAdapter breedRecyclerAdapter;
    Context context = RetroFitExample.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        retroFitExampleBinding = DataBindingUtil.setContentView(RetroFitExample.this, R.layout.activity_retro_fit_example);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RetroFitExample.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        retroFitExampleBinding.recyclerView.setLayoutManager(linearLayoutManager);

        if (getIntent().getExtras().getInt(Constants.ACTIVITY_KEY) == Constants.SINGLE_PARAM ||
                getIntent().getExtras().getInt(Constants.ACTIVITY_KEY) == Constants.MULTIPLE_PARAM  ) {
            retroFitExampleBinding.relSingleParam.setVisibility(View.VISIBLE);
            retroFitExampleBinding.relMultiPart.setVisibility(View.GONE);

            new ParamTask().execute();
        } else if (getIntent().getExtras().getInt(Constants.ACTIVITY_KEY) == Constants.MULTI_PART_SINGLE_IMAGE
                || getIntent().getExtras().getInt(Constants.ACTIVITY_KEY) == Constants.MULTI_PART_MULTIPLE_IMAGE ) {

            retroFitExampleBinding.relSingleParam.setVisibility(View.GONE);
            retroFitExampleBinding.relMultiPart.setVisibility(View.VISIBLE);

            new MultiPartTask().execute();
        }



      /*  Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface restInterface = retrofit.create(ApiInterface.class);

        Call<BreedModel> breedModelCall = restInterface.fetchBreed1("FetchBreed");
        breedModelCall.enqueue(new Callback<BreedModel>() {
            @Override
            public void onResponse(Response<BreedModel> response) {

                Log.d("BreedModel>>",response.isSuccess()+"");
                BreedModel breedModel= new BreedModel();
                breedModel = response.body();

                breedDataBeen = breedModel.getBreedData();
                breedRecyclerAdapter = new BreedRecyclerAdapter(RetroFitExample.this,context,breedDataBeen);
                Log.d("BreedModel>>",breedDataBeen.size()+"");
                retroFitExampleBinding.recyclerView.setAdapter(breedRecyclerAdapter);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("BreedModel>>","FAIL");
            }
        });*/

        setupSearchView();

    }

    private void setupSearchView() {
        retroFitExampleBinding.searchView.setIconifiedByDefault(false);
        retroFitExampleBinding.searchView.setOnQueryTextListener(this);
        retroFitExampleBinding.searchView.setSubmitButtonEnabled(true);
        retroFitExampleBinding.searchView.setQueryHint("Search Here");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        breedRecyclerAdapter.filter(newText);
        return true;
    }

    private class ParamTask extends AsyncTask {

        Retrofit retrofit;
        ApiInterface restInterface;
        BreedModel breedModel;
        PetDetails petDetails;

        Call<BreedModel> callback;
        Call<PetDetails> petDetailsCall;


        @Override
        protected void onPreExecute() {

            /*progressDialog = new ProgressDialog(RetroFitExample.this);
            progressDialog.show();
            */

            retroFitExampleBinding.progressBar.setVisibility(View.VISIBLE);
            retroFitExampleBinding.recyclerView.setVisibility(View.GONE);
            retrofit = ApiClient.getClient();
            restInterface = retrofit.create(ApiInterface.class);


        }


        @Override
        protected Object doInBackground(Object[] params) {

            if(getIntent().getExtras().getInt(ACTIVITY_KEY) == SINGLE_PARAM){
                callback = restInterface.fetchBreed1("FetchBreed");
            }else{
                petDetailsCall = restInterface.fetchPetProfile("PetProfile","101","normal");
            }



            return null;
        }


        @Override
        protected void onPostExecute(Object o) {

            if(getIntent().getExtras().getInt(ACTIVITY_KEY) == SINGLE_PARAM) {

                callback.enqueue(new Callback<BreedModel>() {
                    @Override
                    public void onResponse(Call<BreedModel> call, Response<BreedModel> response) {


                        breedModel = response.body();
                        if (breedModel.getSuccess().equals("1")) {

                            breedDataBeen = breedModel.getBreedData();
                            breedRecyclerAdapter = new BreedRecyclerAdapter(RetroFitExample.this, context, breedDataBeen);
                            retroFitExampleBinding.recyclerView.setAdapter(breedRecyclerAdapter);
                            retroFitExampleBinding.progressBar.setVisibility(View.GONE);
                            retroFitExampleBinding.recyclerView.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(RetroFitExample.this, "Success 0", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<BreedModel> call, Throwable t) {
                        Toast.makeText(RetroFitExample.this, "Trying Again", Toast.LENGTH_LONG).show();
                    }
                });
            }else{

                petDetailsCall.enqueue(new Callback<PetDetails>() {
                    @Override
                    public void onResponse(Call<PetDetails> call, Response<PetDetails> response) {

                        petDetails = response.body();
                        if (petDetails.getSuccess().equals("1")) {
                            PetDetails.PetProfileBean petProfileBean = petDetails.getPetProfile().get(0);
                            Log.d("petDetails", petProfileBean.getBreedName());

                            Toast.makeText(RetroFitExample.this, petProfileBean.getBreedName() + petProfileBean.getPetOwner()
                                    + petProfileBean.getGender(), Toast.LENGTH_LONG).show();

                            retroFitExampleBinding.progressBar.setVisibility(View.GONE);
                            retroFitExampleBinding.recyclerView.setVisibility(View.GONE);
                        }
                    else{
                        Toast.makeText(RetroFitExample.this, "Success 0", Toast.LENGTH_LONG).show();
                    }}
                    @Override
                    public void onFailure(Call<PetDetails> call, Throwable t) {
                        Toast.makeText(RetroFitExample.this, "Trying Again", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

    private class MultiPartTask extends AsyncTask {

        ProgressDialog progressDialog;
        Retrofit retrofit;
        ApiInterface restInterface;
        BreedModel breedModel;

        ResponseBody jsonObject ;
        Call<ResponseBody> callback;

        @Override
        protected void onPreExecute() {

            retroFitExampleBinding.progressBar.setVisibility(View.VISIBLE);
            retroFitExampleBinding.relMultiPart.setVisibility(View.GONE);
            retrofit = ApiClient.getClient();
            restInterface = retrofit.create(ApiInterface.class);


        }


        @Override
        protected Object doInBackground(Object[] params) {

            ArrayList<String> fileList = new ArrayList<>();
            fileList.add("/sdcard/multi_part/test_image.jpg");
            fileList.add("/sdcard/multi_part/temp_photo.jpg");
            fileList.add("/sdcard/multi_part/1475222033808.jpg");
            fileList.add("/sdcard/multi_part/1468047205525.jpg");

            if(getIntent().getExtras().getInt(Constants.ACTIVITY_KEY) == Constants.MULTI_PART_SINGLE_IMAGE){
                callback = uploadFileMultiPart();
            }else {
                callback = uploadMultipleFileMultiPart(fileList);
            }

            return null;
        }


        @Override
        protected void onPostExecute(Object o) {


            if (callback == null) {

                Toast.makeText(RetroFitExample.this, "CALL BACK  NULLL", Toast.LENGTH_LONG).show();
                return;
            }
            callback.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call,
                                       Response<ResponseBody> response) {
                    Log.v("Upload", "success");

                    try {
                        ResponseBody responseBody = response.body();
                        JSONObject jsonObject = new JSONObject(Constants.fromStream(responseBody.byteStream()));
                        if (jsonObject.getString("success").equals("1")) {
                            Toast.makeText(RetroFitExample.this, "Success 1", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(RetroFitExample.this, "Success 0", Toast.LENGTH_LONG).show();
                        }

                        retroFitExampleBinding.relMultiPart.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                        Toast.makeText(RetroFitExample.this, "  " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("Upload error:", t.getMessage());
                    Toast.makeText(RetroFitExample.this, "Trying Again", Toast.LENGTH_LONG).show();
                }
            });
            retroFitExampleBinding.progressBar.setVisibility(View.GONE);
            // progressDialog.dismiss();
        }
    }

    //FOR SINGLE IMAGE UPLOAD
    public Call<ResponseBody> uploadFileMultiPart() {

        ApiInterface service =
                ApiClient.getClient().create(ApiInterface.class);

        // create part for file (photo, video, ...)

        MultipartBody.Part body2 = prepareFilePart("file0");

        RequestBody type = createPartFromString("addPet");
        RequestBody userID = createPartFromString("30");
        RequestBody categoryID = createPartFromString("1");
        RequestBody breedID = createPartFromString("1");
        RequestBody name = createPartFromString("rrrr");
        RequestBody primaryPet = createPartFromString("no");
        RequestBody gender = createPartFromString("male");

        RequestBody age = createPartFromString("3");

        RequestBody height = createPartFromString("2");
        RequestBody weight = createPartFromString("60");
        RequestBody findHome = createPartFromString("yes");

        RequestBody description = createPartFromString("retrofit Pet");

        ApiInterface apiInterface =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> responseBodyCall = apiInterface.uploadFile(type,userID,categoryID,breedID,name,primaryPet,gender,age,height,weight,findHome,description,body2);

        return responseBodyCall;
    }

    //FOR MULTIPLE IMAGES UPLOAD
    public Call<ResponseBody> uploadMultipleFileMultiPart(ArrayList<String> pathList) {

        ApiInterface service =
                ApiClient.getClient().create(ApiInterface.class);

        // create part for file (photo, video, ...)



        MultipartBody.Part[] parts = new MultipartBody.Part[pathList.size()];
        ArrayList<MultipartBody.Part> multiPartList = new ArrayList<>();
        for(int i = 0 ; i < pathList.size(); i ++){

            multiPartList.add(prepareMultipleFilePart("file"+i,pathList.get(i)));
        }

        parts = multiPartList.toArray(parts);

        RequestBody type = createPartFromString("addPet");
        RequestBody userID = createPartFromString("30");
        RequestBody categoryID = createPartFromString("1");
        RequestBody breedID = createPartFromString("1");
        RequestBody name = createPartFromString("rrrr");
        RequestBody primaryPet = createPartFromString("no");
        RequestBody gender = createPartFromString("male");

        RequestBody age = createPartFromString("3");

        RequestBody height = createPartFromString("2");
        RequestBody weight = createPartFromString("60");
        RequestBody findHome = createPartFromString("yes");

        RequestBody description = createPartFromString("retrofit Pet");

        ApiInterface apiInterface =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> responseBodyCall = apiInterface.uploadMultipleFile(type,userID,categoryID,breedID,name,primaryPet,gender,age,height,weight,findHome,description,parts);

        return responseBodyCall;
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String partName) {

        File file = new File(Constants.IMAGE_TO_UPLOAD);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);

        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    @NonNull
    private MultipartBody.Part prepareMultipleFilePart(String partName,String path) {

        File file = new File(path);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);

        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }



}
