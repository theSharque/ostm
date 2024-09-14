import {Component, OnInit} from '@angular/core';
import {TableModule} from "primeng/table";
import {ProjectModel} from "../shared/project-model";
import {ProjectService} from "./project.service";
import {CardModule} from "primeng/card";
import {Button} from "primeng/button";
import {DialogModule} from "primeng/dialog";
import {InputTextModule} from "primeng/inputtext";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-project',
  standalone: true,
  imports: [
    TableModule,
    CardModule,
    Button,
    DialogModule,
    InputTextModule,
    FormsModule
  ],
  templateUrl: './project.component.html',
  styleUrl: './project.component.scss'
})
export class ProjectComponent implements OnInit {
  projects!: ProjectModel[];
  visible: boolean = false;

  public newProject: ProjectModel = {
    id: '',
    nextId: 0,
    name: '',
    owner: ''
  };

  constructor(private projectService: ProjectService) {
  }

  ngOnInit(): void {
    this.updateProjects()
  }

  updateProjects() {
    this.projectService.getProjects().subscribe(value => this.projects = value);
  }

  showDialog() {
    this.newProject = {
      id: '',
      nextId: 0,
      name: '',
      owner: ''
    }
    this.visible = true;
  }

  addProject() {
    if (this.newProject) {
      this.projectService.createProject(this.newProject).subscribe(() => this.updateProjects())
    }

    this.visible = false;
  }
}
