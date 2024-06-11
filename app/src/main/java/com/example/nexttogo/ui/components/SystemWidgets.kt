package com.example.nexttogo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttogo.constants.RaceCategory
import com.example.nexttogo.data.entities.RaceItemData
import com.example.nexttogo.data.entities.RaceSummary
import com.example.nexttogo.ui.theme.Background
import com.example.nexttogo.ui.theme.LightGrey
import com.example.nexttogo.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar() {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Timer, "")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Next To Go", fontSize = 18.sp)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Background,
            titleContentColor = Color.White
        )
    )
}


@Composable
fun ToggleButtonSegmented(
    items: List<RaceCategory>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.wrapContentSize(),
        contentPadding = PaddingValues(horizontal = 3.dp)
    ) {
        itemsIndexed(items) { index, item ->
            val isSelected = index == selectedIndex
            Button(
                onClick = { onItemSelected(index) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                    contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (item.icon != -1) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = "",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    Text(text = item.title, fontSize = 16.sp)
                }
            }
        }
    }
}


@Composable
fun RaceDetailsItem(
    itemData: RaceSummary,
    refreshing: () -> Unit
) {
    var mTimer by remember { mutableStateOf(itemData.advertisedStart.seconds - Instant.now().epochSecond) }

    LaunchedEffect(itemData.advertisedStart.seconds) {
        //To delegate time change and updating remaining text
        while (true) {
            val currentTime = Instant.now().epochSecond
            mTimer = itemData.advertisedStart.seconds - currentTime
            if (mTimer <= -59) {
                withContext(Dispatchers.Main) {
                    refreshing()
                }
                break
            }
            delay(1000)
        }
    }
    DisposableEffect(Unit) {
        onDispose {
            mTimer = 0
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(LightGrey),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = RaceCategory.getIconByCategoryId(itemData.categoryId)),
            contentDescription = "",
            modifier = Modifier
                .padding(10.dp)
                .size(46.dp)
        )
        Text(text = itemData.meetingName, color = Color.Black, modifier = Modifier.weight(1f))
        Text(text = itemData.raceNumber.toString(),color = Color.Black, modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .padding(end = 10.dp)
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {

            Text(
                text = Utils.convertTimerFormat(mTimer),
                color = if (mTimer <= 0) Color.Red else Color.Black,
                fontSize = 16.sp,
            )
        }
    }

}


@Preview
@Composable
fun showTargetView() {

}