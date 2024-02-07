package ad.food2.Dto;

import java.math.BigDecimal;

public class DtoCoast {

    private int allDishesCoast;
    private int deliveryCoast;
    private int allCoast;
    private boolean flag;

    public DtoCoast() {
    }

    public DtoCoast(int allDishesCoast, int deliveryCoast, int allCoast, boolean flag) {
        this.allDishesCoast = allDishesCoast;
        this.deliveryCoast = deliveryCoast;
        this.allCoast = allCoast;
        this.flag = flag;
    }

    public int getAllDishesCoast() {
        return this.allDishesCoast;
    }

    public void setAllDishesCoast(int allDishesCoast) {
        this.allDishesCoast = allDishesCoast;
    }

    public int getDeliveryCoast() {
        return this.deliveryCoast;
    }

    public void setDeliveryCoast(int deliveryCoast) {
        this.deliveryCoast = deliveryCoast;
    }

    public int getAllCoast() {
        return this.allCoast;
    }

    public void setAllCoast(int allCoast) {
        this.allCoast = allCoast;
    }

    public boolean isFlag() {
        return this.flag;
    }

    public boolean getFlag() {
        return this.flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
