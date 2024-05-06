import { Component } from '@angular/core';
import { DataFetcherService } from '../../services/data-fetcher.service';
import { CommonModule } from '@angular/common';
import { Task } from '../../utils/interfaces';
import { TaskItemComponent } from '../task-item/task-item.component';
import { DataUtilService } from '../../services/data-util.service';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule, TaskItemComponent],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.css',
  providers: [DataFetcherService]
})


export class TaskListComponent {
  dataList: Array<Task> = [];

  constructor (private data: DataFetcherService, private dataUtils: DataUtilService) {
    this.data.recievedData.subscribe((value: Task[]) => {
      this.dataList = value;
    })
  }

  changeTask(updatedTask: Task) {
    const list = this.dataList.filter(task => task.id != updatedTask.id);
    list.push(updatedTask);
    list.sort((a, b) => a.id - b.id);
    this.dataList = list;
    this.dataUtils.updateUtils(list);
  }
}
