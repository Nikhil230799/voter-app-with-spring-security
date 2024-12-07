import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [MatInputModule, MatFormFieldModule, MatTableModule, NgIf],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})


export class AdminDashboardComponent implements OnInit {

  userList !: {
    usr_id: number;
    usr_email: String;
    usr_phoneNo: String;
    usr_role: String;
    usr_userStatus: boolean;
    usr_username: String;
    usr_voteStatus: Boolean;
    candidates: {
      cd_name: String;
      cd_id: Number
    }
  }[];

  displayedColumns: string[] = [
    'usr_id',
    'usr_username',
    'usr_email',
    'usr_phoneNo',
    'usr_userStatus',
    'usr_role',
    'usr_voteStatus',
    'cd_name',
  ];
  dataSource: MatTableDataSource<any> | null = null;


  http = inject(HttpClient)
  router = inject(Router)

  ngOnInit(): void {
    this.http.get("http://localhost:2020/voting/admin/userList").subscribe((resp: any) => {
      this.userList = resp.data;
      // console.log(this.userList)
      this.dataSource = new MatTableDataSource(this.userList);

    }, error => {
      console.log(error)
      this.router.navigateByUrl("login")
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    if (this.dataSource != null) { this.dataSource.filter = filterValue.trim().toLowerCase(); }
  }

}
