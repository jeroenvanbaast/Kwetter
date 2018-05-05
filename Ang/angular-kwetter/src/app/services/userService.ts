import {User} from './../Models/user';
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class UserService {
  url = 'http://localhost:8080/Kwetter/api/users/';

  constructor(private http: HttpClient) {
  }

  getUserByProfileName(profileName: string) : Observable<User> {
    return this.http.get<User>(this.url + 'byprofilename/' + profileName);
  }
}
