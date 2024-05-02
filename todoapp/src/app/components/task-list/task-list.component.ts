import { Component } from '@angular/core';
import { DataFetcherService } from '../../services/data-fetcher.service';
import { CommonModule } from '@angular/common';
import { Task } from '../../utils/interfaces';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.css',
  providers: [DataFetcherService]
})


export class TaskListComponent {
  dataList: Array<Task> = [];

  constructor (private data: DataFetcherService) {
    this.data.recievedData.subscribe((value: Task[]) => {
      this.dataList.push(...value);
    })
  }
}
