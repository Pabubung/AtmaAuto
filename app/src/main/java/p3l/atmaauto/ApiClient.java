package p3l.atmaauto;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiClient {
    @GET("View.php")
    Call<Value> getUser();

    @POST("Insert.php")
    @FormUrlEncoded
    Call<String> regUser(@Field("username")String username,
                         @Field("name")String name,
                         @Field("role")String role);
}
