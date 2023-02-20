package com.example.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun InstaPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    pageCount: Int = pagerState.pageCount,
    activeColor: Color = Color.Blue,
    inactiveColor: Color = Color.Gray,
    indicatorWidth: Dp = 8.dp,
    indicatorHeight: Dp = indicatorWidth,
    spacing: Dp = indicatorWidth,
    indicatorShape: Shape = CircleShape,
) {
    val selectedIndex = pagerState.currentPage - 1
    var before = 0
    var after = 0
    var totalVisible = 1
    Box(
        modifier =
        modifier, contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val smallIndicatorModifier =
                Modifier
                    .size(width = indicatorWidth / 3, height = indicatorHeight / 3)
                    .background(color = inactiveColor, shape = indicatorShape)
            val mediumIndicatorModifier =
                Modifier
                    .size(width = indicatorWidth / 2, height = indicatorHeight / 2)
                    .background(color = inactiveColor, shape = indicatorShape)
            val bigIndicatorModifier =
                Modifier
                    .size(width = indicatorWidth, height = indicatorHeight)
                    .background(color = inactiveColor, shape = indicatorShape)
            val selectedIndicatorModifier =
                Modifier
                    .size(width = indicatorWidth, height = indicatorHeight)
                    .background(color = activeColor, shape = indicatorShape)


            if (pageCount < 4) {
                repeat(pageCount) {
                    if (it == selectedIndex) {
                        Box(selectedIndicatorModifier)
                    } else {
                        Box(bigIndicatorModifier)
                    }
                }
            } else {
                before = Integer.min(Integer.max(selectedIndex - 2, 0), 2)
                after = Integer.max(Integer.min(pageCount - selectedIndex - 1, 2), 0)
                when (before) {
                    1 -> Box(mediumIndicatorModifier)
                    2 -> {
                        Box(smallIndicatorModifier)
                        Box(mediumIndicatorModifier)
                    }
                }

                val dotCount = before + 3 + after
                repeat(3) {
                    if (dotCount < 6 && selectedIndex < 3) {
                        if (it == selectedIndex) {
                            Box(selectedIndicatorModifier)
                        } else {
                            Box(bigIndicatorModifier)
                        }
                    } else {
                        if (it == 2) {
                            Box(selectedIndicatorModifier)
                        } else {
                            Box(bigIndicatorModifier)
                        }
                    }
                }
                when (after) {
                    1 -> Box(mediumIndicatorModifier)
                    2 -> {
                        Box(mediumIndicatorModifier)
                        Box(smallIndicatorModifier)
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewIndicator2() {
    InstaPagerIndicator(
        pagerState = PagerState(currentPage = 2),
        pageCount = 7
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewIndicator3() {
    InstaPagerIndicator(
        pagerState = PagerState(currentPage = 3),
        pageCount = 7
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewIndicator4() {
    InstaPagerIndicator(
        pagerState = PagerState(currentPage = 4),
        pageCount = 7
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewIndicator5() {
    InstaPagerIndicator(
        pagerState = PagerState(currentPage = 5),
        pageCount = 7
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewIndicator6() {
    InstaPagerIndicator(
        pagerState = PagerState(currentPage = 6),
        pageCount = 7
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewIndicator7() {
    InstaPagerIndicator(
        pagerState = PagerState(currentPage = 7),
        pageCount = 7
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewIndicator8() {
    InstaPagerIndicator(
        pagerState = PagerState(currentPage = 8),
        pageCount = 8
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewIndicator9() {
    InstaPagerIndicator(
        pagerState = PagerState(currentPage = 9),
        pageCount = 9
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewIndicator10() {
    InstaPagerIndicator(
        pagerState = PagerState(currentPage = 3),
        pageCount = 6
    )
}