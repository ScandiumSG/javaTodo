import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Task } from '../../utils/interfaces';
import { OptionsMenuComponent } from './options-menu/options-menu.component';

@Component({
  selector: 'app-task-item',
  standalone: true,
  imports: [CommonModule, OptionsMenuComponent],
  templateUrl: './task-item.component.html',
  styleUrl: './task-item.component.css'
})
export class TaskItemComponent {
  @Input() task!: Task;
  @Output() updatedTask = new EventEmitter<Task>();
  @Output() deleteTask = new EventEmitter<Task>();

  public toggleComplete(): void {
    this.task.completed = !this.task.completed;
    this.updatedTask.emit(this.task);
  }

  removeTask(): void {
    this.deleteTask.emit(this.task);
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
    if (dateDiff < (1000*60*60*24)) {
      return updateDate.getHours()+":"+updateDate.getMinutes();
    }
    return updateDate.toLocaleDateString("no-BM", options);
  }
}
