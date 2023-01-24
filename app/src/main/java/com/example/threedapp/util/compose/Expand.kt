package com.example.threedapp.util.compose

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.threedapp.data.models.StateExpandView
import com.example.threedapp.ui.theme.myColors

@Composable
fun ExpandView(backgroundColor: Color = MaterialTheme.myColors.invisible,content:@Composable ColumnScope.() -> Unit,): StateExpandView {
    var stateExpandView by remember {
        mutableStateOf(StateExpandView.FIRSTTIME)
    }
    AnimatedVisibility(
        visible = stateExpandView != StateExpandView.CLICKED,
        enter = expandIn(expandFrom = Alignment.BottomStart) + fadeIn(),
        exit = shrinkOut(shrinkTowards = Alignment.BottomStart) + fadeOut()
    ) {
        Icon(
            imageVector = Icons.Filled.ExpandMore,
            contentDescription = "expand less",
            modifier = Modifier.clickable {
                stateExpandView = StateExpandView.CLICKED
            }
        )
    }
    AnimatedVisibility(
        visible = stateExpandView == StateExpandView.CLICKED,
        enter = expandIn(expandFrom = Alignment.TopEnd) + fadeIn(),
        exit = shrinkOut(shrinkTowards = Alignment.TopEnd) + fadeOut()
    ) {
        Card(modifier = Modifier.background(backgroundColor)) {
            Box(modifier = Modifier.background(backgroundColor)) {
                Icon(imageVector = Icons.Filled.ExpandLess,
                    contentDescription = "expand less icon",
                    modifier = Modifier.clickable {
                        stateExpandView = StateExpandView.UNCLICKED
                    }.align(Alignment.TopEnd))
                Column(content = content, modifier = Modifier.background(backgroundColor))
            }
        }
    }

    return stateExpandView
}