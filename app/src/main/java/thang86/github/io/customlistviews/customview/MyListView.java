package thang86.github.io.customlistviews.customview;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author ThangTX2
 */
public class MyListView extends LinearLayout {
    private Adapter adapter;
    private SparseArray<List<View>> typesViews = new SparseArray<List<View>>();
    private final DataSetObserver observer = new DataSetObserver() {

        @Override
        public void onChanged() {
            refreshViewsFromAdapter();
        }

        @Override
        public void onInvalidated() {
            removeAllViews();
        }
    };

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(Adapter adapter) {
        if (this.adapter != null) {
            this.adapter.unregisterDataSetObserver(observer);
        }
        this.adapter = adapter;
        if (this.adapter != null) {
            this.adapter.registerDataSetObserver(observer);
        }
        initViews();
    }

    protected void initViews() {
        typesViews.clear();
        removeAllViews();
        View view;
        if (adapter != null) {
            for (int i = 0; i < adapter.getCount(); i++) {
                view = adapter.getView(i, null, this);
                addToTypesList(adapter.getItemViewType(i), view, typesViews);
                addView(view, i);
            }
        }
    }

    protected void refreshViewsFromAdapter() {
        SparseArray<List<View>> typedViewsCacheCopy = typesViews;
        typesViews = new SparseArray<List<View>>();
        removeAllViews();
        View convertView;
        int type;
        for (int i = 0; i < adapter.getCount(); i++) {
            type = adapter.getItemViewType(i);
            convertView = modifierView(type, typedViewsCacheCopy);
            convertView = adapter.getView(i, convertView, this);
            addToTypesList(type, convertView, typesViews);
            addView(convertView, i);
        }
    }

    private static void addToTypesList(int type, View view, SparseArray<List<View>> typeViews) {
        List<View> typeViewList = typeViews.get(type);
        if (typeViewList == null) {
            typeViewList = new ArrayList<View>();
            typeViews.put(type, typeViewList);
        }
        typeViewList.add(view);
    }

    private static View modifierView(int type, SparseArray<List<View>> typeViews) {
        List<View> view = typeViews.get(type);
        if (view != null) {
            if (view.size() > 0) {
                return view.remove(0);
            }
        }
        return null;
    }
}
