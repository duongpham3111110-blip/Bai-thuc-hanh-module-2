import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        QuanLyDienThoai ql = new QuanLyDienThoai();
        
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n--CHUONG TRINH QUAN LY DIEN THOAI--");
                System.out.println("Chon chuc nang theo so (de tiep tuc):");
                System.out.println("1. Them moi");
                System.out.println("2. Xoa");
                System.out.println("3. Xem danh sach dien thoai");
                System.out.println("4. Tim kiem");
                System.out.println("5. Thoat");
                System.out.print("Chon chuc nang: ");

                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        ql.themMoi();
                        break;
                    case "2":
                        ql.xoaDienThoai();
                        break;
                    case "3":
                        ql.hienThiDanhSach();
                        break;
                    case "4":
                        ql.timKiem();
                        break;
                    case "5":
                        System.out.println("Thoat chuong trinh!");
                        return;
                    default:
                        System.out.println("Chuc nang khong hop le, vui long chon lai!");
                }
            }
        }
    }
}