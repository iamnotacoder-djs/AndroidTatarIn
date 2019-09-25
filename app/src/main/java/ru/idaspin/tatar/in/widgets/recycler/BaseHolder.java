package ru.idaspin.tatar.in.widgets.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ru.idaspin.tatar.in.models.Word;

abstract class BaseHolder extends RecyclerView.ViewHolder{

    BaseHolder(View itemView) {
        super(itemView);
    }

    abstract void bind(@NonNull Word post, String query);

}