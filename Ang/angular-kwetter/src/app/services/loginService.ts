import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {User} from '../models/user';
import {Profile} from '../models/profile';
import {HttpClient, HttpParams} from '@angular/common/http';


@Injectable()
export class LoginService {
  url = 'http://localhost:8080/Kwetter/api/users/';
  public token: string;

  constructor(private http: HttpClient) {
    // set token if saved in local storage
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
  }

  Login(userName: string, password: string): Observable<User> {
    let params: HttpParams = new HttpParams();
    params = params.set('username', userName);
    params = params.set('password', password);
    return this.http.get<User>(this.url + 'login/', {params: params});
  }
}
