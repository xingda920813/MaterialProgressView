import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

public class ProgressViewHolder extends RecyclerView.ViewHolder {

    protected MaterialProgressView mProgressBar;
    protected FrameLayout mFrameLayout;

    protected ProgressViewHolder(View v) {
        super(v);
        mFrameLayout = (FrameLayout) v;
        mProgressBar = (MaterialProgressView) mFrameLayout.findViewById(android.R.id.secondaryProgress);
    }
}
