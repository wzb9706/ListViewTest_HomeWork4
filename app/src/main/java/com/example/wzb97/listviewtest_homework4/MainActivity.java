package com.example.wzb97.listviewtest_homework4;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wzb97.listviewtest_homework4.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wzb97 on 2016/8/30.
 */
public class MainActivity extends Activity {
    private final static String NAME="personName";
    private final static String PHONE="personPhone";
    private final static String ADRESS="personAdress";
    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] name = {"比尔盖茨", "乔布斯", "雷军", "超威蓝猫"};
        String[] phone = {"1111", "2222", "3333", "4444"};
        String[] adress = {"aaaaaaaaaaa", "bbbbbbbbbb", "ccccccccccc", "dddddddddd"};
        final List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < name.length; i++){
            Map<String, Object> item = new HashMap<String, Object>();
            item.put(NAME, name[i]);
            item.put(PHONE, phone[i]);
            item.put(ADRESS, adress[i]);
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, items, R.layout.item, new String[]{NAME, PHONE, ADRESS}, new int[]{R.id.PersonName, R.id.PersonPhone, R.id.PersonAdress});
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        Button x=(Button)findViewById(R.id.button9);
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button add=(Button)findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View v=View.inflate(MainActivity.this,R.layout.frame,null);
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setView(v).setTitle("新建联系人").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TextView name=(TextView)v.findViewById(R.id.editText);
                        TextView phone=(TextView)v.findViewById(R.id.editText2);
                        TextView adress=(TextView)v.findViewById(R.id.editText3);
                        if(name.getText().equals("")||phone.getText().toString().equals("")||adress.getText().toString().equals("")){
                            Toast.makeText(MainActivity.this,"填写栏不能为空",Toast.LENGTH_LONG).show();
                            return;
                        }
                        else{
                            Map<String, Object> item = new HashMap<String, Object>();
                            item.put(NAME, name.getText());
                            item.put(PHONE, phone.getText());
                            item.put(ADRESS, adress.getText());
                            items.add(item);
                            SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, items, R.layout.item, new String[]{NAME, PHONE, ADRESS}, new int[]{R.id.PersonName, R.id.PersonPhone, R.id.PersonAdress});
                            ListView list = (ListView) findViewById(R.id.list);
                            list.setAdapter(adapter);
                            Toast.makeText(MainActivity.this,"新建成功",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
                builder.show();
            }
        });
    }
}
