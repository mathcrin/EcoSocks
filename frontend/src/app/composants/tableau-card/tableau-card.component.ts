import {Component, EventEmitter, Input, Output} from '@angular/core';
import {CardComponent, Carte} from "../card/card.component";
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


    @Input() carteGauche?: Carte;
    @Input() carteDroite?: Carte;
    @Input() animationState?: string;
    @Output() cardClick = new EventEmitter<Carte>();
    @Output() droite = new EventEmitter<Boolean>();
    @Output() gauche = new EventEmitter<Boolean>();

    onCardClick(carte?: Carte) {
        this.cardClick.emit(carte);
    }

    onDroite(droite?: boolean) {
        this.droite.emit(droite);
    }

    onGauche(gauche?: boolean) {
        this.gauche.emit(gauche);
    }
}

