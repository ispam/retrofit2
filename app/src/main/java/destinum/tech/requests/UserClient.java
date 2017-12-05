package destinum.tech.requests;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserClient {

    @POST("api/users")
    Call<User> createAccount(@Body User user);
}
