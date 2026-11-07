public class Position {
    private String name;        // название товара
    private int cost;           // стоимость 1 единицы товара
    private int quantity;         // количество товара
    public Position(int number, String name, int cost)
    {
        if (name== null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Ошибка: Название товара не может быть пустым");
        }
        if (number<=0 || cost<0)
            throw new IllegalArgumentException("Ошибка: Количество должно быть > 0, а цена >= 0!");
        this.quantity =number;
        this.name=name;
        this.cost=cost;
    }
    public int getQuantity() {return this.quantity;}
    public int getCost() {return this.cost;}
    public String getName() {return this.name;}
}
