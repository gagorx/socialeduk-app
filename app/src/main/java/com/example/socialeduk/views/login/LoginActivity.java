package com.example.socialeduk.views.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.example.socialeduk.MainActivity;
import com.example.socialeduk.R;
import com.example.socialeduk.interfaces.VolleyCallBack;
import com.example.socialeduk.models.dto.DefaultResponse;
import com.example.socialeduk.models.dto.LoginRequest;
import com.example.socialeduk.services.AuthService;
import com.example.socialeduk.models.entities.User;
import com.example.socialeduk.sharedpreferences.UserPreferences;
import com.example.socialeduk.views.feed.FeedActivity;
import com.example.socialeduk.views.register.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class LoginActivity extends AppCompatActivity {

    private AuthService authService;
    private UserPreferences userPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        authService = new AuthService(Volley.newRequestQueue(this));
        userPref = new UserPreferences(this);


        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        loadData(sharedPref);
        if (!getEmail().equals("") && !getPassword().equals("")){
            login(getEmail(), getPassword(), sharedPref);
        }

        Button login = findViewById(R.id.LoginScreen_login_button);
        login.setOnClickListener(view -> trylogin(getEmail(), getPassword()));
//login(getEmail(), getPassword(), sharedPref)
        Button newAccount = findViewById(R.id.LoginScreen_newAccount_button);
        newAccount.setOnClickListener(view -> startNewAccount());
    }

    private void startNewAccount() {
        startActivity(new Intent(this, RegisterActivity.class));
//        finish();
    }

    public void loadData(SharedPreferences preferences){
        ((EditText) findViewById(R.id.LoginScreen_inputEmail_plainText)).setText(preferences.getString("email",""));
        ((EditText) findViewById(R.id.LoginScreen_inputPassword_plainText)).setText(preferences.getString("password",""));
    }

    public void savePreferences(SharedPreferences preferences){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email",((EditText) findViewById(R.id.LoginScreen_inputEmail_plainText)).getText().toString());
        editor.putString("password",((EditText) findViewById(R.id.LoginScreen_inputPassword_plainText)).getText().toString());

        editor.commit();
    }

    private void login(String email, String password, SharedPreferences sharedPref){
        if (email.equals("admin") && (password.equals("admin"))) {
            savePreferences(sharedPref);
            startActivity(new Intent(this, FeedActivity.class));
//            finish();
        }else{
            showToast("Usuario ou senha incorretos");
        }
    }
    private void trylogin(String email, String password) {

        if (email.isEmpty() || email.equals(" ")){
            Toast.makeText(this, "O EMAIL NAO PODE ESTAR VAZIO", Toast.LENGTH_LONG).show();
        }else if (password.isEmpty() || password.equals(" ")) {
            Toast.makeText(this, "A SENHA NAO PODE ESTAR VAZIO", Toast.LENGTH_LONG).show();
        }else{

            LoginRequest login =  new LoginRequest(email, password);
            DefaultResponse<String> loginResponse = new DefaultResponse<>();


            try{
                authService.login(login, new VolleyCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject obj = null;
                        JSONObject user;
                        try {
                            obj = (JSONObject) new
                                    JSONTokener(response).nextValue();
                            loginResponse.setMessage(obj.getString("message"));
                            loginResponse.setStatus(obj.getString("status"));
                            user = obj.getJSONObject("data");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        if (loginResponse.getStatus().equals("success")){
                            userPref.setUserJson(user.toString());
                            Toast.makeText(LoginActivity.this, "Login realizado com sucesso.", Toast.LENGTH_LONG).show();
                            Toast.makeText(LoginActivity.this, userPref.getId().toString(), Toast.LENGTH_LONG).show();
                            Toast.makeText(LoginActivity.this, userPref.getName(), Toast.LENGTH_LONG).show();
                            Toast.makeText(LoginActivity.this, userPref.getPassword(), Toast.LENGTH_LONG).show();
                            Toast.makeText(LoginActivity.this, userPref.getEmail(), Toast.LENGTH_LONG).show();
                            Toast.makeText(LoginActivity.this, userPref.getUsername(), Toast.LENGTH_LONG).show();
                        } else if (loginResponse.getStatus().equals("error")) {
                            Toast.makeText(LoginActivity.this, "Email ou senha incorretos", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(LoginActivity.this, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                                    "persistir, contate o administrador", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(LoginActivity.this, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                                "persistir, contate o administrador", Toast.LENGTH_LONG).show();
                    }
                });
            }catch (JSONException e){
                Toast.makeText(LoginActivity.this, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                        "persistir, contate o administrador", Toast.LENGTH_LONG).show();
            }
        }
    }



    private String getEmail() {
        EditText ra = findViewById(R.id.LoginScreen_inputEmail_plainText);
        String raString = ra.getText().toString();
        return raString;
    }

    private String getPassword(){
        EditText password = findViewById(R.id.LoginScreen_inputPassword_plainText);
        String passwordString = password.getText().toString();
        return passwordString;
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message , Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

}