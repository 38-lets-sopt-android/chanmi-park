package com.example.letssopt.presentation.home.fake

import com.example.letssopt.R
import com.example.letssopt.domain.model.ContentModel
import com.example.letssopt.domain.model.PartyModel

object FakeHomeRepository{
    val newContents = listOf(
        ContentModel(id = 7, title = "크라임씬", image = R.drawable.img_content_7),
        ContentModel(id = 8, title = "폭싹 속았수다", image = R.drawable.img_content_8),
        ContentModel(id = 9, title = "다만 악에서 구하소서", image = R.drawable.img_content_9)
    )

    val whatgorismContents = listOf(
        ContentModel(id = 1, title = "이 사랑 통역 되나요", image = R.drawable.img_content_1),
        ContentModel(id = 2, title = "기묘한 이야기 시즌 5", image = R.drawable.img_content_2),
        ContentModel(id = 3, title = "프로젝트 헤일 메리", image = R.drawable.img_content_3),
        ContentModel(id = 4, title = "엑시트", image = R.drawable.img_content_4),
    )

    val upcomingContents = listOf(
        ContentModel(id = 1, title = "이 사랑 통역 되나요", image = R.drawable.img_content_1),
        ContentModel(id = 2, title = "기묘한 이야기 시즌 5", image = R.drawable.img_content_2),
        ContentModel(id = 3, title = "프로젝트 헤일 메리", image = R.drawable.img_content_3),
        ContentModel(id = 4, title = "엑시트", image = R.drawable.img_content_4),
    )

    val partyContents = listOf(
        PartyModel(id = 5, title = "왕과 사는 남자", image = R.drawable.img_content_5, startTime = "오늘 21:13에 시작", tag = "#왕과사는 남자"),
        PartyModel(id = 6, title = "파묘", image = R.drawable.img_content_6, startTime = "오늘 22:22에 시작", tag = "#파묘"),
    )
}