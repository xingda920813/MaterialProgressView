# MaterialProgressView
 
 <a href="https://996.icu"><img src="https://img.shields.io/badge/link-996.icu-red.svg"></a>

[中文 README](https://github.com/xingda920813/MaterialProgressView/blob/master/README_zh.md)

### A ProgressView with SwipeRefreshLayout style.

#### Modified form support-core-ui-25.1.1

![https://raw.githubusercontent.com/xingda920813/MaterialProgressView/master/video.gif](https://raw.githubusercontent.com/xingda920813/MaterialProgressView/master/video.gif)

#### Supports wrap\_content and LayoutParams.WRAP_CONTENT;

#### Customizable indicator color, background color and alpha;

```
//Set indicator color to blue -> red -> blue -> red -> ...
mProgressView.setColorSchemeColors(new int[]{getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent)});
//Set background color to lighter gray
mProgressView.setProgressBackgroundColor(Color.parseColor("#FAFAFA"));
//Set alpha (0-255, default: 255 opaque)
mProgressView.setColorViewAlpha(255);
```

#### setVisibility has the same behavior as ProgressBar：When View.INVISIBLE or View.GONE is passed to setVisibility(int visibility), animation will stop and the status of progress view will be reset (indicator color and spinning degree); When View.VISIBLE is passed to the method, animation will start.

So it can be used as a ProgressViewHolder for RecyclerView.Adapter, to implement a load-more indicator. After you loaded more data to your RecyclerView, invoke MaterialProgressView.setVisibility(View.INVISIBLE / View.GONE); When you start to load more data from network, invoke MaterialProgressView.setVisibility(View.VISIBLE).

The status of progress view was reset when you last time invoke MaterialProgressView.setVisibility(View.INVISIBLE / View.GONE), so this time the call to MaterialProgressView.setVisibility(View.VISIBLE) will cause animation to start from beginning and indicator color to iterate from the int[] you have passed to before, starts from the first color in the int[].

For usage of Adapter and ProgressViewHolder, please refer to the sample.

## Import

In your build.gradle, add:

    compile 'com.xdandroid:materialprogressview:+'
