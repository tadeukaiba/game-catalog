import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private REST_API_SERVER = "http://localhost:8090/v1/games";

  constructor(private httpClient: HttpClient) { }

  getGames(){
    return this.httpClient.get(this.REST_API_SERVER);
  }

}
