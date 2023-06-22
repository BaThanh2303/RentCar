package examjava2;

import java.util.HashMap;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (option != 4){
            System.out.println("----------CRM----------");
            System.out.println("1. Thêm Khách Hàng Mới");
            System.out.println("2. Tìm Theo Tên");
            System.out.println("3. Hiển Thị Tất Cả Khách Hàng");
            System.out.println("4. Exit");
            System.out.print("Hãy Chọn Chức Năng: ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    findCustomer();
                    break;
                case 3:
                    display();
                    break;
                case 4:
                    System.out.println("Thoát Chương Trình!!");
                    break;
                default:
                    System.out.println("Lựa Chọn Không Hợp Lệ!!");
            }
        }


    }
    public static HashMap<String, Customer> customers = new HashMap<String,Customer>();
    public static Scanner scanner = new Scanner(System.in);
    public static void addCustomer(){
        try {
            System.out.println();
            System.out.println("Hãy Nhập Thông tin");
            System.out.print("Nhập Tên Khách: ");
            String name = scanner.nextLine();
            System.out.print("Nhập Email: ");
            String email = scanner.nextLine();
            System.out.print("Nhập Số Điện Thoại: ");
            String phoneNumber = scanner.nextLine();
            System.out.println();
            Customer customer = new Customer(name, email, phoneNumber);
            if (customer != null){
                customers.put(name, customer);
                throw new Exception("Đã Thêm Thành Công!!");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void display(){
        for (String i : customers.keySet()) {
            Customer displayCustomer = customers.get(i);
            System.out.println();
            System.out.println("Tên Khách: " + displayCustomer.getName());
            System.out.println("Email: " + displayCustomer.getEmail());
            System.out.println("Số Điện Thoại: " + displayCustomer.getPhone());
            System.out.println();
        }

    }
    public static void findCustomer(){
        System.out.print("Nhập Người Cần Tìm: ");
        String findName = scanner.nextLine();
        if (customers.containsKey(findName)) {
            Customer findCustomer = customers.get(findName);
            System.out.println();
            System.out.println("Tên Khách: " + findCustomer.getName());
            System.out.println("Email: " + findCustomer.getEmail());
            System.out.println("Số Điện Thoại: " + findCustomer.getPhone());
            System.out.println();
        } else {
            System.out.println("Không Thể Tìm Thấy:");
        }
    }
}
