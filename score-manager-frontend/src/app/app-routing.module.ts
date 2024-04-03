import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'scores',
    pathMatch: 'full'
  },
  {
    path: 'scores',
    loadChildren: () => import('./scores/scores.module').then(m => m.ScoresModule)
  },
  {
    path: 'instruments',
    loadChildren: () => import('./instruments/instruments.module').then(m => m.InstrumentsModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
