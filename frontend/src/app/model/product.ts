export class Product {

  constructor(
    public id?: string,
    public sku?: string,
    public name?: string,
    public description?: string,
    public unitPrice?: number,
    public imageUrl?: string,
    public active?: boolean,
    public unitsInStock?: number,
    public unitsSold?: number,
    public createdAt?: Date,
    public updatedAt?: Date
  ) {
    // You can also initialize fields here if needed
  }
}
