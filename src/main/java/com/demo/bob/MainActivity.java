package com.demo.bob;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView title;
    private Document document;
    private Context context;
    private EditText editText;
    private Button btn_enter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar currentDate = Calendar.getInstance();
        initview();

        // 获取目标日期
        Calendar targetDate = Calendar.getInstance();
        targetDate.set(2022, 8, 13); // 注意月份是从0开始计数的，所以9月对应的是8

        // 计算两个日期之间的天数差
        long diffMillis = targetDate.getTimeInMillis() - currentDate.getTimeInMillis();
        long daysBetween = diffMillis / (24 * 60 * 60 * 1000);

        // 将结果转换为int类型
        int days = (int) daysBetween;
        int day = Math.abs(days);

        // 输出结果
        System.out.println("当前时间到2022年9月13日的天数为：" + day);
        title.setText("今天是遇见他的第"+day+"天");
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = editText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String URL = "https://www.gequbao.com/s/"+msg;
                            // 执行网络请求和解析操作
                            Document doc = (Document) Jsoup.connect(URL).get();
                            // 进行源代码解析
                            if(doc != null){
                                Log.e("1114514",doc.toString());

                            }else {
                                Toast.makeText(context,"不知什么原因失败了",Toast.LENGTH_SHORT).show();
                            }
                            // ...

                            // 在新线程中更新UI需要使用runOnUiThread方法
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // 更新UI操作
                                }

                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }
    private void initview() {
        title = findViewById(R.id.title);//标题
        editText = findViewById(R.id.editText);//输入框
        btn_enter = findViewById(R.id.btn_enter);
    }



}