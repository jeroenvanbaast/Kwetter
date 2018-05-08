import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Kwet} from "../models/kwet";
import {Profile} from "../models/profile";
import {Observable} from "rxjs/Observable";
import { DatePipe } from '@angular/common';

@Injectable()
export class KwetService {
  url = 'http://localhost:8080/Kwetter/api/kwets';

  constructor(private http: HttpClient) {
  }

  sendKwet(profile: Profile, message: string) : Observable<Kwet> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    const params = new HttpParams()
      .set('message', message)
      .set('profileId', String(profile.id));
    const options = {
      headers,
      params
    };
    console.log(this.url + params.get('message') + params.get('profileId'))
    return this.http.put<Kwet>(this.url,null, options);
  }

}
