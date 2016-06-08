# MaterialProgressView
### 具有 SwipeRefreshLayout 样式的转圈动画。

#### CircleImageView和MaterialProgressDrawable来自于AOSP的support-v4包，精简了用不到的成员变量和方法。

![Alt text](https://raw.githubusercontent.com/xingda920813/SimpleRecyclerView/master/videomaterial.gif)

#### 支持wrap\_content和LayoutParams.WRAP_CONTENT;

#### 可自定义颜色 : 

	materialProgressView.setColorSchemeColors(new int[]{getResources().getColor(R.color.colorAccent)});

若int[]中的颜色值多于一个，将按顺序轮换显示不同颜色的转圈，每转一圈换一种颜色。

#### setVisibility具有与ProgressBar相同的行为：setVisibility(int visibility)中参数为View.INVISIBLE或View.GONE时，停止动画并复位状态（圆弧颜色和旋转的程度），参数为View.VISIBLE时，开始动画。

因此可用于RecyclerView.Adapter的ProgressViewHolder，用于加载更多指示器的实现。加载完成后，调用MaterialProgressView.setVisibility(View.INVISIBLE / View.GONE)；开始加载时，调用MaterialProgressView.setVisibility(View.VISIBLE)，因为状态已重置过，所以动画将从头播放、颜色也会取int[]中的第一种颜色，然后每转一圈换一种颜色。

Adapter和ProgressViewHolder示例详见sample.

## 引入

通过jar包引入：引入MaterialProgressView-1.0.0.jar，同时在build.gradle中添加compile 'com.android.support:appcompat-v7:${latest.version}'

通过jcenter引入（推荐）：直接在build.gradle中添加

    compile 'com.xdandroid:materialprogressview:+'
	compile 'com.android.support:appcompat-v7:${latest.version}'

