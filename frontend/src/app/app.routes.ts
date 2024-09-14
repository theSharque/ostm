import {Routes} from '@angular/router';
import {ProjectComponent} from "./project/project.component";
import {RoleComponent} from "./role/role.component";
import {UserComponent} from "./user/user.component";

export const routes: Routes = [
  {
    path: 'project', component: ProjectComponent
  }, {
    path: 'role', component: RoleComponent
  },{
    path: 'user', component: UserComponent
  }
];
