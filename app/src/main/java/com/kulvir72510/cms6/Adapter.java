package com.kulvir72510.cms6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {
    private List<ModelClass> modelClassList;
    public Adapter(List<ModelClass> modelClassList){
        this.modelClassList=modelClassList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_view,viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {
        int resource=modelClassList.get(position).getImageIcon();
        String title=modelClassList.get(position).getTitle();
        String body=modelClassList.get(position).getBody();
        viewholder.setData(resource,title,body);

    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    class  Viewholder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView title;
        private TextView body;

        public Viewholder(@NonNull View itemView){
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            title=itemView.findViewById(R.id.textView);
            body=itemView.findViewById(R.id.textView2);
        }
        private void setData(int imageResource,String titleText,String bodyText){
            imageView.setImageResource(imageResource);
            title.setText((titleText));
            body.setText(bodyText);
        }
    }
}
