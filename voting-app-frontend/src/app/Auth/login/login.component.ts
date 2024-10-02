import { ChangeDetectionStrategy, Component } from '@angular/core';
import { NavbarComponent } from "../../Common/navbar/navbar.component";
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button'
;

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [NavbarComponent, MatFormFieldModule,MatInputModule,MatButtonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',

 
})
export class LoginComponent {
  title='login page';
}
