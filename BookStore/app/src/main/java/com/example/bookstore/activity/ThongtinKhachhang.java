package com.example.bookstore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookstore.R;
import com.example.bookstore.util.CheckConnection;
import com.example.bookstore.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongtinKhachhang extends AppCompatActivity {
    private Button bttxacnhan,btttrove;
    private EditText edttenkh,edtemail,edtsdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_khachhang);
        Anhxa();
        btttrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            EventButton();
        }else{
            CheckConnection.ShowToast_short(getApplicationContext(),"Kiêm tra lại kết nối");
        }
    }
    private void EventButton() {
        bttxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten=edttenkh.getText().toString().trim();
                final String email=edtemail.getText().toString().trim();
                final String sdt=edtsdt.getText().toString().trim();
                if(email.length()>0 && sdt.length()>0 && ten.length()>0){
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.Duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Log.d("madonhang",madonhang);
                            if(Integer.parseInt(madonhang)>0){
                                RequestQueue queue=Volley.newRequestQueue(getApplicationContext());
                                StringRequest  request=new StringRequest(Request.Method.POST, Server.Duongdanchitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        CheckConnection.ShowToast_short(getApplicationContext(),"response = "+response);
                                        if(response.equals("1")){
                                            MainActivity.manggiohang.clear();
                                            CheckConnection.ShowToast_short(getApplicationContext(),"Thêm dữ liệu giỏ hàng thành công");
                                            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);
                                            CheckConnection.ShowToast_short(getApplicationContext(),"Mời bạn tiếp tục mua hàng");
                                        }else{
                                            CheckConnection.ShowToast_short(getApplicationContext(),"Dữ liệu giỏ hàng đã bị lỗi");
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray=new JSONArray();
                                        for(int i=0;i<MainActivity.manggiohang.size();i++){
                                            JSONObject object=new JSONObject();
                                            try {
                                                object.put("madonhang",madonhang);
                                                object.put("masanpham",MainActivity.manggiohang.get(i).getIdsp());
                                                object.put("tensanpham",MainActivity.manggiohang.get(i).getTensp());
                                                object.put("giasanpham",MainActivity.manggiohang.get(i).getGiasp());
                                                object.put("soluongsanpham",MainActivity.manggiohang.get(i).getSoluongsp());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(object);

                                        }
                                        HashMap<String,String> hashMap=new HashMap<String, String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap=new HashMap<String,String>();
                            hashMap.put("tenkhachhang",ten);
                            hashMap.put("sodienthoai",sdt);
                            hashMap.put("email",email);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else{
                    CheckConnection.ShowToast_short(getApplicationContext(),"Kiểm tra lại dữ liệu");
                }
            }
        });
    }
    private void Anhxa() {
        btttrove=(Button)findViewById(R.id.buttonhukhachhang);
        bttxacnhan=(Button)findViewById(R.id.buttonxacnhankhachhang);
        edtemail=(EditText)findViewById(R.id.edittexttmailkhachhang);
        edtsdt=(EditText)findViewById(R.id.edittexttsdtkhachhang);
        edttenkh=(EditText) findViewById(R.id.edittexttenkhachhang);
    }
}
