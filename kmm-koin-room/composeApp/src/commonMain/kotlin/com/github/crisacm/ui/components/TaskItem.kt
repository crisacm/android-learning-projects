package com.github.crisacm.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.crisacm.domain.model.Task
import com.github.crisacm.ui.theme.LightGray
import com.github.crisacm.utils.DateUtils

@Composable
fun TaskItem(
  modifier: Modifier = Modifier,
  task: Task,
  onCheckChange: (Task, Boolean) -> Unit,
  onDelete: (Task) -> Unit,
) {
  Card(
    modifier =
      modifier
        .fillMaxWidth()
        .clickable { onCheckChange(task, !task.isCompleted) },
    backgroundColor = LightGray,
    shape = RoundedCornerShape(12.dp),
    elevation = 0.dp,
    border = BorderStroke(0.5.dp, Color.LightGray),
  ) {
    Column(
      modifier =
        Modifier
          .fillMaxSize()
          .padding(16.dp)
    ) {
      Row(modifier = Modifier.fillMaxWidth()) {
        Text(
          modifier = Modifier.weight(1f),
          text = task.title,
          textDecoration = if (task.isCompleted) {
            TextDecoration.LineThrough
          } else {
            TextDecoration.None
          },
          maxLines = 3,
          overflow = TextOverflow.Ellipsis,
        )
        RoundedCheckbox(
          modifier =
            Modifier
              .padding(start = 8.dp)
              .align(Alignment.CenterVertically),
          isChecked = task.isCompleted,
          onValueChange = { onCheckChange(task, it) }
        )
      }
      Divider(
        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
        thickness = 1.dp,
        color = Color.LightGray,
      )
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            modifier = Modifier.fillMaxWidth(),
            text = DateUtils.convertLongToDateWithConditional(task.createdAt),
            color = Color.Gray.copy(alpha = 0.8f)
          )
          Text(
            modifier = Modifier.fillMaxWidth(),
            text = DateUtils.convertLongToTime(task.createdAt),
            fontSize = 12.sp,
            color = Color.Gray.copy(alpha = 0.8f)
          )
        }
        Card(
          modifier = Modifier.clickable { onDelete(task) },
          shape = RoundedCornerShape(8.dp),
          border = BorderStroke(0.5.dp, Color.LightGray),
        ) {
          Row(
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 4.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "Eliminar",
              modifier = Modifier.padding(end = 8.dp)
            )
            Icon(
              modifier = Modifier.size(16.dp),
              imageVector = Icons.Filled.Delete,
              contentDescription = "Delete",
              tint = Color.Black
            )
          }
        }
      }
    }
  }
}
