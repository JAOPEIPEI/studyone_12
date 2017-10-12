package com.example.a2lab.studyone_12;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link videoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class videoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View myView;
    MediaController mediacontroller;
    VideoView videoView;
    String uriPath;
    ImageButton btn_play,btn_pause;
    SeekBar seekBar;
    Handler handler;

    Runnable runnable;


    Date date ;
    SimpleDateFormat sdf;//日期
    String dateString ;
    FileWriter fw;//
    String filename;
    String path = Environment.getExternalStorageDirectory().getPath();
    static String studentnum1;

    private int position = 0;
    int checkpos = 0;

    public videoFragment() {
        // Required empty public constructor
    }

    public static videoFragment newInstance(String param1, String param2) {
        videoFragment fragment = new videoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public void setStudentnum(String studentnum){
        studentnum1=studentnum;
        Log.i("有收到學號",studentnum1);
    }

    public void appendlog(String behavior){//輸出txt
        try {
            date = new Date();
            sdf = new SimpleDateFormat("MM/dd HH:mm:ss");//日期
            dateString = sdf.format(date);

            filename="/"+studentnum1+"_studyone.txt";//學號為檔名
            fw = new FileWriter(path+filename,true);

            fw.write(dateString+"  "+"video: "+ behavior+"\n");
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedIstanceState) {
        super.onViewCreated(view, savedIstanceState);
        getActivity().setTitle("動畫影片");

        uriPath = "android.resource://com.example.a2lab.studyone_12/" + R.raw.data;
        Uri uri2 = Uri.parse(uriPath);
        videoView.setVideoURI(uri2);

//        videoView.setMediaController(mediacontroller);
        mediacontroller.setAnchorView(videoView);
        videoView.start();
        videoView.pause();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myView = inflater.inflate(R.layout.fragment_video, container, false);
        videoView = (VideoView) myView.findViewById(R.id.videoView);
        mediacontroller=new MediaController(getActivity());
        seekBar = (SeekBar) myView.findViewById(R.id.seekBar);
        videoView = (VideoView) myView.findViewById(R.id.videoView);


        btn_play= (ImageButton) myView.findViewById(R.id.button_play);
        btn_pause = (ImageButton) myView.findViewById(R.id.button_pause);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.i("按", "play");
                    videoView.start();
                    playCycle();
                    btn_play.setEnabled(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });





        /**撥放/暫停/繼續**/
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( videoView.isPlaying()){
                    Log.i("暫停","WWW");

                    videoView.pause();
                    btn_pause.setImageResource(android.R.drawable.ic_media_play);
                }else{
                    Log.i("繼續", "SS");

                    btn_pause.setImageResource(android.R.drawable.ic_media_pause);
                    videoView.start();
                }
            }
        });

        //
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                Toast.makeText(getActivity(),"影片播放完畢", Toast.LENGTH_SHORT).show();
                appendlog("影片撥放完畢");
                btn_pause.setImageResource(android.R.drawable.ic_media_play);
                Log.i("QQ","撥放完畢");
                //播放完畢
            }
        });


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(videoView.getDuration());
                playCycle();
                Log.i("呼叫","playcycle");
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    videoView.seekTo(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                if (videoView != null && videoView.isPlaying()) {
                    // Set the position of the currently playing
                    videoView.seekTo(progress);
                }
            }
        });





        return myView;
    }
    public  void playCycle(){
        seekBar.setProgress(videoView.getCurrentPosition());
        Log.i("換","seekbar");

        Log.i("呼叫","run 外面");
        if(videoView.isPlaying()){
            runnable=new Runnable() {
                @Override
                public void run() {
                    playCycle();
                    Log.i("呼叫","run");
                }
            };
            handler.post(runnable);
        }
    }






}
