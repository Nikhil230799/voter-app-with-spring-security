import { Routes } from '@angular/router';
import { LoginComponent } from './Auth/login/login.component';
import { RegisterComponent } from './Auth/register/register.component';
import { HomeComponent } from './Common/home/home.component';
import { AboutUsComponent } from './Common/about-us/about-us.component';
import { DashboardComponent } from './Common/dashboard/dashboard.component';
import { guardGuard } from './Gaurd/guard.guard';


export const routes: Routes = [
    { path: '', component: HomeComponent, pathMatch:'full' },
    { path: 'login', component: LoginComponent, pathMatch:'full'  },
    { path: 'about', component: AboutUsComponent , pathMatch:'full' },
    { path: 'register', component: LoginComponent, pathMatch:'full'  },
    { path: 'dashboard', component: DashboardComponent , canActivate:[guardGuard], pathMatch:'full' },
    { path: 'forget-password', component: RegisterComponent , pathMatch:'full' },
    // { path: '**', component: RouteNotFoundComponent }
];
