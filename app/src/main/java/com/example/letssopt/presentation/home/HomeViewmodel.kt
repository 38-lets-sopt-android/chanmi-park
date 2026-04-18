package com.example.letssopt.presentation.home

import androidx.lifecycle.ViewModel
import com.example.letssopt.R

data class ContentItem(
    val id: Int,
    val title: String,
    val image: Int,
    val startTime: String? = null,
    val tag: String? = null,
    val hasAlarm: Boolean = false,
)

data class ContentSection(
    val sectionTitle: String,
    val contents: List<ContentItem>
)

class HomeViewModel: ViewModel() {
    private val sections = mutableListOf<ContentSection>()

    fun getSections() : List<ContentSection>{
        if(sections.isEmpty()){
            sections.addAll(createSections())
        }
        return sections
    }

    private fun createSections() = listOf(
        ContentSection(
            sectionTitle = "배너",
            contents = listOf(
                ContentItem(id = 7, title = "크라임씬", image = R.drawable.img_content_7),
                ContentItem(id = 8, title = "폭싹 속았수다", image = R.drawable.img_content_8),
                ContentItem(id = 9, title = "다만 악에서 구하소서", image = R.drawable.img_content_9),
            )
        ),
        ContentSection(
            sectionTitle = "왓고리즘",
            contents = listOf(
                ContentItem(id = 1, title = "이 사랑 통역 되나요", image = R.drawable.img_content_1),
                ContentItem(id = 2, title = "기묘한 이야기 시즌 5", image = R.drawable.img_content_2),
                ContentItem(id = 3, title = "프로젝트 헤일 메리", image = R.drawable.img_content_3),
                ContentItem(id = 4, title = "엑시트", image = R.drawable.img_content_4),
            )
        ),
        ContentSection(
            sectionTitle = "공개 예정 콘텐츠",
            contents = listOf(
                ContentItem(id = 1, title = "이 사랑 통역 되나요", image = R.drawable.img_content_1),
                ContentItem(id = 2, title = "기묘한 이야기 시즌 5", image = R.drawable.img_content_2),
                ContentItem(id = 3, title = "프로젝트 헤일 메리", image = R.drawable.img_content_3),
                ContentItem(id = 4, title = "엑시트", image = R.drawable.img_content_4),
            )
        ),

        ContentSection(
            sectionTitle = "왓챠 파티",
            contents = listOf(
                ContentItem(id = 5, title = "왕과 사는 남자", image = R.drawable.img_content_5, startTime = "오늘 21:13에 시작", tag = "#왕과사는 남자"),
                ContentItem(id = 6, title = "파묘", image = R.drawable.img_content_6, startTime = "오늘 22:22에 시작", tag = "#파묘"),
            )
        ),
    )
}