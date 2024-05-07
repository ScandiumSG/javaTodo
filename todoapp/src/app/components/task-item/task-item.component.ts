import { Component, ElementRef, EventEmitter, Input, InputFunction, Output, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Task } from '../../utils/interfaces';
import { OptionsMenuComponent } from './options-menu/options-menu.component';
import { FormsModule } from '@angular/forms';
import { ModifierServiceService } from '../../services/modifier-service.service';

@Component({
  selector: 'app-task-item',
  standalone: true,
  imports: [CommonModule, OptionsMenuComponent, FormsModule],
  templateUrl: './task-item.component.html',
  styleUrl: './task-item.component.css'
})
export class TaskItemComponent{
  canModify: boolean = false;
  editable: boolean = false;
  allowDelete: boolean = false;
  @Input() task!: Task;
  @Output() updatedTask = new EventEmitter<Task>();
  @Output() deleteTask = new EventEmitter<Task>();
  @ViewChild('taskDescription') taskDescription!: ElementRef;
  private taskCopy: Task = this.task;

  constructor(private modifier: ModifierServiceService) {
    modifier.allowedModifier.subscribe((value) => {
      if (!this.task) {return;}
      if (this.task.id == value) {
        this.canModify = true;
      } else {
        if (this.editable) {
          this.cancelTaskEdit();
        }
        if (this.allowDelete) {
          this.cancelDeleteTask();
        }
        this.canModify = false;
      }
    })
  }

  toggleComplete(): void {
    this.task.completed = !this.task.completed;
    this.updatedTask.emit(this.task);
  }

  handleChange(id: string, event: Event): void {
    this.task = {...this.task, [id]: event}
  }

  allowTaskEdit(value: boolean): void {
    this.cancelDeleteTask();
    this.taskCopy = this.task;
    this.editable = value;
    this.modifier.setModifier(this.task.id);
    if (this.editable) {
      setTimeout(() => {
        this.taskDescription.nativeElement.focus();
      }, 0)
    }
    
  }
  
  cancelTaskEdit(): void {
    this.task = this.taskCopy;
    this.editable = false;
  }

  updateTask(): void {
    this.editable = false;
    this.taskCopy = this.task;
    this.updatedTask.emit(this.task)
  }

  allowDeleteTask(): void {
    this.modifier.setModifier(this.task.id);
    this.allowDelete = true;
    this.editable = false;
  }

  cancelDeleteTask(): void {
    this.allowDelete = false;
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
