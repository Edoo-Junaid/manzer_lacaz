import { Component, OnInit } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup } from '@angular/forms';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { DashboardChartsData, IChartProps } from './dashboard-charts-data';
import {UserService} from "../../user/user.service";

interface IUser {
  day: string;
  state: string;
  registered: string;
  country: string;
  usage: number;
  period: string;
  payment: string;
  activity: string;
  avatar: string;
  status: string;
  color: string;
}

@Component({
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  option!: string;
  payment!: string;
  formData!: FormGroup;



  // form = new FormGroup({
  //   optionMon: new FormControl('', Validators.required),
  //   optionTue: new FormControl('', Validators.required),
  //   optionWed: new FormControl('', Validators.required),
  //   optionThu: new FormControl('', Validators.required),
  //   optionFri: new FormControl('', Validators.required)
  // });
  //
  // get f(){
  //   return this.form.controls;
  // }
  //
  // submit(){
  //   console.log(this.form.value);
  // }

  constructor(private chartsData: DashboardChartsData, public userService: UserService) {
  }

  public uorder: IUser[] = [
    {
      day: 'Monday',
      state: 'New',
      registered: 'Jan 1, 2021',
      country: 'Us',
      usage: 50,
      period: 'Jun 11, 2021 - Jul 10, 2021',
      payment: 'Mastercard',
      activity: '10 sec ago',
      avatar: './assets/img/avatars/1.jpg',
      status: 'success',
      color: 'success'
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
      optionFri: new FormControl()
    });

    this.initCharts();
  }

  //Submit btn
  onClickSubmit(data: any) {
    //so that the values do not remain null
    //mapping
    const order ={
      id: 2,
      //Radio buttons: veg & non-veg
      optionMon: data.optionMon,
      optionTue: data.optionTu,
      optionWed: data.optionWed,
      optionThu: data.optionThu,
      optionFri: data.optionFri
    };

    //to test
    console.log(order)

    // faire appel à l'api
    // this.userService.postOrder(order).subscribe((data: any) => {
    //   console.log('message::::', data);
    //   this.user.nom = data.nom,
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
    // });
  }

  initCharts(): void {
    this.mainChart = this.chartsData.mainChart;
  }

  setTrafficPeriod(value: string): void {
    this.trafficRadioGroup.setValue({ trafficRadio: value });
    this.chartsData.initMainChart(value);
    this.initCharts();
  }
}
