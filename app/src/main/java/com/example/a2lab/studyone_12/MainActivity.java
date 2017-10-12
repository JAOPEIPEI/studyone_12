package com.example.a2lab.studyone_12;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment examfragment1 = getSupportFragmentManager().findFragmentById(R.id.nav_exam1);
    Fragment readfragment1 = getSupportFragmentManager().findFragmentById(R.id.nav_read1);
    Fragment videofragment1 = getSupportFragmentManager().findFragmentById(R.id.nav_video1);
    TextView step1,step2;
    Button sure;
    EditText editText;
    String studentnum;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    Date date ;
    SimpleDateFormat sdf;//日期
    String dateString ;
    FileWriter fw;//
    String filename;
    String path = Environment.getExternalStorageDirectory().getPath();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("動畫電子書");

        drawer.setDrawerLockMode(1);//鎖住drawer

        verifyStoragePermissions(this);

        if (savedInstanceState == null) {
            examfragment1 = examFragment.newInstance("exam1","");
            videofragment1 = videoFragment.newInstance("video1","");
            readfragment1 = readFragment.newInstance("read1","");
        }

        editText=(EditText)findViewById(R.id.editText);//學號
        step1=(TextView) findViewById(R.id.textView2);//step1
        step2=(TextView) findViewById(R.id.textView3);//step2
        sure=(Button)findViewById(R.id.sure);

        editText.setVisibility(View.VISIBLE);
        step1.setVisibility(View.VISIBLE);

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.length()==0)
                    drawer.setDrawerLockMode(1);//鎖住drawer
                else {
                    drawer.setDrawerLockMode(0);//開啟drawer
                    studentnum = editText.getText().toString();

//                    readFragment KK=new readFragment();
//                    KK.setStudentnum(studentnum);

                    Log.i("Studennumber", studentnum);
                    editText.setVisibility(View.GONE);
                    step1.setVisibility(View.GONE);
                    step2.setVisibility(View.VISIBLE);
                    sure.setVisibility(View.INVISIBLE);
                    appendlog("開始\n");//輸出txt
                }
            }
        });


    }
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    public void appendlog(String behavior){//輸出txt

        try {
            verifyStoragePermissions(this);
            date = new Date();
            sdf = new SimpleDateFormat("MM/dd HH:mm:ss");//日期
            dateString = sdf.format(date);

            filename="/"+studentnum+"_studyone.txt";//學號為檔名
            fw = new FileWriter(path+filename,true);

            fw.write(dateString+"  "+ behavior+"\n");
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("EEE",e.toString());
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        verifyStoragePermissions(this);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        //drawer呼叫到read,video,exam
        if (id == R.id.nav_video1) {
            if(videofragment1.isAdded()){
                fragmentManager.beginTransaction().show(videofragment1).commit();
                Log.i("show", "videofragment:");
                setTitle("動畫影片");

                appendlog("顯示videofragment");//寫入
                videoFragment KK=new videoFragment();
                KK.setStudentnum(studentnum);

            }else{
                fragmentManager.beginTransaction().add(R.id.content_frame1,videofragment1,"videofragment1").commit();
                Log.i("add", "new videofragment:");
                setTitle("動畫影片");

                appendlog("新增videofragment");//寫入
                videoFragment KK=new videoFragment();
                KK.setStudentnum(studentnum);
            }
            if(examfragment1.isAdded()){
                fragmentManager.beginTransaction().hide(examfragment1).commit();
                Log.i("hide", "examfragment:(video)");
            }
            if(readfragment1.isAdded()){
                fragmentManager.beginTransaction().hide(readfragment1).commit();
                Log.i("hide", "readfragment:(video)");
            }
        } else if (id == R.id.nav_read1) {

            if(readfragment1.isAdded()){
                fragmentManager.beginTransaction().show(readfragment1).commit();
                Log.i("show", "readfragment: ");
                setTitle("閱讀文章");

                appendlog("顯示readfragment");//寫入
                readFragment KK=new readFragment();
                KK.setStudentnum(studentnum);

            }else{
                fragmentManager.beginTransaction().add(R.id.content_frame1,readfragment1,"readfragment1").commit();
                Log.i("add", "new readfragment: ");
                setTitle("閱讀文章");

                readFragment KK=new readFragment();
                KK.setStudentnum(studentnum);
                appendlog("新增readfragment");

            }

            if(examfragment1.isAdded()){
                fragmentManager.beginTransaction().hide(examfragment1).commit();
                Log.i("hide", "examfragment:(read) ");
            }
            if(videofragment1.isAdded()){
                fragmentManager.beginTransaction().hide(videofragment1).commit();
                VideoView videoView=(VideoView)findViewById(R.id.videoView);
                videoView.pause();
                Log.i("hide", "videofragment:(read) ");
            }


        } else if (id == R.id.nav_exam1) {

            if (examfragment1.isAdded()) {
                fragmentManager.beginTransaction().show(examfragment1).commit();
                Log.i("show", "examfragment");
                setTitle("測驗");

                appendlog("顯示examfragment");
                examFragment KK=new examFragment();
                KK.setStudentnum(studentnum);

            } else {
                fragmentManager.beginTransaction().add(R.id.content_frame1, examfragment1, "examfragment1").commit();
                Log.i("add", "examfragment");
                setTitle("測驗");

                appendlog("新增examfragment");
                examFragment KK=new examFragment();
                KK.setStudentnum(studentnum);
            }

            if (readfragment1.isAdded()) {
                fragmentManager.beginTransaction().hide(readfragment1).commit();
                Log.i("hide", "readfragment:(exam)");
            }
            if (videofragment1.isAdded()) {
                fragmentManager.beginTransaction().hide(videofragment1).commit();
                VideoView videoView = (VideoView) findViewById(R.id.videoView);
                videoView.pause();
                Log.i("hide", "videofragment:(exam)");
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
