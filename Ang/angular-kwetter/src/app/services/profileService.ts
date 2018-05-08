import {Profile} from './../Models/profile';
import {Kwet} from './../Models/kwet';
import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class ProfileService {
  url = 'http://localhost:8080/Kwetter/api/profiles/';

  constructor(private http: HttpClient) {
  }

  getPorfile(username : string) : Observable<Profile>{
    return this.http.get<Profile>(this.url + 'byname/' + username);
  }

  getFollowers(profileId : string) : Observable<Profile[]>{
    return this.http.get<Profile[]>(this.url + 'getfollowers/' + profileId);
  }

  updateProfile(profileName : string, bio : string , location : string, website : string, picture : string) : Observable<Profile>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    }).set('AUTHORIZATION', 'Bearer ' + localStorage.getItem('token'));
    const params = new HttpParams()
      .set('name', profileName)
      .set('bio', bio)
      .set('locatie', location)
      .set('website', website)
      .set('picture', picture);
    const options = {
      headers,
      params
    };
    return this.http.post<Profile>(this.url + 'update', null, options);
  }

  follow(currentUserProfileId : string, profileId : string) : Observable<Profile>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    const params = new HttpParams()
      .set('followerid', profileId);
    const options = {
      headers,
      params
    };
    return this.http.post<Profile>(this.url + currentUserProfileId + '/follow', null, options);
  }

  likeKwet(currentUserProfileId : string, kwetId : string){
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    const params = new HttpParams()
      .set('kwetid', kwetId);
    const options = {
      headers,
      params
    };
  return this.http.post(this.url + currentUserProfileId + '/heart/' , null,options);
  }


}
