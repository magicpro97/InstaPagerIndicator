package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.math.absoluteValue
import kotlin.math.sign

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HorizontalPagerIndicator(
                        pagerState = PagerState(currentPage = 5),
                        pageCount = 10
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    pageCount: Int = pagerState.pageCount,
    pageIndexMapping: (Int) -> Int = { it },
    activeColor: Color = Color.Blue,
    inactiveColor: Color = Color.Gray,
    indicatorWidth: Dp = 8.dp,
    indicatorHeight: Dp = indicatorWidth,
    spacing: Dp = indicatorWidth,
    indicatorShape: Shape = CircleShape,
) {

    val indicatorWidthPx = LocalDensity.current.run { indicatorWidth.roundToPx() }
    val spacingPx = LocalDensity.current.run { spacing.roundToPx() }
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
                before = min(max(selectedIndex - 2, 0), 2)
                after = max(min(pageCount - selectedIndex - 1, 2), 0)
                when (before) {
                    1 -> Box(mediumIndicatorModifier)
                    2 -> {
                        Box(smallIndicatorModifier)
                        Box(mediumIndicatorModifier)
                    }
                }

                val dotCount = if (after > 0) before + 3 + 2 - after else before + 3
                repeat(3) {
                    if (dotCount == selectedIndex) {
                        Box(selectedIndicatorModifier)
                    } else {
                        Box(bigIndicatorModifier)
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
//        Box(
//            Modifier
//                .offset {
//                    val position = pageIndexMapping(pagerState.currentPage)
//                    val offset = pagerState.currentPageOffset
//                    val next =
//                        pageIndexMapping(pagerState.currentPage + offset.sign.toInt())
//                    val scrollPosition =
//                        ((next - position) * offset.absoluteValue + position).coerceIn(
//                            0f,
//                            (pageCount - 1)
//                                .coerceAtLeast(0)
//                                .toFloat()
//                        )
//                    IntOffset(
//                        x = ((spacingPx + indicatorWidthPx) * scrollPosition).toInt(),
//                        y = 0
//                    )
//                }
//                .size(width = indicatorWidth, height = indicatorHeight)
//                .then(
//                    if (pageCount > 0) Modifier.background(
//                        color = activeColor,
//                        shape = indicatorShape,
//                    ) else Modifier
//                )
//        )
    }
}


@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewIndicator2() {
    HorizontalPagerIndicator(
        pagerState = PagerState(currentPage = 2),
        pageCount = 7
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewIndicator3() {
    HorizontalPagerIndicator(
        pagerState = PagerState(currentPage = 3),
        pageCount = 7
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewIndicator4() {
    HorizontalPagerIndicator(
        pagerState = PagerState(currentPage = 4),
        pageCount = 7
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewIndicator5() {
    HorizontalPagerIndicator(
        pagerState = PagerState(currentPage = 5),
        pageCount = 7
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewIndicator6() {
    HorizontalPagerIndicator(
        pagerState = PagerState(currentPage = 6),
        pageCount = 7
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewIndicator7() {
    HorizontalPagerIndicator(
        pagerState = PagerState(currentPage = 7),
        pageCount = 7
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewIndicator8() {
    HorizontalPagerIndicator(
        pagerState = PagerState(currentPage = 8),
        pageCount = 8
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewIndicator9() {
    HorizontalPagerIndicator(
        pagerState = PagerState(currentPage = 9),
        pageCount = 9
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewIndicator10() {
    HorizontalPagerIndicator(
        pagerState = PagerState(currentPage = 10),
        pageCount = 10
    )
}