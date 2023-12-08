import {Component, Input} from '@angular/core';
import {NgIf} from "@angular/common";

export interface Carte {
  name: string;
  id: number;
  imageURL: string;
  emissionCO2: number;
}
@Component({
  selector: 'app-card',
  standalone: true,
  imports: [
    NgIf
  ],
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent {
  constructor() {
    this.carte = {
      id: 0,
      name: 'error 404',
      imageURL: 'https://http.dog/404.jpg',
      emissionCO2: 0
    };
  }
  @Input() carte?: Carte;
}