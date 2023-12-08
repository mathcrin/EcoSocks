import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Carte} from "../composants/card/card.component";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class CarteService {

  constructor(private http: HttpClient) { }

  getCartes(): Observable<Carte> {
    return this.http.get<Carte>('http://localhost:8080/cartes/');
  }
}
