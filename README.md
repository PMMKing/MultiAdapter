#RecyclerView Adapter 

###应用到项目

To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

gradle
maven
sbt
leiningen
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.evernightking:RecyclerViewLibrary:d8ebec3d2c'
	}
Share this release:


###这是一个recyclerView 通用的 adapter 支持多布局，
viewholder需要继承baseViewHolder ，实现两个抽象方法去向布局中的view填充数据

```
@Override
    public void onCreateViewHolder(BaseViewHolder holder) {
        ViewHolder viewHolder = (ViewHolder)holder;
        View convertView = viewHolder.getConvertView();
        viewHolder.tvItem = (TextView) convertView.findViewById(R.id.tv_item);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, int position) {
//        tvItem = (TextView) viewHolder.getConvertView().findViewById(R.id.tv_item);
        ViewHolder holder = (ViewHolder)viewHolder;
        holder.tvItem.setText("这是第 ：" + position + "个Item。");
    }
```

###当然，为了构造viewholder方便，推荐在viewholder中写一个静态构造方法

```
	public static ViewHolder createViewHolder(Context context, View itemView) {
        return new ViewHolder(context, itemView);
    }

    public static ViewHolder createViewHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new ViewHolder(context, itemView);
    }
``` 

###构造adapter
不需要再去写adapter了，只需要实例化写好的MultiAdapter 就可以构造出adapter
支持 item点击和长按

```

 	adapter = new MultiAdapter<World>(getApplicationContext(), worlds);
        adapter.setOnItemClickListener(this);
        adapter.setOnLongItemClickListener(this);
        adapter.addTypeView(new ITypeView() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_layout;
            }

            @Override
            public boolean isForViewType(Object item, int position) {

                World world = (World)item;
                return world.random > 50;
            }

            @Override
            public BaseViewHolder createViewHolder(Context mContext, ViewGroup parent, int layoutId) {
                return ViewHolder.createViewHolder(mContext, parent, layoutId);
            }
        }).addTypeView(new ITypeView() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item2_layout;
            }

            @Override
            public boolean isForViewType(Object item, int position) {
                World world = (World)item;
                return world.random <= 50;
            }

            @Override
            public BaseViewHolder createViewHolder(Context mContext, ViewGroup parent, int layoutId) {
                return ViewHolder2.createViewHolder(mContext,parent,layoutId);
            }
        });
        rlvTest.setAdapter(adapter);

```
###其他方法

```
	adapter.addData(worlds);//在原有数据的基础上添加数据
    adapter.setData(worlds);//把原有数据清空，替换新数据
```