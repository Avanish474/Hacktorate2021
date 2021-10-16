package com.example.hacktorate.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hacktorate.R;
import com.example.hacktorate.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        
    }

    public void onLoginClick(View View){
//        startActivity(new Intent(this,RegisterActivity.class));
//        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }

    public void onCll(View view){
//        startActivity(new Intent(this,MainActivity2.class));
//        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
        // navigate to stock
    }
}
