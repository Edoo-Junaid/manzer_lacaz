import {Component, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {OrderService} from "../../services/order/order.service";
import {MenuService} from "../../services/menu/menu.service";
import {Order} from "./Order";
import {Menu} from "./Menu";
import {GetMenuList} from "./GetMenuList";
import {DailyConfirmation} from "./DailyConfirmation";
import {GetOrderList} from "./GetOrderList";


@Component({
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  userId = localStorage.getItem('id');
  private existingMenuDescription = ['menuMon', 'menuTue', 'menuWed', 'menuThu', 'menuFri'];
  //---modal----
  public dayArray: string[] = new Array("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
  public visibleArr = [false, false, false, false, false];

  toggleLiveDemo(index: any) {
    this.visibleArr[index] = !this.visibleArr[index];
  }

  //------------
  public orderTotal!: number;
  private controlNamesMonday = ['optionMon', 'paymentMon'];
  private controlNamesTuesday = ['optionTue', 'paymentTue'];
  private controlNamesWednesday = ['optionWed', 'paymentWed'];
  private controlNamesThursday = ['optionThu', 'paymentThu'];
  private controlNamesFriday = ['optionFri', 'paymentFri'];

  orderOption!: string[];
  orderPayment!: number[];

  option1!: any[];
  payment1!: any[];
  option!: any[];
  payment!: any[];
  menu: Menu[] = new Array(5);
  menuDescriptions!: string[];
  priceDescriptions!: string[];
  optionDescriptions!: string[];
  days:string[]= ['Monday','Tuesday','Wednesday','Thursday','Friday'];
  formData!: FormGroup;
  visibleError!: boolean
  visible: any;

  constructor(public orderService: OrderService, public menuService: MenuService) {


  }

  ngOnInit(): void {

    //Form variables
    this.formData = new FormGroup({
      //Number area-input week number
      weekNo: new FormControl(),

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
      paymentFri: new FormControl(),
      //Bin buttons
      binMon: new FormControl(),
      binTue: new FormControl(),
      binWed: new FormControl(),
      binThu: new FormControl(),
      binFri: new FormControl()

    });

    this.formData.get('weekNo')?.setValue(47);
    this.changeInWeekNum(1)
  }

  changeInWeekNum($event: any) {
    this.formData.get("optionMon")?.setValue(null);
    this.formData.get("optionTue")?.setValue(null);
    this.formData.get("optionWed")?.setValue(null);
    this.formData.get("optionThu")?.setValue(null);
    this.formData.get("optionFri")?.setValue(null);
    this.formData.get("paymentMon")?.setValue(null);
    this.formData.get("paymentTue")?.setValue(null);
    this.formData.get("paymentWed")?.setValue(null);
    this.formData.get("paymentThu")?.setValue(null);
    this.formData.get("paymentFri")?.setValue(null);

    console.log("weekNum changes")
    const formData = this.formData.getRawValue();
    // console.log(formData);
    let weekNum = new GetMenuList(formData.weekNo);
    // console.log(weekNum);
    let subscription = this.menuService.getMenus(weekNum).subscribe((data: Array<Menu>) => {
      // console.log(data)
      if (data.length != 0) {
        for (const datum of data) {
          if (datum.day == "Monday") this.menu[0] = datum;
          if (datum.day == "Tuesday") this.menu[1] = datum;
          if (datum.day == "Wednesday") this.menu[2] = datum;
          if (datum.day == "Thursday") this.menu[3] = datum;
          if (datum.day == "Friday") this.menu[4] = datum;
        }
        this.menuDescriptions = [this.menu[0].description, this.menu[1].description, this.menu[2].description, this.menu[3].description, this.menu[4].description,]
        this.priceDescriptions = [this.menu[0].price, this.menu[1].price, this.menu[2].price, this.menu[3].price, this.menu[4].price,]
        this.optionDescriptions = [this.menu[0].option, this.menu[1].option, this.menu[2].option, this.menu[3].option, this.menu[4].option,]
        for (var i in this.menu) {
          let orders = new GetOrderList(Number(this.userId), this.menu[i].id);
          this.getWeekOrder(orders, i);
        }
      } else {
        for (var i in this.menuDescriptions) {
          this.menuDescriptions[i] = "";
          this.priceDescriptions[i] = "";
          this.optionDescriptions[i] = "";

        }
      }
    });



  }

  getWeekOrder(orders: GetOrderList, index: any) {
    //console.log(orders)
    this.orderService.getOrders(orders).subscribe((data: any) => {
      console.log(data);
      if (data != null) {
        for (let i = 0; i < this.menu.length; i++) {
          console.log(this.menu[i].id)
          if (data.menu_id == this.menu[i].id) {
            if(i==0){
              this.formData.get('optionMon')?.setValue(data.option);
              this.formData.get('paymentMon')?.setValue(data.payment)
            }else if(i==1){
              this.formData.get('optionTue')?.setValue(data.option);
              this.formData.get('paymentTue')?.setValue(data.payment)
            }else if(i==2){
              this.formData.get('optionWed')?.setValue(data.option);
              this.formData.get('paymentWed')?.setValue(data.payment)
            }else if(i==3){
              this.formData.get('optionThu')?.setValue(data.option);
              this.formData.get('paymentThu')?.setValue(data.payment)
            }else if (i == 4) {
              this.formData.get('optionFri')?.setValue(data.option);
              this.formData.get('paymentFri')?.setValue(data.payment)
            }
          }
        }
      }
    });
  }

//Bin icon: reset
  //Reset btn: resets option radio, payment checkbox and minus price from total when bin is clicked
  onClickResetMon(data: any) {

    if (data.optionMon != null && data.paymentMon != true) {
      let numberValue = Number(this.priceDescriptions[0]);
      this.orderTotal = this.orderTotal - numberValue;

    }
    this.controlNamesMonday.map((value: string) => this.formData.get(value)?.setValue(null));
    //delete order in database
    var menuId = this.menu[0].id;
    this.deleteOrder(this.userId, menuId);
  }

  onClickResetTue(data: any) {
    if (data.optionTue != null && data.paymentTue != true) {

      let numberValue = Number(this.priceDescriptions[1]);
      this.orderTotal = this.orderTotal - numberValue;
    }
    this.controlNamesTuesday.map((value: string) => this.formData.get(value)?.setValue(null));
    //delete order in database
    var menuId = this.menu[1].id;
    this.deleteOrder(this.userId, menuId);
  }

  onClickResetWed(data: any) {
    if (data.optionWed != null && data.paymentWed != true) {

      let numberValue = Number(this.priceDescriptions[2]);
      this.orderTotal = this.orderTotal - numberValue;
    }
    this.controlNamesWednesday.map((value: string) => this.formData.get(value)?.setValue(null));
    //delete order in database
    var menuId = this.menu[2].id;
    this.deleteOrder(this.userId, menuId);
  }

  onClickResetThu(data: any) {

    if (data.optionThu != null && data.paymentThu != true) {

      let numberValue = Number(this.priceDescriptions[3]);
      this.orderTotal = this.orderTotal - numberValue;
    }
    this.controlNamesThursday.map((value: string) => this.formData.get(value)?.setValue(null));
    //delete order in database
    var menuId = this.menu[3].id;
    this.deleteOrder(this.userId, menuId);
  }

  onClickResetFri(data: any) {
    if (data.optionFri != null && data.paymentFri != true) {

      let numberValue = Number(this.priceDescriptions[4]);
      this.orderTotal = this.orderTotal - numberValue;
    }
    this.controlNamesFriday.map((value: string) => this.formData.get(value)?.setValue(null));
    //delete order in database
    var menuId = this.menu[4].id;

    this.deleteOrder(this.userId, menuId);
  }

  //option selection
//When option is changed price is calculated taking into consideration if payment is made or not
  changeOption(data: any) {
    this.calcPrice(data)
  }

  calcPrice(data: any) {
    const radios = this.formData.getRawValue();
    //  console.log(radios)
    this.option = [radios.optionMon, radios.optionTue, radios.optionWed, radios.optionThu, radios.optionFri]
    this.payment = [radios.paymentMon, radios.paymentTue, radios.paymentWed, radios.paymentThu, radios.paymentFri]
    var count = 0
    var total = 0;
    while (count < this.option.length) {
      if ((this.option[count] != null || this.option[count] != undefined) && this.payment[count] == null) {
        let numberValue = Number(this.priceDescriptions[count]);
        total = total + numberValue
      }
      count++
    }
    // console.log(total)
    this.orderTotal = total;
  }


  //Payment checkbox when checked or unchecked
  changePaymentMon(data: any) {
    this.onClickPaymentMon(data)

  }

  changePaymentTue(data: any) {
    this.onClickPaymentTue(data)

  }

  changePaymentWed(data: any) {
    this.onClickPaymentWed(data)
  }

  changePaymentThu(data: any) {
    this.onClickPaymentThu(data)

  }

  changePaymentFri(data: any) {
    this.onClickPaymentFri(data)

  }

  onClickPaymentMon(data: any) {
    this.funcPayment(0)
  }

  onClickPaymentTue(data: any) {
    this.funcPayment(1)

  }

  onClickPaymentWed(data: any) {
    this.funcPayment(2)
  }

  onClickPaymentThu(data: any) {
    this.funcPayment(3)

  }

  onClickPaymentFri(data: any) {
    this.funcPayment(4)
  }

  funcPayment(num: number) {
    this.changeInForm()
    this.payment1 = [this.formData.getRawValue().paymentMon, this.formData.getRawValue().paymentTue, this.formData.getRawValue().paymentWed, this.formData.getRawValue().paymentThu, this.formData.getRawValue().paymentFri]
    this.option1 = [this.formData.getRawValue().optionMon, this.formData.getRawValue().optionTue, this.formData.getRawValue().optionWed, this.formData.getRawValue().optionThu, this.formData.getRawValue().optionFri]
    let numberValue = Number(this.priceDescriptions[num]);
    if (this.payment1[num] != false && this.option1[num] != null) {
      this.orderTotal = this.orderTotal - numberValue
    } else if (this.payment1[num] != true && this.option1[num] != null) {
      this.orderTotal = this.orderTotal + numberValue
    }
  }

  dismissible: any;

  changeInForm() {
    // console.log(this.menu)
    // console.log(this.formData.getRawValue());
    const {
      optionFri,
      optionMon,
      optionThu,
      optionTue,
      optionWed,
      paymentFri,
      paymentMon,
      paymentThu,
      paymentTue,
      paymentWed
    } = this.formData.getRawValue();
    //console.log(radios)
    this.orderOption = [optionMon, optionTue, optionWed, optionThu, optionFri];
    this.orderPayment = [paymentMon ? 1 : 0, paymentTue ? 1 : 0, paymentWed ? 1 : 0, paymentThu ? 1 : 0, paymentFri ? 1 : 0];
    let orders = new Array<Order>;
    for (let i in this.orderOption) {
      if (this.orderOption[i] != null) {
        let order = new Order(Number(this.userId), this.menu[i].id, this.orderPayment[i], this.orderOption[i]);
        orders.push(order);
      }
    }
    //  console.log(orders);
    // saving orders
    this.orderService.postOrder(orders).subscribe((response) => {
        let resSTR = JSON.stringify(response);
        let resJSON = JSON.parse(resSTR);

        // console.log('message::::', response);
        this.visible = true;
        setTimeout(() => {
          this.visible = false;
        }, 1500);
      },
      (error) => {
        console.log("error caught");
        this.visibleError = true;
      }
    )
  }

  deleteOrder(userId: any, menuId: any) {
    let deleteRequest = new DailyConfirmation(Number(userId), menuId)
    this.orderService.removeOrder(deleteRequest).subscribe((response) => {
      //
      //
      //
      // console.log(response)
    });
  }
}
