package com.example.bookstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookstore.R;
import com.example.bookstore.adapter.SachAdapter;
import com.example.bookstore.adapter.TruyenAdapter;
import com.example.bookstore.model.GioHang;
import com.example.bookstore.model.Sanpham;
import com.example.bookstore.util.CheckConnection;
import com.example.bookstore.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TruyenActivity extends AppCompatActivity {
    Toolbar toolbartruyen;
    ListView listView;
    TruyenAdapter truyenAdapter;
    ArrayList<Sanpham> mangtruyen;
    int idtruyen=0;
    int page=1;
    boolean isLoading=false;
    boolean limitdata=false;
    View footerview;
    TruyenActivity.mHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen);
        Anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())) {
            GetIdloaisp();
            ActionToolBar();
            GetData(page);
            LoadmoreData();
        }else {
            CheckConnection.ShowToast_short(getApplicationContext(),"lỗi kết nối");
            finish();
        }
    }

    private void LoadmoreData() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),ChiTietSanPham.class);
                intent.putExtra("thongtinsanpham",mangtruyen.get(position));
                startActivity(intent);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem +visibleItemCount == totalItemCount && totalItemCount != 0 && isLoading==false && limitdata ==false){
                    isLoading=true;
                    TruyenActivity.ThreadData threadData=new TruyenActivity.ThreadData();
                    threadData.start();
                }
            }
        });
    }
    public class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    listView.addFooterView(footerview);
                    break;
                case 1:
                    GetData(++page);
                    isLoading=false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    private void GetData(int Page) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= Server.Duongdansach+String.valueOf(page);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id=0;
                String Tensanpham="";
                Integer Giasanpham=0;
                String Hinhanhsanpham="";
                String Motasanpham="";
                int IDsanpham=0;
                if(response!=null&& response.length() !=2){
                    listView.removeFooterView(footerview); //có dữ liệu thì sẽ mất biểu tưởng load
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            id=jsonObject.getInt("id");
                            Tensanpham=jsonObject.getString("tensanpham");
                            Giasanpham=jsonObject.getInt("giasanpham");
                            Hinhanhsanpham=jsonObject.getString("hinhsanpham");
                            Motasanpham=jsonObject.getString("motasanpham");
                            IDsanpham=jsonObject.getInt("idsanpham");
                            mangtruyen.add(new Sanpham(id,Tensanpham,Giasanpham,Hinhanhsanpham,Motasanpham,IDsanpham));
                            truyenAdapter.notifyDataSetChanged();

                        }
                        truyenAdapter=new TruyenAdapter(getApplicationContext(),mangtruyen);
                        listView.setAdapter(truyenAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }else{
                    limitdata=true;
                    listView.removeFooterView(footerview);
                    CheckConnection.ShowToast_short(getApplicationContext(),"Đã hết dữ liệu");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param=new HashMap<String, String>();
                param.put("idsanpham",String.valueOf(idtruyen));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbartruyen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbartruyen.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIdloaisp() {
        idtruyen=getIntent().getIntExtra("idloaisanpham",-1);
        Log.d("giatriloaisanpham",idtruyen+"");
    }
    public class ThreadData extends Thread{
        public void run(){
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message=mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        SearchView searchView= (SearchView) menu.findItem(R.id.menusearch).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                truyenAdapter.filter(s.trim());
                return false;
            }
        });
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang :
                Intent intent=new Intent(getApplicationContext(), Giohang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void Anhxa() {
        toolbartruyen=(Toolbar)findViewById(R.id.toolbartruyen);
        listView=(ListView)findViewById(R.id.listviewtruyen);

        mangtruyen=new ArrayList<>();
        truyenAdapter=new TruyenAdapter(getApplicationContext(),mangtruyen);
        listView.setAdapter(truyenAdapter);
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview =inflater.inflate(R.layout.progressbar,null);
        mHandler=new mHandler();
    }
}
