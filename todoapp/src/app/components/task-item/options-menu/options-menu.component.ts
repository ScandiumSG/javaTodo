import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnDestroy, Output } from '@angular/core';
import { TaskManagerService } from '../task-services/task-manager-service.service';

@Component({
  selector: 'app-options-menu',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './options-menu.component.html',
  styleUrl: './options-menu.component.css'
})
export class OptionsMenuComponent {
  public showOptions: boolean = false;
  @Input() parentTaskID!: number;
  @Output() deleteTask = new EventEmitter();
  @Output() allowUpdates = new EventEmitter<boolean>();

  constructor(private taskManager: TaskManagerService) {
    this.taskManager.taskOptionsMenuManager.subscribe((id) => {
      if (this.parentTaskID != id) {
        this.showOptions = false;
      }
    })
  }

  toggleOptions(): void {
    this.taskManager.setMenuManager(this.parentTaskID)
    this.showOptions = true;
  }

  allowItemUpdate(value: boolean): void {
    this.allowUpdates.emit(value);
    this.showOptions = false;
  }

  deleteItem(): void {
    this.deleteTask.emit();
    this.showOptions = false;
  }
  

}
