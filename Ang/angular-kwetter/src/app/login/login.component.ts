import {Component, OnInit} from '@angular/core';
import {LoginService} from "../services/loginService";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;

  constructor(private loginService: LoginService, private router: Router) {
  }

  ngOnInit() {
  }

  Login() {
    this.loginService.Login(this.username, this.password).subscribe(data => {
      if(data != null){
        localStorage.setItem('userPorfileId', String(data.profile.id));
        localStorage.setItem('profileName', String(data.profile.name));
        this.router.navigate(['/profile', data.profile.name]);
      }
    });

  }

}
