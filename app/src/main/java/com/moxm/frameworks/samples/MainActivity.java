package com.moxm.frameworks.samples;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.moxm.frameworks.samples.domain.JsonResult;
import com.moxm.frameworks.samples.domain.Weather;
import com.moxm.frameworks.samples.domain.WeatherData;
import com.moxm.frameworks.samples.manager.DatabaseManager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    private ListView mListView;


    private RequestQueue mQueue;
    private DatabaseManager mDatabaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
        fillView();
        setListener();
        loadData();
    }

    protected void init(Bundle savedInstanceState) {
        setContentView(R.layout.item_main);
        mDatabaseManager = new DatabaseManager(this);
        mQueue = Volley.newRequestQueue(this);
    }

    /**
     * 获取View对象
     */
    protected void fillView() {
        mListView = (ListView) findViewById(R.id.list);
    }
    /**
     * 设置监听事件
     */
    protected void setListener() {
        mListView.setOnItemClickListener(this);
    }
    /**
     * 加载填充数据
     */
    protected void loadData() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, getData());
        mListView.setAdapter(adapter);
    }




    public void postClicker(View v) {
        StringRequest postRequest = new StringRequest(Request.Method.GET, "http://api.map.baidu.com/telematics/v3/weather?location=%E7%BB%A5%E5%BE%B7&output=json&ak=11ffd27d38deda622f51c9d314d46b17",  listener, errorListener) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("ip", "63.223.108.42");
//                return map;
//            }
        };
        mQueue.add(postRequest);
    }
    public void clearClicker(View v) {
        mDatabaseManager.deleteWeatherDatas();
        loadData();
    }

    private Response.Listener<String> listener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d("Listener", "++++++" + response);
//            Type t = new TypeToken<JsonResult>() {}.getType();
            Gson gson = new Gson();
            JsonResult result = gson.fromJson(response, JsonResult.class);
            if (result != null && result.getResults() != null && result.getResults().size() > 0) {
                Weather weather = result.getResults().get(0);
                //保存Weather Data
                mDatabaseManager.saveWeatherDatas(weather.getWeather_data());
            }

            //查询显示数据
            loadData();
        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.d("ErrorListener", "++++++" + volleyError.getMessage());
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    private List<String> getData() {
        List<WeatherData> weatherDatas = mDatabaseManager.findWeatherDatas();
        List<String> data = new ArrayList<String>();
        for (WeatherData item : weatherDatas) {
            data.add(item.getWeather());
        }
        return data;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
