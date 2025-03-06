package shahzoddev.mobile.moviesapp.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val spaceSize: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val spanCount = (parent.layoutManager as? GridLayoutManager)?.spanCount ?: 1

        // Oxirgi ustun emasligiga ishonch hosil qilish
        val isLastColumn = (position + 1) % spanCount == 0
        if (!isLastColumn) {
            outRect.right = spaceSize // Faqat o‘ng tomonga margin qo‘yiladi
        }
    }
}

