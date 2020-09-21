package com.example.bookstore.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookstore.R;
import com.example.bookstore.adapter.GioHangAdapter;
import com.example.bookstore.model.GioHang;
import com.example.bookstore.util.CheckConnection;
import com.example.bookstore.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Giohang extends AppCompatActivity {
    private ListView lvgiohang;
    private TextView txtthongbao;
    public static TextView txttongtien;
    public  static EditText edtdc;
    private static long tongtien=0;
    private Button btnthanhtoan,btntieptucmua;
    private Toolbar toolbargiohang;
    private GioHangAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        Anhxa();
        ActionToolBar();
        CheckData();
        EventUtil();
        CatchOnItemListView();
        EventButton();
    }
//    public void EventThanhToan(){
//
//        AlertDialog.Builder builder=new AlertDialog.Builder(Giohang.this);
//        builder.setTitle("Xác Nhận Thanh Toán Sản Phẩm");
//        builder.setMessage("Bạn Có chắc Muốn Đặt Giỏ Hàng Này ");
//        builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                final RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
//                StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.duongdandonhang, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(final String madonhang) {
//                        Log.d("mahoadon",madonhang);
//                        if(Integer.parseInt(madonhang)>0){
//                            RequestQueue queue=Volley.newRequestQueue(getApplicationContext());
//                            StringRequest  stringRequest1=new StringRequest(Request.Method.POST, Server.duongchitiecdonhang, new Response.Listener<String>() {
//                                @Override
//                                public void onResponse(String response) {
//                                    Log.d("machitiec",response);
//                                    if(response.equals("success")){
//                                        MainActivity.manggiohang.clear();
//                                        CheckConnection.ShowToast_short(getApplicationContext(),"Bạn đã thêm giỏ hàng thành công");
//                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                                        CheckConnection.ShowToast_short(getApplicationContext(),"Mời bạn tiếp tục mua sản phẩm");
//                                    }else{
//                                        CheckConnection.ShowToast_short(getApplicationContext(),"dữ liệu của bạn đã bị lỗi");
//                                    }
//                                }
//                            }, new Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//
//                                }
//                            }){
//                                @Override
//                                protected Map<String, String> getParams() throws AuthFailureError {
//                                    JSONArray jsonArray=new JSONArray();
//
//                                    for(int i=0;i<MainActivity.manggiohang.size();i++){
//                                        JSONObject object=new JSONObject();
//                                        try {
//                                            object.put("madonhang",madonhang);
//                                            object.put("masanpham",MainActivity.manggiohang.get(i).getIdsp());
//                                            object.put("soluongsanpham",MainActivity.manggiohang.get(i).getSoluongsp());
//                                            object.put("tientungsanpham",MainActivity.manggiohang.get(i).getGiasp());
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }
//                                        jsonArray.put(object);
//
//                                    }
//                                    HashMap<String,String> hashMap=new HashMap<String, String>();
//                                    hashMap.put("json",jsonArray.toString());
//                                    return hashMap;
//                                }
//                            };
//                            queue.add(stringRequest1);
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }){
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        HashMap<String,String> hashMap=new HashMap<String,String>();
//                        hashMap.put("idkhachhang",String.valueOf(DangNhapActivity.id));
//                        hashMap.put("tongtien",String.valueOf(tongtien));
//                        return hashMap;
//                    }
//                };
//                requestQueue.add(stringRequest);
//
//
//            }
//        });
//        builder.setNegativeButton("Không ", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                startActivity(new Intent(getApplicationContext(),Giohang.class));
//            }
//        });
//        builder.show();
//
//    }

    private void EventButton() {
        btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.manggiohang.size()>0){
                    startActivity(new Intent(getApplicationContext(),ThongtinKhachhang.class));
                }
                else {
                    CheckConnection.ShowToast_short(getApplicationContext(),"Giỏ hàng đang trống");
                }
            }
        });


    }
    private void CatchOnItemListView() {
        lvgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(Giohang.this);
                builder.setTitle("Xác Nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc xóa sản phẩm này ");
                builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.manggiohang.size()<=0){
                            txtthongbao.setVisibility(View.VISIBLE);
                        }else{
                            MainActivity.manggiohang.remove(position);
                            adapter.notifyDataSetChanged();
                            EventUtil();
                            if(MainActivity.manggiohang.size()<=0){
                                txtthongbao.setVisibility(View.VISIBLE);
                            }else{
                                txtthongbao.setVisibility(View.INVISIBLE);
                                adapter.notifyDataSetChanged();
                                EventUtil();
                            }

                        }
                    }
                });
                builder.setNegativeButton("Không ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyDataSetChanged();
                        EventUtil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }
    public static void EventUtil() {
        tongtien=0;
        for(int i=0;i<MainActivity.manggiohang.size();i++){
            tongtien+=MainActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttongtien.setText(""+decimalFormat.format(tongtien)+" đ");
    }
    private void CheckData() {
        if(MainActivity.manggiohang.size() <= 0){
            adapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);
        }else{
            adapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        lvgiohang=(ListView)findViewById(R.id.listviewgiohang);
        txttongtien=(TextView)findViewById(R.id.textviewgiatrigiohang);
        txtthongbao=(TextView)findViewById(R.id.textviewthongbao);
        btnthanhtoan=(Button)findViewById(R.id.buttonthanhtoanngiohang);
        btntieptucmua=(Button)findViewById(R.id.buttontieptucmuahang);
        toolbargiohang=(Toolbar)findViewById(R.id.toolbargiohang);
        adapter=new GioHangAdapter(Giohang.this,MainActivity.manggiohang);
        lvgiohang.setAdapter(adapter);
    }
}
