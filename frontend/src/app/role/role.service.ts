import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RoleModel} from "../shared/role-model";

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private httpClient: HttpClient) {
  }

  getRoles(): Observable<RoleModel[]> {
    return this.httpClient.get<RoleModel[]>('/api/role');
  }

  createRole(role: RoleModel): Observable<RoleModel> {
    return this.httpClient.post<RoleModel>('/api/role', role)
  }
}
