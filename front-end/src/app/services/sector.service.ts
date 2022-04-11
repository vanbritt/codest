import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import Sector from '../model/Sector';

@Injectable({
  providedIn: 'root'
})
export class SectorService {

  baseUrl = environment.apiUrl;


  constructor(private httpClient:HttpClient) { }

  getAllSectors(): Observable<Sector[]>{
    return this.httpClient.get<Sector[]>(`${this.baseUrl}/sectors`);
  }
}
