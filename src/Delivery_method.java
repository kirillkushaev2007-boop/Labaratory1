public interface Delivery_method {
    int BASE_PREPARATION_TIME =20;
    int get_time();
    int calculate_cost(int cost_order);
    void assign_courier(Courier courier);
}
