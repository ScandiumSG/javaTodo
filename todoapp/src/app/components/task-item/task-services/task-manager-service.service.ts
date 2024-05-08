import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskManagerService {
  currentModifiableTask: BehaviorSubject<number> = new BehaviorSubject<number>(-1);
  taskOptionsMenuManager: BehaviorSubject<number> = new BehaviorSubject<number>(-1);

  constructor() { }

  setModifiable(id: number) {
    this.currentModifiableTask.next(id);
  }

  setMenuManager(id: number) {
    this.taskOptionsMenuManager.next(id);
    console.log("Current options menu allowed: "+id)
  }
}
