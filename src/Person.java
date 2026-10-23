public class Person {
    //enum number_of_statuses{}
    String name;
    String address;
    int money;
    //Order_history orderHistory;

    public Person (int all)
    {
        throw new IllegalArgumentException("Пожалуйста введите как минимум 2 значения <Имя>, <Адрес>");
    }
    public Person (String all)
    {
        throw new IllegalArgumentException("Пожалуйста введите как минимум 2 значения <Имя>, <Адрес>");
    }
    public Person(String name, String address, int money) {
        if (name==null || address==null)
            throw new IllegalArgumentException("Ошибка: Имя и адрес должны быть обязательно заполнены!");
        this.name=name;
        this.address=address;
        this.money=money;
        //this.orderHistory = new Order_history();
    }
    public Person (String name, String address){
        this(name, address, 0);
    }
    void add_money(int other_money)
    {
        this.money+=other_money;
    }

}

