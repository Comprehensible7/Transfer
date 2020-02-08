package com.tf.datatransfer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import myutil.Jumin;

public class Result2Activity extends AppCompatActivity {

    String jumin_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);

        //호출시에 사용했던 Intent정보를 구한다
        Intent intent = getIntent();

        //intent내에 저장(연결)된 데이터를 읽어오기
        jumin_no = intent.getStringExtra("jumin_no");
    }

    public void onClickFinish(View view){

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

        HashMap map = new HashMap();
        map.put("year",year);
        map.put("age",age);
        map.put("tti",tti);
        map.put("season",season);
        map.put("local",local);
        map.put("ganji",ganji);
        map.put("gender",gender);

        //결과 전송용 인텐트
        Intent intent = new Intent();
        intent.putExtra("map",map);

        setResult(Activity.RESULT_OK,intent);// onActivityResult Call

        //현재 화면 닫기
        finish();

    }
}
