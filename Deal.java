/* Deal.java */

import java.util.Date;

public class Deal{
    private Party buyer;
    private Party seller;
    private Product[] products;
    private double[] quantity;
    private double dealCost;
    private int listLength;
    private Date date;
    
    public Deal(Party buyer, Party seller, Product[] products, double[] quantity, int listLength){
        this.date = new Date();
        this.buyer = buyer;
        this.seller = seller;
        this.products = products;
        this.quantity = quantity;
        this.listLength = listLength;
        this.dealCost = 0;
        for(int i = 0; i < listLength; ++i){
            dealCost += (this.products[i].getPrice() * this.quantity[i]);
        }
    }
    
    public double getCost(){
        return this.dealCost;
    }
    
    public Party getBuyer(){
        return this.buyer;
    }
    
    public Party getSeller(){
        return this.seller;
    }
    
    public Product[] getProducts(){
        return this.products;
    }
    
    public double[] getQuantity(){
        return this.quantity;
    }
    
    public int getListLength(){
        return this.listLength;
    }
    
    public Date getDate(){
        return this.date;
    }

}
