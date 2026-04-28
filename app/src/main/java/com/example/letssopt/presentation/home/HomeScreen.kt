package com.example.letssopt.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.core.designsystem.theme.LetsTheme
import com.example.letssopt.presentation.home.component.HomeTopAppBar
import com.example.letssopt.presentation.home.component.NewContentSection
import com.example.letssopt.presentation.home.component.PartySection
import com.example.letssopt.presentation.home.component.UpcomingSection
import com.example.letssopt.presentation.home.component.WhatgorismSection

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
){
    val sections = viewModel.getSections()

    Scaffold(
        modifier = modifier,
        containerColor = LetsTheme.colors.background,
        topBar = {HomeTopAppBar()}
    ) { innerPadding ->
        HomeScreen(
            newContents = sections.find { it.sectionTitle == "배너" }?.contents ?: emptyList(),
            whatgorismContents = sections.find { it.sectionTitle == "왓고리즘" }?.contents ?: emptyList(),
            upcomingContents = sections.find { it.sectionTitle == "공개 예정 콘텐츠" }?.contents ?: emptyList(),
            partyContents = sections.find { it.sectionTitle == "왓챠 파티" }?.contents ?: emptyList(),
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
private fun HomeScreen(
    newContents: List<ContentItem>,
    whatgorismContents: List<ContentItem>,
    upcomingContents: List<ContentItem>,
    partyContents: List<ContentItem>,
    modifier: Modifier = Modifier,
){
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LetsTheme.colors.background)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(26.dp),
        ) {
        NewContentSection(contents = newContents)

        WhatgorismSection(contents = whatgorismContents)

        UpcomingSection(contents = upcomingContents)

        PartySection(contents = partyContents)
    }
}

