public class Pickup implements Delivery_method {
    private final int COST_PICKUP=10;  // Стоимость доставки

    @Override
    public int get_time(){
        return BASE_PREPARATION_TIME;
    }
    @Override
    public int calculate_cost(int cost_order){
        return cost_order+COST_PICKUP;
    }

}
