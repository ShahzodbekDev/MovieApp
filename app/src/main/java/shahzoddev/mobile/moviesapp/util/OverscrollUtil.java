package shahzoddev.mobile.moviesapp.util;

import android.widget.ScrollView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class OverscrollUtil {
    
    /**
     * RecyclerView uchun overscroll effektini yoqish.
     * @param recyclerView RecyclerView obyekti
     * @param isHorizontal True bo‘lsa, horizontal, aks holda vertical
     */
    public static void enableRecyclerViewOverscroll(RecyclerView recyclerView, boolean isHorizontal) {
        int orientation = isHorizontal ? OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL
                                       : OverScrollDecoratorHelper.ORIENTATION_VERTICAL;
        OverScrollDecoratorHelper.setUpOverScroll(recyclerView, orientation);
    }

    /**
     * ScrollView uchun overscroll effektini yoqish.
     * @param scrollView ScrollView obyekti
     */
    public static void enableScrollViewOverscroll(ScrollView scrollView) {
        OverScrollDecoratorHelper.setUpOverScroll(scrollView);
    }
}
