import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {InstrumentsListComponent} from "./instruments-list/instruments-list.component";

const routes: Routes = [
  {path: '', component: InstrumentsListComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InstrumentsRoutingModule {
}
