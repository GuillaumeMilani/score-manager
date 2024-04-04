import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {InstrumentsRoutingModule} from './instruments-routing.module';
import {InstrumentsListComponent} from "./instruments-list/instruments-list.component";
import {MatFormField} from "@angular/material/form-field";
import {ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {MatIconModule} from "@angular/material/icon";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatFabButton, MatIconButton} from "@angular/material/button";


@NgModule({
  declarations: [
    InstrumentsListComponent
  ],
  imports: [
    CommonModule,
    InstrumentsRoutingModule,
    MatFormField,
    ReactiveFormsModule,
    MatSortModule,
    MatIconModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatFabButton,
    MatIconButton,
  ]
})
export class InstrumentsModule {
}
