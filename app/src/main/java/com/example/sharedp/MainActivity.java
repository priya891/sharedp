package com.example.sharedp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText editText,editText1;
    Button submit,retreive,clear;
    TextView textView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
        editText1=findViewById(R.id.editText1);
        retreive=findViewById(R.id.retreive_btn);
        textView=findViewById(R.id.textView);
        submit=findViewById(R.id.submit_btn);
        clear=findViewById(R.id.clearbtn);
        sharedPreferences=getSharedPreferences("info",MODE_PRIVATE);
        editor= sharedPreferences.edit();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text=editText.getText().toString();
                String text1=editText.getText().toString();
                if(text.equals("")&&text1.equals("")){
                    editText.setError("please enter text");
                }
                else{
                    writetofile(text);
                    editor.putString("text",text).commit();
                    editor.putString("text1",text1).commit();

                    Toast.makeText(MainActivity.this,"text=="+text,Toast.LENGTH_SHORT).show();


                }
            }
        });

        retreive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=sharedPreferences.getString("text","");
                String text1=sharedPreferences.getString("text1","");
                Toast.makeText(MainActivity.this,"text=="+text,Toast.LENGTH_SHORT).show();
                textView.setText(text + text1);

            }
        });



        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readtofile();
            }
        });


    }
    private void writetofile(String text) {
        try {
            FileOutputStream fOut = openFileOutput("file.txt", Context.MODE_PRIVATE);
            fOut.write(text.getBytes());
            Toast.makeText(MainActivity.this, "text writtten sucessfully in file", Toast.LENGTH_SHORT).show();

            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readtofile(){
        FileInputStream f= null;
        int c;
        String temp="";
        try {
            f = openFileInput("file.txt");
            while( (c = f.read()) != -1){
                temp = temp + Character.toString((char)c);
            }
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        editText.setText("");
        editText1.setText("");
        Toast.makeText(MainActivity.this,"text=="+temp,Toast.LENGTH_SHORT).show();
    }
}

