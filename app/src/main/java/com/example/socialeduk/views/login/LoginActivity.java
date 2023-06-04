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

import com.example.socialeduk.R;
import com.example.socialeduk.interfaces.VolleyCallBack;
import com.example.socialeduk.models.dto.LoginRequest;
import com.example.socialeduk.models.entities.ResponseLogin;
import com.example.socialeduk.services.AuthService;
import com.example.socialeduk.user.User;
import com.example.socialeduk.views.feed.FeedActivity;
import com.example.socialeduk.views.register.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_screen);


        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        loadData(sharedPref);
        if (!getEmail().equals("") && !getPassword().equals("")){
            login(getEmail(), getPassword(), sharedPref);
        }

        Button login = findViewById(R.id.LoginScreen_login_button);
        login.setOnClickListener(view -> login(getEmail(), getPassword(), sharedPref));

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

            try{
                authService.login(login, new VolleyCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            rs.setStatus(jsonObject.getString("status"));
                            rs.setMessage(jsonObject.getString("message"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (rs.getStatus().equals("success")){
                            User user = new User();
                            user
                        }


                        Toast.makeText(LoginActivity.this, "Login realizado com sucesso.", Toast.LENGTH_LONG).show();
                        finish();
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