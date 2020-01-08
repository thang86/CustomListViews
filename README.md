# MyListView
Custom ViewGroup same as ListView



# Sample CustomView same as ListView!




- Code sample:



```sh
private Adapter adapter;
    private SparseArray<List<View>> typesViews = new SparseArray<List<View>>();
    private final DataSetObserver observer = new DataSetObserver() {

        @Override
        public void onChanged() {
            refreshViews();
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

    protected void refreshViews() {
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
```

How to using:
 - In Fragment,Activity:
```sh
   final MyListView mMyListView = view.findViewById(R.id.my_list_view);
   adapter = new MyAdapter(getContext(), mStudentList, this);
   mMyListView.setAdapter(adapter);
```
- layout:
```sh
     <thang86.github.io.customlistviews.customview.MyListView
        android:id="@+id/my_list_view"
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
```

- Result:
![Result](https://github.com/thang86/CustomListViews/blob/master/images/ListView.png)


