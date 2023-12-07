import { Component } from '@angular/core';
import {NgOptimizedImage} from "@angular/common";
export interface ObjetQuotidien {
  nom: string;
  url: string;
  emission: number;
}
@Component({
  selector: 'app-card',
  standalone: true,
  imports: [
    NgOptimizedImage
  ],
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent {

}
