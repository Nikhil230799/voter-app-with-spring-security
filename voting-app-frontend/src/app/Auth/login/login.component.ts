import { Component, inject, OnInit } from '@angular/core';
import { NavbarComponent } from "../../Common/navbar/navbar.component";
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule, DOCUMENT, LowerCasePipe } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { ToastService, AngularToastifyModule } from 'angular-toastify';
import { jwtDecode } from 'jwt-decode';
import { TokenDecoderService } from '../../service/token-decoder.service';



@Component({
  selector: 'app-login',
  standalone: true,
  imports: [NavbarComponent, MatFormFieldModule, MatInputModule, MatButtonModule, ReactiveFormsModule, CommonModule, HttpClientModule, AngularToastifyModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})

export class LoginComponent implements OnInit {


  title = 'login page';
  formBuilder = inject(FormBuilder);
  http = inject(HttpClient);
  router = inject(Router);
  toaster = inject(ToastService);
  document = inject(DOCUMENT);
  tokenDecoder = inject(TokenDecoderService);



  userForm: any = {
    username: String,
    password: String
  };

  loginuser(userForm: any) {
    this.http.post<any>("http://localhost:2020/voting/auth/dologin", userForm)
      .subscribe((resp) => {
        if (resp.responseCode === 200 && resp.responseDesc === "login success") {
          const decoded = this.tokenDecoder.decodeToken(resp.data);
          console.log(decoded)
          if (decoded.exp > decoded.iat)
            localStorage.setItem("token", resp.data);
          this.document.defaultView?.localStorage.setItem("role", decoded.role);
          this.document.defaultView?.localStorage.setItem("email", decoded.email);
          this.document.defaultView?.localStorage.setItem("phoneno", decoded.phoneno);
          this.document.defaultView?.localStorage.setItem("votestatus", decoded.votestatus);
       
          this.router.navigateByUrl("dashboard");
        }
        if (resp.responseCode === 202 && (resp.data === "Bad credentials" || resp.data === "User does not exists")) {
          this.toaster.error(resp.responseDesc)
        }
      }, (error) => {
        console.error("Login error:", error);
        this.toaster.error("Internal server error, Try again after some time.")
      });
  }

  submitForm() {
    if (this.userForm?.valid) {
      this.loginuser(this.userForm.value);
    }
  }

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(4)]],
      password: ['', Validators.required]
    });
  }
}
