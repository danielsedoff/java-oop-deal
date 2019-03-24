/* Party.java */

public class Party{
    private String name;
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String newName){
        this.name = newName;
    }
    
    public Party(String partyName){
        this.name = partyName;
    }
    
}
