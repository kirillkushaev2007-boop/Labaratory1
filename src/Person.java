public class Person {
    private String name;
    private String address;



    public Person(String name, String address) {
        if (name==null || address==null)
            throw new IllegalArgumentException("Ошибка: Имя и адрес должны быть обязательно заполнены!");
        this.name=name;
        this.address=address;
    }


}

