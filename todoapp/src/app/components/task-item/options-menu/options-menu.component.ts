import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { Task } from '../../../utils/interfaces';

@Component({
  selector: 'app-options-menu',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './options-menu.component.html',
  styleUrl: './options-menu.component.css'
})
export class OptionsMenuComponent {
  public showOptions: boolean = false;
  @Output() deleteTask = new EventEmitter();

  toggleOptions(): void {
    this.showOptions = !this.showOptions;
  }

  deleteItem(): void {
    this.deleteTask.emit();
  }
  
}
