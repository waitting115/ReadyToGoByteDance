package com.example.dazuoye;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
//    声明viewpager，list
    private ViewPager viewPager;
    private List<View> views;
    private MyNewAdapter myNewAdapter;

    private ListView listView1;

    private ExpandableListView expandableListView;
    private MyExpandAdapter myExpandAdapter;

    private String[] name = new String[]{"弗雷尔卓德","德玛西亚","艾欧尼亚","诺克萨斯","以绪塔尔"};
    private String[][] user = new String[][]{{"代表英雄：","弗雷尔卓德之心，丽桑卓"},{"代表英雄：","德玛西亚之力，无双剑姬"},{"代表英雄：","刀锋舞者，冰晶凤凰"},{"代表英雄：","诺克萨斯之手，荣耀行刑官"},{"代表英雄：","元素女皇，万花之灵"}};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);//找到viewpager
        views = new ArrayList<>();
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());

        View view1 = layoutInflater.inflate(R.layout.onelayout, null);
        View view2 = layoutInflater.inflate(R.layout.otwolayout, null);
        View view3 = layoutInflater.inflate(R.layout.threelayout, null);
        views.add(view1);
        views.add(view2);
        views.add(view3);

        myNewAdapter = new MyNewAdapter(views);
        viewPager.setAdapter(myNewAdapter);

        //第二页
        listView1 = findViewById(R.id.listview1);

        final int[]heads = new int[]{R.drawable.azir,R.drawable.xerath,R.drawable.nasus,R.drawable.renekton,R.drawable.sivir};
        final String[]names = new String[]{"阿兹尔","泽拉斯","内瑟斯","雷克顿","希维尔"};
        final String[]nicknames = new String[]{"沙漠皇帝","远古巫灵","沙漠死神","荒漠屠夫","战争女神"};

        List list = new ArrayList();
        for(int i = 0; i < names.length; i ++) {
            Map<String,Object> item = new HashMap<>();
            item.put("head",heads[i]);
            item.put("name",names[i]);
            item.put("nickname",nicknames[i]);
            list.add(item);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.item_lly,new String[]{"head","name","nickname"},new int[]{R.id.imageView2,R.id.textView5,R.id.textView6});

        listView1.setAdapter(simpleAdapter);

//        第三页
        expandableListView = findViewById(R.id.elv);
        myExpandAdapter = new MyExpandAdapter(this,user,name);
        expandableListView.setAdapter(myExpandAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(),
                        name[groupPosition] + ":" + user[groupPosition][childPosition],
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
