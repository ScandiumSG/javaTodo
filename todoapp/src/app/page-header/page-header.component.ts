import { Component } from '@angular/core';
import { DataUtilService } from '../services/data-util.service';

@Component({
  selector: 'app-page-header',
  standalone: true,
  imports: [],
  templateUrl: './page-header.component.html',
  styleUrl: './page-header.component.css'
})
export class PageHeaderComponent {
  totalTasks: number = 0;
  incompleteTasks: number = 0;

  constructor (private data: DataUtilService) { 
    this.data.numberOfTasks.subscribe((value: number) => {
      this.totalTasks = value;
    })
    this.data.numberOfIncompleteTasks.subscribe((value: number) => {
      this.incompleteTasks = value;
    })
  }
}
