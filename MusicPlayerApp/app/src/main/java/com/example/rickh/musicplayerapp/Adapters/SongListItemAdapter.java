package com.example.rickh.musicplayerapp.Adapters;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rickh.musicplayerapp.Audio;
import com.example.rickh.musicplayerapp.R;
import com.example.rickh.musicplayerapp.Services.MediaPlayerService;
import com.example.rickh.musicplayerapp.Services.StorageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rickh on 9/14/2017.
 */

public class SongListItemAdapter extends BaseAdapter {

    public static final String Broadcast_PLAY_NEW_AUDIO = "com.valdioveliu.valdio.audioplayer.PlayNewAudio";
    private Context mContext;
//    private List<Audio> mMusicList;

    private MediaPlayerService player;
    boolean serviceBound = false;
    ArrayList<Audio> audioList;

    public SongListItemAdapter(Context mContext, ArrayList<Audio> audioList) {
        this.mContext = mContext;
        this.audioList = audioList;
    }

    @Override
    public int getCount() {
        return audioList.size();
    }

    @Override
    public Object getItem(int i) {
        return audioList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final View v = View.inflate(mContext, R.layout.item_song_list, null);

        // Define the TextViews
        TextView mSongNameTV = (TextView)v.findViewById(R.id.mSongNameTV);
        TextView mArtistNameTV = (TextView)v.findViewById(R.id.mSongArtistTV);
        final ImageView mPlaySongIV = (ImageView) v.findViewById(R.id.mPlayIV);
        ImageView mOptionsIV = (ImageView) v.findViewById(R.id.mOptionsIV);

        // Set the Text
        mSongNameTV.setText(audioList.get(i).getSongName());
        mArtistNameTV.setText(audioList.get(i).getSongArtist());

        mOptionsIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Options " + i, Toast.LENGTH_SHORT).show();
            }
        });

        mPlaySongIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // Save music to tag
        v.setTag(audioList.get(i).getId());

        return v;
    }
}