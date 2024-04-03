import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ScoresRoutingModule } from './scores-routing.module';
import { ScoresListComponent } from './scores-list/scores-list.component';


@NgModule({
  declarations: [
    ScoresListComponent
  ],
  imports: [
    CommonModule,
    ScoresRoutingModule
  ]
})
export class ScoresModule { }
