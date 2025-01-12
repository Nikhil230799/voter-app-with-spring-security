import { CommonModule, DOCUMENT, NgIf } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { AngularToastifyModule, ToastService } from 'angular-toastify';
import { Router, RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { TokenDecoderService } from '../../service/token-decoder.service';



@Component({
  selector: 'app-right-div',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatButtonModule, ReactiveFormsModule, CommonModule, HttpClientModule, AngularToastifyModule, RouterLink],
  templateUrl: './right-div.component.html',
  styleUrl: './right-div.component.css'
})
export class RightDivComponent {

  title = 'login page';
  formBuilder = inject(FormBuilder);
  http = inject(HttpClient);
  router = inject(Router);
  toaster = inject(ToastService);
  document = inject(DOCUMENT);
  tokenDecoder = inject(TokenDecoderService);
  currentRoute!: String;
  userForm: any = {
    username: String,
    password: String
  };

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(4)]],
      password: ['', Validators.required]
    });
  }
}

