package com.hujiejeff.wanandroid.module.project.ui.project

import androidx.lifecycle.liveData
import com.hujiejeff.wanadnroid.module.base.base.BaseViewModel
import network.DataModel
import network.bean.TreeBean

class ProjectViewModel: BaseViewModel() {
    val treeListBean = liveData<List<TreeBean>> {
/*        emit(Result.loading())
        try {
            emit(Result.success(fetchUser()))
        } catch(ioException: Exception) {
            emit(Result.error(ioException))
        }*/

        val rep = DataModel.getProjectTree()
        if (rep.errorCode == 0 && rep.data != null) {
            emit(rep.data!!)
        }
    }
}