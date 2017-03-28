package com.imarneanu.alexiuliawedding.custom;

import com.imarneanu.alexiuliawedding.R;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by iulia on 28/03/2017.
 */

public class EmptyLayout extends FrameLayout {

    @BindView(R.id.empty_layout_progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.empty_layout_empty_text)
    TextView mEmptyText;

    @BindView(R.id.empty_layout_try_again_layout)
    View mTryAgainLayout;

    @BindView(R.id.empty_layout_error_text)
    TextView mErrorText;

    private View.OnClickListener mOnTryAgainClickListener;

    public EmptyLayout(Context context) {
        super(context);
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EmptyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.empty_layout, this);
        ButterKnife.bind(this);

        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.EmptyLayout, 0, 0);

        try {
            final CharSequence emptyText = a.getText(R.styleable.EmptyLayout_emptyLayoutEmptyText);
            if (emptyText != null) {
                mEmptyText.setText(emptyText);
            }
            final CharSequence errorText = a.getText(R.styleable.EmptyLayout_emptyLayoutErrorText);
            if (errorText != null) {
                mErrorText.setText(errorText);
            }
        } finally {
            a.recycle();
        }
    }

    // TODO save and restore instance state

    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    @OnClick(R.id.empty_layout_try_again_button)
    void tryAgain(View view) {
        if (mOnTryAgainClickListener != null) {
            mOnTryAgainClickListener.onClick(view);
        }
    }

    public void showLoading() {
        mProgressBar.setVisibility(VISIBLE);
        mEmptyText.setVisibility(GONE);
        mTryAgainLayout.setVisibility(GONE);
    }

    public void showError() {
        mProgressBar.setVisibility(GONE);
        mEmptyText.setVisibility(GONE);
        mTryAgainLayout.setVisibility(VISIBLE);
    }

    public void showEmpty() {
        mProgressBar.setVisibility(GONE);
        mEmptyText.setVisibility(VISIBLE);
        mTryAgainLayout.setVisibility(GONE);
    }

    public void setProgressBarTintColor(@ColorInt int color) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            mProgressBar.getIndeterminateDrawable().mutate()
                    .setColorFilter(color, PorterDuff.Mode.SRC_IN);
        } else {
            mProgressBar.setIndeterminateTintList(ColorStateList.valueOf(color));
        }
    }

    public void setEmptyText(CharSequence emptyText) {
        mEmptyText.setText(emptyText);
    }

    public void setEmptyText(@StringRes int emptyTextRes) {
        mEmptyText.setText(emptyTextRes);
    }

    public void setErrorText(@StringRes int errorTextRes) {
        mErrorText.setText(errorTextRes);
    }

    public void setErrorText(CharSequence errorText) {
        mErrorText.setText(errorText);
    }

    public View.OnClickListener getOnTryAgainClickListener() {
        return mOnTryAgainClickListener;
    }

    public void setOnTryAgainClickListener(View.OnClickListener onTryAgainClickListener) {
        mOnTryAgainClickListener = onTryAgainClickListener;
    }
}

