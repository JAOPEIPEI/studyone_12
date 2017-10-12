package com.example.a2lab.studyone_12;


import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.graphics.Color;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.support.v4.content.res.ResourcesCompat.getDrawable;
import static com.example.a2lab.studyone_12.R.drawable.buttonshape_yellow;
import static com.example.a2lab.studyone_12.R.drawable.buttonshape_yellow2;


public class examFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View myView, dialog_paragraph;
    TextView text_answer, text_pp,text_question,text_core;
    Button buttoncheck, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, close;
    Button btn_deletewords, btn_paragraph,btn_help;
    boolean hint_delete = true;
    RadioGroup mRadioGroup;
    RadioButton radiobtn1, radiobtn2, radiobtn3, radiobtn4;
    int Quesnum[] = new int[17];
    int btn_index = 0;
    int i =500;//core
    int k;//紀錄刪除按鈕次數
    String ans_user = "";
    TextView text_paragraph;
    ArrayList list_help;
    int name;


    Date date ;
    SimpleDateFormat sdf;//日期
    String dateString,starttime,endtime ;
    FileWriter fw;//文件
    String filename;
    String path = Environment.getExternalStorageDirectory().getPath();
    static String studentnum1;//傳入的學號

    public examFragment() {
        // Required empty public constructor
    }

    public static examFragment newInstance(String param1, String param2) {
        examFragment fragment = new examFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void setStudentnum(String studentnum){//收學號
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

            fw.write(dateString+"  "+"exam: "+ behavior+"\n");
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void finishlog(String result){
        try {
            date = new Date();
            sdf = new SimpleDateFormat("MM/dd HH:mm:ss");//日期
            endtime = sdf.format(date);

            filename="/"+studentnum1+"_studyone.txt";//學號為檔名
            fw = new FileWriter(path+filename,true);

            fw.write(" "+ result);
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    String correct = "Correct";
    String incorrect = "Incorrect";
    View.OnClickListener lis_check = new View.OnClickListener() {//Check按鈕:對答案
        @Override
        public void onClick(View v) {
            if (radiobtn1.isChecked()) {
                ans_user = radiobtn1.getText().toString();
                if (ans_user.equals(exam_ques_ans2.ArrayCorrectAns1[btn_index])) {
                    text_answer.setText(correct);
                    text_core.setText(Integer.toString(i+=10));
                    draw(btn_index, correct);
                    appendlog("選擇答案:A");
                } else {
                    text_answer.setText(incorrect);
                    draw(btn_index, incorrect);
                    appendlog("選擇答案:A");
                }
            } else if (radiobtn2.isChecked()) {
                ans_user = radiobtn2.getText().toString();
                if (ans_user.equals(exam_ques_ans2.ArrayCorrectAns1[btn_index])) {
                    text_answer.setText(correct);
                    text_core.setText(Integer.toString(i+=10));
                    draw(btn_index, correct);
                    appendlog("選擇答案:B");
                } else {
                    text_answer.setText(incorrect);
                    draw(btn_index, incorrect);
                    appendlog("選擇答案:B");
                }
            } else if (radiobtn3.isChecked()) {
                ans_user = radiobtn3.getText().toString();
                if (ans_user.equals(exam_ques_ans2.ArrayCorrectAns1[btn_index])) {
                    text_answer.setText(correct);
                    text_core.setText(Integer.toString(i+=10));
                    draw(btn_index, correct);
                    appendlog("選擇答案:C");
                } else {
                    text_answer.setText(incorrect);
                    draw(btn_index, incorrect);
                    appendlog("選擇答案:C");
                }
            } else if (radiobtn4.isChecked()) {
                ans_user = radiobtn4.getText().toString();
                if (ans_user.equals(exam_ques_ans2.ArrayCorrectAns1[btn_index])) {
                    text_answer.setText(correct);
                    text_core.setText(Integer.toString(i+=10));
                    draw(btn_index, correct);
                    appendlog("選擇答案:D");
                } else {
                    text_answer.setText(incorrect);
                    draw(btn_index, incorrect);
                    appendlog("選擇答案:D");
                }
            }

        }
    };


    public void draw(int btn_index, String ci) {//在按鈕中畫OX
        switch (btn_index) {
            case 0://第1題
                if (ci.equals(correct)) {
                    appendlog("第1題:O");
                    b1.setText("O");
                    Quesnum[0] = 1;
                    b1.setTextColor(0xFF0000FF);//藍色
                    text_pp.setTextColor(0xFF0000FF);
                    text_answer.setTextColor(0xFF0000FF);

                } else if (ci.equals(incorrect)) {
                    appendlog("第1題:X");
                    b1.setText("X");
                    Quesnum[0] = 2;
                    b1.setTextColor(0xFFFF0000);//紅色
                    text_pp.setTextColor(0xFFFF0000);
                    text_answer.setTextColor(0xFFFF0000);
                }
                b1.setEnabled(false);
                break;

            case 1:
                if (ci.equals(correct)) {
                    appendlog("第2題:O");
                    b2.setText("O");
                    Quesnum[1] = 1;
                    b2.setTextColor(0xFF0000FF);//藍色
                    text_pp.setTextColor(0xFF0000FF);
                    text_answer.setTextColor(0xFF0000FF);
                } else if (ci.equals(incorrect)) {
                    appendlog("第2題:X");
                    b2.setText("X");
                    Quesnum[1] = 2;
                    b2.setTextColor(0xFFFF0000);//紅色
                    text_pp.setTextColor(0xFFFF0000);
                    text_answer.setTextColor(0xFFFF0000);
                }
                b2.setEnabled(false);
                break;
            case 2:
                if (ci.equals(correct)) {
                    appendlog("第3題:O");
                    b3.setText("O");
                    Quesnum[2] = 1;
                    b3.setTextColor(0xFF0000FF);//藍色
                    text_pp.setTextColor(0xFF0000FF);
                    text_answer.setTextColor(0xFF0000FF);
                } else if (ci.equals(incorrect)) {
                    appendlog("第3題:X");
                    b3.setText("X");
                    Quesnum[2] = 2;
                    b3.setTextColor(0xFFFF0000);//紅色
                    text_pp.setTextColor(0xFFFF0000);
                    text_answer.setTextColor(0xFFFF0000);
                }
                b3.setEnabled(false);
                break;
            case 3:
                if (ci.equals(correct)) {
                    appendlog("第4題:O");
                    b4.setText("O");
                    Quesnum[3] = 1;
                    b4.setTextColor(0xFF0000FF);//藍色
                    text_pp.setTextColor(0xFF0000FF);
                    text_answer.setTextColor(0xFF0000FF);
                } else if (ci.equals(incorrect)) {
                    appendlog("第4題:X");
                    b4.setText("X");
                    Quesnum[3] = 2;
                    b4.setTextColor(0xFFFF0000);//紅色
                    text_pp.setTextColor(0xFFFF0000);
                    text_answer.setTextColor(0xFFFF0000);
                }
                b4.setEnabled(false);
                break;
            case 4:
                if (ci.equals(correct)) {
                    appendlog("第5題:O");
                    b5.setText("O");
                    b5.setTextColor(0xFF0000FF);//藍色
                    text_pp.setTextColor(0xFF0000FF);
                    text_answer.setTextColor(0xFF0000FF);
                    Quesnum[4] = 1;
                } else if (ci.equals(incorrect)) {
                    appendlog("第5題:X");
                    b5.setText("X");
                    Quesnum[4] = 2;
                    b5.setTextColor(0xFFFF0000);//紅色
                    text_pp.setTextColor(0xFFFF0000);
                    text_answer.setTextColor(0xFFFF0000);
                }
                b5.setEnabled(false);
                break;
            case 5:
                if (ci.equals(correct)) {
                    appendlog("第6題:O");
                    b6.setText("O");
                    b6.setTextColor(0xFF0000FF);//藍色
                    text_pp.setTextColor(0xFF0000FF);
                    text_answer.setTextColor(0xFF0000FF);
                    Quesnum[5] = 1;
                } else if (ci.equals(incorrect)) {
                    appendlog("第6題:X");
                    b6.setText("X");
                    Quesnum[5] = 2;
                    b6.setTextColor(0xFFFF0000);//紅色
                    text_pp.setTextColor(0xFFFF0000);
                    text_answer.setTextColor(0xFFFF0000);
                }
                b6.setEnabled(false);
                break;
            case 6:
                if (ci.equals(correct)) {
                    appendlog("第7題:O");
                    b7.setText("O");
                    b7.setTextColor(0xFF0000FF);//藍色
                    text_pp.setTextColor(0xFF0000FF);
                    text_answer.setTextColor(0xFF0000FF);
                    Quesnum[6] = 1;
                } else if (ci.equals(incorrect)) {
                    appendlog("第7題:X");
                    b7.setText("X");
                    Quesnum[6] = 2;
                    b7.setTextColor(0xFFFF0000);//紅色
                    text_pp.setTextColor(0xFFFF0000);
                    text_answer.setTextColor(0xFFFF0000);
                }
                b7.setEnabled(false);
                break;

            case 7:
                if (ci.equals(correct)) {
                    appendlog("第8題:O");
                    b8.setText("O");
                    Quesnum[7] = 1;
                    b8.setTextColor(0xFF0000FF);//藍色
                    text_pp.setTextColor(0xFF0000FF);
                    text_answer.setTextColor(0xFF0000FF);

                } else if (ci.equals(incorrect)) {
                    appendlog("第8題:X");
                    b8.setText("X");
                    Quesnum[7] = 2;
                    b8.setTextColor(0xFFFF0000);//紅色
                    text_pp.setTextColor(0xFFFF0000);
                    text_answer.setTextColor(0xFFFF0000);
                }
                b8.setEnabled(false);
                break;
            case 8:
                if (ci.equals(correct)) {
                    appendlog("第9題:O");
                    b9.setText("O");
                    b9.setTextColor(0xFF0000FF);//藍色
                    text_pp.setTextColor(0xFF0000FF);
                    text_answer.setTextColor(0xFF0000FF);
                    Quesnum[8] = 1;
                } else if (ci.equals(incorrect)) {
                    appendlog("第9題:X");
                    b9.setText("X");
                    Quesnum[8] = 2;
                    b9.setTextColor(0xFFFF0000);//紅色
                    text_pp.setTextColor(0xFFFF0000);
                    text_answer.setTextColor(0xFFFF0000);
                }
                b9.setEnabled(false);
                break;
            case 9:
                if (ci.equals(correct)) {
                    appendlog("第10題:O");
                    b10.setText("O");
                    Quesnum[9] = 1;
                    b10.setTextColor(0xFF0000FF);//藍色
                    text_pp.setTextColor(0xFF0000FF);
                    text_answer.setTextColor(0xFF0000FF);
                } else if (ci.equals(incorrect)) {
                    appendlog("第10題:X");
                    b10.setText("X");
                    Quesnum[9] = 2;
                    b10.setTextColor(0xFFFF0000);//紅色
                    text_pp.setTextColor(0xFFFF0000);
                    text_answer.setTextColor(0xFFFF0000);
                }
                b10.setEnabled(false);
                break;
            case 10:
                if (ci.equals(correct)) {
                    appendlog("第11題:O");
                    b11.setText("O");
                    Quesnum[10] = 1;
                    b11.setTextColor(0xFF0000FF);//藍色
                    text_pp.setTextColor(0xFF0000FF);
                    text_answer.setTextColor(0xFF0000FF);
                } else if (ci.equals(incorrect)) {
                    appendlog("第11題:X");
                    b11.setText("X");
                    Quesnum[10] = 2;
                    b11.setTextColor(0xFFFF0000);//紅色
                    text_pp.setTextColor(0xFFFF0000);
                    text_answer.setTextColor(0xFFFF0000);
                }
                b11.setEnabled(false);
                break;
            case 11:
                if (ci.equals(correct)) {
                    appendlog("第12題:O");
                    b12.setText("O");
                    b12.setTextColor(0xFF0000FF);//藍色
                    text_pp.setTextColor(0xFF0000FF);
                    text_answer.setTextColor(0xFF0000FF);
                    Quesnum[11] = 1;
                } else if (ci.equals(incorrect)) {
                    appendlog("第12題:X");
                    b12.setText("X");
                    Quesnum[11] = 2;
                    b12.setTextColor(0xFFFF0000);//紅色
                    text_pp.setTextColor(0xFFFF0000);
                    text_answer.setTextColor(0xFFFF0000);
                }
                b12.setEnabled(false);
                break;
            case 12:
                if (ci.equals(correct)) {
                    appendlog("第13題:O");
                    b13.setText("O");
                    Quesnum[12] = 1;
                    b13.setTextColor(0xFF0000FF);//藍色
                    text_pp.setTextColor(0xFF0000FF);
                    text_answer.setTextColor(0xFF0000FF);
                } else if (ci.equals(incorrect)) {
                    appendlog("第13題:X");
                    b13.setText("X");
                    Quesnum[12] = 2;
                    b13.setTextColor(0xFFFF0000);//紅色
                    text_pp.setTextColor(0xFFFF0000);
                    text_answer.setTextColor(0xFFFF0000);
                }
                b13.setEnabled(false);
                break;
            case 13:
                if (ci.equals(correct)) {
                    appendlog("第14題:O");
                    b14.setText("O");
                    Quesnum[13] = 1;
                    b14.setTextColor(0xFF0000FF);//藍色
                    text_pp.setTextColor(0xFF0000FF);
                    text_answer.setTextColor(0xFF0000FF);
                } else if (ci.equals(incorrect)) {
                    appendlog("第14題:X");
                    b14.setText("X");
                    Quesnum[13] = 2;
                    b14.setTextColor(0xFFFF0000);//紅色
                    text_pp.setTextColor(0xFFFF0000);
                    text_answer.setTextColor(0xFFFF0000);
                }
                b14.setEnabled(false);
                break;
            case 14:
                if (ci.equals(correct)) {
                    appendlog("第15題:O");
                    b15.setText("O");
                    Quesnum[14] = 1;
                    b15.setTextColor(0xFF0000FF);//藍色
                    text_pp.setTextColor(0xFF0000FF);
                    text_answer.setTextColor(0xFF0000FF);
                } else if (ci.equals(incorrect)) {
                    appendlog("第15題:X");
                    b15.setText("X");
                    Quesnum[14] = 2;
                    b15.setTextColor(0xFFFF0000);//紅色
                    text_pp.setTextColor(0xFFFF0000);
                    text_answer.setTextColor(0xFFFF0000);
                }
                b15.setEnabled(false);
                break;
            case 15:
                if (ci.equals(correct)) {
                    appendlog("第16題:O");
                    b16.setText("O");
                    Quesnum[15] = 1;
                    b16.setTextColor(0xFF0000FF);//藍色
                    text_pp.setTextColor(0xFF0000FF);
                    text_answer.setTextColor(0xFF0000FF);
                } else if (ci.equals(incorrect)) {
                    appendlog("第16題:X");
                    b16.setText("X");
                    Quesnum[15] = 2;
                    b16.setTextColor(0xFFFF0000);//紅色
                    text_pp.setTextColor(0xFFFF0000);
                    text_answer.setTextColor(0xFFFF0000);
                }
                b16.setEnabled(false);
                break;
        }
        if (Quesnum[0] == Quesnum[1] && Quesnum[1] == Quesnum[2] && Quesnum[2] == Quesnum[3] && Quesnum[0] == 1)//橫判斷
        {
            appendlog("測驗結果:連線 1.2.3.4");
            text_core.setText(Integer.toString(i+=200));
            showDialog();
            disableButtons();
        } else if (Quesnum[4] == Quesnum[5] && Quesnum[5] == Quesnum[6] && Quesnum[6] == Quesnum[7] && Quesnum[4] == 1) {
            appendlog("測驗結果:連線 5.6.7.8");
            text_core.setText(Integer.toString(i+=200));
            showDialog();
            disableButtons();
        } else if (Quesnum[8] == Quesnum[9] && Quesnum[9] == Quesnum[10] && Quesnum[10] == Quesnum[11] && Quesnum[11] == 1) {
            appendlog("測驗結果:連線 9.10.11.12");
            text_core.setText(Integer.toString(i+=200));
            showDialog();
            disableButtons();
        } else if (Quesnum[12] == Quesnum[13] && Quesnum[13] == Quesnum[14] && Quesnum[14] == Quesnum[15] && Quesnum[15] == 1) {
            appendlog("測驗結果:連線 13.14.15.16");
            text_core.setText(Integer.toString(i+=200));
            showDialog();
            disableButtons();
        } else if (Quesnum[0] == Quesnum[4] && Quesnum[4] == Quesnum[8] && Quesnum[8] == Quesnum[12] && Quesnum[0] == 1) {//直判斷
            appendlog("測驗結果:連線 1.5.9.13");
            text_core.setText(Integer.toString(i+=200));
            showDialog();
            disableButtons();
        } else if (Quesnum[1] == Quesnum[5] && Quesnum[5] == Quesnum[9] && Quesnum[9] == Quesnum[13] && Quesnum[1] == 1) {
            appendlog("測驗結果:連線 2.6.10.14");
            text_core.setText(Integer.toString(i+=200));
            showDialog();
            disableButtons();
        } else if (Quesnum[2] == Quesnum[6] && Quesnum[6] == Quesnum[10] && Quesnum[10] == Quesnum[14] && Quesnum[14] == 1) {
            appendlog("測驗結果:連線 3.7.11.15");
            text_core.setText(Integer.toString(i+=200));
            showDialog();
            disableButtons();
        } else if (Quesnum[3] == Quesnum[7] && Quesnum[7] == Quesnum[11] && Quesnum[11] == Quesnum[15] && Quesnum[15] == 1) {
            appendlog("測驗結果:連線 4.8.12.16");
            text_core.setText(Integer.toString(i+=200));
            showDialog();
            disableButtons();
        } else if (Quesnum[0] == Quesnum[5] && Quesnum[5] == Quesnum[10] && Quesnum[10] == Quesnum[15] && Quesnum[15] == 1) {//斜判斷
            appendlog("測驗結果:連線 1.6.11.16");
            text_core.setText(Integer.toString(i+=200));
            showDialog();
            disableButtons();
        } else if (Quesnum[3] == Quesnum[6] && Quesnum[6] == Quesnum[9] && Quesnum[9] == Quesnum[12] && Quesnum[12] == 1) {
            appendlog("測驗結果:連線 4.7.10.13");
            text_core.setText(Integer.toString(i+=200));
            showDialog();
            disableButtons();
        } else if(Quesnum[1]!=0 && Quesnum[2]!=0 && Quesnum[3]!=0 && Quesnum[4]!=0 && Quesnum[5]!=0 && Quesnum[6]!=0 && Quesnum[7]!=0 && Quesnum[8]!=0 && Quesnum[9]!=0 && Quesnum[10]!=0 && Quesnum[11]!=0 && Quesnum[12]!=0 && Quesnum[13]!=0 && Quesnum[14]!=0 && Quesnum[15]!=0&& Quesnum[0]!=0){
            //輸了
            appendlog("測驗結果:沒連線");
            showDialog();
        }
        radiobtn1.setEnabled(false);
        radiobtn2.setEnabled(false);
        radiobtn3.setEnabled(false);
        radiobtn4.setEnabled(false);
        text_pp.setText("請選下一個格子");
    }

    public void showDialog() {//show nextdialog
        appendlog("完成閱讀測驗，得到的分數:"+Integer.toString(i));
        finishlog("測驗結果");
        for(int q=0;q<16;q++){finishlog(" "+Quesnum[q]);}

        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
        AlertDialog.Builder builder = new AlertDialog.Builder(contextThemeWrapper);
        LayoutInflater inflaterdialog =getActivity().getLayoutInflater();
        View view_core = inflaterdialog.inflate(R.layout.dialog_listvocabulary,null);
        TextView text_finish_core=(TextView)view_core.findViewById(R.id.textView_list_vocabulary);
        text_finish_core.setText("你的分數 :"+Integer.toString(i));
        builder.setTitle("完成閱讀測驗")
                .setView(view_core)
                .setPositiveButton("關閉", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        btn_deletewords.setEnabled(false);
                        btn_help.setEnabled(false);
                        btn_paragraph.setEnabled(false);
                    }
                }).show();
        builder.setCancelable(true);

    }

    private void disableButtons() {//不能按按鈕
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(false);
        b9.setEnabled(false);
        b10.setEnabled(false);
        b11.setEnabled(false);
        b12.setEnabled(false);
        b13.setEnabled(false);
        b14.setEnabled(false);
        b15.setEnabled(false);
        b16.setEnabled(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedIstanceState) {
        super.onViewCreated(view, savedIstanceState);
        getActivity().setTitle("測驗");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_exam, container, false);//我的畫面


        mRadioGroup = (RadioGroup) myView.findViewById(R.id.radioGroup);

        radiobtn1 = (RadioButton) myView.findViewById(R.id.radiobut1);
        radiobtn2 = (RadioButton) myView.findViewById(R.id.radiobut2);
        radiobtn3 = (RadioButton) myView.findViewById(R.id.radiobut3);
        radiobtn4 = (RadioButton) myView.findViewById(R.id.radiobut4);
        radiobtn1.setVisibility(View.INVISIBLE);
        radiobtn2.setVisibility(View.INVISIBLE);
        radiobtn3.setVisibility(View.INVISIBLE);
        radiobtn4.setVisibility(View.INVISIBLE);


        text_question = (TextView) myView.findViewById(R.id.text_ques);
        text_answer = (TextView) myView.findViewById(R.id.text_ans);
        buttoncheck = (Button) myView.findViewById(R.id.button_check);
        text_pp = (TextView) myView.findViewById(R.id.text_pp);//請選下一個格子(textview)
        text_core=(TextView)myView.findViewById(R.id.text_core2);//分數


        b1 = (Button) myView.findViewById(R.id.button101);//抓id
        b2 = (Button) myView.findViewById(R.id.button102);
        b3 = (Button) myView.findViewById(R.id.button103);
        b4 = (Button) myView.findViewById(R.id.button104);
        b5 = (Button) myView.findViewById(R.id.button105);
        b6 = (Button) myView.findViewById(R.id.button106);
        b7 = (Button) myView.findViewById(R.id.button107);
        b8 = (Button) myView.findViewById(R.id.button108);
        b9 = (Button) myView.findViewById(R.id.button109);
        b10 = (Button) myView.findViewById(R.id.button110);
        b11 = (Button) myView.findViewById(R.id.button111);
        b12 = (Button) myView.findViewById(R.id.button112);
        b13 = (Button) myView.findViewById(R.id.button113);
        b14 = (Button) myView.findViewById(R.id.button114);
        b15 = (Button) myView.findViewById(R.id.button115);
        b16 = (Button) myView.findViewById(R.id.button116);

        buttoncheck.setOnClickListener(lis_check);//check按鈕呼叫
        text_core.setText(Integer.toString(i));// 分數初始=i

        radiobtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendlog("點擊radiobutton:A");
                Log.i("點擊radiobutton","A");
            }
        });
        radiobtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendlog("點擊radiobutton:B");
                Log.i("點擊radiobutton","B");
            }
        });
        radiobtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendlog("點擊radiobutton:C");
                Log.i("點擊radiobutton","C");
            }
        });
        radiobtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendlog("點擊radiobutton:D");
                Log.i("點擊radiobutton","C");
            }
        });



        b1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("第1題","目前作答題目");
                appendlog("目前選擇題目:第1題");
                text_question.setText(exam_ques_ans2.ArrayQues1[0]);
                radiobtn1.setText(exam_ques_ans2.ArrayAns1[0]);//設定radiobutton的字串
                radiobtn2.setText(exam_ques_ans2.ArrayAns1[1]);
                radiobtn3.setText(exam_ques_ans2.ArrayAns1[2]);
                radiobtn4.setText(exam_ques_ans2.ArrayAns1[3]);

                radiobtn1.setChecked(false);//設定radiobutton的狀態
                radiobtn2.setChecked(false);
                radiobtn3.setChecked(false);
                radiobtn4.setChecked(false);

                radiobtn1.setEnabled(true);
                radiobtn2.setEnabled(true);
                radiobtn3.setEnabled(true);
                radiobtn4.setEnabled(true);
                text_pp.setText("");

                radiobtn1.setVisibility(View.VISIBLE);
                radiobtn2.setVisibility(View.VISIBLE);
                radiobtn3.setVisibility(View.VISIBLE);
                radiobtn4.setVisibility(View.VISIBLE);
                text_answer.setText("");//清除上一題答案畫面
                k=1;
                btn_index = 0;
                b1.setBackgroundResource(R.drawable.buttonshape_yellow2);

                btn_deletewords.setOnClickListener(new View.OnClickListener() {//刪除選項
                    @Override
                    public void onClick(View v) {//Ans D
                        Log.i("第1題","使用刪除提示");
                        switch (k) {
                            case 1: {
                                appendlog("第1題:按下刪除提示B(-20分)");
                                radiobtn2.setEnabled(false);
                                k+=1;
                                break;
                            }
                            case 2: {
                                appendlog("第1題:按下刪除提示C(-20分)");
                                radiobtn3.setEnabled(false);
                                k+=1;
                                break;
                            }
                            case 3: {
                                appendlog("第1題:按下刪除提示A(-20分)");
                                radiobtn1.setEnabled(false);
                                k+=1;
                                break;
                            }
                        }

                        text_core.setText(Integer.toString(i-=20));
                    }
                });
                btn_paragraph.setOnClickListener(new View.OnClickListener() {  //文章
                    @Override
                    public void onClick(View v) {
                        Log.i("第1題","使用文章段落提示");
                        appendlog("第1題:按下文章段落提示(-5分)");
                        LayoutInflater inflaterdialog = getActivity().getLayoutInflater();
                        View paragraph = inflaterdialog.inflate(R.layout.dialog_paragraph, null);
                        text_paragraph = (TextView) paragraph.findViewById(R.id.textView_paragraph);
                        text_paragraph.setText(Html.fromHtml(exam_ques_ans2.reading_summary1[2]));
                        text_paragraph.setMovementMethod(ScrollingMovementMethod.getInstance());
                        text_core.setText(Integer.toString(i-=5));

                        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
                        final AlertDialog.Builder dialog_paragraph = new AlertDialog.Builder(contextThemeWrapper);
                        dialog_paragraph.setTitle("文章段落")
                                .setView(paragraph)
                                .setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        appendlog("第1題:按下文章段落提示-關閉");
                                    }
                                })
                                .show();


                    }
                });


            }

        });

        b2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("第2題","目前作答題目");
                appendlog("目前選擇題目:第2題");
                text_question.setText(exam_ques_ans2.ArrayQues1[1]);
                radiobtn1.setText(exam_ques_ans2.ArrayAns1[4]);
                radiobtn2.setText(exam_ques_ans2.ArrayAns1[5]);
                radiobtn3.setText(exam_ques_ans2.ArrayAns1[6]);
                radiobtn4.setText(exam_ques_ans2.ArrayAns1[7]);
                text_answer.setText("");
                text_pp.setText("");

                radiobtn1.setChecked(false);//設定radiobutton的狀態
                radiobtn2.setChecked(false);
                radiobtn3.setChecked(false);
                radiobtn4.setChecked(false);

                radiobtn1.setEnabled(true);
                radiobtn2.setEnabled(true);
                radiobtn3.setEnabled(true);
                radiobtn4.setEnabled(true);

                radiobtn1.setVisibility(View.VISIBLE);
                radiobtn2.setVisibility(View.VISIBLE);
                radiobtn3.setVisibility(View.VISIBLE);
                radiobtn4.setVisibility(View.VISIBLE);
                b2.setBackgroundResource(R.drawable.buttonshape_yellow2);
                btn_index = 1;
                k=1;

                btn_deletewords.setOnClickListener(new View.OnClickListener() {//刪除選項
                    @Override
                    public void onClick(View v) {//Ans D
                        switch (k) {
                            case 1: {
                                appendlog("第2題:按下刪除提示A(-20分)");
                                radiobtn1.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 2: {
                                appendlog("第2題:按下刪除提示C(-20分)");
                                radiobtn3.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 3: {
                                appendlog("第2題:按下刪除提示B(-20分)");
                                radiobtn2.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                        }


                    }
                });

                btn_paragraph.setOnClickListener(new View.OnClickListener() {  //文章
                    @Override
                    public void onClick(View v) {
                        appendlog("第2題:按下文章段落提示(-5分)");
                        LayoutInflater inflaterdialog = getActivity().getLayoutInflater();
                        View paragraph = inflaterdialog.inflate(R.layout.dialog_paragraph, null);
                        text_paragraph = (TextView) paragraph.findViewById(R.id.textView_paragraph);
                        text_paragraph.setText(Html.fromHtml(exam_ques_ans2.reading_summary1[3]));
                        text_paragraph.setMovementMethod(ScrollingMovementMethod.getInstance());
                        text_paragraph.setMovementMethod(ScrollingMovementMethod.getInstance());
                        text_core.setText(Integer.toString(i-=5));
                        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
                        final AlertDialog.Builder dialog_paragraph = new AlertDialog.Builder(contextThemeWrapper);
                        dialog_paragraph.setTitle("文章段落")
                                .setView(paragraph)
                                .setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        appendlog("第2題:按下文章段落提示-關閉");
                                    }
                                })
                                .show();
                    }
                });



            }
        });
        b3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendlog("目前選擇題目:第3題");
                text_question.setText(exam_ques_ans2.ArrayQues1[2]);
                radiobtn1.setText(exam_ques_ans2.ArrayAns1[8]);
                radiobtn2.setText(exam_ques_ans2.ArrayAns1[9]);
                radiobtn3.setText(exam_ques_ans2.ArrayAns1[10]);
                radiobtn4.setText(exam_ques_ans2.ArrayAns1[11]);
                text_answer.setText("");
                text_pp.setText("");

                radiobtn1.setChecked(false);//設定radiobutton的狀態
                radiobtn2.setChecked(false);
                radiobtn3.setChecked(false);
                radiobtn4.setChecked(false);

                radiobtn1.setEnabled(true);
                radiobtn2.setEnabled(true);
                radiobtn3.setEnabled(true);
                radiobtn4.setEnabled(true);

                radiobtn1.setVisibility(View.VISIBLE);
                radiobtn2.setVisibility(View.VISIBLE);
                radiobtn3.setVisibility(View.VISIBLE);
                radiobtn4.setVisibility(View.VISIBLE);
                b3.setBackgroundResource(R.drawable.buttonshape_yellow2);
                btn_index = 2;
                k=1;

                btn_deletewords.setOnClickListener(new View.OnClickListener() {//刪除選項
                    @Override
                    public void onClick(View v) {//Ans C
                        switch (k) {
                            case 1: {
                                appendlog("第3題:按下刪除提示B(-20分)");
                                radiobtn2.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 2: {
                                appendlog("第3題:按下刪除提示A(-20分)");
                                radiobtn1.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 3: {
                                appendlog("第3題:按下刪除提示D(-20分)");
                                radiobtn4.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                        }


                    }
                });
                btn_paragraph.setOnClickListener(new View.OnClickListener() {  //文章
                    @Override
                    public void onClick(View v) {
                        appendlog("第3題:按下文章段落提示(-5分)");
                        LayoutInflater inflaterdialog = getActivity().getLayoutInflater();
                        View paragraph = inflaterdialog.inflate(R.layout.dialog_paragraph, null);
                        text_paragraph = (TextView) paragraph.findViewById(R.id.textView_paragraph);
                        text_paragraph.setText(Html.fromHtml(exam_ques_ans2.reading_summary1[4]));
                        text_paragraph.setMovementMethod(ScrollingMovementMethod.getInstance());
                        text_core.setText(Integer.toString(i-=5));

                        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
                        final AlertDialog.Builder dialog_paragraph = new AlertDialog.Builder(contextThemeWrapper);
                        dialog_paragraph.setTitle("文章段落")
                                .setView(paragraph)
                                .setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        appendlog("第3題:按下文章段落提示-關閉");
                                    }
                                })
                                .show();
                    }
                });


            }
        });
        b4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendlog("目前選擇題目:第4題");
                text_question.setText(exam_ques_ans2.ArrayQues1[3]);
                radiobtn1.setText(exam_ques_ans2.ArrayAns1[12]);
                radiobtn2.setText(exam_ques_ans2.ArrayAns1[13]);
                radiobtn3.setText(exam_ques_ans2.ArrayAns1[14]);
                radiobtn4.setText(exam_ques_ans2.ArrayAns1[15]);
                text_answer.setText("");

                radiobtn1.setChecked(false);//設定radiobutton的狀態
                radiobtn2.setChecked(false);
                radiobtn3.setChecked(false);
                radiobtn4.setChecked(false);

                radiobtn1.setEnabled(true);
                radiobtn2.setEnabled(true);
                radiobtn3.setEnabled(true);
                radiobtn4.setEnabled(true);
                text_pp.setText("");

                radiobtn1.setVisibility(View.VISIBLE);
                radiobtn2.setVisibility(View.VISIBLE);
                radiobtn3.setVisibility(View.VISIBLE);
                radiobtn4.setVisibility(View.VISIBLE);
                b4.setBackgroundResource(R.drawable.buttonshape_yellow2);
                btn_index = 3;
                k=1;

                btn_deletewords.setOnClickListener(new View.OnClickListener() {//刪除選項
                    @Override
                    public void onClick(View v) {//Ans B
                        switch (k) {
                            case 1: {
                                appendlog("第4題:按下刪除提示C(-20分)");
                                radiobtn3.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 2: {
                                appendlog("第4題:按下刪除提示A(-20分)");
                                radiobtn1.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 3: {
                                appendlog("第4題:按下刪除提示D(-20分)");
                                radiobtn4.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                        }


                    }
                });
                btn_paragraph.setOnClickListener(new View.OnClickListener() {  //文章
                    @Override
                    public void onClick(View v) {
                        appendlog("第4題:按下文章段落提示(-5分)");
                        LayoutInflater inflaterdialog = getActivity().getLayoutInflater();
                        View paragraph = inflaterdialog.inflate(R.layout.dialog_paragraph, null);
                        text_paragraph = (TextView) paragraph.findViewById(R.id.textView_paragraph);
                        text_paragraph.setText(Html.fromHtml(exam_ques_ans2.reading_summary1[5]));
                        text_paragraph.setMovementMethod(ScrollingMovementMethod.getInstance());
                        text_core.setText(Integer.toString(i-=5));

                        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
                        final AlertDialog.Builder dialog_paragraph = new AlertDialog.Builder(contextThemeWrapper);
                        dialog_paragraph.setTitle("文章段落")
                                .setView(paragraph)
                                .setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        appendlog("第4題:按下文章段落提示-關閉");
                                    }
                                })
                                .show();
                    }
                });


            }
        });
        b5.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendlog("目前選擇題目:第5題");
                text_question.setText(exam_ques_ans2.ArrayQues1[4]);
                radiobtn1.setText(exam_ques_ans2.ArrayAns1[16]);
                radiobtn2.setText(exam_ques_ans2.ArrayAns1[17]);
                radiobtn3.setText(exam_ques_ans2.ArrayAns1[18]);
                radiobtn4.setText(exam_ques_ans2.ArrayAns1[19]);
                text_answer.setText("");

                radiobtn1.setChecked(false);//設定radiobutton的狀態
                radiobtn2.setChecked(false);
                radiobtn3.setChecked(false);
                radiobtn4.setChecked(false);

                radiobtn1.setEnabled(true);
                radiobtn2.setEnabled(true);
                radiobtn3.setEnabled(true);
                radiobtn4.setEnabled(true);
                text_pp.setText("");

                radiobtn1.setVisibility(View.VISIBLE);
                radiobtn2.setVisibility(View.VISIBLE);
                radiobtn3.setVisibility(View.VISIBLE);
                radiobtn4.setVisibility(View.VISIBLE);
                b5.setBackgroundResource(R.drawable.buttonshape_yellow2);
                btn_index = 4;
                k=1;

                btn_deletewords.setOnClickListener(new View.OnClickListener() {//刪除選項
                    @Override
                    public void onClick(View v) {//Ans A
                        switch (k) {
                            case 1: {
                                appendlog("第5題:按下刪除提示B(-20分)");
                                radiobtn2.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 2: {
                                appendlog("第5題:按下刪除提示C(-20分)");
                                radiobtn3.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 3: {
                                appendlog("第5題:按下刪除提示D(-20分)");
                                radiobtn4.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                        }



                    }
                });

                btn_paragraph.setOnClickListener(new View.OnClickListener() {  //文章
                    @Override
                    public void onClick(View v) {
                        appendlog("第5題:按下文章段落提示(-5分)");
                        LayoutInflater inflaterdialog = getActivity().getLayoutInflater();
                        View paragraph = inflaterdialog.inflate(R.layout.dialog_paragraph, null);
                        text_paragraph = (TextView) paragraph.findViewById(R.id.textView_paragraph);
                        text_paragraph.setText(Html.fromHtml(exam_ques_ans2.reading_summary1[6]));
                        text_paragraph.setMovementMethod(ScrollingMovementMethod.getInstance());
                        text_core.setText(Integer.toString(i-=5));

                        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
                        final AlertDialog.Builder dialog_paragraph = new AlertDialog.Builder(contextThemeWrapper);
                        dialog_paragraph.setTitle("文章段落")
                                .setView(paragraph)
                                .setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        appendlog("第5題:按下文章段落提示-關閉");
                                    }
                                })
                                .show();
                    }
                });


            }
        });
        b6.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendlog("目前選擇題目:第6題");
                text_question.setText(exam_ques_ans2.ArrayQues1[5]);
                radiobtn1.setText(exam_ques_ans2.ArrayAns1[20]);
                radiobtn2.setText(exam_ques_ans2.ArrayAns1[21]);
                radiobtn3.setText(exam_ques_ans2.ArrayAns1[22]);
                radiobtn4.setText(exam_ques_ans2.ArrayAns1[23]);
                text_answer.setText("");

                radiobtn1.setChecked(false);//設定radiobutton的狀態
                radiobtn2.setChecked(false);
                radiobtn3.setChecked(false);
                radiobtn4.setChecked(false);

                radiobtn1.setEnabled(true);
                radiobtn2.setEnabled(true);
                radiobtn3.setEnabled(true);
                radiobtn4.setEnabled(true);
                text_pp.setText("");

                radiobtn1.setVisibility(View.VISIBLE);
                radiobtn2.setVisibility(View.VISIBLE);
                radiobtn3.setVisibility(View.VISIBLE);
                radiobtn4.setVisibility(View.VISIBLE);
                b6.setBackgroundResource(R.drawable.buttonshape_yellow2);
                btn_index = 5;
                k=1;

                btn_deletewords.setOnClickListener(new View.OnClickListener() {//刪除選項
                    @Override
                    public void onClick(View v) {//Ans C
                        switch (k) {
                            case 1: {
                                appendlog("第6題:按下刪除提示D(-20分)");
                                radiobtn4.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 2: {
                                appendlog("第6題:按下刪除提示B(-20分)");
                                radiobtn2.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 3: {
                                appendlog("第6題:按下刪除提示A(-20分)");
                                radiobtn1.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                        }

                    }
                });
                btn_paragraph.setOnClickListener(new View.OnClickListener() {  //文章
                    @Override
                    public void onClick(View v) {
                        appendlog("第6題:按下文章段落提示(-5分)");
                        LayoutInflater inflaterdialog = getActivity().getLayoutInflater();
                        View paragraph = inflaterdialog.inflate(R.layout.dialog_paragraph, null);
                        text_paragraph = (TextView) paragraph.findViewById(R.id.textView_paragraph);
                        text_paragraph.setText(Html.fromHtml(exam_ques_ans2.reading_summary1[7]));
                        text_paragraph.setMovementMethod(ScrollingMovementMethod.getInstance());
                        text_core.setText(Integer.toString(i-=5));

                        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
                        final AlertDialog.Builder dialog_paragraph = new AlertDialog.Builder(contextThemeWrapper);
                        dialog_paragraph.setTitle("文章段落")
                                .setView(paragraph)
                                .setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        appendlog("第6題:按下文章段落提示-關閉");
                                    }
                                })
                                .show();
                    }
                });


            }
        });
        b7.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendlog("目前選擇題目:第7題");
                text_question.setText(exam_ques_ans2.ArrayQues1[6]);
                radiobtn1.setText(exam_ques_ans2.ArrayAns1[24]);
                radiobtn2.setText(exam_ques_ans2.ArrayAns1[25]);
                radiobtn3.setText(exam_ques_ans2.ArrayAns1[26]);
                radiobtn4.setText(exam_ques_ans2.ArrayAns1[27]);
                text_answer.setText("");

                radiobtn1.setChecked(false);//設定radiobutton的狀態
                radiobtn2.setChecked(false);
                radiobtn3.setChecked(false);
                radiobtn4.setChecked(false);

                radiobtn1.setEnabled(true);
                radiobtn2.setEnabled(true);
                radiobtn3.setEnabled(true);
                radiobtn4.setEnabled(true);
                text_pp.setText("");

                radiobtn1.setVisibility(View.VISIBLE);
                radiobtn2.setVisibility(View.VISIBLE);
                radiobtn3.setVisibility(View.VISIBLE);
                radiobtn4.setVisibility(View.VISIBLE);
                b7.setBackgroundResource(R.drawable.buttonshape_yellow2);
                btn_index = 6;
                k=1;

                btn_deletewords.setOnClickListener(new View.OnClickListener() {//刪除選項
                    @Override
                    public void onClick(View v) {//Ans A
                        switch (k) {
                            case 1: {
                                appendlog("第7題:按下刪除提示C(-20分)");
                                radiobtn3.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 2: {
                                appendlog("第7題:按下刪除提示B(-20分)");
                                radiobtn2.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 3: {
                                appendlog("第7題:按下刪除提示D(-20分)");
                                radiobtn4.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                        }



                    }
                });
                btn_paragraph.setOnClickListener(new View.OnClickListener() {  //文章
                    @Override
                    public void onClick(View v) {
                        appendlog("第7題:按下文章段落提示(-5分)");
                        LayoutInflater inflaterdialog = getActivity().getLayoutInflater();
                        View paragraph = inflaterdialog.inflate(R.layout.dialog_paragraph, null);
                        text_paragraph = (TextView) paragraph.findViewById(R.id.textView_paragraph);
                        text_paragraph.setText(Html.fromHtml(exam_ques_ans2.reading_summary1[8]));
                        text_paragraph.setMovementMethod(ScrollingMovementMethod.getInstance());
                        text_core.setText(Integer.toString(i-=5));

                        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
                        final AlertDialog.Builder dialog_paragraph = new AlertDialog.Builder(contextThemeWrapper);
                        dialog_paragraph.setTitle("文章段落")
                                .setView(paragraph)
                                .setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        appendlog("第7題:按下文章段落提示-關閉");
                                    }
                                })
                                .show();
                    }
                });


            }
        });
        b8.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendlog("目前選擇題目:第8題");
                text_question.setText(exam_ques_ans2.ArrayQues1[7]);
                radiobtn1.setText(exam_ques_ans2.ArrayAns1[28]);
                radiobtn2.setText(exam_ques_ans2.ArrayAns1[29]);
                radiobtn3.setText(exam_ques_ans2.ArrayAns1[30]);
                radiobtn4.setText(exam_ques_ans2.ArrayAns1[31]);
                text_answer.setText("");

                radiobtn1.setChecked(false);//設定radiobutton的狀態
                radiobtn2.setChecked(false);
                radiobtn3.setChecked(false);
                radiobtn4.setChecked(false);

                radiobtn1.setEnabled(true);
                radiobtn2.setEnabled(true);
                radiobtn3.setEnabled(true);
                radiobtn4.setEnabled(true);
                text_pp.setText("");

                radiobtn1.setVisibility(View.VISIBLE);
                radiobtn2.setVisibility(View.VISIBLE);
                radiobtn3.setVisibility(View.VISIBLE);
                radiobtn4.setVisibility(View.VISIBLE);
                b8.setBackgroundResource(R.drawable.buttonshape_yellow2);
                btn_index = 7;
                k=1;

                btn_deletewords.setOnClickListener(new View.OnClickListener() {//刪除選項
                    @Override
                    public void onClick(View v) {//Ans D
                        switch (k) {
                            case 1: {
                                appendlog("第8題:按下刪除提示B(-20分)");
                                radiobtn2.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 2: {
                                appendlog("第8題:按下刪除提示C(-20分)");
                                radiobtn3.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 3: {
                                appendlog("第8題:按下刪除提示A(-20分)");
                                radiobtn1.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                        }



                    }
                });
                btn_paragraph.setOnClickListener(new View.OnClickListener() {  //文章
                    @Override
                    public void onClick(View v) {
                        appendlog("第8題:按下文章段落提示(-5分)");
                        LayoutInflater inflaterdialog = getActivity().getLayoutInflater();
                        View paragraph = inflaterdialog.inflate(R.layout.dialog_paragraph, null);
                        text_paragraph = (TextView) paragraph.findViewById(R.id.textView_paragraph);
                        text_paragraph.setText(Html.fromHtml(exam_ques_ans2.reading_summary1[9]));
                        text_paragraph.setMovementMethod(ScrollingMovementMethod.getInstance());
                        text_core.setText(Integer.toString(i-=5));

                        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
                        final AlertDialog.Builder dialog_paragraph = new AlertDialog.Builder(contextThemeWrapper);
                        dialog_paragraph.setTitle("文章段落")
                                .setView(paragraph)
                                .setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        appendlog("第8題:按下文章段落提示-關閉");
                                    }
                                })
                                .show();
                    }
                });


            }
        });
        b9.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendlog("目前選擇題目:第9題");
                text_question.setText(exam_ques_ans2.ArrayQues1[8]);
                radiobtn1.setText(exam_ques_ans2.ArrayAns1[32]);
                radiobtn2.setText(exam_ques_ans2.ArrayAns1[33]);
                radiobtn3.setText(exam_ques_ans2.ArrayAns1[34]);
                radiobtn4.setText(exam_ques_ans2.ArrayAns1[35]);
                text_answer.setText("");

                radiobtn1.setChecked(false);//設定radiobutton的狀態
                radiobtn2.setChecked(false);
                radiobtn3.setChecked(false);
                radiobtn4.setChecked(false);

                radiobtn1.setEnabled(true);
                radiobtn2.setEnabled(true);
                radiobtn3.setEnabled(true);
                radiobtn4.setEnabled(true);
                text_pp.setText("");

                radiobtn1.setVisibility(View.VISIBLE);
                radiobtn2.setVisibility(View.VISIBLE);
                radiobtn3.setVisibility(View.VISIBLE);
                radiobtn4.setVisibility(View.VISIBLE);
                b9.setBackgroundResource(R.drawable.buttonshape_yellow2);
                btn_index = 8;
                k=1;

                btn_deletewords.setOnClickListener(new View.OnClickListener() {//刪除選項
                    @Override
                    public void onClick(View v) {//Ans C
                        switch (k) {
                            case 1: {
                                appendlog("第9題:按下刪除提示B(-20分)");
                                radiobtn2.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 2: {
                                appendlog("第9題:按下刪除提示A(-20分)");
                                radiobtn1.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 3: {
                                appendlog("第9題:按下刪除提示D(-20分)");
                                radiobtn4.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                        }


                    }
                });
                btn_paragraph.setOnClickListener(new View.OnClickListener() {  //文章
                    @Override
                    public void onClick(View v) {
                        appendlog("第9題:按下文章段落提示(-5分)");
                        LayoutInflater inflaterdialog = getActivity().getLayoutInflater();
                        View paragraph = inflaterdialog.inflate(R.layout.dialog_paragraph, null);
                        text_paragraph = (TextView) paragraph.findViewById(R.id.textView_paragraph);
                        text_paragraph.setText(Html.fromHtml(exam_ques_ans2.reading_summary1[10]));
                        text_paragraph.setMovementMethod(ScrollingMovementMethod.getInstance());
                        text_core.setText(Integer.toString(i-=5));

                        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
                        final AlertDialog.Builder dialog_paragraph = new AlertDialog.Builder(contextThemeWrapper);
                        dialog_paragraph.setTitle("文章段落")
                                .setView(paragraph)
                                .setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        appendlog("第9題:按下文章段落提示-關閉");
                                    }
                                })
                                .show();
                    }
                });


            }
        });
        b10.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendlog("目前選擇題目:第10題");
                text_question.setText(exam_ques_ans2.ArrayQues1[9]);
                radiobtn1.setText(exam_ques_ans2.ArrayAns1[36]);
                radiobtn2.setText(exam_ques_ans2.ArrayAns1[37]);
                radiobtn3.setText(exam_ques_ans2.ArrayAns1[38]);
                radiobtn4.setText(exam_ques_ans2.ArrayAns1[39]);
                text_answer.setText("");

                radiobtn1.setChecked(false);//設定radiobutton的狀態
                radiobtn2.setChecked(false);
                radiobtn3.setChecked(false);
                radiobtn4.setChecked(false);

                radiobtn1.setEnabled(true);
                radiobtn2.setEnabled(true);
                radiobtn3.setEnabled(true);
                radiobtn4.setEnabled(true);
                text_pp.setText("");

                radiobtn1.setVisibility(View.VISIBLE);
                radiobtn2.setVisibility(View.VISIBLE);
                radiobtn3.setVisibility(View.VISIBLE);
                radiobtn4.setVisibility(View.VISIBLE);
                b10.setBackgroundResource(R.drawable.buttonshape_yellow2);
                btn_index = 9;
                k=1;

                btn_deletewords.setOnClickListener(new View.OnClickListener() {//刪除選項
                    @Override
                    public void onClick(View v) {//Ans B
                        switch (k) {
                            case 1: {
                                appendlog("第10題:按下刪除提示A(-20分)");
                                radiobtn1.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 2: {
                                appendlog("第10題:按下刪除提示C(-20分)");
                                radiobtn3.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 3: {
                                appendlog("第10題:按下刪除提示D(-20分)");
                                radiobtn4.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                        }


                    }
                });
                btn_paragraph.setOnClickListener(new View.OnClickListener() {  //文章
                    @Override
                    public void onClick(View v) {
                        appendlog("第10題:按下文章段落提示(-5分)");
                        LayoutInflater inflaterdialog = getActivity().getLayoutInflater();
                        View paragraph = inflaterdialog.inflate(R.layout.dialog_paragraph, null);
                        text_paragraph = (TextView) paragraph.findViewById(R.id.textView_paragraph);
                        text_paragraph.setText(Html.fromHtml(exam_ques_ans2.reading_summary1[11]));
                        text_paragraph.setMovementMethod(ScrollingMovementMethod.getInstance());
                        text_core.setText(Integer.toString(i-=5));

                        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
                        final AlertDialog.Builder dialog_paragraph = new AlertDialog.Builder(contextThemeWrapper);
                        dialog_paragraph.setTitle("文章段落")
                                .setView(paragraph)
                                .setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        appendlog("第10題:按下文章段落提示-關閉");
                                    }
                                })
                                .show();
                    }
                });


            }
        });
        b11.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendlog("目前選擇題目:第11題");
                text_question.setText(exam_ques_ans2.ArrayQues1[10]);
                radiobtn1.setText(exam_ques_ans2.ArrayAns1[40]);
                radiobtn2.setText(exam_ques_ans2.ArrayAns1[41]);
                radiobtn3.setText(exam_ques_ans2.ArrayAns1[42]);
                radiobtn4.setText(exam_ques_ans2.ArrayAns1[43]);
                text_answer.setText("");
                text_pp.setText("");

                radiobtn1.setChecked(false);//設定radiobutton的狀態
                radiobtn2.setChecked(false);
                radiobtn3.setChecked(false);
                radiobtn4.setChecked(false);

                radiobtn1.setEnabled(true);
                radiobtn2.setEnabled(true);
                radiobtn3.setEnabled(true);
                radiobtn4.setEnabled(true);

                radiobtn1.setVisibility(View.VISIBLE);
                radiobtn2.setVisibility(View.VISIBLE);
                radiobtn3.setVisibility(View.VISIBLE);
                radiobtn4.setVisibility(View.VISIBLE);
                b11.setBackgroundResource(R.drawable.buttonshape_yellow2);
                btn_index = 10;
                k=1;

                btn_deletewords.setOnClickListener(new View.OnClickListener() {//刪除選項
                    @Override
                    public void onClick(View v) {//Ans A
                        switch (k) {
                            case 1: {
                                appendlog("第11題:按下刪除提示B(-20分)");
                                radiobtn2.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 2: {
                                appendlog("第11題:按下刪除提示D(-20分)");
                                radiobtn4.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 3: {
                                appendlog("第11題:按下刪除提示C(-20分)");
                                radiobtn3.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                        }


                    }
                });
                btn_paragraph.setOnClickListener(new View.OnClickListener() {  //文章
                    @Override
                    public void onClick(View v) {
                        appendlog("第11題:按下文章段落提示(-5分)");
                        LayoutInflater inflaterdialog = getActivity().getLayoutInflater();
                        View paragraph = inflaterdialog.inflate(R.layout.dialog_paragraph, null);
                        text_paragraph = (TextView) paragraph.findViewById(R.id.textView_paragraph);
                        text_paragraph.setText(Html.fromHtml(exam_ques_ans2.reading_summary1[12]));
                        text_paragraph.setMovementMethod(ScrollingMovementMethod.getInstance());
                        text_core.setText(Integer.toString(i-=5));

                        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
                        final AlertDialog.Builder dialog_paragraph = new AlertDialog.Builder(contextThemeWrapper);
                        dialog_paragraph.setTitle("文章段落")
                                .setView(paragraph)
                                .setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        appendlog("第11題:按下文章段落提示-關閉");
                                    }
                                })
                                .show();
                    }
                });


            }
        });
        b12.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendlog("目前選擇題目:第12題");
                text_question.setText(exam_ques_ans2.ArrayQues1[11]);
                radiobtn1.setText(exam_ques_ans2.ArrayAns1[44]);
                radiobtn2.setText(exam_ques_ans2.ArrayAns1[45]);
                radiobtn3.setText(exam_ques_ans2.ArrayAns1[46]);
                radiobtn4.setText(exam_ques_ans2.ArrayAns1[47]);
                text_answer.setText("");

                radiobtn1.setChecked(false);//設定radiobutton的狀態
                radiobtn2.setChecked(false);
                radiobtn3.setChecked(false);
                radiobtn4.setChecked(false);

                radiobtn1.setEnabled(true);
                radiobtn2.setEnabled(true);
                radiobtn3.setEnabled(true);
                radiobtn4.setEnabled(true);
                text_pp.setText("");

                radiobtn1.setVisibility(View.VISIBLE);
                radiobtn2.setVisibility(View.VISIBLE);
                radiobtn3.setVisibility(View.VISIBLE);
                radiobtn4.setVisibility(View.VISIBLE);
                b12.setBackgroundResource(R.drawable.buttonshape_yellow2);
                btn_index = 11;
                k=1;

                btn_deletewords.setOnClickListener(new View.OnClickListener() {//刪除選項
                    @Override
                    public void onClick(View v) {//Ans D
                        switch (k) {
                            case 1: {
                                appendlog("第12題:按下刪除提示B(-20分)");
                                radiobtn2.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 2: {
                                appendlog("第12題:按下刪除提示A(-20分)");
                                radiobtn1.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 3: {
                                appendlog("第12題:按下刪除提示C(-20分)");
                                radiobtn3.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                        }



                    }
                });
                btn_paragraph.setOnClickListener(new View.OnClickListener() {  //文章
                    @Override
                    public void onClick(View v) {
                        appendlog("第12題:按下文章段落提示(-5分)");
                        LayoutInflater inflaterdialog = getActivity().getLayoutInflater();
                        View paragraph = inflaterdialog.inflate(R.layout.dialog_paragraph, null);
                        text_paragraph = (TextView) paragraph.findViewById(R.id.textView_paragraph);
                        text_paragraph.setText(Html.fromHtml(exam_ques_ans2.reading_summary1[13]));
                        text_paragraph.setMovementMethod(ScrollingMovementMethod.getInstance());
                        text_core.setText(Integer.toString(i-=5));

                        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
                        final AlertDialog.Builder dialog_paragraph = new AlertDialog.Builder(contextThemeWrapper);
                        dialog_paragraph.setTitle("文章段落")
                                .setView(paragraph)
                                .setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        appendlog("第12題:按下文章段落提示-關閉");
                                    }
                                })
                                .show();
                    }
                });

            }
        });
        b13.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendlog("目前選擇題目:第13題");
                text_question.setText(exam_ques_ans2.ArrayQues1[12]);
                radiobtn1.setText(exam_ques_ans2.ArrayAns1[48]);
                radiobtn2.setText(exam_ques_ans2.ArrayAns1[49]);
                radiobtn3.setText(exam_ques_ans2.ArrayAns1[50]);
                radiobtn4.setText(exam_ques_ans2.ArrayAns1[51]);
                text_answer.setText("");

                radiobtn1.setChecked(false);//設定radiobutton的狀態
                radiobtn2.setChecked(false);
                radiobtn3.setChecked(false);
                radiobtn4.setChecked(false);

                radiobtn1.setEnabled(true);
                radiobtn2.setEnabled(true);
                radiobtn3.setEnabled(true);
                radiobtn4.setEnabled(true);
                text_pp.setText("");

                radiobtn1.setVisibility(View.VISIBLE);
                radiobtn2.setVisibility(View.VISIBLE);
                radiobtn3.setVisibility(View.VISIBLE);
                radiobtn4.setVisibility(View.VISIBLE);
                b13.setBackgroundResource(R.drawable.buttonshape_yellow2);
                btn_index = 12;
                k=1;


                btn_deletewords.setOnClickListener(new View.OnClickListener() {//刪除選項
                    @Override
                    public void onClick(View v) {//Ans B
                        switch (k) {
                            case 1: {
                                appendlog("第13題:按下刪除提示A(-20分)");
                                radiobtn1.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 2: {
                                appendlog("第13題:按下刪除提示C(-20分)");
                                radiobtn3.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 3: {
                                appendlog("第13題:按下刪除提示D(-20分)");
                                radiobtn4.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                        }




                    }
                });
                btn_paragraph.setOnClickListener(new View.OnClickListener() {  //文章
                    @Override
                    public void onClick(View v) {
                        appendlog("第13題:按下文章段落提示(-5分)");
                        LayoutInflater inflaterdialog = getActivity().getLayoutInflater();
                        View paragraph = inflaterdialog.inflate(R.layout.dialog_paragraph, null);
                        text_paragraph = (TextView) paragraph.findViewById(R.id.textView_paragraph);
                        text_paragraph.setText(Html.fromHtml(exam_ques_ans2.reading_summary1[14]));
                        text_paragraph.setMovementMethod(ScrollingMovementMethod.getInstance());
                        text_core.setText(Integer.toString(i-=5));

                        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
                        final AlertDialog.Builder dialog_paragraph = new AlertDialog.Builder(contextThemeWrapper);
                        dialog_paragraph.setTitle("文章段落")
                                .setView(paragraph)
                                .setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        appendlog("第13題:按下文章段落提示-關閉");
                                    }
                                })
                                .show();
                    }
                });


            }
        });
        b14.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendlog("目前選擇題目:第14題");
                text_question.setText(exam_ques_ans2.ArrayQues1[13]);
                radiobtn1.setText(exam_ques_ans2.ArrayAns1[52]);
                radiobtn2.setText(exam_ques_ans2.ArrayAns1[53]);
                radiobtn3.setText(exam_ques_ans2.ArrayAns1[54]);
                radiobtn4.setText(exam_ques_ans2.ArrayAns1[55]);
                text_answer.setText("");

                radiobtn1.setChecked(false);//設定radiobutton的狀態
                radiobtn2.setChecked(false);
                radiobtn3.setChecked(false);
                radiobtn4.setChecked(false);

                radiobtn1.setEnabled(true);
                radiobtn2.setEnabled(true);
                radiobtn3.setEnabled(true);
                radiobtn4.setEnabled(true);
                text_pp.setText("");

                radiobtn1.setVisibility(View.VISIBLE);
                radiobtn2.setVisibility(View.VISIBLE);
                radiobtn3.setVisibility(View.VISIBLE);
                radiobtn4.setVisibility(View.VISIBLE);
                b14.setBackgroundResource(R.drawable.buttonshape_yellow2);
                btn_index = 13;
                k=1;

                btn_deletewords.setOnClickListener(new View.OnClickListener() {//刪除選項
                    @Override
                    public void onClick(View v) {//Ans D
                        switch (k) {
                            case 1: {
                                appendlog("第14題:按下刪除提示B(-20分)");
                                radiobtn2.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 2: { appendlog("第14題:按下刪除提示C(-20分)");
                                radiobtn3.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 3: { appendlog("第14題:按下刪除提示A(-20分)");
                                radiobtn1.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                        }


                    }
                });
                btn_paragraph.setOnClickListener(new View.OnClickListener() {  //文章
                    @Override
                    public void onClick(View v) {
                        appendlog("第14題:按下文章段落提示(-5分)");
                        LayoutInflater inflaterdialog = getActivity().getLayoutInflater();
                        View paragraph = inflaterdialog.inflate(R.layout.dialog_paragraph, null);
                        text_paragraph = (TextView) paragraph.findViewById(R.id.textView_paragraph);
                        text_paragraph.setText(Html.fromHtml(exam_ques_ans2.reading_summary1[15]));
                        text_paragraph.setMovementMethod(ScrollingMovementMethod.getInstance());
                        text_core.setText(Integer.toString(i-=5));

                        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
                        final AlertDialog.Builder dialog_paragraph = new AlertDialog.Builder(contextThemeWrapper);
                        dialog_paragraph.setTitle("文章段落")
                                .setView(paragraph)
                                .setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        appendlog("第14題:按下文章段落提示-關閉");
                                    }
                                })
                                .show();
                    }
                });


            }
        });
        b15.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendlog("目前選擇題目:第15題");
                text_question.setText(exam_ques_ans2.ArrayQues1[14]);
                radiobtn1.setText(exam_ques_ans2.ArrayAns1[56]);//設定radiobutton的字串
                radiobtn2.setText(exam_ques_ans2.ArrayAns1[57]);
                radiobtn3.setText(exam_ques_ans2.ArrayAns1[58]);
                radiobtn4.setText(exam_ques_ans2.ArrayAns1[59]);
                text_answer.setText("");

                radiobtn1.setChecked(false);//設定radiobutton的狀態
                radiobtn2.setChecked(false);
                radiobtn3.setChecked(false);
                radiobtn4.setChecked(false);

                radiobtn1.setEnabled(true);
                radiobtn2.setEnabled(true);
                radiobtn3.setEnabled(true);
                radiobtn4.setEnabled(true);
                text_pp.setText("");

                radiobtn1.setVisibility(View.VISIBLE);
                radiobtn2.setVisibility(View.VISIBLE);
                radiobtn3.setVisibility(View.VISIBLE);
                radiobtn4.setVisibility(View.VISIBLE);
                b15.setBackgroundResource(R.drawable.buttonshape_yellow2);
                btn_index = 14;
                k=1;

                btn_deletewords.setOnClickListener(new View.OnClickListener() {//刪除選項
                    @Override
                    public void onClick(View v) {//Ans D
                        switch (k) {
                            case 1: {
                                appendlog("第15題:按下刪除提示C(-20分)");
                                radiobtn3.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 2: {
                                appendlog("第15題:按下刪除提示A(-20分)");
                                radiobtn1.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 3: {
                                appendlog("第15題:按下刪除提示B(-20分)");
                                radiobtn2.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                        }


                    }
                });
                btn_paragraph.setOnClickListener(new View.OnClickListener() {  //文章
                    @Override
                    public void onClick(View v) {
                        appendlog("第15題:按下文章段落提示(-5分)");
                        LayoutInflater inflaterdialog = getActivity().getLayoutInflater();
                        View paragraph = inflaterdialog.inflate(R.layout.dialog_paragraph, null);
                        text_paragraph = (TextView) paragraph.findViewById(R.id.textView_paragraph);
                        text_paragraph.setText(Html.fromHtml(exam_ques_ans2.reading_summary1[16]));
                        text_paragraph.setMovementMethod(ScrollingMovementMethod.getInstance());
                        text_core.setText(Integer.toString(i-=5));

                        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
                        final AlertDialog.Builder dialog_paragraph = new AlertDialog.Builder(contextThemeWrapper);
                        dialog_paragraph.setTitle("文章段落")
                                .setView(paragraph)
                                .setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        appendlog("第15題:按下文章段落提示-關閉");
                                    }
                                })
                                .show();
                    }
                });


            }
        });
        b16.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendlog("目前選擇題目:第16題");
                text_question.setText(exam_ques_ans2.ArrayQues1[15]);
                radiobtn1.setText(exam_ques_ans2.ArrayAns1[60]);
                radiobtn2.setText(exam_ques_ans2.ArrayAns1[61]);
                radiobtn3.setText(exam_ques_ans2.ArrayAns1[62]);
                radiobtn4.setText(exam_ques_ans2.ArrayAns1[63]);
                text_answer.setText("");

                radiobtn1.setChecked(false);//設定radiobutton的狀態
                radiobtn2.setChecked(false);
                radiobtn3.setChecked(false);
                radiobtn4.setChecked(false);

                radiobtn1.setEnabled(true);
                radiobtn2.setEnabled(true);
                radiobtn3.setEnabled(true);
                radiobtn4.setEnabled(true);
                text_pp.setText("");

                radiobtn1.setVisibility(View.VISIBLE);
                radiobtn2.setVisibility(View.VISIBLE);
                radiobtn3.setVisibility(View.VISIBLE);
                radiobtn4.setVisibility(View.VISIBLE);
                b16.setBackgroundResource(R.drawable.buttonshape_yellow2);
                btn_index = 15;
                k=1;

                btn_deletewords.setOnClickListener(new View.OnClickListener() {//刪除選項
                    @Override
                    public void onClick(View v) {//Ans C
                        switch (k) {
                            case 1: {
                                appendlog("第16題:按下刪除提示B(-20分)");
                                radiobtn2.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 2: {
                                appendlog("第16題:按下刪除提示A(-20分)");
                                radiobtn1.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                            case 3: {
                                appendlog("第16題:按下刪除提示D(-20分)");
                                radiobtn4.setEnabled(false);
                                k+=1;
                                text_core.setText(Integer.toString(i-=20));
                                break;
                            }
                        }



                    }
                });
                btn_paragraph.setOnClickListener(new View.OnClickListener() {  //文章
                    @Override
                    public void onClick(View v) {
                        appendlog("第16題:按下文章段落提示(-5分)");
                        LayoutInflater inflaterdialog = getActivity().getLayoutInflater();
                        View paragraph = inflaterdialog.inflate(R.layout.dialog_paragraph, null);
                        text_paragraph = (TextView) paragraph.findViewById(R.id.textView_paragraph);
                        text_paragraph.setText(Html.fromHtml(exam_ques_ans2.reading_summary1[17]));
                        text_paragraph.setMovementMethod(ScrollingMovementMethod.getInstance());
                        text_core.setText(Integer.toString(i-=5));

                        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
                        final AlertDialog.Builder dialog_paragraph = new AlertDialog.Builder(contextThemeWrapper);
                        dialog_paragraph.setTitle("文章段落")
                                .setView(paragraph)
                                .setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        appendlog("第16題:按下文章段落提示-關閉");
                                    }
                                })
                                .show();
                    }
                });


            }
        });
        btn_deletewords = (Button) myView.findViewById(R.id.hint_deletewords);
        btn_paragraph = (Button) myView.findViewById(R.id.hint_paragraph);
        btn_help = (Button) myView.findViewById(R.id.hint_help);

        list_help = new ArrayList<>();
        list_help.add("identical vs different");
        list_help.add("so as to");
        list_help.add("widespread vs rare");
        list_help.add("正反關係(However)");
        list_help.add("因果關係(Due to)");

        btn_help.setOnClickListener(new View.OnClickListener() {//輔助功能dialog
            @Override
            public void onClick(View v) {
                appendlog("按下按鈕:輔助功能");//寫入
                text_core.setText(Integer.toString(i-=5));
                ContextThemeWrapper contextThemeWrapper =new ContextThemeWrapper(getActivity(), R.style.dialog);
                final AlertDialog.Builder dialog_help = new AlertDialog.Builder(contextThemeWrapper);
                dialog_help.setTitle("輔助功能");
                dialog_help.setSingleChoiceItems((String[]) list_help.toArray(new String[list_help.size()]), 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        name = which;
                    }
                });
                dialog_help.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        appendlog("按下按鈕:輔助功能-選擇的功能:"+ list_help.get(name).toString());//寫入
                        LayoutInflater inflaterdialog = getActivity().getLayoutInflater();
                        int help_image[] = new int[]{
                                R.drawable.identical_vs_different,R.drawable.so_as_to,R.drawable.widespread_vs_rare,
                                R.drawable.however,R.drawable.due_to
                        };
                        ImageView help_img = new ImageView(getContext());
                        help_img.setImageResource(help_image[name]);

                        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.dialog);
                        final AlertDialog.Builder ad = new AlertDialog.Builder(contextThemeWrapper);//抓單字解釋
                        ad.setTitle(list_help.get(name).toString())
                                .setView(help_img)
                                .setPositiveButton("關閉", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        appendlog("按下按鈕:輔助功能-選擇的功能:"+ list_help.get(name).toString()+"-關閉");//寫入
                                    }
                                })
                                .show();
                        Log.i("選擇的輔助功能:", list_help.get(name).toString());

                    }
                });
                dialog_help.setNegativeButton("關閉", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        appendlog("按下按鈕:輔助功能-關閉");//寫入

                    }
                });
                dialog_help.show();
            }
        });


        return myView;
    }

}
