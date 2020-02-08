package com.tf.datatransfer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import myutil.Jumin;
import vo.PersonVo;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //호출시에 사용했던 Intent정보를 구한다
        Intent intent = getIntent();

        //intent내에 저장(연결)된 데이터를 읽어오기
        String jumin_no = intent.getStringExtra("jumin_no");

        //직렬화된 객체만 전달된다
        PersonVo p = (PersonVo) intent.getSerializableExtra("p");

        Log.d("MY",jumin_no);
        Log.d("MY",p.getName() + "/" + p.getAge());


        // 주민번호-> 부가적 정보 추출
        Jumin jumin = new Jumin();
        jumin.setJumin_no(jumin_no);

        int year        = jumin.getYear();  //출생년도
        int age         = jumin.getAge();   //나이
        String tti      = jumin.getTti();   //띠
        String season   = jumin.getSeason();//계절
        String local    = jumin.getLocal(); //지역
        String ganji    = jumin.getGanji(); //60갑자
        String gender   = jumin.getGender();//성별

        //추출된 정보를 TextView에 넣기

        //  ID를 이용해서 위젯(컨트롤)의 참조값을 구하기
        //  findViewById oreo(8.0) auto cast 된다
        //  에러나면 강제형변환 시킬것
        TextView tv_jumin_no  = (TextView) findViewById(R.id.tv_jumin_no);
        TextView tv_year      = findViewById(R.id.tv_year);
        TextView tv_age       = findViewById(R.id.tv_age);
        TextView tv_tti       = findViewById(R.id.tv_tti);
        TextView tv_gender    = findViewById(R.id.tv_gender);
        TextView tv_local     = findViewById(R.id.tv_local);
        TextView tv_ganji     = findViewById(R.id.tv_ganji);
        TextView tv_season    = findViewById(R.id.tv_season);

        //값넣기
        tv_jumin_no.setText("주민번호:" + jumin_no);
        tv_year.setText("출생년도:" + year);
        tv_age.setText("나이:" + age);
        tv_tti.setText("띠:" + tti);
        tv_gender.setText("성별:" + gender);
        tv_local.setText("출생지역:" + local);
        tv_season.setText("출생계절:" + season);
        tv_ganji.setText("10간12지:" + ganji);



    }

    public void onClickFinish(View view){
        finish();
    }

}
