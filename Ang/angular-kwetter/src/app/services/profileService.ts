import {Profile} from './../Models/profile';
import {Kwet} from './../Models/kwet';
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class ProfileService {
  url = 'http://localhost:8080/Kwetter/api/profiles/';

  constructor(private http: HttpClient) {
  }

  getPorfile(username : string) : Observable<Profile>{
    return this.http.get<Profile>(this.url + 'byname/' + username);
  }
}
