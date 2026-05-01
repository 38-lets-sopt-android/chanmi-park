package com.example.letssopt.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.core.designsystem.theme.LetsTheme
import com.example.letssopt.presentation.home.component.HomeTopAppBar
import com.example.letssopt.presentation.home.component.NewContentSection
import com.example.letssopt.presentation.home.component.PartySection
import com.example.letssopt.presentation.home.component.UpcomingSection
import com.example.letssopt.presentation.home.component.WhatgorismSection
import com.example.letssopt.presentation.home.uistate.HomeUiState

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
){
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier,
        containerColor = LetsTheme.colors.background,
        topBar = {HomeTopAppBar()}
    ) { innerPadding ->
        HomeScreen(
            uiState = uiState,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
){
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState(pageCount = { Int.MAX_VALUE })
    val newContents = uiState.newContents
    val totalPage = newContents.size

    LaunchedEffect(totalPage) {
        var initialPage = Int.MAX_VALUE / 2

        while (initialPage % totalPage != 0) {
            initialPage++
        }
        pagerState.scrollToPage(initialPage)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LetsTheme.colors.background)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(26.dp),
        ) {
        NewContentSection(
            contents = uiState.newContents,
            pagerState = pagerState
        )

        WhatgorismSection(contents = uiState.whatgorismContents)

        UpcomingSection(contents = uiState.upcomingContents)

        PartySection(contents = uiState.partyContents)
    }
}

