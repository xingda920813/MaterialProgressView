import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public abstract class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected ProgressViewHolder mProgressViewHolder;
    protected int[] mColorSchemeColors;
    protected boolean mIsLoading;
    protected int mThreshold = 7;

    public void setColorSchemeColors(int[] colors) {
        this.mColorSchemeColors = colors;
    }

    protected abstract void onLoadMore();
    protected abstract boolean hasMoreElements();
    protected abstract RecyclerView.ViewHolder onViewHolderCreate(ViewGroup parent, int viewType);
    protected abstract void onViewHolderBind(RecyclerView.ViewHolder holder, int position, int viewType);
    protected abstract int getViewType(int position);
    protected abstract int getCount();
    protected abstract int getItemSpanSizeForGrid(int position, int viewType);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType != 65535) {
            return onViewHolderCreate(parent, viewType);
        } else {
            FrameLayout frameLayout = new FrameLayout(parent.getContext());
            ViewGroup.MarginLayoutParams outerParams = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
            outerParams.setMargins(0, UIUtils.dp2px(parent.getContext(), 6), 0, UIUtils.dp2px(parent.getContext(), 6));
            frameLayout.setLayoutParams(outerParams);
            MaterialProgressView materialProgressView = new MaterialProgressView(parent.getContext());
            if (mColorSchemeColors == null || mColorSchemeColors.length <= 0) {
                int colorAccentId = parent.getContext().getResources().getIdentifier("colorAccent", "color", parent.getContext().getPackageName());
                int color;
                if (colorAccentId > 0) {
                    color = parent.getContext().getResources().getColor(colorAccentId);
                } else {
                    color = Color.parseColor("#FF4081");
                }
                materialProgressView.setColorSchemeColors(new int[]{color});
            } else {
                materialProgressView.setColorSchemeColors(mColorSchemeColors);
            }
            FrameLayout.LayoutParams innerParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            innerParams.gravity = Gravity.CENTER;
            materialProgressView.setLayoutParams(innerParams);
            materialProgressView.setId(android.R.id.secondaryProgress);
            frameLayout.addView(materialProgressView);
            mProgressViewHolder = new ProgressViewHolder(frameLayout);
            return mProgressViewHolder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == getCount() ? 65535 : getViewType(position);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (!mIsLoading && getCount() > 0 && position >= getCount() - mThreshold && hasMoreElements(null)) {
            mIsLoading = true;
            onLoadMore(null);
        }
        if (position == getCount()) {
            if (holder instanceof ProgressViewHolder) {
                ((ProgressViewHolder) holder).mProgressBar.setVisibility(mIsLoading ? View.VISIBLE : View.GONE);
            }
        } else {
            onViewHolderBind(holder, holder.getAdapterPosition(), getViewType(holder.getAdapterPosition()));
        }
    }

    @Override
    public int getItemCount() {
        return getCount() + 1;
    }

    public void setLoadingFalse() {
        mIsLoading = false;
        if (mUseMaterialProgress && mProgressViewHolder != null) {
            mProgressViewHolder.mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    protected abstract class ProgressSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

        protected int spanSize;

        protected ProgressSpanSizeLookup(int spanSize) {
            this.spanSize = spanSize;
        }

        @Override
        public int getSpanSize(int position) {
            int viewType = getItemViewType(position);
            if (viewType == 65535) {
                return spanSize;
            } else {
                int itemSpanSize = getItemSpanSize(position, viewType);
                if (itemSpanSize < 1) itemSpanSize = 1;
                if (itemSpanSize > spanSize) itemSpanSize = spanSize;
                return itemSpanSize;
            }
        }

        protected abstract int getItemSpanSize(int position, int viewType);
    }

    public ProgressSpanSizeLookup getSpanSizeLookup(int spanSize) {
        return new ProgressSpanSizeLookup(spanSize) {
            @Override
            protected int getItemSpanSize(int position, int viewType) {
                return getItemSpanSizeForGrid(position, viewType);
            }
        };
    }
}
