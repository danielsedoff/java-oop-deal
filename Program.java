/* Program.java */

import java.util.Scanner;
import java.util.Locale;

public class Program{
    
    private static Product[] allProducts = new Product[65535];
    private static int productCounter = -1;
    
    private static Party[] allParties = new Party[65535];
    private static int partyCounter = -1;

    private static Deal[] allDeals = new Deal[65535];
    private static int dealCounter = -1;
    
    public static void main(String[] args){
        int choice = 0;
        while(true){
            choice = (int) dReadKeyb(
                "\n" + 
                "DealScribbler > Type digit and hit enter.\n" +
                "(1) New party      (2) List parties\n" +
                "(3) New product    (4) List products\n" +
                "(5) New deal       (6) List deals\n" +
                "(7) Change party   (8) Change product\n" +
                "(9) Exit");
            
            switch(choice){

                case 1: inputParty(); break;    // ready 
                case 2: listParties(); break;   // ready 
                case 3: inputProduct(); break;  // ready 
                case 4: listProducts(); break;  // ready 
                case 5: newDeal(); break;       // ready
                case 6: listDeals(); break;     // ready
                case 7: changeParty(); break;   // ready
                case 8: changeProduct(); break; // ready
                case 9: System.exit(0); break;  // ready
                }
        }
    }
    
    public static void changeParty(){
	Party seller = chooseParty("Changing a party.");
	seller.setName(szReadKeyb("Input new name:"));
    }
    
    public static void changeProduct(){
	System.out.println("Changing a product.");
        Product product = chooseProduct();
	System.out.println(product.getName() + " : " + product.getPrice());
	product.setName(szReadKeyb("Input new name:"));
	product.setPrice(dReadKeyb("Input new price:"));
    }    
    
    public static void newDeal(){
        Party seller; 
        Party buyer;
        boolean inputMore = false;
        Product[] products = new Product[65535];
        double[] quantity = new double[65535];
        double productQuantity = 0.0;
        int listLength = -1;
        
        seller = chooseParty("Choosing a seller.");
        buyer  = chooseParty("Choosing a buyer.");
        
        do{
            try{
                listLength ++;
                products[listLength] = chooseProduct();
                productQuantity = dReadKeyb(
                "Input product quantity");
                quantity[listLength] = productQuantity;
                inputMore = key01(
                "Input next product? 0 = NO, 1 = YES");
            } catch(Exception e){ 
                System.out.println(
                "Wrong input. No deal written.");
                return;
                }
        }while(inputMore);
        
        dealCounter ++;
        allDeals[dealCounter] = new Deal(
            buyer, seller, products, quantity, listLength);
    }
    
    public static void listDeals(){
        int counter = 0; 
        int j = 0;
        for(Deal aDeal : allDeals){
            j = 0;
            if(counter > dealCounter) break;
            System.out.print(aDeal.getDate() + " | ");
            System.out.print(aDeal.getSeller().getName() + " -> " + 
                aDeal.getBuyer().getName() + " : ");
            for(Product pr : aDeal.getProducts()){
                if(j > aDeal.getListLength()) break;
                System.out.print("+ " + aDeal.getProducts()[j].getName());
                System.out.print(" x " + aDeal.getQuantity()[j]);
                j ++;
            }
            System.out.println(" = " + aDeal.getCost());
            counter ++;
        }
    }

    public static boolean key01(String prompt){
        int answer = 2;
        while(true){
            answer = (int) dReadKeyb(prompt);
            if(answer == 1) return true;
            if(answer == 0) return false;
        }
    }
    
    public static Product chooseProduct(){
        String szIn = szReadKeyb("Hit enter to see the product list.");
        listProducts();
        return allProducts[(int) dReadKeyb("Please input product number.")];
    }

    public static Party chooseParty(String prompt){
        String szIn = szReadKeyb(prompt + "\nHit enter to see the party list.");
        listParties();
        return allParties[(int) dReadKeyb("Please input party number.")];
    }
    
    public static void inputParty(){
        Party aParty = new Party (
            szReadKeyb("Input party name and hit enter."));
        partyCounter ++;
        allParties[partyCounter] = aParty;
    }
    
    public static void inputProduct(){
        String newName = "";
        double newPrice = 0.0;
        
        newName = szReadKeyb("Input product name and hit enter.");
        newPrice = dReadKeyb("Input product price and hit enter.\n" + 
                             "Use dot separation like this: 9.25");
        Product aNewProduct = new Product(newName, newPrice);
        productCounter ++;
        allProducts[productCounter] = aNewProduct;
    }   
    
    public static void listParties(){
        int ctr = 0;
        for(Party aParty: allParties){
            try{
                System.out.println(ctr + ") " + 
                aParty.getName());
                ctr ++;
            } catch(Exception e){
                break;
            }
        }
    }
    
    public static void listProducts(){
        int ctr = 0;
        for(Product aProduct: allProducts){
            try{
                System.out.println(ctr + ") " + 
                aProduct.getName() + " --- " + 
                aProduct.getPrice());
                ctr ++;
            } catch(Exception e){
                break;
            }
        }
    }
    
    public static String szReadKeyb(String prompt){
        System.out.println(prompt);
        Scanner scan1 = new Scanner(System.in);
        return scan1.nextLine();
    }
    
    public static double dReadKeyb(String prompt){
        System.out.println(prompt);
        String szInput;
        Double result = -42.0;
        boolean resultAvailable;
        
        Scanner scan1 = new Scanner(System.in);
            resultAvailable = false;
            while(!resultAvailable){
                try{
                    szInput = scan1.nextLine();
                    result = Double.valueOf(szInput);
                    resultAvailable = true;
                } catch (Exception e){
                    System.out.println(
             "Sorry, I got an incorrect value. Please retry."
                                      );
                    System.out.println(prompt);
                }
            }
            return result;
    }
}
