package export

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.hujiejeff.wanadnroid.module.base.constans.RouteMap

object ModuleService {
    fun getMainFragment(): Fragment = ARouter.getInstance().build(RouteMap.Main.HOME_FRAGMENT).navigation() as Fragment
    fun getProjectFragment(): Fragment = ARouter.getInstance().build(RouteMap.Project.HOME_FRAGMENT).navigation() as Fragment
    fun getTreeFragment(): Fragment = ARouter.getInstance().build(RouteMap.Tree.HOME_FRAGMENT).navigation() as Fragment
    fun getMineFragment(): Fragment = ARouter.getInstance().build(RouteMap.User.HOME_FRAGMENT).navigation() as Fragment
}