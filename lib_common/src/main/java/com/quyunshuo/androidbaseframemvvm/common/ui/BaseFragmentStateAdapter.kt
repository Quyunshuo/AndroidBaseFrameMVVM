package com.quyunshuo.androidbaseframemvvm.common.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlin.random.Random

/**
 * @author DBoy 2021/8/5 <p>
 * - 文件描述 : ViewPager2 FragmentAdapter封装
 * - 对于元数据[mData]的增加删除操作只能通过内部提供的4种方式进行操作:
 *     - [setNewData]
 *     - [addNewData]
 *     - [addData]
 *     - [removeData]
 * - 内部使用[DiffUtil]工具实现更新UI,不需调用[notifyDataSetChanged]等一系列方法。
 */
abstract class BaseFragmentStateAdapter<T> : FragmentStateAdapter {

    private val TAG = "FragmentAdapter"

    /**
     * 记录生成的Fragment id列表
     */
    private var mFragmentIdMap = mutableMapOf<T, Long>()

    /**
     * 需要生成页面的数据
     */
    var mData: MutableList<T>
        set(value) {
            field = value
            mFragmentIdMap.clear()
            createFragmentsIds(field)
        }

    constructor(fragment: Fragment, data: MutableList<T> = mutableListOf()) : super(fragment) {
        mData = data
    }

    constructor(activity: FragmentActivity, data: MutableList<T> = mutableListOf()) : super(
        activity
    ) {
        mData = data
    }

    /**
     * 获取需要创建几个Fragment
     */
    override fun getItemCount(): Int = mData.size

    /**
     * 创建Fragment
     */
    final override fun createFragment(position: Int): Fragment {
        return createFragment(mData[position], position)
    }

    /**
     * 创建fragment 传递数据
     */
    abstract fun createFragment(item: T, position: Int): Fragment

    /**
     * 获取fragment 对应 id
     */
    override fun getItemId(position: Int): Long {
        if (position >= mData.size) return RecyclerView.NO_ID
        return mFragmentIdMap[mData[position]] ?: return RecyclerView.NO_ID
    }

    /**
     * 判断是否包含这个id的数
     */
    override fun containsItem(itemId: Long): Boolean {
        return mFragmentIdMap.values.contains(itemId)
    }

    /**
     * 设置新数据
     */
    fun setNewData(data: MutableList<T> = mutableListOf()) {
        val oldData = copyData()
        mData = data
        diffNotifyDataSetChanged(oldData, mData)
    }

    /**
     * 累加新数据
     */
    fun addNewData(data: MutableList<T> = mutableListOf()) {
        val oldData = copyData()
        mData.addAll(data)
        //创建新的对应位置的id
        createFragmentsIds(data)
        diffNotifyDataSetChanged(oldData, mData)

    }

    /**
     * 添加数据
     */
    fun addData(data: T) {
        val oldData = copyData()
        mData.add(data)
        //随机一个id对应当前位置Fragment,两次随机确保同id率为最低概率
        mFragmentIdMap[data] = Random.nextLong() - Random.nextInt()
        diffNotifyDataSetChanged(oldData, mData)
    }

    /**
     * 移除某个数据
     */
    fun removeData(data: T): Boolean {
        val oldData = copyData()
        if (mData.remove(data)) {
            mFragmentIdMap.remove(data)
            diffNotifyDataSetChanged(oldData, mData)
            return true
        }
        return false
    }

    /**
     * 移除某个位置的数据
     */
    fun removeData(position: Int): Boolean {
        if (position < mData.size && position < mFragmentIdMap.size) {
            val oldData = copyData()
            val removeItem = mData.removeAt(position)
            mFragmentIdMap.remove(removeItem)
            diffNotifyDataSetChanged(oldData, mData)
            return true
        }
        return false
    }

    /**
     * 拷贝原数据
     */
    private fun copyData(): MutableList<T> {
        val oldData = mutableListOf<T>()
        oldData.addAll(mData)
        return oldData
    }

    /**
     * 使用diff工具更新UI,当前diff工具对比使用的方式是 [==] 所以如果需要精确对比不同item数据，可以重写[T]的[equals]方法.
     */
    private fun diffNotifyDataSetChanged(oldData: MutableList<T>, newData: MutableList<T>) {
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun getOldListSize(): Int = oldData.size

            override fun getNewListSize(): Int = newData.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldData[oldItemPosition] == newData[newItemPosition]

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldData[oldItemPosition] == newData[newItemPosition]

        }, true).dispatchUpdatesTo(this)
    }


    /**
     * 创建[mData]对应Fragment的id
     */
    private fun createFragmentsIds(data: MutableList<T>) {
        for (item in data) {
            mFragmentIdMap[item] = Random.nextLong() - Random.nextInt()
        }
    }

}
