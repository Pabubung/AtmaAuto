package p3l.atmaauto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import p3l.atmaauto.Controller.ApiClient;

import p3l.atmaauto.Session.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;


public class SignActivity extends AppCompatActivity {

    private Button button;
    private Button signInBtn;
    private TextView tError;
    private EditText txtUsername, txtPassword;


    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(getApplicationContext());
        if(session.isLoggedIn())
        {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }

        init();
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void init(){
        signInBtn = (Button) findViewById(R.id.btnRegisterLogin);
        txtUsername = (EditText)findViewById(R.id.eUsernameRegister);
        txtPassword = (EditText)findViewById(R.id.ePasswordRegister);
        tError = findViewById(R.id.tvErrorLogin);
        tError.setText("");
    }

    public void openHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void login(){
        if(txtUsername.getText().toString().isEmpty()||
                txtPassword.getText().toString().isEmpty()){
            tError.setText("Inputan Tidak Boleh Kosong");
        }else{

            final ProgressDialog progressDialog = new ProgressDialog(SignActivity.this);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();

            Retrofit retrofit= new retrofit2.Retrofit.Builder()
                    .baseUrl("http://atmaauto.b-laundry.com/api")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiClient apiClient = retrofit.create(ApiClient.class);
//            Call<ResponseUser> call = apiUser.GetLogin(txtUsername.getText().toString(),txtPassword.getText().toString());
            Call<ResponseBody> call = apiClient.GetLogin(txtUsername.getText().toString(), txtPassword.getText().toString());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        JSONObject jsonRes = new JSONObject(response.body().string());
                        Toast.makeText(getApplicationContext(), jsonRes.getJSONObject("data").getString("role"), Toast.LENGTH_SHORT).show();
                        session.createLoginSessions(
                                jsonRes.getJSONObject("data").getString("role"),
                                jsonRes.getJSONObject("data").getString("username"), jsonRes.getJSONObject("data").getString("id"));
                        final Intent intent = new Intent(SignActivity.this, HomeActivity.class);
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                        progressDialog.dismiss();
                                    }
                                },3000);

                    } catch (JSONException e){
                        progressDialog.dismiss();
                        e.printStackTrace();
                    } catch (IOException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    } catch (Throwable e){
                        progressDialog.dismiss();
                        tError.setText("Username dan Password tidak cocok");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    tError.setText("Koneksi Internet Tidak Stabil");
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Internet err\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

}
