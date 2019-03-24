/* Product.java */

public class Product{
    protected String name;
    protected double price;
    
    public void setName(String newName){
        this.name = newName;
    }
    
    public String getName(){
        return this.name;
    }
    
    public double getPrice(){
        return this.price;
    }
    
    public void setPrice(double newPrice){
        this.price = newPrice;
    }
    
    public Product(String aName, double aPrice){
        this.name = aName;
        this.price = aPrice;
    }
    
}
