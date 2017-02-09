# MaterialProgressView

### 具有 SwipeRefreshLayout 样式的转圈动画

#### 修改自 support-core-ui-25.1.1

![https://raw.githubusercontent.com/xingda920813/MaterialProgressView/master/video.gif](https://raw.githubusercontent.com/xingda920813/MaterialProgressView/master/video.gif)

#### 支持wrap\_content和LayoutParams.WRAP_CONTENT;

#### 可自定义转圈的颜色和转圈所在圆形突起的背景色，可自定义透明度：

```
//设置转圈的颜色为蓝红两色渐变交替
mProgressView.setColorSchemeColors(new int[]{getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent)});
//设置转圈所在圆形突起的背景色为默认的浅灰色
mProgressView.setProgressBackgroundColor(Color.parseColor("#FAFAFA"));
//设置透明度(0-255, 默认为255不透明)
mProgressView.setColorViewAlpha(255);
```

#### setVisibility具有与ProgressBar相同的行为：setVisibility(int visibility)中参数为View.INVISIBLE或View.GONE时，停止动画并复位状态（圆弧颜色和旋转的程度），参数为View.VISIBLE时，开始动画。

因此可用于RecyclerView.Adapter的ProgressViewHolder，用于加载更多指示器的实现。加载完成后，调用MaterialProgressView.setVisibility(View.INVISIBLE / View.GONE)；开始加载时，调用MaterialProgressView.setVisibility(View.VISIBLE)，因为状态已重置过，所以动画将从头播放、颜色也会取int[]中的第一种颜色，然后每转一圈换一种颜色。

Adapter和ProgressViewHolder示例详见sample.

## 引入

build.gradle中添加

    compile 'com.xdandroid:materialprogressview:+'
