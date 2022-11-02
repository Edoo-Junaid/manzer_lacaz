import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ChartsComponent } from './charts.component';

const routes: Routes = [
  {
    path: '',
    component: ChartsComponent,
    data: {
      title: 'Add Menu',
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ChartsRoutingModule {}

