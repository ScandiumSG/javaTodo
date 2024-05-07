import { Component } from '@angular/core';
import { DataFetcherService } from '../../services/data-fetcher.service';
import { PostTask } from '../../utils/interfaces';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-post-task',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './post-task.component.html',
  styleUrl: './post-task.component.css'
})
export class PostTaskComponent {
  public newTask: PostTask = {
    title: "",
    description: ""
  }
  public showPostForm: boolean = false;

  constructor(private service: DataFetcherService) {}

  showForm(): void {
    this.showPostForm = true;
  }

  handleChange(event: Event): void {
    if (event && event.target) {
      const { id, value } = event.target as HTMLInputElement;
      this.newTask = {...this.newTask, [id]: value}
    }
  }

  handleSubmit(): void {
    if (this.newTask.title != "" && this.newTask.description != "") {
      this.service.postData(this.newTask);
      this.newTask.title = "";
      this.newTask.description = "";
      this.showPostForm = false;
    }
  }
}
