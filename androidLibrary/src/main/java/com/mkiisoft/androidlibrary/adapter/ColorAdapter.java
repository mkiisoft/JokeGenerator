package com.mkiisoft.androidlibrary.adapter;

import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mkiisoft.androidlibrary.R;
import com.mkiisoft.androidlibrary.model.ColorModel;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorHolder> {

    private ClickListener clickListener;
    private List<ColorModel> list;

    public <T extends List<ColorModel>> ColorAdapter setColorList(T list) {
        this.list = list;
        return this;
    }

    public ColorAdapter addListener(ClickListener listener) {
        this.clickListener = listener;
        return this;
    }

    @NonNull
    @Override
    public ColorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ColorHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ColorHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ColorHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView colorView;

        ColorHolder(View itemView) {
            super(itemView);

            colorView = itemView.findViewById(R.id.color_shape);
            colorView.setOnClickListener(this);
        }

        void bind(ColorModel color) {
            GradientDrawable colorDrawable = (GradientDrawable) ContextCompat.getDrawable(itemView.getContext(), R.drawable.circular_color);
            if (colorDrawable != null) {
                colorDrawable.setColor(color.getBgColor());
                colorView.setImageDrawable(colorDrawable);
            }
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onItemClick(list.get(getAdapterPosition()), getAdapterPosition());
            }
        }
    }

    public interface ClickListener {
        void onItemClick(ColorModel color, int position);
    }
}
