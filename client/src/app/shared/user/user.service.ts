import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public API = '//localhost:8080';

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.API+'/teammembers');
  }

  getUsersWithskillLevel(level: any): Observable<any> {
    if(level.skillLevel) {
      return this.http.get(this.API+'/teammembers/' + level.skillLevel);
    } else {
      return this.http.get(this.API+'/teammembers');
    }
  }

  get(id: string) {
    return this.http.get(this.API + '/teammember/' + id);
  }

  save(user: any): Observable<any> {

    let result: Observable<any>;
    if (user.id) {
      result = this.http.put(this.API + '/teammember/' + user.id, user);
    } else {
      result = this.http.post(this.API + '/teammember', user);
    }
    return result;
  }
}
