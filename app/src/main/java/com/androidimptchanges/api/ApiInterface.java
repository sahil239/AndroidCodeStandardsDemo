package com.androidimptchanges.api;

import com.androidimptchanges.common_classes.Constants;
import com.androidimptchanges.model.BreedModel;
import com.androidimptchanges.model.MultiPartModel;
import com.androidimptchanges.model.PetDetails;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;


/**
 * Created by android on 11/11/2016.
 */

public interface ApiInterface {

    @GET("developer/PetMate/api/api.php?type=FetchBreed")
    Call<BreedModel> fetchBreed();

    //http://192.168.2.3/developer/PetMate/api/api.php?type=PetProfile&petID=101&petType=normal

    @FormUrlEncoded
    @POST(Constants.FILE_PATH)
    Call<PetDetails> fetchPetProfile(@Field("type") String type, @Field("petID") String petID, @Field("petType") String petType);


    @FormUrlEncoded
    @POST(Constants.FILE_PATH)
    Call<PetDetails> fetchPetProfileSingleField(@FieldMap Map<String,String> stringMap);

    @FormUrlEncoded
    @POST(Constants.FILE_PATH)
    Call<BreedModel> fetchBreed1(@Field("type") String first);

    @FormUrlEncoded
    @POST(Constants.FILE_PATH)
    BreedModel fetchBreed2(@Field("type") String first);

    //http://test.alive-mind.com/PetMate/api/api.php?type=addPet&userID=93&categoryID=1&breedID=1&name=daizy&primaryPet=no&gender=female&age=1&height=100&weight=30&findHome=yes&description=swtpet

    @Multipart
    @POST(Constants.FILE_PATH)
    Call<ResponseBody> uploadFile(
            @Part("type") RequestBody  type
            , @Part("userID") RequestBody  userID
            , @Part("categoryID") RequestBody  categoryID
            , @Part("breedID") RequestBody  breedID
            , @Part("name") RequestBody  name
            , @Part("primaryPet") RequestBody  primaryPet
            , @Part("gender") RequestBody  gender
            , @Part("age") RequestBody  age
            , @Part("height") RequestBody  height
            , @Part("weight") RequestBody  weight
            , @Part("findHome") RequestBody  findHome
            , @Part("description") RequestBody description,
            @Part MultipartBody.Part file);

    @Multipart
    @POST(Constants.FILE_PATH)
    Call<ResponseBody> uploadMultipleFile(
            @Part("type") RequestBody  type
            , @Part("userID") RequestBody  userID
            , @Part("categoryID") RequestBody  categoryID
            , @Part("breedID") RequestBody  breedID
            , @Part("name") RequestBody  name
            , @Part("primaryPet") RequestBody  primaryPet
            , @Part("gender") RequestBody  gender
            , @Part("age") RequestBody  age
            , @Part("height") RequestBody  height
            , @Part("weight") RequestBody  weight
            , @Part("findHome") RequestBody  findHome
            , @Part("description") RequestBody description,
            @Part MultipartBody.Part[] file);

}
