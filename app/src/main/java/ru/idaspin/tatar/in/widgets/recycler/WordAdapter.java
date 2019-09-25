package ru.idaspin.tatar.in.widgets.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import ru.idaspin.tatar.in.R;
import ru.idaspin.tatar.in.models.Word;

public class WordAdapter extends BaseRecyclerAdapter<BaseHolder, Word> {

    private String mQuery = "";

    public WordAdapter() {

    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WordHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_word, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Word post = getItem(position);
        holder.bind(post, mQuery);
    }

    public void putQuery(String query) {
        mQuery = query;
    }
}
