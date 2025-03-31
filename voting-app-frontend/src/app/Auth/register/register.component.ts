import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { RouterLink } from '@angular/router';
import { AngularToastifyModule, ToastService } from 'angular-toastify';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatButtonModule, ReactiveFormsModule, CommonModule, HttpClientModule, RouterLink, AngularToastifyModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {


  http = inject(HttpClient);
  toaster = inject(ToastService);
  formBuilder = inject(FormBuilder);
  userForm: any = {
    usr_username: String,
    usr_password: String,
    usr_email: String,
    usr_phoneNo: String
  };
  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      usr_username: ['', [Validators.required, Validators.minLength(4)]],
      usr_password: ['', Validators.required],
      usr_email: ['', [Validators.required, Validators.email]],
      usr_phoneNo: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(12)]]
    });
  }

  submitForm() {
    if (this.userForm?.valid) {
      this.registeruser(this.userForm.value);
    }
  }
  registeruser(userForm: any) {
    this.http.post<any>("http://localhost:2020/voting/auth/register", userForm)
      .subscribe((resp) => {
        console.log(resp)
        if (resp.responseCode === 202 && resp.responseDesc === "User registered successfully") {
          this.toaster.success("User registered successfully")
          this.userForm.clear();
        }

      }, (error) => {
        for (const field in error.error.data) {
          const errorMessage = error.error.data[field];
          this.userForm.get(field)?.setErrors({ customError: errorMessage });
        }
        // if (error.error.responseCode === 400) {
        //   this.toaster.error(error.error.data)
        // }
        if (error.error.responseCode === 409) {
          this.toaster.error("User is already registered.")
        }
        else
          this.toaster.error("Internal server error, Try again after some time.")
      });
  }
}
