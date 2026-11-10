public class Standart_delivery implements Delivery_method {
    private Courier standart_courier;

    @Override
    public int get_time()
    {
        return BASE_PREPARATION_TIME*2;
    }
    @Override
    public int calculate_cost(int cost_order)
    {
        if (standart_courier!=null)
            return cost_order + (standart_courier.get_order_fee());
        else return cost_order;
    }
    @Override
    public void assign_courier(Courier courier)
    {
        if (courier==null)
            throw new IllegalArgumentException("Курьер не может быть пустым");
        else this.standart_courier=courier;
    }
}
