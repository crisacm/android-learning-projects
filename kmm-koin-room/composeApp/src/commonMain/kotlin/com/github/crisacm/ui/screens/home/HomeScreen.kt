package com.github.crisacm.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.crisacm.domain.model.Task
import com.github.crisacm.ui.components.SearchBar
import com.github.crisacm.ui.components.TagItem
import com.github.crisacm.ui.components.TaskItem
import com.github.crisacm.ui.theme.BlueLightBackground
import com.github.crisacm.ui.theme.BlueLightBorder
import com.github.crisacm.utils.DateUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

@Composable
fun HomeScreen(
  state: HomeContracts.State,
  onEvent: (HomeContracts.Events) -> Unit,
  effect: Flow<HomeContracts.Effect>?
) {
  val tag = remember { mutableStateOf(HomeScreenTags.ALL) }
  val query = remember { mutableStateOf("") }
  val filteredTasks = if (query.value.isEmpty()) {
    state.tasks.filter {
      when (tag.value) {
        HomeScreenTags.ALL -> true
        HomeScreenTags.OPEN -> !it.isCompleted
        HomeScreenTags.CLOSED -> it.isCompleted
      }
    }
  } else {
    state.tasks.filter {
      it.title.contains(query.value, ignoreCase = true) &&
          when (tag.value) {
            HomeScreenTags.ALL -> true
            HomeScreenTags.OPEN -> !it.isCompleted
            HomeScreenTags.CLOSED -> it.isCompleted
          }
    }
  }

  val snackbarHostState = remember { SnackbarHostState() }
  val skipHalfExpanded by remember { mutableStateOf(false) }
  val modalBottomSheetState = rememberModalBottomSheetState(
    initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = skipHalfExpanded
  )
  val scope = rememberCoroutineScope()

  fun updateState() {
    scope.launch {
      if (modalBottomSheetState.isVisible) {
        modalBottomSheetState.hide()
      } else {
        modalBottomSheetState.show()
      }
    }
  }

  LaunchedEffect(Unit) {
    effect?.collect { effect ->
      when (effect) {
        is HomeContracts.Effect.ShowMsg -> {
          snackbarHostState.showSnackbar(effect.msg)
        }
      }
    }
  }

  ModalBottomSheetLayout(
    sheetShape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp),
    sheetState = modalBottomSheetState, sheetContent = {
      SheetContent {
        onEvent(HomeContracts.Events.OnTaskAdded(it))
        updateState()
      }
    }) {
    Scaffold(
      snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
      Column(
        modifier =
          Modifier
            .padding(innerPadding)
            .fillMaxSize()
      ) {
        SearchBar(
          modifier = Modifier.padding(24.dp),
          searchBy = query.value,
          onSearch = { query.value = it }
        )

        Row(
          modifier = Modifier.padding(start = 24.dp, end = 24.dp),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Column {
            Text(
              text = "Today's Tasks",
              fontSize = 24.sp,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = DateUtils.convertLongToDate(Clock.System.now().toEpochMilliseconds()),
              fontSize = 16.sp,
              color = Color.Gray
            )
          }
          Spacer(modifier = Modifier.weight(1f))
          Card(
            shape = RoundedCornerShape(12.dp),
            backgroundColor = BlueLightBackground,
            modifier = Modifier.clickable { updateState() },
            border = BorderStroke(1.dp, BlueLightBorder)
          ) {
            Row(
              modifier = Modifier.padding(12.dp, 8.dp, 12.dp, 8.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(Icons.Default.Add, "Add Task")
              Text(
                text = "New Task",
                modifier = Modifier.padding(start = 8.dp)
              )
            }
          }
        }

        Row(
          modifier =
            Modifier
              .padding(start = 12.dp, top = 12.dp, end = 12.dp)
              .fillMaxWidth(),
        ) {
          TagItem(
            name = "All",
            size = state.tasks.size,
            isSelected = tag.value == HomeScreenTags.ALL,
            onSelected = { tag.value = HomeScreenTags.ALL }
          )
          TagItem(
            name = "Open",
            size = state.tasks.filter { !it.isCompleted }.size,
            isSelected = tag.value == HomeScreenTags.OPEN,
            onSelected = { tag.value = HomeScreenTags.OPEN }
          )
          TagItem(
            name = "Closed",
            size = state.tasks.filter { it.isCompleted }.size,
            isSelected = tag.value == HomeScreenTags.CLOSED,
            onSelected = { tag.value = HomeScreenTags.CLOSED }
          )
        }

        if (state.isLoading) {
          Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
          ) {
            CircularProgressIndicator(
              modifier = Modifier.size(24.dp),
              strokeWidth = 2.dp,
            )
          }
        } else {
          if (filteredTasks.isNotEmpty()) {
            LazyColumn(
              modifier = Modifier.padding(12.dp)
            ) {
              items(filteredTasks) { task ->
                AnimatedVisibility(
                  visible = true,
                  enter = fadeIn(animationSpec = tween(durationMillis = 500)),
                  exit = fadeOut(animationSpec = tween(durationMillis = 500))
                ) {
                  TaskItem(
                    modifier = Modifier.padding(12.dp),
                    task = task,
                    onCheckChange = { t, isCompleted ->
                      onEvent(
                        HomeContracts.Events.OnTaskUpdated(
                          t.copy(isCompleted = isCompleted)
                        )
                      )
                    },
                    onDelete = {
                      onEvent(HomeContracts.Events.OnTaskDeleted(it))
                    }
                  )
                }
              }
            }
          } else {
            Box(
              modifier = Modifier.fillMaxSize(),
              contentAlignment = Alignment.Center,
            ) {
              Text(
                text = "No tasks found",
                color = Color.Gray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
              )
            }
          }
        }
      }

      onEvent(HomeContracts.Events.OnLoad)
    }
  }
}

@Composable
fun SheetContent(
  modifier: Modifier = Modifier,
  onSave: (Task) -> Unit,
) {
  val taskName = remember { mutableStateOf("") }
  val actualTime = Clock.System.now().toEpochMilliseconds()
  Column(
    modifier =
      modifier
        .fillMaxWidth()
        .padding(24.dp)
        .imePadding()
  ) {
    Text(
      modifier = Modifier.fillMaxWidth(),
      text = "Add a new task",
      fontWeight = FontWeight.Bold,
      fontSize = 20.sp,
    )
    OutlinedTextField(
      modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 16.dp),
      value = taskName.value,
      onValueChange = { taskName.value = it },
      minLines = 3,
      maxLines = 3,
      placeholder = {
        Text("Add the title of the task")
      },
    )
    Row(modifier = Modifier.padding(bottom = 16.dp)) {
      Icon(
        Icons.Default.DateRange,
        contentDescription = "Date Picker",
        tint = Color.Gray
      )
      Text(
        modifier = Modifier.padding(start = 8.dp),
        text = DateUtils.convertLongToDate(actualTime),
        fontSize = 16.sp,
        color = Color.Gray
      )
    }
    Card(
      shape = RoundedCornerShape(12.dp),
      backgroundColor = BlueLightBackground,
      modifier = Modifier.clickable {
        onSave(
          Task(
            title = taskName.value,
            createdAt = actualTime,
            isCompleted = false
          )
        )
        taskName.value = ""
      },
      border = BorderStroke(1.dp, BlueLightBorder)
    ) {
      Text(
        text = "Save Task",
        modifier =
          Modifier
            .fillMaxWidth()
            .padding(12.dp, 8.dp, 12.dp, 8.dp),
        textAlign = TextAlign.Center
      )
    }
  }
}

enum class HomeScreenTags {
  ALL,
  OPEN,
  CLOSED
}