package com.example.threedapp.util.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


data class Stars(
    val oneStarIcon:ImageVector,
    val twoStarsIcon:ImageVector,
    val threeStarsIcon:ImageVector,
    val fourStarsIcon:ImageVector,
    val fiveStarsIcon:ImageVector,
)

@Composable
fun InAppReview(
    iconReview: ImageVector,
    iconReviewFilled: ImageVector,
    ratedIn: MutableState<Boolean> = mutableStateOf(false),
    size: Dp = 10.dp,
    colorIcon: Color,
    modifier: Modifier = Modifier,
    rowModifier: Modifier = Modifier,
    oneStar:()->Unit = {},
    twoStars:()->Unit = {},
    threeStars:()->Unit = {},
    fourStars:()->Unit = {},
    fiveStars:()->Unit = {},
): Boolean {
    val rated = remember {
        ratedIn
    }
    val _size = remember {
       mutableStateOf(size)
    }
    val stars = object {
        val localRatedState = remember {
            mutableStateOf(false)
        }
        val icons = remember {
            mutableStateOf(Stars(iconReview,
                iconReview,
                iconReview,
                iconReview,
                iconReview,))
        }
        val timeAnimation = 100L

        fun oneStar() {

            icons.value = Stars(iconReviewFilled,
                iconReview,
                iconReview,
                iconReview,
                iconReview,)
            localRatedState.value = true
        }

        fun twoStars() {
            icons.value = Stars(iconReviewFilled,
                iconReviewFilled,
                iconReview,
                iconReview,
                iconReview,)
            localRatedState.value = true

        }

        fun threeStars() {
            icons.value = Stars(iconReviewFilled,
                iconReviewFilled,
                iconReviewFilled,
                iconReview,
                iconReview,)
            localRatedState.value = true

        }

        fun fourStars() {
            icons.value = Stars(iconReviewFilled,
                iconReviewFilled,
                iconReviewFilled,
                iconReviewFilled,
                iconReview,)
            localRatedState.value = true

        }

        fun fiveStars() {
            icons.value = Stars(iconReviewFilled,
                iconReviewFilled,
                iconReviewFilled,
                iconReviewFilled,
                iconReviewFilled,)
            localRatedState.value = true

        }
    }
    Row(horizontalArrangement = Arrangement.Center, modifier = rowModifier) {
        val review = "review"
        Icon(
            modifier = modifier.clickable {
                stars.oneStar()

            }.size(_size.value),
            imageVector = stars.icons.value.oneStarIcon,
            contentDescription = review,
            tint = colorIcon,
        )
        Icon(
            modifier = modifier.clickable {

                stars.twoStars()

            }.size(_size.value),
            imageVector = stars.icons.value.twoStarsIcon,
            contentDescription = review,
            tint = colorIcon,
        )
        Icon(
            modifier = modifier.clickable {
                stars.threeStars()

            }.size(_size.value),
            imageVector = stars.icons.value.threeStarsIcon,
            contentDescription = review,
            tint = colorIcon,
        )
        Icon(
            modifier = modifier.clickable {
                stars.fourStars()
            }.size(_size.value),
            imageVector = stars.icons.value.fourStarsIcon,
            contentDescription = review,
            tint = colorIcon,
        )
        Icon(
            modifier = modifier.clickable {
                stars.fiveStars()
            }.size(_size.value),
            imageVector = stars.icons.value.fiveStarsIcon,
            contentDescription = review,
            tint = colorIcon,
        )
    }
    return stars.localRatedState.value
}