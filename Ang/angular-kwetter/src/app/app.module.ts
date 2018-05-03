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

const appRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'profile/:username', component: ProfileComponent},
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
    LoginComponent
  ],
  imports: [
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true} // <-- debugging purposes only
    ),
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [LoginService, ProfileService, KwetService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
