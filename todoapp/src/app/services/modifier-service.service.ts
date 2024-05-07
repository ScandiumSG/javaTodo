import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ModifierServiceService {
  allowedModifier: BehaviorSubject<number> = new BehaviorSubject<number>(-1);
  constructor() { }

  setModifier(id: number) {
    this.allowedModifier.next(id);
  }
}
