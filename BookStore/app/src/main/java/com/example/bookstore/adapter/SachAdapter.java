package com.example.bookstore.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookstore.R;
import com.example.bookstore.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class SachAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraysach;
    private ArrayList<Sanpham> arrayList;

    public SachAdapter(Context context, ArrayList<Sanpham> arraysach) {
        this.context = context;
        this.arraysach = arraysach;
        this.arrayList=new ArrayList<Sanpham>();
        this.arrayList.addAll(arraysach);
    }

    @Override
    public int getCount() {
        return arraysach.size();
    }

    @Override
    public Object getItem(int position) {
        return arraysach.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        private ImageView imghinhsach;
        private TextView txtsach,txtgiasach,txtmotasach;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.dong_sach,null);
            viewHolder.imghinhsach=(ImageView)convertView.findViewById(R.id.imageviewsach);
            viewHolder.txtsach=(TextView) convertView.findViewById(R.id.textviewsach);
            viewHolder.txtgiasach=(TextView)convertView.findViewById(R.id.textviewgiasach);
            viewHolder.txtmotasach=(TextView)convertView.findViewById(R.id.textviewmotasach);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Sanpham sanpham= (Sanpham) getItem(position);
        viewHolder.txtsach.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtgiasach.setText("Gía: "+decimalFormat.format(sanpham.getGiasanpham())+" đ");
        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.imageico)
                .error(R.drawable.error)
                .into(viewHolder.imghinhsach);
        viewHolder.txtmotasach.setMaxLines(2);
        viewHolder.txtmotasach.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotasach.setText(sanpham.getMotasanpham());

        return convertView;
    }
    public void  filter(String charText){
        charText=charText.toLowerCase(Locale.getDefault());
        arraysach.clear();
        if(charText.length() == 0){
            arraysach.addAll(arrayList);
        }else{
            for(Sanpham sp : arrayList){
                if(sp.getTensanpham().toLowerCase(Locale.getDefault()).contains(charText)){
                    arraysach.add(sp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
