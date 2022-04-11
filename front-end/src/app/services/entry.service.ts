import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import Entry from '../model/Entry';

@Injectable({
  providedIn: 'root',
})
export class EntryService {
  baseUrl = environment.apiUrl;

  constructor(private httpClient: HttpClient) {}

  createEntry(data: any): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}/entries`, data,  { observe: 'response' });
  }

  updateEntry(data:any): Observable<any>{
    return this.httpClient.put(`${this.baseUrl}/entries`, data,  { observe: 'response' });
  }

  getEntryById(id: number): Observable<Entry> {
    return this.httpClient.get<Entry>(`${this.baseUrl}/entries/${id}`);
  }
}
