import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ScoresListComponent} from "./scores-list/scores-list.component";

const routes: Routes = [
  {
    path: '',
    component: ScoresListComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ScoresRoutingModule {
}
