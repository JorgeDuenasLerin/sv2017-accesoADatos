package keyboard;

//Mario Belso 
public class Keyboard implements java.io.Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 80186L;
    private String brand;
    private String model;
    private boolean mechanical;
    private float price;

    public Keyboard() {
        
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
    public boolean isMechanical() {
        return mechanical;
    }

    public void setMechanical(boolean mechanical) {
        this.mechanical = mechanical;
    }

    
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
