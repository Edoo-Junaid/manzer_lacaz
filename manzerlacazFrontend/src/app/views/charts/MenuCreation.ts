export class MenuCreation{
  description: string ;
  price:string;
  day:string
  option:string;

  constructor(description: string, price: string, day: string, option: string) {
    this.description = description;
    this.price = price;
    this.day = day;
    this.option = option;
  }
}
