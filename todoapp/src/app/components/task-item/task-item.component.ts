import { Component, Input } from '@angular/core';
import { Task } from '../../utils/interfaces';

@Component({
  selector: 'app-task-item',
  standalone: true,
  imports: [],
  templateUrl: './task-item.component.html',
  styleUrl: './task-item.component.css'
})
export class TaskItemComponent {
  @Input() task : Task | undefined;

  constructor() {
    console.log(this.task?.completed);
  }
}
