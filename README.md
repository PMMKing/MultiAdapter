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
viewholder需要继承baseViewHolder ，实现抽象方法去向布局中的view填充数据

```

public class ViewHolder extends BaseViewHolder {


    TextView tvItem;

    public ViewHolder(Context context, View itemView) {
        super(context, itemView);
        tvItem = (TextView) itemView.findViewById(R.id.tv_item);
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, Object data, int position) {
        tvItem.setText("这是第"  + position + "个item,值为：" + ((World)data).random);
    }
}
```

###构造adapter
只需要实例化写好的MultiAdapter 就可以构造出adapter
支持 item点击和长按

```

 		adapter = new MultiAdapter<World>(getApplicationContext(), worlds);
        adapter.setOnItemClickListener(this);
        adapter.setOnLongItemClickListener(this);
        adapter.addTypeView(new ITypeView() {
            @Override
            public boolean isForViewType(Object item, int position) {
                World world = (World) item;
                return world.random > 50;
            }

            @Override
            public BaseViewHolder createViewHolder(Context mContext, ViewGroup parent) {
                return new ViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.item_layout , parent , false));
            }

        }).addTypeView(new ITypeView() {
            @Override
            public boolean isForViewType(Object item, int position) {
                World world = (World) item;
                return world.random <= 50;
            }

            @Override
            public BaseViewHolder createViewHolder(Context mContext, ViewGroup parent) {
                return new ViewHolder2(mContext, LayoutInflater.from(mContext).inflate(R.layout.item2_layout , parent , false));
            }

        });
        rlvTest.setAdapter(adapter);

```
###其他方法

```
	adapter.addData(worlds);//在原有数据的基础上添加数据
    adapter.setData(worlds);//把原有数据清空，替换新数据
```

### 更新日志
去掉ITypeView中的指定布局方法，改为直接new viewholder并返回
viewholder去掉onCreateViewHolder 方法，改为在构造方法中绑定view

```
 @Override
 public BaseViewHolder createViewHolder(Context mContext, ViewGroup parent) {
     return new ViewHolder2(mContext, LayoutInflater.from(mContext).inflate(R.layout.item2_layout , parent , false));
 }
            
```

这里必须使用 LayoutInflater.from(mContext).inflate(R.layout.item2_layout , parent , false)这个方法，View.inflate()方法会报错。