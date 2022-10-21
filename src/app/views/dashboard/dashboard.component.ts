import { Component, OnInit } from '@angular/core';
import {UntypedFormControl, UntypedFormGroup} from '@angular/forms';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { DashboardChartsData, IChartProps } from './dashboard-charts-data';
import {UserService} from "../../user/user.service";

interface IOrder {
  user_id: number;
  menu_id: number;
  payment: number;
  option: string
}

@Component({
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  option!: string;
  payment!: string;
  formData!: FormGroup;

  constructor(private chartsData: DashboardChartsData, public userService: UserService) {
  }

  public userOrder: IOrder[] = [
    {
      user_id: 0,
      menu_id: 0,
      payment: 0,
      option: ''
    }
  ];
  public mainChart: IChartProps = {};
  public chart: Array<IChartProps> = [];
  public trafficRadioGroup = new UntypedFormGroup({
    trafficRadio: new UntypedFormControl('Month')
  });

  ngOnInit(): void {
    //Form variables
    this.formData = new FormGroup({
      //Radio buttons: veg & non-veg
      optionMon: new FormControl(),
      optionTue: new FormControl(),
      optionWed: new FormControl(),
      optionThu: new FormControl(),
      optionFri: new FormControl(),
      //Checkbox paid
      paymentMon: new FormControl(),
      paymentTue: new FormControl(),
      paymentWed: new FormControl(),
      paymentThu: new FormControl(),
      paymentFri: new FormControl()
    });

    this.initCharts();
  }

  //Reset btn
  onClickReset(data: any){
    this.formData.reset();
  }

  //Submit btn
  onClickSubmit(data: any) {
    //so that the values do not remain null
    //mapping
    const order ={
      id: 2,
      //Radio buttons: veg & non-veg
      optionMon: data.optionMon,
      optionTue: data.optionTue,
      optionWed: data.optionWed,
      optionThu: data.optionThu,
      optionFri: data.optionFri,
      //Checkbox paid
      paymentMon: data.paymentMon,
      paymentTue: data.paymentTue,
      paymentWed: data.paymentWed,
      paymentThu: data.paymentThu,
      paymentFri: data.paymentFri
    };

    //to test
    console.log(order)

    // faire appel à l'api
    this.userService.postOrder(order).subscribe((data: IOrder[]) => {
      console.log('message::::', data);
      this.userOrder = data

    //     this.user.prenom = data.prenom,
    //     this.user.sexe = data.sexe,
    //     this.user.dateDeNaissance = data.dateDeNaissance,
    //     //to get the response inside DetailsDeces => data.detailsDeces....
    //     this.user.codelieuDeNaissance = data.detailsDeces.codeLieuDeNaissance,
    //     this.user.communeDeNaissance = data.detailsDeces.communeDeNaissance,
    //     this.user.paysDeNaissance = data.detailsDeces.paysDeNaissance,
    //     this.user.dateDeDeces = data.detailsDeces.dateDeDeces,
    //     this.user.codeLieuDeDeces = data.detailsDeces.codeLieuDeDeces,
    //     this.user.acteDeDeces = data.detailsDeces.acteDeDeces
    //
    //   this.formData.reset();
    });
  }

  initCharts(): void {
    this.mainChart = this.chartsData.mainChart;
  }

  // setTrafficPeriod(value: string): void {
  //   this.trafficRadioGroup.setValue({ trafficRadio: value });
  //   this.chartsData.initMainChart(value);
  //   this.initCharts();
  // }
}
