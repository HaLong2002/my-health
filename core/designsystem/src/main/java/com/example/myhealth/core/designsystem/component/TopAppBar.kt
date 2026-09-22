package com.example.myhealth.core.designsystem.component

//noinspection SuspiciousImport
import android.R
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.myhealth.core.designsystem.R as designSystemR
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.MyHealthTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyHealthTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int? = null,
    navigationIcon: ImageVector? = null,
    navigationIconContentDescription: String = "",
    actionIcon: ImageVector? = null,
    actionContentDescription: String = "",
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            titleRes?.let {
                Text(
                    text = stringResource(id = titleRes),
                    fontWeight = FontWeight.Bold
                )
            }
        },
        navigationIcon = {
            navigationIcon?.let {
                IconButton(
                    onClick = onNavigationClick
                ) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = navigationIconContentDescription,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        actions = {
            actionIcon?.let {
                IconButton(
                    onClick = onActionClick
                ) {
                    Icon(
                        imageVector = actionIcon,
                        contentDescription = actionContentDescription,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        colors = colors,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyHealthMediumTopAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    navigationIcon: ImageVector? = null,
    navigationIconContentDescription: String = "",
    actions: @Composable RowScope.() -> Unit,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    colors: TopAppBarColors? = null,
    onNavigationClick: () -> Unit = {},
) {
    MediumTopAppBar(
        modifier = modifier,
        title = title,
        navigationIcon = {
            navigationIcon?.let {
                IconButton(
                    onClick = onNavigationClick
                ) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = navigationIconContentDescription,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        actions = actions,
        colors = colors ?: TopAppBarDefaults.topAppBarColors(
            scrolledContainerColor = Color.Transparent,
            containerColor = Color.Transparent,
        ),
        scrollBehavior = scrollBehavior,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyHealthFormTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    navigationIconShown: Boolean = true,
    actionIconShown: Boolean = true,
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = titleRes),
                style = MaterialTheme.typography.headlineLarge
            )
        },
        navigationIcon = {
            if (navigationIconShown) {
                IconButton(
                    onClick = onNavigationClick
                ) {
                    Icon(
                        imageVector = MyHealthIcons.ArrowBack,
                        contentDescription = stringResource(id = designSystemR.string.back),
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        actions = {
            if (actionIconShown) {
                MHElevatedButton(
                    onClick = onActionClick,
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    ),
                ) {
                    Icon(
                        imageVector = MyHealthIcons.Check,
                        contentDescription = stringResource(id = designSystemR.string.save),
                    )
                }
            }
        },
    )
}

@Preview
@Composable
private fun TopAppBarPreview() {
    MyHealthTheme {
        MyHealthTopAppBar(
            titleRes = R.string.untitled,
            navigationIcon = MyHealthIcons.Search,
            navigationIconContentDescription = "Navigation icon",
            actionIcon = MyHealthIcons.MoreVert,
            actionContentDescription = "Action icon"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun MediumTopAppBarPreview() {
    MyHealthTheme {
        MyHealthMediumTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.untitled),
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = MyHealthIcons.ArrowBack,
            actions = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = MyHealthIcons.Add,
                        contentDescription = "Add icon",
                    )
                }
                MyHealthCheckbox(
                    checked = false,
                    onCheckedChange = {},
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                scrolledContainerColor = Color.Transparent,
            ),
        )
    }
}

@Preview
@Composable
private fun FormTopAppBarPreview() {
    MyHealthTheme {
        MyHealthFormTopAppBar(
            titleRes = R.string.untitled,
        )
    }
}