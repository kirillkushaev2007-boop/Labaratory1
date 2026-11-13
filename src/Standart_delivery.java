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
            return (standart_courier.get_order_fee());
        else return 0;
    }
    @Override
    public void assign_courier(Courier courier)
    {
        if (courier==null)
            throw new IllegalArgumentException("Курьер не может быть пустым");
        else this.standart_courier=courier;
    }
    @Override
    public Courier get_courier(){return standart_courier;}
}
