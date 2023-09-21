package com.rondi.core.presentation

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val verticalMargin: Int, private val horizontalMargin: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            val position = parent.getChildAdapterPosition(view)
            val isFirstItem = position == 0
            val isLastItem = position == state.itemCount - 1

            Log.d("MarginItemDecoration", "total child: $state.itemCount")

            top = if (isFirstItem) 0 else verticalMargin
            bottom = if (isLastItem) 0 else verticalMargin
            left = if (isFirstItem) 0 else horizontalMargin
            right = if (isLastItem) 0 else horizontalMargin
        }
    }
}