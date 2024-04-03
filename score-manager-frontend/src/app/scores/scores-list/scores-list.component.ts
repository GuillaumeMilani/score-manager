import { Component } from '@angular/core';
import {ScoresService} from "../../../api";

@Component({
  selector: 'app-scores-list',
  templateUrl: './scores-list.component.html',
  styleUrls: ['./scores-list.component.scss']
})
export class ScoresListComponent {

  constructor(private scoresService: ScoresService) {
    this.getScores();
  }

  getScores() {
    this.scoresService.getAllScores().subscribe(scores => {
      console.log(scores);
    });
  }
}
