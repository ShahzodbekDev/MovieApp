package shahzoddev.mobile.moviesapp.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val right: Int, private val bottom: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val spanCount = (parent.layoutManager as? GridLayoutManager)?.spanCount ?: 1


        val isLastColumn = (position + 1) % spanCount == 0
        if (!isLastColumn) {
            outRect.right = right

        }

        outRect.bottom = bottom
    }
}

