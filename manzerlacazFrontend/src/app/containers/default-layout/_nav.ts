import { INavData } from '@coreui/angular';

export const navItems: INavData[] = [
  {
    name: 'Add Order',
    url: '/dashboard',
    iconComponent: { name: 'cil-speedometer' },
    badge: {
      color: 'info',
      text: 'NEW'
    }
  },
  {
    name: 'Add Menu',
    url: '/charts',
    iconComponent: { name: 'cil-chart-pie' }
  }

];
