import { Component, EventEmitter, Output } from '@angular/core';
import { DataFetcherService } from '../../services/data-fetcher.service';
import { CommonModule } from '@angular/common';
import { Task } from '../../utils/interfaces';
import { TaskItemComponent } from '../task-item/task-item.component';
import { DataUtilService } from '../../page-header/header-service/data-util.service';
import { PostTaskComponent } from '../post-task/post-task.component';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule, TaskItemComponent, PostTaskComponent],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.css',
  providers: [DataFetcherService]
})


export class TaskListComponent {
  dataList: Array<Task> = [];
  id!: number;

  constructor (private data: DataFetcherService, private dataUtils: DataUtilService) {
    this.data.recievedData.subscribe((value: Task[]) => {
      this.dataList = value;
    })
  }

  deleteTask(taskToBeDeleted: Task) {
    const list = this.dataList.filter(task => task.id != taskToBeDeleted.id);
    this.dataList = list;

    this.data.deleteData(taskToBeDeleted);
  }

  changeTask(updatedTask: Task) {
    const list = this.dataList.filter(task => task.id != updatedTask.id);
    list.push(updatedTask);
    list.sort((a, b) => a.id - b.id);
    this.dataList = list;
    this.dataUtils.updateUtils(list);
    this.data.putData(updatedTask);
  }
}
