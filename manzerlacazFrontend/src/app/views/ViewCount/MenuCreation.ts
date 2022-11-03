export class MenuCreation{
  description: string ;
  price:string;
  day:string
  option:string;
  weekNum: number;

  constructor(description: string, price: string, day: string, option: string, weekNum: number) {
    this.description = description;
    this.price = price;
    this.day = day;
    this.option = option;
    this.weekNum =  weekNum;
  }
}
