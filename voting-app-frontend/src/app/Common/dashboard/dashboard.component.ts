import { Component, inject, OnInit } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { DOCUMENT, NgFor } from '@angular/common';
import { FormsModule, NgModel } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { UserDashboardComponent } from "../../User/user-dashboard/user-dashboard.component";
import { AdminDashboardComponent } from "../../Admin/admin-dashboard/admin-dashboard.component";

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [NavbarComponent, NgFor, FormsModule, MatInputModule, MatButtonModule, UserDashboardComponent, AdminDashboardComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  router = inject(Router);
  http = inject(HttpClient);
  document = inject(DOCUMENT) ; 
  userRole :any=""

  logout() {
    document.defaultView?.localStorage.removeItem('token')
    this.router.navigateByUrl('/login')
  }

  constructor(){
    this.userRole=this.document.defaultView?.localStorage.getItem("role");
  }
}
