package com.backupbro.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backupbro.R;
import com.backupbro.model.User;
import com.backupbro.model.UserViewModel;
import com.backupbro.network.RetrofitInstance;
import com.backupbro.network.UserService;
import com.backupbro.repository.UserRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private Button resetPasswordButton;
    private Button signupButton;
    private EditText emailInputText;
    private EditText passwordInputText;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userService = RetrofitInstance.getRetrofitInstance().create(UserService.class);
        emailInputText = (EditText)findViewById(R.id.login_input_email);
        passwordInputText = (EditText)findViewById(R.id.login_input_password);
        loginButton = (Button)findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                login();
            }
        });
        resetPasswordButton = (Button)findViewById(R.id.reset_password_page_button);
        resetPasswordButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(i);
            }
        });
        signupButton = (Button)findViewById(R.id.signup_page_button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(i);
            }
        });
    }

    private void login() {
        User user = new User();
        user.setEmail(emailInputText.getText().toString());
        user.setPassword(passwordInputText.getText().toString());
        Call<User> userModel = userService.login(user);
        userModel.enqueue(new Callback<User>(){
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Login Succeeded", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();

            }
        });




    }

//    public static class SplashPageActivity extends AppCompatActivity {
//
//        @Override
//        protected void onCreate(@Nullable Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }
}
