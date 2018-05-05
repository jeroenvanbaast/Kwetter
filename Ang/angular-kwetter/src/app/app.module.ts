import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms'; // <-- NgModel lives here
import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {ProfileComponent} from './profile/profile.component';
import {LoginComponent} from './login/login.component';
import {ProfileService} from './services/profileService';
import {LoginService} from './services/loginService';
import {KwetService} from './services/kwetService';
import {P} from "@angular/core/src/render3";
import {HttpClientModule} from "@angular/common/http";
import { LogoutComponent } from './logout/logout.component';
import {UserService} from "./services/userService";

const appRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LogoutComponent},
  {path: 'profile/:username', component: ProfileComponent},
  {path: 'profile', component: ProfileComponent},
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {path: '**', component: LoginComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    LoginComponent,
    LogoutComponent
  ],
  imports: [
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: false} // <-- debugging purposes only
    ),
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [LoginService, ProfileService, KwetService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
