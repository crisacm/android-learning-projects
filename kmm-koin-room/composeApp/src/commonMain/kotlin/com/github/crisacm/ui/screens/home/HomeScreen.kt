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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.crisacm.ui.theme.LightGray
import com.github.crisacm.ui.theme.Purple
import kotlinx.coroutines.launch

data class Task(
  var id: Int = 0, val name: String, var isCompleted: Boolean
)

private val loremIpsumText = """
    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor 
    incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
    exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure 
    dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
    Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt 
    mollit anim id est laborum.
""".trimIndent()

private val mockData by lazy {
  List(3) { index ->
    val start = (0..loremIpsumText.length / 2).random()
    val end = (start + (50..150).random()).coerceAtMost(loremIpsumText.length)
    Task(index, loremIpsumText.substring(start, end), false)
  }
}

@Composable
fun HomeScreen() {
  val query = remember { mutableStateOf("") }
  val tasks = remember { mutableStateListOf<Task>().apply { addAll(mockData) } }
  val filteredTasks = if (query.value.isEmpty()) {
    tasks
  } else {
    tasks.filter { it.name.contains(query.value, ignoreCase = true) }
  }

  val skipHalfExpanded by remember { mutableStateOf(false) }
  val state = rememberModalBottomSheetState(
    initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = skipHalfExpanded
  )
  val scope = rememberCoroutineScope()

  fun addTask(task: Task) {
    tasks.add(
      task.apply { id = tasks.size + 1 }
    )
  }

  fun updateTask(task: Task, isCompleted: Boolean) {
    val index = tasks.indexOf(task)
    if (index != -1) {
      tasks[index] = task.copy(isCompleted = isCompleted)
    }
  }

  fun updateState() {
    scope.launch {
      if (state.isVisible) {
        state.hide()
      } else {
        state.show()
      }
    }
  }

  ModalBottomSheetLayout(
    sheetState = state, sheetContent = {
      SheetContent {
        addTask(it)
        updateState()
      }
    }) {
    Scaffold(
      floatingActionButton = {
        FloatingActionButton(
          backgroundColor = Purple,
          shape = RoundedCornerShape(16.dp),
          onClick = { updateState() }) {
          Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Icon(
              imageVector = Icons.Filled.Add, contentDescription = "Add", tint = Color.White
            )
            Text(
              modifier = Modifier.padding(start = 8.dp, end = 8.dp),
              text = "ADD",
              color = Color.White,
              fontWeight = FontWeight.SemiBold
            )
          }
        }
      }) { innerPadding ->
      LazyColumn(modifier = Modifier.padding(innerPadding)) {
        item {
          Box(modifier = Modifier.padding(12.dp)) {
            OutlinedTextField(
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(50.dp),
              value = query.value,
              onValueChange = { query.value = it },
              leadingIcon = {
                Icon(
                  Icons.Filled.Search,
                  contentDescription = "Search",
                  modifier = Modifier.padding(start = 8.dp),
                )
              },
              trailingIcon = {
                if (query.value.isNotEmpty()) {
                  IconButton(
                    modifier = Modifier.padding(end = 8.dp),
                    onClick = { query.value = "" }) {
                    Icon(Icons.Filled.Clear, contentDescription = "Clear")
                  }
                }
              },
              placeholder = { Text(text = "Search...") },
              singleLine = true
            )
          }
        }
        items(filteredTasks) { task ->
          AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
          ) {
            TaskItem(
              task = task,
              onCheckChange = { t, isCompleted -> updateTask(t, isCompleted) },
              onDelete = { tasks.remove(it) }
            )
          }
        }
      }
    }
  }
}

@Composable
fun SheetContent(
  modifier: Modifier = Modifier,
  onSave: (Task) -> Unit,
) {
  val taskName = remember { mutableStateOf("") }
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
      fontSize = 18.sp,
    )
    OutlinedTextField(
      modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp),
      value = taskName.value,
      onValueChange = { taskName.value = it },
      minLines = 3,
      maxLines = 3,
      placeholder = {
        Text("Add the title of the task")
      },
    )
    Button(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(8.dp),
      onClick = {
        onSave(Task(name = taskName.value, isCompleted = false))
        taskName.value = ""
      }) {
      Text("Save Task")
    }
  }
}

@Composable
fun TaskItem(
  modifier: Modifier = Modifier,
  task: Task,
  onCheckChange: (Task, Boolean) -> Unit,
  onDelete: (Task) -> Unit,
) {
  Card(
    modifier = modifier.fillMaxWidth().padding(12.dp, 8.dp, 12.dp, 8.dp)
      .clickable { onCheckChange(task, !task.isCompleted) },
    backgroundColor = LightGray,
    shape = RoundedCornerShape(12.dp),
    elevation = 0.dp,
    border = BorderStroke(1.dp, Color.LightGray),
  ) {
    Row(
      modifier = Modifier.fillMaxSize(),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Checkbox(
        checked = task.isCompleted, onCheckedChange = { onCheckChange(task, it) })
      Text(
        modifier =
          Modifier
            .weight(1f)
            .padding(top = 4.dp, bottom = 4.dp),
        text = task.name,
        textDecoration = if (task.isCompleted) {
          TextDecoration.LineThrough
        } else {
          TextDecoration.None
        },
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
      )
      IconButton(onClick = { onDelete(task) }) {
        Icon(
          imageVector = Icons.Filled.Delete,
          contentDescription = "Delete",
          tint = Color.Black
        )
      }
    }
  }
}
