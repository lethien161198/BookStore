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

public class TruyenAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraytruyen;
    private ArrayList<Sanpham> arrayList;

    public TruyenAdapter(Context context, ArrayList<Sanpham> arraytruyen) {
        this.context = context;
        this.arraytruyen = arraytruyen;
        this.arrayList=new ArrayList<Sanpham>();
        this.arrayList.addAll(arraytruyen);
    }

    @Override
    public int getCount() {
        return arraytruyen.size();
    }

    @Override
    public Object getItem(int position) {
        return arraytruyen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        private ImageView imghinhtruyen;
        private TextView txttruyen,txtgiatruyen,txtmotatruyen;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TruyenAdapter.ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new TruyenAdapter.ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.dong_truyen,null);
            viewHolder.imghinhtruyen=(ImageView)convertView.findViewById(R.id.imageviewtruyen);
            viewHolder.txttruyen=(TextView) convertView.findViewById(R.id.textviewtruyen);
            viewHolder.txtgiatruyen=(TextView)convertView.findViewById(R.id.textviewgiatruyen);
            viewHolder.txtmotatruyen=(TextView)convertView.findViewById(R.id.textviewmotatruyen);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (TruyenAdapter.ViewHolder) convertView.getTag();
        }
        Sanpham sanpham= (Sanpham) getItem(position);
        viewHolder.txttruyen.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtgiatruyen.setText("Gía: "+decimalFormat.format(sanpham.getGiasanpham())+" đ");
        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.imageico)
                .error(R.drawable.error)
                .into(viewHolder.imghinhtruyen);
        viewHolder.txtmotatruyen.setMaxLines(2);
        viewHolder.txtmotatruyen.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotatruyen.setText(sanpham.getMotasanpham());

        return convertView;
    }
    public void  filter(String charText){
        charText=charText.toLowerCase(Locale.getDefault());
        arraytruyen.clear();
        if(charText.length() == 0){
            arraytruyen.addAll(arrayList);
        }else{
            for(Sanpham sp : arrayList){
                if(sp.getTensanpham().toLowerCase(Locale.getDefault()).contains(charText)){
                    arraytruyen.add(sp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
