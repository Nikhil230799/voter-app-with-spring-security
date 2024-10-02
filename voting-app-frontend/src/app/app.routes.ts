import { Routes } from '@angular/router';
import { LoginComponent } from './Auth/login/login.component';
import { RegisterComponent } from './Auth/register/register.component';
import { HomeComponent } from './Common/home/home.component';
import { AboutUsComponent } from './Common/about-us/about-us.component';

export const routes: Routes = [
    { path: '', component: HomeComponent, pathMatch:'full' },
    { path: 'login', component: LoginComponent },
    { path: 'about', component: AboutUsComponent },
    { path: 'register', component: RegisterComponent },
    // { path: '**', component: RouteNotFoundComponent }
];
