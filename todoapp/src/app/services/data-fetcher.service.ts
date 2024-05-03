import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, tap } from 'rxjs';
import { Task } from '../utils/interfaces';
import { error } from 'node:console';

@Injectable({
  providedIn: 'any'
})

export class DataFetcherService {
  public recievedData: BehaviorSubject<Task[]> =  new BehaviorSubject<Task[]>([]);

  constructor(private http: HttpClient) {
    this.http.get<Task[]>('http://localhost:8080/task/v2/')
    .pipe(
      tap(tasks => {
        this.recievedData.next(tasks);
      })
    )
    .subscribe({
      error: (e) => console.log(e),
    });
  }

}
