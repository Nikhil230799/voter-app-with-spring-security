import { Routes } from '@angular/router';
import { LoginComponent } from './Auth/login/login.component';
import { RegisterComponent } from './Auth/register/register.component';
import { HomeComponent } from './Common/home/home.component';
import { AboutUsComponent } from './Common/about-us/about-us.component';
import { DashboardComponent } from './Common/dashboard/dashboard.component';
import { guardGuard } from './Gaurd/guard.guard';


export const routes: Routes = [
    { path: '', component: HomeComponent, pathMatch:'full' },
    { path: 'login', component: LoginComponent },
    { path: 'about', component: AboutUsComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'dashboard', component: DashboardComponent , canActivate:[guardGuard]},
    // { path: '**', component: RouteNotFoundComponent }
];
