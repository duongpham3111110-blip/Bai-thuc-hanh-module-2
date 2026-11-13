public abstract class DienThoai {
    protected int id;
    protected String tenDienThoai;
    protected double giaBan;
    protected int soLuong;
    protected String nhaSanXuat;

    public DienThoai() {
    }

    public DienThoai(int id, String tenDienThoai, double giaBan, int soLuong, String nhaSanXuat) {
        this.id = id;
        this.tenDienThoai = tenDienThoai;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.nhaSanXuat = nhaSanXuat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDienThoai() {
        return tenDienThoai;
    }

    public void setTenDienThoai(String tenDienThoai) {
        this.tenDienThoai = tenDienThoai;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(String nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public abstract String toCSV();

    @Override
    public String toString() {
        return "ID: " + id +
                " | Ten: " + tenDienThoai +
                " | Gia: " + String.format("%.0f", giaBan) +
                " | So luong: " + soLuong +
                " | NSX: " + nhaSanXuat;
    }
}