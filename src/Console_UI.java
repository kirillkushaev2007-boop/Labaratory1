import java.util.Scanner;

public class Console_UI {
    private final Order_manager manager;
    private final Scanner scanner;

    public Console_UI(Order_manager manager){
        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Order_manager systemManager = new Order_manager();
        Console_UI ui = new Console_UI(systemManager);
        ui.menu_loop();
    }

    public void menu_loop(){
        while (true) {
            System.out.println("Выберете один из пунктов:\n" +
                    "0. Выйти\n" +
                    "(1-9). Начать\n");
            int client_input = -1;
            try {
                client_input = scanner.nextInt();
                scanner.nextLine();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Ошибка: Нужно вводить только цифры!");
                scanner.nextLine();
            }

            if (client_input == 0)
                return;
            else if (client_input == -1) {
                System.out.println("Попробуйте перезапустить программу и ввести цифру");
                return;
            } else {
                System.out.print("Введите своё имя: ");
                String client_input1 = scanner.nextLine();
                System.out.println();

                System.out.print("Введите свой адрес: ");
                String client_input2 = scanner.nextLine();
                System.out.println();

                Person client = new Person(client_input1, client_input2);
                manager.create_new_order(client);

                main_menu();
            }
        }
    }

    private void main_menu() {
        while (true) {
            System.out.println("ГЛАВНОЕ МЕНЮ:\n" +
                    "0. Назад\n" +
                    "1. Открыть меню блюд\n" +
                    "2. Посмотреть статус заказа\n" +
                    "3. Посмотреть историю заказов\n" +
                    "4. Поменять статус заказа с Отправлено на Принято");
            int client_input = -1;
            try {
                client_input = scanner.nextInt();
                scanner.nextLine();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Ошибка: Нужно вводить только цифры!");
                scanner.nextLine();
            }
            if (client_input == -1) {
                System.out.println("Вводить только цифры");
                client_input = 0;
            }
            switch (client_input) {
                case (0):
                    return ;
                case (1):
                    if(price_menu())
                        return;
                    break;
                case (2):
                    Status_order status = manager.get_last_order().get_status();
                    System.out.println("Статус заказа: " + status);
                    break;
                case (3):
                    Order[] order_history = manager.get_order_history();
                    for (int i = 0; i < manager.get_count_orders(); i++) {
                        System.out.println("Заказ №" + order_history[i].get_id() + " | Status: " + order_history[i].get_status()
                                + " | Стоимость заказанных блюд: "
                                + order_history[i].get_cost_order() + " руб.");
                    }
                    break;
                case (4):
                    Order[] shipped_orders = manager.get_shipped_orders();
                    if (shipped_orders == null) {
                        System.out.println("Нет ни одного отправленного заказа");
                        break;
                    }
                    for (int i = 0; i < shipped_orders.length; i++) {
                        if (shipped_orders[i].get_status() == Status_order.SHIPPED) {
                            System.out.println("Заказ №" + i + " | Status: " + shipped_orders[i].get_status()
                                    + " | Стоимость заказанных блюд: "
                                    + shipped_orders[i].get_cost_order() + " руб.");
                        }
                    }
                    System.out.println("Выберете номер заказа или введите любую строку, если хотите выйти из этого меню");
                    String client_input1 = scanner.nextLine().trim();
                    try {
                        int number = Integer.parseInt(client_input1);
                        if (number >= 0 && number < shipped_orders.length) {
                            shipped_orders[number].change_status(Status_order.ACCEPTED);
                            shipped_orders[number].get_delivery().get_courier().set_free(true);
                        } else {
                            System.out.println("Ваше число число не соответсвует ни одному из заказов," +
                                    " поэтому было считано, как строка");
                            break;
                        }

                    } catch (NumberFormatException e) {
                        break;
                    }


            }
        }
    }

