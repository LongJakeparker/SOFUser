package com.example.sofuser.templete

interface BaseListContract {

    interface View: Contract.View {
        /**
         * Add more data to recycler view
         */
        fun onBindData(data: List<Any>)

        fun notifyUpdateItem(data: Any, position: Int)

        fun notifyDeleteItem(position: Int)

//        /**
//         * click on item -> call to presenter to get data
//         * -> presenter call view to execute action when click item
//         * @param position
//         */
//        fun onItemClick(position: Int)
    }

    interface UserActionsListener<V : View>: Contract.UserActionsListener<V> {
        fun loadData(page: Int, isRefresh: Boolean, isLoadMore: Boolean)

        fun clickOnItem(position: Int, baseViewHolder: BaseViewHolder<Any>)
    }
}