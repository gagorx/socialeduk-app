package com.example.socialeduk.views.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialeduk.R;
import com.example.socialeduk.interfaces.VolleyCallBack;
import com.example.socialeduk.models.dto.LoginRequest;
import com.example.socialeduk.services.AuthService;
import com.example.socialeduk.views.feed.FeedActivity;
import com.example.socialeduk.views.register.RegisterActivity;

import org.json.JSONException;

public class LoginActivity extends AppCompatActivity {

    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Button login = findViewById(R.id.LoginScreen_login_button);
        login.setOnClickListener(view -> login(getEmail(), getPassword()));

        Button newAccount = findViewById(R.id.LoginScreen_newAccount_button);
        newAccount.setOnClickListener(view -> startNewAccount());
    }

    private void startNewAccount() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }


    private void login(String email, String password){
        if (email.equals("admin") && (password.equals("admin"))) {
            startActivity(new Intent(this, FeedActivity.class));
            finish();
        }else{
            showToast("Usuario ou senha incorretos");
        }
    }
    private void trylogin(String email, String password) {

        if (email.isEmpty() || email.equals(" ")){
            Toast.makeText(this, "O EMAIL NAO PODE ESTAR VAZIO", Toast.LENGTH_LONG).show();
        }else if (password.isEmpty() || password.equals(" ")) {
            Toast.makeText(this, "O EMAIL NAO PODE ESTAR VAZIO", Toast.LENGTH_LONG).show();
        }else{
            LoginRequest login =  new LoginRequest(email, password);

            try{
                authService.login(login, new VolleyCallBack() {
                    @Override
                    public void onSuccess(String response) {
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