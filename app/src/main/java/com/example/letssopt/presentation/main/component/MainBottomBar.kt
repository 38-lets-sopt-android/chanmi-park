package com.example.letssopt.presentation.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.theme.LetsTheme
import com.example.letssopt.core.util.noRippleClickable
import com.example.letssopt.presentation.main.component.MainTab

@Composable
fun MainBottomBar(
    tabs: List<MainTab>,
    currentTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            modifier
                .height(72.dp)
                .fillMaxWidth()
                .background(LetsTheme.colors.background)
                .padding(horizontal = 18.dp, vertical = 11.dp),
    ) {
        tabs.forEach { tab ->
            key(tab) {
                MainBottomBarItem(
                    tab = tab,
                    selected = (tab == currentTab),
                    onClick = { onTabSelected(tab) },
                )
            }
        }
    }

}

@Composable
private fun RowScope.MainBottomBarItem(
    tab: MainTab,
    selected: Boolean,
    onClick: () -> Unit = {},
){
    val label = stringResource(tab.labelRes)
    Column (
        modifier = Modifier
            .noRippleClickable(onClick = onClick)
            .weight(1f)
            .padding(top = 4.dp, bottom = 1.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Icon(
            imageVector = ImageVector.vectorResource(tab.iconRes),
            modifier = Modifier.size(24.dp),
            contentDescription = label,
            tint = if (selected) LetsTheme.colors.textPrimary else LetsTheme.colors.disabled,
        )

        Spacer(modifier = Modifier.height(7.dp))

        Text(
            text = label,
            color = if(selected) LetsTheme.colors.textPrimary else LetsTheme.colors.disabled,
            style = LetsTheme.typography.subtitle.body_12
        )
    }
}

@Preview
@Composable
private fun PreviewMainBottomBar(){
    LetsTheme{
        MainBottomBar(
            tabs = MainTab.values().toList(),
            currentTab = MainTab.HOME,
            onTabSelected = {},
        )
    }
}