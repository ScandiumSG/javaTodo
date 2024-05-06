export interface Task {
    title: string,
    description: string,
    id: number, 
    createdAt: string,
    updatedAt: string,
    completed: boolean,
  }

export interface PostTask {
  title: string,
  description: string
}