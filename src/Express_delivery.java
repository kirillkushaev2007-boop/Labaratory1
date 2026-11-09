public class Express_delivery implements Delivery_method {
    Courier express_courier;

    @Override
    public int get_time() {
        return 10 + BASE_PREPARATION_TIME;
    }

    @Override
    public int calculate_cost(int cost_order) {
        if (express_courier!=null)
            return cost_order + (express_courier.get_order_fee()*2);
        else return cost_order;
    }

    public void assign_courier(Courier courier)
    {
        if (courier ==null)
            throw new IllegalArgumentException("Нельзя назначить пустого курьера");
        this.express_courier=courier;
    }
}

