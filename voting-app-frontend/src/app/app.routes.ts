import { Routes } from '@angular/router';
import { LoginComponent } from './Auth/login/login.component';
import { RegisterComponent } from './Auth/register/register.component';
import { HomeComponent } from './Common/home/home.component';
import { AboutUsComponent } from './Common/about-us/about-us.component';
import { DashboardComponent } from './Common/dashboard/dashboard.component';
import { guardGuard } from './Gaurd/guard.guard';
import { UserAuthComponent } from './Auth/user-auth/user-auth.component';


export const routes: Routes = [
    { path: '', component: HomeComponent, pathMatch:'full' },
    { path: 'login', component: UserAuthComponent, pathMatch:'full'  },
    { path: 'about', component: AboutUsComponent , pathMatch:'full' },
    { path: 'register', component: UserAuthComponent, pathMatch:'full'  },
    { path: 'dashboard', component: DashboardComponent , canActivate:[guardGuard], pathMatch:'full' },
    { path: 'forget-password', component: UserAuthComponent , pathMatch:'full' },
    // { path: '**', component: RouteNotFoundComponent }
];
