public class Person {
    private String name;
    private int balance;
    private String address;



    public Person(String name, String address, int balance) {
        if (name==null || address==null)
            throw new IllegalArgumentException("Ошибка: Имя и адрес должны быть обязательно заполнены!");
        if (balance < 0)
            throw new IllegalArgumentException("Ошибка: Баланс клиента не может быть меньше 0");
        this.name=name;
        this.address=address;
        this.balance = balance;
    }

    public int get_balance(){
        return this.balance;
    }
    public void minus_money(int cost) {this.balance=this.balance-cost;}
    public void plus_money(int cost) {this.balance=this.balance+cost;}

}

