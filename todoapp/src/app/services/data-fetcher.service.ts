import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Task } from '../utils/interfaces';

@Injectable({
  providedIn: 'any'
})

export class DataFetcherService {
  public recievedData: BehaviorSubject<Task[]> =  new BehaviorSubject<Task[]>([]);

  constructor(public http: HttpClient) { 
    this.fetchData();
  }

  private fetchData(): void {
    const options = {
      headers: {
        "content-type": "application/json",
      }
    }
    const req = this.http.get<Array<Task>>('http://localhost:8080/task/v2/', options)

    req.subscribe(value => {
      this.recievedData.next(value)
    }, error => {
      console.error("Error fetching data: ", error);
    });
  }
}
