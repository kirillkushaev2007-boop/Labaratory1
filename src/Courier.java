public class Courier {
    private String name;        //Имя
    private boolean is_free=true;       //Свободен или нет
    private int order_fee;              //Зарплата за заказ

    public Courier(String name, int order_fee)
    {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Имя курьера не может быть пустым");
        if (order_fee < 0)
            throw new IllegalArgumentException("Зарплата курьера не может быть меньше 0");
        this.name=name;
        this.order_fee=order_fee;
    }

    public int get_order_fee(){
        return this.order_fee;
    }

    public void set_free(boolean x){
        this.is_free=x;
    }
}

