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
    public Order get_last_order(){return order_history[count_orders-1];}
    public Order [] get_order_history(){return order_history;}
    public int get_count_orders() {return count_orders;}
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
    public void complete_order() {
        if (count_orders == 0) {
            System.out.println("Ошибка: Нет активных заказов для завершения!");
            return;
        }

        Order current_order = order_history[count_orders - 1];
        int food_cost = current_order.get_cost_order();
        Delivery_method current_delivery = current_order.get_delivery();

        if (current_delivery == null) {
            System.out.println("Ошибка: Сначала выберите способ доставки!");
            return;
        }

        int total_cost = current_delivery.calculate_cost(food_cost);
        Person client = current_order.get_client();

        if (client.get_balance()+current_order.get_cost_order() < total_cost) {
            System.out.println("Ошибка: Недостаточно средств на балансе!");
            System.out.println("Стоимость заказа: " + total_cost + " руб. Ваш баланс: "
                    + client.get_balance()+current_order.get_cost_order() + " руб.");
            return;
        }

        client.minus_money(total_cost);
        current_order.change_status(Status_order.ACCEPTED);

        System.out.println("\n=== ЗАКАЗ УСПЕШНО ОПЛАЧЕН И ПОЛУЧЕН ===");
        System.out.println("Списано с баланса: " + total_cost + " руб.");
        System.out.println("Остаток на счете " + ": " + client.get_balance() + " руб.");
        System.out.println("Статус заказа изменен на: ACCEPTED");
    }
}
