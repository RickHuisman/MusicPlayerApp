package com.example.rickh.musicplayerapp.Fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rickh.musicplayerapp.Adapters.SongListItemAdapter;
import com.example.rickh.musicplayerapp.Audio;
import com.example.rickh.musicplayerapp.R;
import com.example.rickh.musicplayerapp.Services.MediaPlayerService;
import com.example.rickh.musicplayerapp.Services.StorageUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by rickh on 9/14/2017.
 */

public class Tab1SongsFragment extends android.support.v4.app.Fragment {

    View myView;
    Context mContext;
    public static final String Broadcast_PLAY_NEW_AUDIO = "com.valdioveliu.valdio.audioplayer.PlayNewAudio";

    SongListItemAdapter adapter;
    ListView mSongsListView;

    FirebaseDatabase database;
    DatabaseReference mMyMusicRef;


    private MediaPlayerService player;
    boolean serviceBound = false;

    ArrayList<Audio> audioList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.tab1_songs_fragment, container, false);
        mContext = getContext().getApplicationContext();
        mSongsListView = (ListView) myView.findViewById(R.id.songs_list);

        database = FirebaseDatabase.getInstance();
        mMyMusicRef = database.getReference().child("Music").child("Users").child("Pf5HewN1wfNtCseRp0kJQ9aoTNw2").child("Songs");

        loadAudio();

        mSongsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("Debug", String.valueOf(i));
                Toast.makeText(view.getContext(), "Options " + i, Toast.LENGTH_SHORT).show();
                playAudio(i);
            }
        });

        return myView;
    }

    public void loadAudio() {
        mMyMusicRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                audioList = new ArrayList<>();

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String downloadUrl = childSnapshot.child("Download url").getValue().toString();
                    String randomKey = childSnapshot.child("Randomkey").getValue().toString();
                    String songName = childSnapshot.child("Song name").getValue().toString();
                    String songArtist = childSnapshot.child("Song artist").getValue().toString();
                    String uploaderUid = childSnapshot.child("Uploader uid").getValue().toString();
                    String timeStamp = childSnapshot.child("Time stamp").getValue().toString();

                    audioList.add(new Audio(
                            5,
                            downloadUrl,
                            randomKey,
                            songName,
                            songArtist,
                            uploaderUid,
                            timeStamp));
                    initListView();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void initListView() {
        if (audioList != null && audioList.size() > 0) {
            adapter = new SongListItemAdapter(getActivity().getApplicationContext(), audioList);
            mSongsListView.setAdapter(adapter);
        }
    }



    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("Debug tag", "serviceConnection");
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MediaPlayerService.LocalBinder binder = (MediaPlayerService.LocalBinder) service;
            player = binder.getService();
            serviceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceBound = false;
        }
    };

    private void playAudio(int audioIndex) {
        Log.d("Debug tag", "playAudio");
        //Check is service is active
        if (!serviceBound) {
            Log.d("Debug tag", "serviceBound");
            //Store Serializable audioList to SharedPreferences
            StorageUtil storage = new StorageUtil(mContext.getApplicationContext());
            storage.storeAudio(audioList);
            storage.storeAudioIndex(audioIndex);

            Intent playerIntent = new Intent(mContext.getApplicationContext(), MediaPlayerService.class);
            mContext.startService(playerIntent);
            mContext.getApplicationContext().bindService(playerIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        } else {
            Log.d("Debug tag", "serviceNotBound");
            //Store the new audioIndex to SharedPreferences
            StorageUtil storage = new StorageUtil(mContext.getApplicationContext());
            storage.storeAudioIndex(audioIndex);

            //Service is active
            //Send a broadcast to the service -> PLAY_NEW_AUDIO
            Intent broadcastIntent = new Intent(Broadcast_PLAY_NEW_AUDIO);
            mContext.getApplicationContext().sendBroadcast(broadcastIntent);
        }
    }
}
