package com.example.sportevent.data.repository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

public class LoginRepository {
    private Application application;
    private MutableLiveData<FirebaseUser> userMultiLiveData;
    private FirebaseAuth firebaseAuth;
    public LoginRepository(Application application){
        this.application = application;
        userMultiLiveData = new MutableLiveData<>();
        firebaseAuth = FirebaseAuth.getInstance();
    }
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void register(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(application.getMainExecutor(),new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                      if(task.isSuccessful()){
                          userMultiLiveData.postValue(firebaseAuth.getCurrentUser());
                      } else {

                      }
                    }
                });
    }
}
