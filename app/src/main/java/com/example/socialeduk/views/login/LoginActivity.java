package com.example.socialeduk.views.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.example.socialeduk.R;
import com.example.socialeduk.interfaces.VolleyCallBack;
import com.example.socialeduk.models.dto.DefaultResponse;
import com.example.socialeduk.models.dto.LoginRequest;
import com.example.socialeduk.services.AuthService;
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

        //sharedPreferences para login automatico
        loadData(userPref.getEmail(), userPref.getPassword());
        if (!getEmail().equals("") || !getPassword().equals("")){
            trylogin(getEmail(), getPassword());
        }

        //botoes da tela
        Button login = findViewById(R.id.LoginScreen_login_button);
        login.setOnClickListener(view -> trylogin(getEmail(), getPassword()));

        Button newAccount = findViewById(R.id.LoginScreen_newAccount_button);
        newAccount.setOnClickListener(view -> startNewAccount());
    }

    private void trylogin(String email, String password) {

        if (email.isEmpty() || email.equals(" ")){
            Toast.makeText(this, "O e-mail não pode estar vazio!", Toast.LENGTH_LONG).show();
        }else if (password.isEmpty() || password.equals(" ")) {
            Toast.makeText(this, "A senha não pode estar vazia!", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(LoginActivity.this, "Login realizado com sucesso!", Toast.LENGTH_LONG).show();
                            startFeed();

                        } else if (loginResponse.getStatus().equals("error")) {
                            Toast.makeText(LoginActivity.this, "E-mail ou senha incorretos!", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(LoginActivity.this, "Algo de errado ocorreu. Por favor, tente novamente. Se o erro " +
                                    "persistir, contate o administrador", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(LoginActivity.this, "Algo de errado ocorreu. Por favor, tente novamente. Se o erro " +
                                "persistir, contate o administrador", Toast.LENGTH_LONG).show();
                    }
                });
            }catch (JSONException e){
                Toast.makeText(LoginActivity.this, "Algo de errado ocorreu. Por favor, tente novamente. Se o erro " +
                        "persistir, contate o administrador", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void startFeed() {
        startActivity(new Intent(this, FeedActivity.class));
        finish();
    }

    private void startNewAccount() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }


    private String getEmail() {
        EditText email = findViewById(R.id.LoginScreen_inputEmail_plainText);
        String emailString = email.getText().toString();
        return emailString;
    }

    private String getPassword(){
        EditText password = findViewById(R.id.LoginScreen_inputPassword_plainText);
        String passwordString = password.getText().toString();
        return passwordString;
    }

    public void loadData(String email, String password){
        ((EditText) findViewById(R.id.LoginScreen_inputEmail_plainText)).setText(email);
        ((EditText) findViewById(R.id.LoginScreen_inputPassword_plainText)).setText(password);
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message , Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

}