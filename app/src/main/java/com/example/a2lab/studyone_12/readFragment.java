package com.example.a2lab.studyone_12;


import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link readFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class readFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    View myView;
    TextView readtext,text_summary,text_list_vocabulary;
    ArrayList list_vocabulary;
    int  name;

    Date date ;
    SimpleDateFormat sdf;//日期
    String dateString ;
    FileWriter fw;//
    String filename;
    String path = Environment.getExternalStorageDirectory().getPath();
    static String studentnum1;

    public readFragment() {
        // Required empty public constructor
    }


    public static readFragment newInstance(String param1, String param2) {
        readFragment fragment = new readFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list_vocabulary = new ArrayList<>();
        list_vocabulary.add("aforementioned");//1
        list_vocabulary.add("animation");//2
        list_vocabulary.add("appreciate");//3
        list_vocabulary.add("approach");//4
        list_vocabulary.add("attract");//5
        list_vocabulary.add("cluster");//6
        list_vocabulary.add("communication");//7
        list_vocabulary.add("concern");//8
        list_vocabulary.add("discover");//9
        list_vocabulary.add("dissimilar");//10
        list_vocabulary.add("division");//11
        list_vocabulary.add("factor");//12
        list_vocabulary.add("formulation");//13
        list_vocabulary.add("hypotheses");//14
        list_vocabulary.add("identify");//15
        list_vocabulary.add("In turn");//16
        list_vocabulary.add("incorporate");//17
        list_vocabulary.add("indicate");//18
        list_vocabulary.add("initial");//19
        list_vocabulary.add("instruction");//20
        list_vocabulary.add("interactive");//21
        list_vocabulary.add("investigation");//22
        list_vocabulary.add("multimedia");//23
        list_vocabulary.add("novice");//24
        list_vocabulary.add("object");//25
        list_vocabulary.add("pattern");//26
        list_vocabulary.add("preference");//27
        list_vocabulary.add("restricted");//28
        list_vocabulary.add("scheme");//29
        list_vocabulary.add("scope");//30
        list_vocabulary.add("significance");//31
        list_vocabulary.add("statistical");//32
        list_vocabulary.add("type");//33
        list_vocabulary.add("utilize");//34
        list_vocabulary.add("vary");//35


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedIstanceState) {
        super.onViewCreated(view, savedIstanceState);
        getActivity().setTitle("閱讀文章");

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

            fw.write(dateString+"  "+"read: "+ behavior+"\n");
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.fragment_read, container, false);
        readtext = (TextView) myView.findViewById(R.id.read_article);
        readtext.setText(exam_ques_ans2.reading_summary1[0]);//reading
        readtext.setMovementMethod(ScrollingMovementMethod.getInstance());//textview滾動!!



        Button but_summary = (Button) myView.findViewById(R.id.hint_summary);
        final Button but_image = (Button) myView.findViewById(R.id.hint_image);
        Button but_vocabulary = (Button) myView.findViewById(R.id.hint_vocabulary);
        but_image.setOnClickListener(new View.OnClickListener() {//圖片dialog
            @Override
            public void onClick(View v) {
                Log.i("點選按鈕","文章圖片");
                appendlog("按下按鈕:文章圖片");//寫入
                ImageView img = new ImageView(getContext());
                img.setImageResource(R.drawable.dialog_image1);
                ContextThemeWrapper contextThemeWrapper =new ContextThemeWrapper(getActivity(), R.style.dialog);
                final AlertDialog.Builder dialog_image = new AlertDialog.Builder(contextThemeWrapper);
                dialog_image.setTitle("文章圖片")
                        .setView(img)
                        .setPositiveButton("關閉",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                Log.i("點選按鈕","文章圖片_關閉");
                                appendlog("按下按鈕:文章圖片-關閉");//寫入
                            }
                        })
                        .show();
            }
        });
        but_summary.setOnClickListener(new View.OnClickListener() {//文章大綱dialog
            @Override
            public void onClick(View v) {
                Log.i("點選按鈕","中文摘要");
                appendlog("按下按鈕:文章摘要");//寫入
                LayoutInflater inflaterdialog =getActivity().getLayoutInflater();
                View summary = inflaterdialog.inflate(R.layout.dialog_paragraph,null);
                text_summary=(TextView)summary.findViewById(R.id.textView_paragraph);
                text_summary.setText(exam_ques_ans2.reading_summary1[1]);
                ContextThemeWrapper contextThemeWrapper =new ContextThemeWrapper(getActivity(), R.style.dialog);
                final AlertDialog.Builder dialog_summary = new AlertDialog.Builder(contextThemeWrapper);
                dialog_summary.setTitle("中文摘要")
                        .setView(summary)
                        .setPositiveButton("關閉",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                appendlog("按下按鈕:文章摘要-關閉");//寫入
                            }
                        })
                        .show();

            }
        });
        but_vocabulary.setOnClickListener(new View.OnClickListener() {//單字dialog
            @Override
            public void onClick(View v) {
                Log.i("點選按鈕","單字查詢");
                appendlog("按下按鈕:單字查詢");//寫入
                ContextThemeWrapper contextThemeWrapper =new ContextThemeWrapper(getActivity(), R.style.dialog);
                final AlertDialog.Builder dialog_vocabulary = new AlertDialog.Builder(contextThemeWrapper);
                dialog_vocabulary.setTitle("單字查詢")
                        .setSingleChoiceItems((String[]) list_vocabulary.toArray(new String[list_vocabulary.size()]),0, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                name= which;
                            }
                        })
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LayoutInflater inflaterdialog =getActivity().getLayoutInflater();
                                View listvocabulary = inflaterdialog.inflate(R.layout.dialog_listvocabulary,null);
                                text_list_vocabulary=(TextView)listvocabulary.findViewById(R.id.textView_list_vocabulary);
                                text_list_vocabulary.setText(exam_ques_ans.reading_vocabularychinese[name]);

                                appendlog("按下按鈕:單字查詢-選擇的單字:"+list_vocabulary.get(name).toString());//寫入

                                ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
                                final AlertDialog.Builder ad = new AlertDialog.Builder(contextThemeWrapper);//抓單字解釋
                                ad.setTitle(list_vocabulary.get(name).toString())
                                        .setView(listvocabulary)
                                        .setPositiveButton("關閉",new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                                appendlog("按下按鈕:單字查詢-選擇的單字:"+list_vocabulary.get(name).toString()+"-關閉");//寫入
                                            }
                                        })
                                        .show();
                                Log.i("選擇的單字:",list_vocabulary.get(name).toString());
                            }
                        })
                        .setNegativeButton("關閉", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                appendlog("按下按鈕:單字查詢-關閉");//寫入
                            }
                        })
                        .show();
            }
        });

        return myView;
    }

}
