package destinum.tech.requests;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private EditText mName, mEmail, mAge, mTopics;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mName = findViewById(R.id.editText);
        mEmail = findViewById(R.id.editText2);
        mAge = findViewById(R.id.editText3);
        mTopics = findViewById(R.id.editText4);
        mButton = findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User(
                        mName.getText().toString(),
                        mName.getText().toString(),
                        Integer.parseInt(mAge.getText().toString()),
                        mTopics.getText().toString()
                );

                Log.d("Main", mName.getText().toString() +
                                mName.getText().toString()+ Integer.parseInt(mAge.getText().toString()) +
                                Integer.parseInt(mAge.getText().toString()) +mTopics.getText().toString());

                        sendNetworkRequest(user);
            }
        });

    }

    private void sendNetworkRequest(User user) {
        //Interceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //okhttpclient
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(interceptor);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        UserClient client = retrofit.create(UserClient.class);

        Call<User> call = client.createAccount(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(MainActivity.this, "Success UserID = "+ response.body().getId(), Toast.LENGTH_SHORT).show();
                if (response.body() == null){
                    Log.e("Error",response.code()+"");
                    Log.e("Erro2", response.errorBody().toString());
                    return;
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
