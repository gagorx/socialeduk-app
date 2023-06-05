package com.example.socialeduk.views.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.example.socialeduk.R;
import com.example.socialeduk.interfaces.VolleyCallBack;
import com.example.socialeduk.models.dto.UserRegister;
import com.example.socialeduk.services.RegisterService;
import com.example.socialeduk.views.login.LoginActivity;

import org.json.JSONException;

public class RegisterActivity extends AppCompatActivity {

    private RegisterService registerService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerService = new RegisterService(Volley.newRequestQueue(this));

        Button back = findViewById(R.id.registerScreen_back_button);
        back.setOnClickListener(v -> startLoginScreen());

        Button register = findViewById(R.id.registerScreen_register_button);
        register.setOnClickListener(v -> tryRegister(getUsername(), getPassword(), getName(), getEmail()));

    }


    private void tryRegister(String username, String password, String name, String email ) {


        if (username.isEmpty() || username.equals(" ")){
           Toast.makeText(this, "O NOME DO USUARIO NAO PODE ESTAR VAZIO", Toast.LENGTH_LONG).show();
        } else if (name.isEmpty() || name.equals(" ")) {
           Toast.makeText(this, "O NOME NAO PODE ESTAR VAZIO", Toast.LENGTH_LONG).show();
        }else if (email.isEmpty() || email.equals(" ")) {
           Toast.makeText(this, "O EMAIL NAO PODE ESTAR VAZIO", Toast.LENGTH_LONG).show();
        }else if (password.isEmpty() || password.equals(" ")) {
           Toast.makeText(this, "O EMAIL NAO PODE ESTAR VAZIO", Toast.LENGTH_LONG).show();
        }else{
            UserRegister user =  new UserRegister(username, password, name, email);

            try{
                registerService.register(user, new VolleyCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(RegisterActivity.this, "Cadastro realizado! Faça o Login.", Toast.LENGTH_LONG).show();
                        startLoginScreen();
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(RegisterActivity.this, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                                "persistir, contate o administrador", Toast.LENGTH_LONG).show();
                    }
                });
            }catch (JSONException e){
                Toast.makeText(RegisterActivity.this, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                        "persistir, contate o administrador", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void startLoginScreen() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private String getUsername() {
        EditText inputUsername =  findViewById(R.id.registerScreen_inputUsername_plainText);
        return inputUsername.getText().toString();

    }

    private String getName() {
        EditText inputName =  findViewById(R.id.registerScreen_inputName_plainText);
        return inputName.getText().toString();
    }

    private String getEmail() {
        EditText inputEmail =  findViewById(R.id.registerScreen_inputEmail_plainText);
        return inputEmail.getText().toString();
    }

    private String getPassword() {
        EditText inputPassword =  findViewById(R.id.registerScreen_inputPassword_plainTextPassword);
        return inputPassword.getText().toString();
    }
}