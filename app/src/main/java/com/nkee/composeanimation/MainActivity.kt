package com.nkee.composeanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column (modifier = Modifier.fillMaxSize()) {
                var isVisible = remember { mutableStateOf(false) }
                var isRound = remember { mutableStateOf(false) }
                Button(onClick = {
                        isVisible.value = !isVisible.value
                        isRound.value = !isRound.value
                    }) {
                    Text("Toggle")
                }
                /*val transition = updateTransition(
                    targetState = isRound,
                    label = null
                )
                val borderRadius by transition.animateInt(
                    transitionSpec = { tween(2000) },
                    label = "borderRadius",
                    targetValueByState = {
                        if (it.value) 100 else 0
                    }
                )
                val color by transition.animateColor (
                    transitionSpec = { tween(1000) },
                    label = "color",
                    targetValueByState = {
                        if (it.value) Color.Green else Color.Red
                    }
                )
                Box(modifier = Modifier.size(200.dp)
                    .clip(RoundedCornerShape(borderRadius.dp))
                    .background(color = color)
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
                )*/
//                AnimatedVisibility(
//                    visible = isVisible.value,
//                    enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
//                    modifier = Modifier.fillMaxWidth()
//                        .weight(weight = 1f)
//                ) {
//                    Box(modifier = Modifier.background(Color.Red))
//                }

                AnimatedContent(
                    targetState = isVisible.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    content = {
                        if (it) {
                            Box(modifier = Modifier.background(Color.Green))
                        } else {
                            Box(modifier = Modifier.background(Color.Red))
                        }
                    },
                    transitionSpec = {
                        slideInHorizontally(
                            initialOffsetX = { (if (isVisible.value) it else -it) }
                        ) togetherWith slideOutHorizontally(
                            targetOffsetX = { (if (isVisible.value) -it else it) }
                        )
                    },
                    label = ""
                )

            }
        }
    }
}