    private boolean price_menu() {
        while (true) {
            System.out.println("МЕНЮ БЛЮД:\n" +
                    "0. Перейти к выбору доставки\n" +
                    "1. Кола --------------- 150 руб.\n" +
                    "2. Картошка фри ------- 250 руб.\n" +
                    "3. Бургер ------------- 200 руб.\n" +
                    "4. Назад\n"
            );
            int client_choice1 = scanner.nextInt();
            scanner.nextLine();

            int quantity = 0;
            Position pos = null;

            switch (client_choice1) {
                case(0):
                    System.out.println("Вами заказано продуктов на сумму: " + manager.get_last_order().get_cost_order());
                    System.out.println("Продолжить: 1-да, 2-нет");
                    int client_choice2 = scanner.nextInt();
                    scanner.nextLine();
                    if (client_choice2 == 1){
                        if(delivery_menu())
                            return true;
                        break;
                    }
                    else {
                        manager.get_last_order().clear_positions();
                        System.out.println("Корзина удалена");
                        break;
                    }

                case(1):
                    System.out.print("Введите количество порций Колы: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine();

                    pos = new Position(quantity, "Кола", 150);
                    manager.get_last_order().add_pos(pos);
                    System.out.println("Кола добавлена в корзину!\n");
                    break;

                case(2):
                    System.out.print("Введите количество порций Картошки фри: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine();

                    pos = new Position(quantity, "Картошка фри", 250);
                    manager.get_last_order().add_pos(pos);
                    System.out.println("Картошка фри добавлена в корзину!\n");
                    break;

                case(3):
                    System.out.print("Введите количество порций Бургеров: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine();

                    pos = new Position(quantity, "Бургер", 200);
                    manager.get_last_order().add_pos(pos);
                    System.out.println("Бургер добавлен в корзину!\n");
                    break;
                case(4):
                    return false;

                default:
                    System.out.println("Ошибка: Такого блюда нет в меню!\n");
                    break;
            }
        }
    }

    private boolean delivery_menu() {
        while (true) {
            System.out.println("\nВЫБОР СПОСОБА ДОСТАВКИ:\n" +
                    "0. Назад\n" +
                    "1. Самовывоз (Платная сборка: 10 руб.)\n" +
                    "2. Стандартная доставка (+ один тариф курьера)\n" +
                    "3. Экспресс-доставка (+ два тарифа курьера)\n" +
                    "Выберите номер пункта: ");

            int delivery_choice = scanner.nextInt();
            scanner.nextLine();

            switch (delivery_choice) {
                case (0):
                    return false;
                case (1):
                    manager.get_last_order().set_delivery(new Pickup());
                    manager.get_last_order().change_status(Status_order.SHIPPED);
                    manager.get_last_order().change_status(Status_order.ACCEPTED);
                    System.out.println("Успешно: Оформлен самовывоз. Курьер не требуется.");
                    manager.complete_order();
                    return true;

                case (2):
                    manager.get_last_order().set_delivery(new Standart_delivery());
                    System.out.println("Успешно: Выбрана стандартная доставка курьером.");
                    if (manager.assign_courier_to_order() == true) {
                        System.out.println("Вашим заказом занимается: " +
                                manager.get_last_order().get_delivery().get_courier().get_name());
                        System.out.println("\nОформление завершено.");
                        manager.complete_order();
                        return true;
                    }
                    break;

                case (3):
                    manager.get_last_order().set_delivery(new Express_delivery());
                    System.out.println("Успешно: Выбрана экспресс-доставка.");
                    if (manager.assign_courier_to_order() == true) {
                        System.out.println("Вашим заказом занимается: " +
                                manager.get_last_order().get_delivery().get_courier().get_name());
                        System.out.println("\nОформление завершено.");
                        manager.complete_order();
                        return true;
                    }
                    break;


                default:
                    System.out.println("Ошибка: Неверный пункт! Доставка не установлена.\n");
                    break;
            }

        }
    }
}
