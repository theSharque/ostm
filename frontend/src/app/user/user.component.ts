import {Component, OnInit} from '@angular/core';
import {Button} from "primeng/button";
import {DialogModule} from "primeng/dialog";
import {FormsModule} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {PrimeTemplate} from "primeng/api";
import {TableModule} from "primeng/table";
import {UserService} from "./user.service";
import {UserModel} from "../shared/user-model";
import {RoleModel} from "../shared/role-model";
import {ListboxModule} from "primeng/listbox";
import {RoleService} from "../role/role.service";
import {PasswordModule} from "primeng/password";

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [
    Button,
    DialogModule,
    FormsModule,
    InputTextModule,
    PrimeTemplate,
    TableModule,
    ListboxModule,
    PasswordModule
  ],
  templateUrl: './user.component.html',
  styleUrl: './user.component.scss'
})
export class UserComponent implements OnInit {
  visible: boolean = false;
  users!: UserModel[];
  roles!: RoleModel[];
  newUser: UserModel = {
    id: '',
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    roles: []
  }

  constructor(private userService: UserService, private roleService: RoleService) {
  }

  ngOnInit(): void {
    this.updateData()
    this.roleService.getRoles().subscribe(value => this.roles = value);
  }

  updateData() {
    this.userService.getUsers().subscribe(value => {
      this.users = value
    })
  }

  showDialog() {
    this.newUser = {
      id: '',
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      roles: []
    }

    this.visible = true;
  }

  addUser() {
    this.userService.createUser(this.newUser).subscribe(() => this.updateData())
    this.visible = false;
  }
}
