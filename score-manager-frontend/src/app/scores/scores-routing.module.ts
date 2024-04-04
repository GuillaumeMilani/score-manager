import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ScoresListComponent} from "./scores-list/scores-list.component";
import {ScoresFormComponent} from "./scores-form/scores-form.component";

const routes: Routes = [
  {
    path: '',
    component: ScoresListComponent
  },
  {
    path: 'create',
    component: ScoresFormComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ScoresRoutingModule {
}
