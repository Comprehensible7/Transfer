package com.tf.datatransfer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import vo.PersonVo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //결과화면 띄우기
    public void onClickResult(View view){

        //입력된 주민번호을 읽어오기
        EditText et_jumin_no = findViewById(R.id.et_jumin_no);
        String jumin_no = et_jumin_no.getText().toString().trim();

        //값이 비어있는 경우
        if(jumin_no.isEmpty()){
            et_jumin_no.setText("");//비우기
            //Toast Message 띄우기
            Toast.makeText(this,"주민번호를 입력하세요",Toast.LENGTH_SHORT).show();
            return;
        }

        //명시적 인텐트:호출대상이 명시적으로
        Intent intent = new Intent(this,ResultActivity.class);

        //넘길 데이터 인텐트에 넣는다
        //                     Key        Value
        intent.putExtra("jumin_no",jumin_no);

        PersonVo p = new PersonVo("일길동",20);
        intent.putExtra("p",p);

        startActivity(intent);
    }


    public void onClickResultData(View view){

        //입력된 주민번호을 읽어오기
        EditText et_jumin_no = findViewById(R.id.et_jumin_no);
        String jumin_no = et_jumin_no.getText().toString().trim();

        //값이 비어있는 경우
        if(jumin_no.isEmpty()){
            et_jumin_no.setText("");//비우기
            //Toast Message 띄우기
            Toast.makeText(this,"주민번호를 입력하세요",Toast.LENGTH_SHORT).show();
            return;
        }

        //명시적 인텐트:호출대상이 명시적으로
        Intent intent = new Intent(this,Result2Activity.class);

        //넘길 데이터 인텐트에 넣는다
        //                     Key        Value
        intent.putExtra("jumin_no",jumin_no);
        startActivityForResult(intent,REQUEST_JUMIN);

    }
    //상수선언
    public static final int REQUEST_JUMIN  = 1;
    public static final int REQUEST_PHOTO  = 2;

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            @Nullable Intent data) {

        if(requestCode==REQUEST_JUMIN){

            if(resultCode== Activity.RESULT_OK){
                //결과 정상적으로 수신
                HashMap map = (HashMap) data.getSerializableExtra("map");
                Log.d("MY","(결과)출생년도:" + map.get("year"));

                StringBuffer sb = new StringBuffer();
                sb.append(String.format("[년도]: %s(%s생)\n",map.get("year"),map.get("ganji")));
                sb.append(String.format("[나이]: %s살\n",map.get("age")));
                sb.append(String.format("[성별]: %s\n",map.get("gender")));
                sb.append(String.format("[띠이]: %s\n",map.get("tti")));
                sb.append(String.format("[계절]: %s\n",map.get("season")));
                sb.append(String.format("[지역]: %s\n",map.get("local")));

                //TextView에 multi line으로 출력 :  lines=8
                ((TextView)findViewById(R.id.tv_display)).setText(sb.toString());


            }else{
                Toast.makeText(this,"결과실패!!",Toast.LENGTH_SHORT).show();
            }

        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}
