```mermaid
classDiagram
   %% Перечисление статусов (Statuses)
    class Status_order{
        <<enum>>
        ORDERED
        SHIPPED
        ACCEPTED
    }
    %% Позиция заказа (Position)
    class Position{
        -String name
        -int cost
        -int quantity
   }
   %% Клиент (Person)
   class Person{
       -String name
       -int balance
       -String address
   }
   %% Заказ (Order)
   class Order {
      -Status_order status
      -int id
      -int cost_order
      -Person client
      -Position[] pos_array
      -Delivery_method delivery
      +void add_pos(Position pos)
         %%Добавляет позицию в заказ
      +int get_cost_order()
         %%Считает стоимость всего заказа
   }
   %% Курьер (Courier)
   class Courier {
      -String name
      -boolean is_free
         %%Статус незанятости (true-свободен, false-занят)
      -int order_fee
         %%Зарплата за заказ
   }
   %% Интерфейс доставки (Delivery_method)
   class Delivery_method {
      + int get_time()
      + int calculate_cost (int cost_order)
   }
   %% Самовывоз (Pickup)
   class Pickup{
      -int cost_pickup
      -int time_pickup
      +int get_time()
      +int calculate_cost (int cost_order)
   }
   %% Стандартная доставка (Standart_delivery)
   class Standart_delivery {
      -Courier standart_courier
         %% Изначально может быть null
      +int get_time()
      +int calculate_cost (int cost_order)
      +void assign_courier(Courier courier)
   }
   %% Экспресс доставка (Express_delivery)
   class Express_delivery {
      -Courier express_courier
         %% Изначально может быть null
      +int get_time()
      +int calculate_cost (int cost_order)
      +void assign_courier(Courier courier)
   }
   %% Менеджер заказов (Order_manager)
   class Order_manager {
      -Courier [] courier_array
      -Order[] order_history
         %% Хранилище всех заказов системы
   }
   

   Order *-- Position
   Order *-- Delivery_method
   Order *-- Status_order
   Order o-- Person
   Pickup ..|> Delivery_method
   Standart_delivery ..|> Delivery_method
   Express_delivery ..|> Delivery_method
   Standart_delivery o-- Courier
   Express_delivery o-- Courier
```
