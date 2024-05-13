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

export interface UserObject {
  token: string,
  type: string,
  id: string,
  username: string,
  email: string, 
  roles: string[]
}