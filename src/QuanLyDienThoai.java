import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuanLyDienThoai {
    private static final String FILE_PATH = "../data/mobiles.csv";
    private List<DienThoai> danhSach = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public QuanLyDienThoai() {
        docFileCSV();
    }

    public void docFileCSV() {
        danhSach.clear();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    int id = Integer.parseInt(parts[0].trim());
                    String ten = parts[1].trim();
                    double gia = Double.parseDouble(parts[2].trim());
                    int soLuong = Integer.parseInt(parts[3].trim());
                    String nsx = parts[4].trim();
                    String col6 = parts[5].trim();
                    String col7 = parts[6].trim();

                    if (isNumeric(col6)) {
                        int baoHanh = Integer.parseInt(col6);
                        danhSach.add(new DienThoaiChinhHang(id, ten, gia, soLuong, nsx, baoHanh, col7));
                    } else {
                        danhSach.add(new DienThoaiXachTay(id, ten, gia, soLuong, nsx, col6, col7));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void ghiFileCSV() {
        File dir = new File("../data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (DienThoai dt : danhSach) {
                bw.write(dt.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void themMoi() {
        System.out.println("\n--- THEM MOI DIEN THOAI ---");
        System.out.println("1. Dien thoai chinh hang");
        System.out.println("2. Dien thoai xach tay");
        System.out.print("Chon loai dien thoai: ");
        String loai = scanner.nextLine();

        int newId = 1;
        if (!danhSach.isEmpty()) {
            newId = danhSach.get(danhSach.size() - 1).getId() + 1;
        }

        System.out.print("Nhap ten dien thoai: ");
        String ten = scanner.nextLine();

        double gia = 0;
        while (true) {
            try {
                System.out.print("Nhap gia ban: ");
                gia = Double.parseDouble(scanner.nextLine());
                if (gia > 0) break;
                System.out.println("Gia ban phai lon hon 0!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so hop le!");
            }
        }

        int soLuong = 0;
        while (true) {
            try {
                System.out.print("Nhap so luong: ");
                soLuong = Integer.parseInt(scanner.nextLine());
                if (soLuong > 0) break;
                System.out.println("So luong phai lon hon 0!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen hop le!");
            }
        }

        System.out.print("Nhap nha san xuat: ");
        String nsx = scanner.nextLine();

        if (loai.equals("1")) {
            int thoiGianBH = 0;
            while (true) {
                try {
                    System.out.print("Nhap thoi gian bao hanh (ngay): ");
                    thoiGianBH = Integer.parseInt(scanner.nextLine());
                    if (thoiGianBH > 0) break;
                } catch (NumberFormatException e) {
                    System.out.println("Vui long nhap so ngay hop le!");
                }
            }
            System.out.print("Nhap pham vi bao hanh (Toan Quoc / Quoc Te): ");
            String phamVi = scanner.nextLine();

            danhSach.add(new DienThoaiChinhHang(newId, ten, gia, soLuong, nsx, thoiGianBH, phamVi));
        } else {
            System.out.print("Nhap quoc gia xach tay: ");
            String quocGia = scanner.nextLine();
            System.out.print("Nhap trang thai (Da sua chua / Chua sua chua): ");
            String trangThai = scanner.nextLine();

            danhSach.add(new DienThoaiXachTay(newId, ten, gia, soLuong, nsx, quocGia, trangThai));
        }

        ghiFileCSV();
        System.out.println("Them moi thanh cong!");
    }

    public void xoaDienThoai() {
        System.out.println("\n--- XOA DIEN THOAI ---");
        while (true) {
            System.out.print("Nhap ID dien thoai can xoa: ");
            int idXoa;
            try {
                idXoa = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID phai la mot so!");
                continue;
            }

            try {
                DienThoai dtCanXoa = timTheoId(idXoa);
                if (dtCanXoa == null) {
                    throw new NotFoundProductException("ID dien thoai khong ton tai.");
                }

                System.out.println("Tim thay: " + dtCanXoa);
                System.out.print("Ban co chac chan muon xoa? (Yes/No): ");
                String confirm = scanner.nextLine();

                if (confirm.equalsIgnoreCase("Yes")) {
                    danhSach.remove(dtCanXoa);
                    ghiFileCSV();
                    System.out.println("Da xoa thanh cong! Danh sach hien tai:");
                    hienThiDanhSach();
                } else {
                    System.out.println("Da huy thao tac xoa. Quay ve menu chinh.");
                }
                break;
            } catch (NotFoundProductException e) {
                System.out.println(e.getMessage());
                System.out.println("Nhan Enter de quay lai menu chinh hoac nhap bat ky de thu lai...");
                String choice = scanner.nextLine();
                if (choice.isEmpty()) {
                    break;
                }
            }
        }
    }

    private DienThoai timTheoId(int id) {
        for (DienThoai dt : danhSach) {
            if (dt.getId() == id) {
                return dt;
            }
        }
        return null;
    }

    public void hienThiDanhSach() {
        System.out.println("\n--- DANH SACH DIEN THOAI ---");
        docFileCSV();
        if (danhSach.isEmpty()) {
            System.out.println("Danh sach trong!");
            return;
        }
        for (DienThoai dt : danhSach) {
            System.out.println(dt);
        }
    }

    public void timKiem() {
        System.out.println("\n--- TIM KIEM DIEN THOAI ---");
        System.out.print("Nhap tu khoa (ID hoac Ten dien thoai): ");
        String keyword = scanner.nextLine().toLowerCase().trim();

        boolean found = false;
        for (DienThoai dt : danhSach) {
            String idStr = String.valueOf(dt.getId());
            String tenStr = dt.getTenDienThoai().toLowerCase();
            if (idStr.contains(keyword) || tenStr.contains(keyword)) {
                System.out.println(dt);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay dien thoai nao phu hop voi tu khoa: " + keyword);
        }
    }
}