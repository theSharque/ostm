import {Component, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {MenubarModule} from "primeng/menubar";
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MenubarModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'frontend';
  items: MenuItem[] | undefined;

  ngOnInit() {
    this.items = [{
      label: 'Projects',
      routerLink: '/project',
    }, {
      label: 'Roles',
      routerLink: '/role',
    }, {
      label: 'Users',
      routerLink: '/user',
    }, {
      label: 'Projects',
      icon: 'pi pi-search',
      items: [{
        label: 'Components',
        icon: 'pi pi-bolt'
      }, {
        label: 'Blocks',
        icon: 'pi pi-server'
      }, {
        label: 'UI Kit',
        icon: 'pi pi-pencil'
      }, {
        label: 'Templates',
        icon: 'pi pi-palette',
        items: [{
          label: 'Apollo',
          icon: 'pi pi-palette'
        }, {
          label: 'Ultima',
          icon: 'pi pi-palette'
        }]
      }]
    }, {
      label: 'Contact',
      icon: 'pi pi-envelope'
    }]
  }
}
