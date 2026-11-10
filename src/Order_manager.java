public class Order_manager {
    private Courier[] courier_array=new Courier [10];
    private Order [] order_history=new Order [10];
    private int count_orders=0;
    public Order_manager() {
        courier_array[0] = new Courier("Иван", 150);
        courier_array[1] = new Courier("Петр", 200);
        courier_array[2] = new Courier("Анна", 250);
    }
    public void create_new_order(Person client)
    {
        order_history[count_orders]=new Order(count_orders, client);
        count_orders++;
    }
    void assign_courier_to_order() {
        if (count_orders == 0) {
            System.out.println("Ошибка: курьер не может прийти, если заказов ещё нет");
            return;
        }
        Order target_order = order_history[count_orders - 1];
        Courier found_courier = null;
        for (int i = 0; i < courier_array.length; i++) {
            if (courier_array[i] != null) {
                if (courier_array[i].get_is_free() == true) {
                    found_courier = courier_array[i];
                    break;
                }
            }

        }
        if (found_courier == null) {
            System.out.println("Свободные курьеры закончились(");
            return;
        }
        found_courier.set_free(false);
        Delivery_method selected_delivery = target_order.get_delivery();
        selected_delivery.assign_courier(found_courier);
            target_order.change_status(Status_order.SHIPPED);
    }
}
