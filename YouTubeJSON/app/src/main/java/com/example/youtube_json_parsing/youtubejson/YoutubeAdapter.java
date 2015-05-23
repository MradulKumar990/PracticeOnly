package com.example.youtube_json_parsing.youtubejson;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mradul on 3/31/2015.
 */
public class YoutubeAdapter extends BaseAdapter {
    Context context;
    ArrayList<YoutubeData> youtubeDatas;

    public YoutubeAdapter(Context context, ArrayList<YoutubeData> youtubeDatas){
        this.context = context;
        this.youtubeDatas = youtubeDatas;
    }

    @Override
    public int getCount() {
        return youtubeDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return youtubeDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.youtube_layout,viewGroup,false);
        TextView title = (TextView) row.findViewById(R.id.video_title);
        TextView uploader = (TextView) row.findViewById(R.id.video_uploader);
        TextView description = (TextView) row.findViewById(R.id.video_description);
        ImageView thumbnial_pic = (ImageView) row.findViewById(R.id.yooutube_thumbnail);

        final YoutubeData singlerow = youtubeDatas.get(i);
        title.setText(singlerow.getTitle());
        uploader.setText(singlerow.getLoader());
        description.setText(singlerow.getDescription());
        Picasso.with(context).load(singlerow.getImage()).into(thumbnial_pic);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("url", singlerow.getUrl());
                Log.e("title", singlerow.getTitle());
                Log.e("desc", singlerow.getDescription());

                Intent intent = new Intent(context,YoutubePlayer.class);
                intent.putExtra("url", singlerow.getUrl());
                intent.putExtra("title", singlerow.getTitle());
                intent.putExtra("desc", singlerow.getDescription());
                context.startActivity(intent);
            }
        });

        return row;
    }


}
