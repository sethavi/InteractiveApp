package btech.oneandall.interactivelearning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter2 extends  RecyclerView.Adapter<Adapter2.Viewholder> {


    ArrayList<String> mNames = new ArrayList<>();
    ArrayList<String> mMsg = new ArrayList<>();
    ArrayList<String> mPics = new ArrayList<>();
    Context mcontext;

    public Adapter2(ArrayList<String> mNames, ArrayList<String> mMsg, ArrayList<String> mypic, Context mcontext) {
        this.mNames = mNames;
        this.mMsg = mMsg;
        this.mcontext = mcontext;
        this.mPics=mypic;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_view,parent,false);
        Viewholder holder = new Viewholder(view);
        //onViewAttachedToWindow(holder);
      //  view.requestFocus();
        //holder.setIsRecyclable(false);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        holder.textView.setText(mNames.get(position));
        holder.textView2.setText(mMsg.get(position));
        Glide.with(mcontext)
                .load(mPics.get(position))
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public  class  Viewholder extends RecyclerView.ViewHolder
    {

      //  ImageView imageView;
        TextView textView,textView2;
        LinearLayout linearLayout;
        ImageView imageView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
          //  imageView = itemView.findViewById(R.id.imageView2);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            linearLayout = itemView.findViewById(R.id.ll1);
            imageView = itemView.findViewById(R.id.imageView2);

        }
    }
}
