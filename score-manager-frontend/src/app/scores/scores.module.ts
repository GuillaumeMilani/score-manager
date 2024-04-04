import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ScoresRoutingModule} from './scores-routing.module';
import {ScoresListComponent} from './scores-list/scores-list.component';
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {MatPaginatorModule} from "@angular/material/paginator";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MinuteSecondsPipe} from "../pipes/minutes-seconds.pipe";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import { ScoresFormComponent } from './scores-form/scores-form.component';
import {MatCardModule} from "@angular/material/card";
import {DurationSecondsComponent} from "./scores-form/duration-seconds.component";


@NgModule({
  declarations: [
    ScoresListComponent,
    ScoresFormComponent
  ],
    imports: [
        CommonModule,
        ScoresRoutingModule,
        MatTableModule,
        MatSortModule,
        MatPaginatorModule,
        FormsModule,
        MatInputModule,
        ReactiveFormsModule,
        MinuteSecondsPipe,
        MatButtonModule,
        MatIconModule,
        MatCardModule,
        DurationSecondsComponent
    ]
})
export class ScoresModule {
}
