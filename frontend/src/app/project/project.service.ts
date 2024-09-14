import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProjectModel} from "../shared/project-model";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private httpClient: HttpClient) {
  }

  getProjects(): Observable<ProjectModel[]> {
    return this.httpClient.get<ProjectModel[]>('/api/project');
  }

  createProject(project: ProjectModel): Observable<ProjectModel> {
    return this.httpClient.post<ProjectModel>('/api/project', project)
  }
}
