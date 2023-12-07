import { Component } from '@angular/core';
import {CardComponent} from "../card/card.component";
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-tableau-card',
  standalone: true,
    imports: [
        CardComponent,
        RouterOutlet
    ],
  templateUrl: './tableau-card.component.html',
  styleUrl: './tableau-card.component.css'
})
export class TableauCardComponent {

}
