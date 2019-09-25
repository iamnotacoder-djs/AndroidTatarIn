package ru.idaspin.tatar.in.widgets.recycler;

import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.idaspin.tatar.in.R;
import ru.idaspin.tatar.in.models.Word;

class WordHolder extends BaseHolder{

    @BindView(R.id.item_text) TextView mTextView;

    WordHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bind(@NonNull Word post, String query) {
        if (post.getRus().contains(query)) {
            String t = post.getTat();
            t = Character.toUpperCase(t.charAt(0)) + t.substring(1);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mTextView.setText(Html.fromHtml(t, 0));
            } else {
                mTextView.setText(Html.fromHtml(t));
            }
        } else {
            String t = post.getRus();
            t = Character.toUpperCase(t.charAt(0)) + t.substring(1);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mTextView.setText(Html.fromHtml(t, 0));
            } else {
                mTextView.setText(Html.fromHtml(t));
            }
        }
    }

}