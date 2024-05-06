import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Task } from '../../utils/interfaces';

@Component({
  selector: 'app-task-item',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './task-item.component.html',
  styleUrl: './task-item.component.css'
})
export class TaskItemComponent {
  @Input() task!: Task;
  @Output() updatedTask = new EventEmitter<Task>();

  public toggleComplete(): void {
    this.task.completed = !this.task.completed;
    this.updatedTask.emit(this.task);
  }

  public translateDate(): string {
    const currentDate: number = new Date().getTime();
    const updateDate: Date = new Date(this.task.updatedAt);
    const dateDiff: number = currentDate - updateDate.getTime();
    const dateDiffObj: Date = new Date(dateDiff);

    const options: Intl.DateTimeFormatOptions = {
      hour12: false,
      year: "numeric",
      month: "numeric",
      day: "numeric"
    }
    return updateDate.toLocaleDateString("no-BM", options);
  }
}
