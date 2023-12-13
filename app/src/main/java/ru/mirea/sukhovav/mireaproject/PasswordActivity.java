package ru.mirea.sukhovav.mireaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executor;

public class PasswordActivity extends AppCompatActivity {


    String fileName = "password.txt";

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errString.equals("выйти")) {
                    Toast.makeText(getApplicationContext(),
                                    "\uD83D\uDE3F: " + errString, Toast.LENGTH_SHORT)
                            .show();
                    System.exit(0);
                } else {
                    Toast.makeText(getApplicationContext(),
                                    "\uD83D\uDE3F: " + errString, Toast.LENGTH_SHORT)
                            .show();
                    System.exit(0);
                }
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(),
                                "Лапка распознана (=^･ω･^=)", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Попробуй использовать котомордочку (^._.^)",
                                Toast.LENGTH_SHORT)
                        .show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Приложите лапку к экрану")
                .setSubtitle("ฅ•ω•ฅ")
                .setNegativeButtonText("выйти")
                .build();

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.


        biometricPrompt.authenticate(promptInfo);
    }



}