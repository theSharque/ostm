import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserModel} from "../shared/user-model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) {
  }

  getUsers(): Observable<UserModel[]> {
    return this.httpClient.get<UserModel[]>('/api/user');
  }

  createUser(user: UserModel): Observable<UserModel> {
    return this.httpClient.post<UserModel>('/api/user', user)
  }
}
