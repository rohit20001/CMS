package com.kulvir72510.cms6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {
    private Context mContext;
    private List<ModelClass> mUploads;

    public Adapter(Context context, List<ModelClass> uploads) {
        mContext = context;
        mUploads=uploads;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.post_view,viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        ModelClass UploadCurreent=mUploads.get(position);
        holder.model_name.setText(UploadCurreent.getModel_name());
        holder.color.setText(UploadCurreent.getColor());
        holder.price.setText(UploadCurreent.getPrice());
        holder.gst.setText(UploadCurreent.getGst());
        holder.other.setText(UploadCurreent.getOther());
        holder.contact.setText(UploadCurreent.getContact());
        holder.email.setText(UploadCurreent.getEmail());

    }

    @Override
    public int getItemCount() {

        return mUploads.size();
    }

    public class  Viewholder extends RecyclerView.ViewHolder{
        private TextView model_name,color,price,gst,other,email,contact;

        public Viewholder(@NonNull View itemView){
            super(itemView);
            email=itemView.findViewById(R.id.email_id);
            contact=itemView.findViewById(R.id.contact);
            model_name=itemView.findViewById(R.id.model_name);
            price=itemView.findViewById(R.id.price);
            color=itemView.findViewById(R.id.color);
            gst=itemView.findViewById(R.id.gst);
            other=itemView.findViewById(R.id.other);

        }
    }
}
