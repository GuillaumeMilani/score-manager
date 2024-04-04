import {Component, ViewChild} from '@angular/core';
import {MatSidenav} from '@angular/material/sidenav';

interface MenuItem {
  text: string;
  link: string;
}

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent {
  @ViewChild('sidenav')
  sidenav!: MatSidenav;

  menuItems: MenuItem[] = [
    {text: 'Scores', link: '/scores'},
    {text: 'Instruments', link: '/instruments'}
  ];

  constructor() {
  }

  // Function to toggle the side menu
  toggleMenu() {
    this.sidenav.toggle().then();
  }
}
