package com.stillfly.mvplibrary.base.recyclerview;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> mViews;

    public BaseRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }


    /**
     * 根据 ID 获取 对应的控件，如果 mViews中没有缓存此控件，则先获取再缓存
     * @param viewId 控件 Id
     * @param <T> 控件类型
     * @return BaseRecyclerViewHolder
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置Text
     * @param viewId viewId 控件 Id
     * @param text 字符串
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder setText(@IdRes int viewId, CharSequence text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    /**
     * 设置Text
     * @param viewId 控件 Id
     * @param strId 字符串 Id
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder setText(@IdRes int viewId, @StringRes int strId) {
        TextView textView = getView(viewId);
        textView.setText(strId);
        return this;
    }


    /**
     * 设置 TextView 的 TextColor
     * @param viewId 控件 Id
     * @param color 颜色
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder setTextColor(@IdRes int viewId, @ColorInt int color) {
        TextView textView = getView(viewId);
        textView.setTextColor(color);
        return this;
    }


    /**
     * 设置 Typeface
     * @param viewId 控件 Id
     * @param typeface {@link Typeface}
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder setTypeface(@IdRes int viewId, Typeface typeface) {
        TextView textView = getView(viewId);
        textView.setTypeface(typeface);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    /**
     * 设置文字下划线
     * @param textViewId TextView id
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder setTextUnderline(@IdRes int textViewId) {
        TextView textView = getView(textViewId);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG | Paint.UNDERLINE_TEXT_FLAG);
        return this;
    }

    /**
     * 清除文字下划线
     * @param textViewId TextView id
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder clearTextUnderline(@IdRes int textViewId) {
        TextView textView = getView(textViewId);
        textView.setPaintFlags(textView.getPaintFlags() & ~Paint.UNDERLINE_TEXT_FLAG);
        return this;
    }

    /**
     * 设置文字加粗
     * @param textViewId TextView id
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder setTextBold(@IdRes int textViewId) {
        TextView textView = getView(textViewId);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG | Paint.FAKE_BOLD_TEXT_FLAG);
        return this;
    }

    /**
     * 设置文字删除线
     * @param textViewId TextView id
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder setTextStrikeThru(@IdRes int textViewId) {
        TextView textView = getView(textViewId);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG | Paint.STRIKE_THRU_TEXT_FLAG);
        return this;
    }

    /**
     * 取消文字删除线
     * @param textViewId TextView id
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder clearTextStrikeThru(@IdRes int textViewId) {
        TextView textView = getView(textViewId);
        textView.setPaintFlags(textView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        return this;
    }

    /**
     * 取消文字加粗
     * @param textViewId TextView id
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder clearTextBold(@IdRes int textViewId) {
        TextView textView = getView(textViewId);
        textView.setPaintFlags(textView.getPaintFlags() & ~Paint.FAKE_BOLD_TEXT_FLAG);
        return this;
    }


    /**
     * 设置 Linkify
     * @param textViewId TextView id
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder setTextViewLinkify(@IdRes int textViewId) {
        TextView textView = getView(textViewId);
        Linkify.addLinks(textView, Linkify.ALL);
        return this;
    }

    /**
     * 设置 Typeface
     * @param typeface {@link Typeface}
     * @param viewIds 控件 Ids
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder setTypeface(Typeface typeface, @IdRes int ...viewIds) {
        for (@IdRes int viewId : viewIds) {
            setTypeface(viewId, typeface);
        }
        return this;
    }

    /**
     * 设置ImageView 的 ImageResource
     * @param viewId 控件 Id
     * @param imgResId ImageResource Id
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder setImageResource(@IdRes int viewId, @DrawableRes int imgResId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(imgResId);
        return this;
    }

    /**
     * 设置ImageView 的 ImageDrawable
     * @param viewId 控件 Id
     * @param imgDrawable ImageDrawable
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder setImageDrawable(@IdRes int viewId, Drawable imgDrawable) {
        ImageView imageView = getView(viewId);
        imageView.setImageDrawable(imgDrawable);
        return this;
    }

    /**
     * 设置 imageView 的 imageBitmap
     * @param viewId 控件 Id
     * @param bitmap bitmap
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        ImageView imageView = getView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置 View 的 backgroundColor
     * @param viewId 控件 Id
     * @param color 颜色
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 设置 View 的 backgroundResource
     * @param viewId 控件 Id
     * @param backgroundResId  BackgroundResource Id
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder setBackgroudResource(@IdRes int viewId, @DrawableRes int backgroundResId) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundResId);
        return this;
    }

    /**
     * 设置 view 的Visibility
     * @param viewId 控件 Id
     * @param visibility  Visibility
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder setVisibility(@IdRes int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    /**
     * 设置控件 tag
     * @param viewId 控件 Id
     * @param tag tag 值
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder setTag(@IdRes int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * 设置控件 tag
     * @param viewId 控件 Id
     * @param key The key identifying the tag
     * @param tag tag 值
     * @return BaseRecyclerViewHolder
     */
    public BaseRecyclerViewHolder setTag(@IdRes int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

}
