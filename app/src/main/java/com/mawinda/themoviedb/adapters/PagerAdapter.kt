package com.mawinda.themoviedb.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PagingAdapter<T : Any, R : ViewBinding>(
    private val comparator: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, PagingAdapter<T, R>.LazyViewHolder>(comparator) {

    private var originalData = listOf<T>()
    private var currentData = originalData

    private var mCreate: ((parent: ViewGroup) -> R)? = null
    private var mBind: (R.(item: T) -> Unit)? = null
    private var mBindPosition: (R.(item: T, position: Int) -> Unit)? = null
    private var mBindSelected: (R.(item: T, selected: Boolean) -> Unit)? = null

    private var onItemClicked: ((item: T) -> Unit)? = null
    private var onItemLongClicked: ((item: T) -> Boolean)? = null
    private var onItemsSelected: ((items: List<T?>) -> Unit)? = null
    private var onItemSelected: ((item: T?) -> Unit)? = null
    private var onItemFilter: ((item: T) -> Boolean)? = null

    private val selectedItems = mutableListOf<Long>()

    inner class LazyViewHolder(
        val context: Context,
        val binding: R?
    ) : RecyclerView.ViewHolder(binding?.root ?: View(context)) {

        init {

            binding?.let {
                it.root.setOnClickListener {

                    // ON CLICK
                    onItemClicked?.invoke(getItem(absoluteAdapterPosition) as T)

                    // CHECK SELECTIONS AND TOGGLE
                    if (onItemSelected != null || onItemsSelected != null)
                        when (selectedItems.contains(absoluteAdapterPosition.toLong())) {
                            true -> removeSelection(absoluteAdapterPosition)
                            false -> addSelection(absoluteAdapterPosition)
                        }

                    // ON SINGLE ITEM SELECTED
                    onItemSelected?.let { mSelect ->
                        val item = selectedItems.map { position -> getItem(position.toInt()) }
                            .firstOrNull()
                        mSelect.invoke(item)
                    }

                    // ON MULTIPLE ITEM SELECTED
                    onItemsSelected?.let { mSelects ->
                        val list = selectedItems.map { position -> getItem(position.toInt()) }
                        mSelects.invoke(list)
                    }

                }
                it.root.setOnLongClickListener {
                    getItem(absoluteAdapterPosition)?.let { item ->
                        onItemLongClicked?.invoke(item)
                    } ?: false
                }
            }

        }

        fun bindHolder(item: T) {
            mBind?.let { block -> binding?.block(item) }
            mBindPosition?.let { block -> binding?.block(item, absoluteAdapterPosition) }
        }

        fun bindHolder(item: T, selected: Boolean) {
            mBindSelected?.let { block -> binding?.block(item, selected) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LazyViewHolder {
        val binding = mCreate?.invoke(parent)
        return LazyViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: LazyViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            if (onItemSelected != null || onItemsSelected != null)
                holder.bindHolder(it, selectedItems.contains(position.toLong()))
            else
                holder.bindHolder(it)
        }

    }

    /**
     * CREATE VIEWS
     */

    fun onCreate(create: (parent: ViewGroup) -> R) = apply {
        mCreate = create
    }

    /**
     * INVOKE BINDINGS
     */

    fun onBind(bind: R.(item: T) -> Unit) = apply {
        mBind = bind
    }

    @JvmName("onBindWithPosition")
    fun onBind(block: R.(item: T, position: Int) -> Unit) = apply {
        mBindPosition = block
    }

    fun onBind(bind: R.(item: T, selected: Boolean) -> Unit) = apply {
        mBindSelected = bind
    }

    /**
     * ADAPTER SELECTIONS, CLICKS, SWIPES
     */

    fun onItemClicked(block: ((item: T) -> Unit)? = null) = apply {
        onItemClicked = block
    }

    fun onItemLongClicked(block: ((item: T) -> Boolean)? = null) = apply {
        onItemLongClicked = block
    }

    fun onItemSelected(block: ((item: T?) -> Unit)? = null) = apply {
        onItemSelected = block
    }

    fun onItemsSelected(block: ((items: List<T?>) -> Unit)? = null) = apply {
        onItemsSelected = block
    }


    fun onItemFilter(filtered: Boolean = true, predicate: (T) -> Boolean) = apply {
        onItemFilter = when (filtered) {
            true -> {
                originalData = getMutableList()
                currentData = originalData.filter(predicate)
                predicate
            }
            false -> {
                currentData = originalData
                null
            }
        }

        updateList(currentData)
        notifyDataSetChanged()

    }

    /**
     * CRUD functions for the adapter
     */

    fun add(item: T) {
        val list: MutableList<T> = getMutableList()
        list.add(item)
        updateList(list)
    }

    fun add(item: T, index: Int) {
        val list: MutableList<T> = getMutableList()
        list.add(index = index, element = item)
        updateList(list)
    }

    fun update(item: T, index: Int) {
        val list: MutableList<T> = getMutableList()
        list.set(index = index, element = item)
        updateList(list)
    }

    fun remove(item: T) {
        val list = getMutableList()
        if (!list.contains(item)) return
        list.remove(item)
        updateList(list)
    }

    fun remove(index: Int) {
        val list = getMutableList()
        if (index >= list.size) return
        list.removeAt(index)
        updateList(list)
    }

    private fun getMutableList(): MutableList<T> = this.snapshot().filterNotNull().toMutableList()

    private fun updatedPagedData(list: List<T>): PagingData<T> = PagingData.from(list)

    private fun addSelection(position: Int) {
        if (selectedItems.contains(position.toLong())) return
        val previousPosition = selectedItems.firstOrNull()
        if (onItemSelected != null) selectedItems.clear()
        selectedItems.add(position.toLong())
        previousPosition?.toInt()?.let { notifyItemChanged(it) }
        notifyItemChanged(position)
    }

    private fun removeSelection(position: Int) {
        if (!selectedItems.contains(position.toLong())) return
        selectedItems.remove(position.toLong())
        notifyItemChanged(position)
    }

    private fun updateList(list: List<T>) = CoroutineScope(Dispatchers.Main).launch {
        submitData(updatedPagedData(list))
    }

}

