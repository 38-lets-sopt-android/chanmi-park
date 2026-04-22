package com.example.letssopt.presentation.main.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.letssopt.R

enum class MainTab (
    @param:DrawableRes val iconRes: Int,
    @param:StringRes val labelRes: Int,
){
    HOME(
        iconRes = R.drawable.ic_nav_main_24,
        labelRes = R.string.bottom_main
    ),
    PURCHASE(
        iconRes = R.drawable.ic_nav_purchase_24,
        labelRes = R.string.bottom_puchase
    ),
    WEBTOON(
        iconRes = R.drawable.ic_nav_webtoon_24,
        labelRes = R.string.bottom_webtoon
    ),
    SEARCH(
        iconRes = R.drawable.ic_nav_search_24,
        labelRes = R.string.bottom_search
    ),
    COLLECTION(
        iconRes = R.drawable.ic_nav_collection_24,
        labelRes = R.string.bottom_collection
    ),
}