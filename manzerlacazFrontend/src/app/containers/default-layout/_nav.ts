import { INavData } from '@coreui/angular';

export const navItems: INavData[] = [
  {
    name: 'Add Order',
    url: '/dashboard',
    iconComponent: { name: 'cil-speedometer' },

  },
  {
    name: 'Add Menu',
    url: '/charts',
    iconComponent: { name: 'cil-chart-pie' }
  },
  {
    name: 'View Order Count',
    url: '/viewCount',
    iconComponent: { name: 'cil-chart-pie' }
  },

];
