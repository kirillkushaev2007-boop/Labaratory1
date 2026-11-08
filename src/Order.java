public class Order  {
    private final int ARRAY_LIMIT=20;
    private int count_position;
    private Status_order status;
    private int id;                     // номер заказа
    private int cost_order;             // сумма (в деньгах) на которую сделан один заказ
    private Person client;              // Клиент
    private Position [] pos_array;      // список позиций в заказе
    private DeliveryMethod delevery;
    public Order(int id, Person client){
        if (id <0)
            throw new IllegalArgumentException("id заказа не может быть меньше 0");
        if (client == null)
            throw new IllegalArgumentException("У заказа должен быть клиент");
        this.id=id;
        this.pos_array = new Position[ARRAY_LIMIT];
        this.cost_order =0;
        this.count_position=0;
        this.status = Status_order.ORDERED;
        this.client = client;
    }

    public int get_cost_order(){
        return this.cost_order;
    }

    public int get_id(){
        return this.id;
    }
    public void add_pos(Position position)
    {
        if (position==null)
            throw new IllegalArgumentException("Нельзя добавить пустую позицию");
        if (count_position< ARRAY_LIMIT) {
            pos_array[count_position++] = position;
            this.cost_order+=position.getCost()*position.getQuantity();
        }
        else throw new IllegalStateException("Превышен лимит позиций в одном заказе: " + ARRAY_LIMIT);
    }

    public void change_status(Status_order new_status) {
        if (this.status == Status_order.ORDERED && new_status == Status_order.SHIPPED) {
            this.status = new_status;
        } else if (this.status == Status_order.SHIPPED && new_status == Status_order.ACCEPTED){
            this.status = new_status;
        } else {
            throw new IllegalStateException("Недопустимый переход статуса с " + this.status + " на " +new_status);
        }
    }
}
