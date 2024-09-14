import {Component, OnInit} from '@angular/core';
import {Button} from "primeng/button";
import {DialogModule} from "primeng/dialog";
import {FormsModule} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {PrimeTemplate} from "primeng/api";
import {TableModule} from "primeng/table";
import {RoleModel} from "../shared/role-model";
import {RoleService} from "./role.service";

@Component({
  selector: 'app-role',
  standalone: true,
  imports: [
    Button,
    DialogModule,
    FormsModule,
    InputTextModule,
    PrimeTemplate,
    TableModule
  ],
  templateUrl: './role.component.html',
  styleUrl: './role.component.scss'
})
export class RoleComponent implements OnInit {
  visible: boolean = false;
  newRole: RoleModel = {
    id: ''
  }
  roles!: RoleModel[];

  constructor(private roleService: RoleService) {
  }

  ngOnInit(): void {
    this.updateData()
  }

  showDialog() {
    this.visible = true;
  }

  updateData() {
    this.roleService.getRoles().subscribe(value => this.roles = value);
  }

  addRole() {
    this.roleService.createRole(this.newRole).subscribe(() => this.updateData())
    this.visible = false;
  }
}
