package com.example.rickh.musicplayerapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rickh.musicplayerapp.R;

/**
 * Created by rickh on 9/14/2017.
 */

public class HomeFragment extends Fragment {

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.home_fragment, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");

        return myView;
    }
}
