package network.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TreeBean(
    val children: List<TreeBean>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
): Parcelable