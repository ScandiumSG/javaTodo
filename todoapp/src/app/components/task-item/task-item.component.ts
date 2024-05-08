import { Component, ElementRef, EventEmitter, Input, InputFunction, OnDestroy, OnInit, Output, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Task } from '../../utils/interfaces';
import { OptionsMenuComponent } from './options-menu/options-menu.component';
import { FormsModule } from '@angular/forms';
import { TaskManagerService } from './task-services/task-manager-service.service';

@Component({
  selector: 'app-task-item',
  standalone: true,
  imports: [CommonModule, OptionsMenuComponent, FormsModule],
  templateUrl: './task-item.component.html',
  styleUrl: './task-item.component.css'
})
export class TaskItemComponent implements OnInit {
  canModify: boolean = false;
  editable: boolean = false;
  allowDelete: boolean = false;
  @Input() task!: Task;
  @Output() updatedTask = new EventEmitter<Task>();
  @Output() deleteTask = new EventEmitter<Task>();
  @ViewChild('taskDescription') taskDescription!: ElementRef;
  private taskCopy: Task = this.task;
  
  constructor(private modifier: TaskManagerService) {}

  ngOnInit(): void {
    this.modifier.currentModifiableTask.subscribe((value) => {
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
    this.modifier.setModifiable(this.task.id);
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
    this.modifier.setModifiable(this.task.id);
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

    const TimeOptions: Intl.DateTimeFormatOptions = {
      second: undefined,
    }

    const DateOptions: Intl.DateTimeFormatOptions = {
      hour12: false,
      day: "numeric",
      month: "2-digit",
      year: "numeric",
    }

    
    if (dateDiff < (1000*60*60*24)) {
      return updateDate.toLocaleTimeString("no-BM", TimeOptions).substring(0,5);
    }
    return updateDate.toLocaleDateString("no-BM", DateOptions);
  }


}
