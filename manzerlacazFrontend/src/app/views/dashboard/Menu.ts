export class Menu{
  id: number;
  description: string ;
  price:string;
  day:string
  option:string;

  constructor(id: number, description: string, price: string, day: string, option: string) {
    this.id = id;
    this.description = description;
    this.price = price;
    this.day = day;
    this.option = option;
  }
}
