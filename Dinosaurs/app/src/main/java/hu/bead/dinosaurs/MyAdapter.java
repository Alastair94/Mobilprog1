package hu.bead.dinosaurs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import hu.bead.dinosaurs.model.Dino;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    Dino[] dinos;

    public MyAdapter(Context ct, Dino[] dinos) {
        this.context = ct;
        this.dinos = new Dino[dinos.length];
        for (int i = 0; i < dinos.length; i++){
            this.dinos[i] = dinos[i];
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.dino_Name.setText(dinos[position].getName());
        holder.myImage.setImageResource(dinos[position].getImage());

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DinoPageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("dino_name_txt", dinos[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dinos.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView dino_Name;
        ImageView myImage;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dino_Name = itemView.findViewById(R.id.dino_name_txt);
            myImage = itemView.findViewById(R.id.myImageView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
