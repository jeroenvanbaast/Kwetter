import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Kwet} from '../models/kwet';
import {Profile} from '../models/profile';
import {Observable} from 'rxjs/Observable';
import {DatePipe} from '@angular/common';
import {Subscriber} from 'rxjs/Subscriber';

@Injectable()
export class KwetService {
  url = 'http://localhost:8080/Kwetter/api/kwets';
  private ws: WebSocket;

  constructor(private http: HttpClient) {
  }

  sendKwet(profile: Profile, message: string): Observable<Kwet> {
    this.send(message, profile.name);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    }).set('AUTHORIZATION', 'Bearer ' + localStorage.getItem('token'));
    const params = new HttpParams()
      .set('message', message)
    const options = {
      headers,
      params
    };
    return this.http.put<Kwet>(this.url, null, options);
  }

  createObservableSocket(url: string): Observable<any> {
    this.ws = new WebSocket(url);
    return new Observable(observer => {
      this.ws.onmessage = event => observer.next(
        console.log(event.data));
      this.ws.onerror = event => observer.error(event);
      this.ws.onclose = event => observer.complete();
      this.ws.onopen = event => {
        console.log('open');
      };

      return () => this.ws.close();
    });
  }

  send(message: any, name: string) {
    this.ws.send(JSON.stringify(message));
  }

}
