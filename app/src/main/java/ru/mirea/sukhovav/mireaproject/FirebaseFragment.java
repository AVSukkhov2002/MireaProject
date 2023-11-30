package ru.mirea.sukhovav.mireaproject;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import ru.mirea.sukhovav.mireaproject.databinding.ActivityMainBinding;
import ru.mirea.sukhovav.mireaproject.databinding.FragmentFirebaseBinding;

 /*
public class FirebaseFragment extends Fragment {



    private String mParam1;
    private String mParam2;
    private FirebaseAuth mAuth;
    private FirebaseApp app;
    private ViewDataBinding binding;

    EditText EmailStr;
    EditText PasswordStr;
    Button SignIn;
    Button CreateAccount;
    Button VerifycationEmail;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        Log.d(TAG, "MY FIREBASE - " + FirebaseApp.getInstance().getOptions().getProjectId());
        // mireaproject2-e3f9b


    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_any_desk, container, false);

        EmailStr = view.findViewById(R.id.EmailStr);
        PasswordStr = view.findViewById(R.id.PasswordStr);
        SignIn = view.findViewById(R.id.SignIn);
        CreateAccount = view.findViewById(R.id.CreateAccount);
        VerifycationEmail = view.findViewById(R.id.VerifycationEmail);


        return view;
    }

    @Override
    public void onResume(){
        super.onResume();


        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(String.valueOf(EmailStr.getText()), String.valueOf(PasswordStr.getText()));
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            //binding.statusTextView.setText(getString(R.string.emailpassword_status_fmt,
            //        user.getEmail(), user.isEmailVerified()));
            //binding.detailTextView.setText(getString(R.string.firebase_status_fmt,
           //         user.getUid()));
            //binding.EmailPasswordButtons.setVisibility(View.GONE);
            //binding.EmailPasswordFields.setVisibility(View.GONE);
            //binding.SignedButtons.setVisibility(View.VISIBLE);
            //binding.VerifycationEmail.setEnabled(!user.isEmailVerified());
        } else {
            //binding.detailTextView.setText(null);
            //binding.PasswordStr.setVisibility(View.VISIBLE);
            //binding.CreateAccount.setVisibility(View.VISIBLE);
           // binding.VerifycationEmail.setVisibility(View.GONE);
            //binding.EmailStr.setVisibility(View.VISIBLE);
            //binding.SignIn.setVisibility(View.VISIBLE);
            //binding.SignOut.setVisibility(View.GONE);
        }
    }

    public void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(currentUser);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

    }
    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        //if (!task.isSuccessful()) {
                        //    binding.statusTextView.setText(R.string.auth_failed);
                        //}
                    }
                });
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    private void sendEmailVerification() {

        VerifycationEmail.setEnabled(false);
        final FirebaseUser user = mAuth.getCurrentUser();
        Objects.requireNonNull(user).sendEmailVerification()
                .addOnCompleteListener((Executor) this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        VerifycationEmail.setEnabled(true);
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(),
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(getActivity(), "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}
        */