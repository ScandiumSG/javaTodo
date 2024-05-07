import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Task } from '../../utils/interfaces';
import { OptionsMenuComponent } from './options-menu/options-menu.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-task-item',
  standalone: true,
  imports: [CommonModule, OptionsMenuComponent, FormsModule],
  templateUrl: './task-item.component.html',
  styleUrl: './task-item.component.css'
})
export class TaskItemComponent {
  editable: boolean = false;
  @Input() task!: Task;
  @Output() updatedTask = new EventEmitter<Task>();
  @Output() deleteTask = new EventEmitter<Task>();

  toggleComplete(): void {
    this.task.completed = !this.task.completed;
    this.updatedTask.emit(this.task);
  }

  handleChange(event: Event): void {
    if (event && event.target) {
      const { id, value } = event.target as HTMLInputElement;
      this.task = {...this.task, [id]: value}
    }
  }

  allowTaskEdit(value: boolean): void {
    console.log(this.task.title.concat(" editable").concat(String(value)))
    this.editable = value;
  }

  updateTask(): void {
    this.editable = false;
    this.updatedTask.emit(this.task)
  }

  removeTask(): void {
    this.deleteTask.emit(this.task);
  }

  translateDate(): string {
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
      return String(updateDate.getHours()).padStart(2,"0")+":"+String(updateDate.getMinutes()).padStart(2,"0");
    }
    return updateDate.toLocaleDateString("no-BM", options);
  }
}
