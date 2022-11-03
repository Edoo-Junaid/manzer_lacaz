import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ViewcountComponent } from './viewcount.component';

const routes: Routes = [
  {
    path: '',
    component: ViewcountComponent,
    data: {
      title: 'View Order Count',
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ViewcountRoutingModule {}

