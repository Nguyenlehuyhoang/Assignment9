package lop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class SinhVien {
    String maSinhVien;
    String hoVaTen;
    Date ngaySinh;
    String chuyenNganh;
    float diemTB;

    public void nhapThongTin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ma sinh vien: ");
        maSinhVien = scanner.nextLine();
        System.out.print("Nhap ho va ten: ");
        hoVaTen = scanner.nextLine();
        System.out.print("Nhap ngay sinh (yyyy-MM-dd): ");
        try {
            String dob = scanner.nextLine();
            ngaySinh = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
        } catch (ParseException e) {
            System.out.println("Dinh dang ngay khong hop le. Dat gia tri thanh null.");
            ngaySinh = null;
        }
        System.out.print("Nhap chuyen nganh: ");
        chuyenNganh = scanner.nextLine();
        System.out.print("Nhap diem trung binh: ");
        diemTB = scanner.nextFloat();
    }

    public void hienThiThongTin() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngaySinhChuoi = (ngaySinh != null) ? sdf.format(ngaySinh) : "N/A";
        System.out.println("Ma SV: " + maSinhVien + ", Ho va ten: " + hoVaTen + ", Ngay sinh: " + ngaySinhChuoi 
            + ", Chuyen nganh: " + chuyenNganh + ", Diem TB: " + diemTB);
    }
}

public class DanhSachSinhVien {
    ArrayList<SinhVien> danhSachSinhVien = new ArrayList<>();

    public void hienThiTatCa() {
        if (danhSachSinhVien.isEmpty()) {
            System.out.println("Danh sach sinh vien trong.");
            return;
        }
        for (SinhVien sv : danhSachSinhVien) {
            sv.hienThiThongTin();
        }
    }
    
    public SinhVien timSinhVienTheoMa(String maCanTim) {
        for (SinhVien sv : danhSachSinhVien) {
            if (sv.maSinhVien.equals(maCanTim)) {
                return sv;
            }
        }
        return null;
    }

    public boolean xoaSinhVienTheoMa(String maCanXoa) {
        SinhVien sv = timSinhVienTheoMa(maCanXoa);
        if (sv != null) {
            danhSachSinhVien.remove(sv);
            return true;
        }
        return false;
    }

    public boolean suaSinhVienTheoMa(String maCanSua) {
        SinhVien sv = timSinhVienTheoMa(maCanSua);
        if (sv != null) {
            System.out.println("Dang chinh sua sinh vien voi ma: " + maCanSua);
            sv.nhapThongTin();
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        DanhSachSinhVien danhSach = new DanhSachSinhVien();
        try (Scanner scanner = new Scanner(System.in)) {
            int luaChon;

            do {
                System.out.println("\nMenu:");
                System.out.println("1. Them sinh vien");
                System.out.println("2. Hien thi tat ca sinh vien");
                System.out.println("3. Tim sinh vien theo ma");
                System.out.println("4. Xoa sinh vien theo ma");
                System.out.println("5. Sua thong tin sinh vien theo ma");
                System.out.println("0. Thoat");
                System.out.print("Nhap lua chon cua ban: ");
                luaChon = scanner.nextInt();
                scanner.nextLine();

                switch (luaChon) {
                    case 1 -> {
                        SinhVien svMoi = new SinhVien();
                        svMoi.nhapThongTin();
                        danhSach.danhSachSinhVien.add(svMoi);
                        System.out.println("Them sinh vien thanh cong!");
                    }
                    case 2 -> danhSach.hienThiTatCa();
                    case 3 -> {
                        System.out.print("Nhap ma sinh vien can tim: ");
                        String maCanTim = scanner.nextLine();
                        SinhVien svTimDuoc = danhSach.timSinhVienTheoMa(maCanTim);
                        if (svTimDuoc != null) {
                            System.out.println("Tim thay sinh vien:");
                            svTimDuoc.hienThiThongTin();
                        } else {
                            System.out.println("Khong tim thay sinh vien.");
                        }
                    }
                    case 4 -> {
                        System.out.print("Nhap ma sinh vien can xoa: ");
                        String maCanXoa = scanner.nextLine();
                        if (danhSach.xoaSinhVienTheoMa(maCanXoa)) {
                            System.out.println("Xoa sinh vien thanh cong!");
                        } else {
                            System.out.println("Khong tim thay sinh vien.");
                        }
                    }
                    case 5 -> {
                        System.out.print("Nhap ma sinh vien can sua: ");
                        String maCanSua = scanner.nextLine();
                        if (danhSach.suaSinhVienTheoMa(maCanSua)) {
                            System.out.println("Sua sinh vien thanh cong!");
                        } else {
                            System.out.println("Khong tim thay sinh vien.");
                        }
                    }
                    case 0 -> System.out.println("Thoat chương trình. Tạm biệt!");
                    default -> System.out.println("Lua chon khong hop le. Vui long thu lai.");
                }
            } while (luaChon != 0);
        }
    }
}