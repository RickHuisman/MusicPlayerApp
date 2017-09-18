package com.example.rickh.musicplayerapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rickh.musicplayerapp.R;

/**
 * Created by rickh on 9/14/2017.
 */

public class Tab2PlayListsFragment extends android.support.v4.app.Fragment {

    View myView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.tab2_playlists_fragment, container, false);

        return myView;
    }
}
