public class DienThoaiXachTay extends DienThoai {
    private String quocGiaXachTay;
    private String trangThai;

    public DienThoaiXachTay(int id, String tenDienThoai, double giaBan, int soLuong, String nhaSanXuat,
                            String quocGiaXachTay, String trangThai) {
        super(id, tenDienThoai, giaBan, soLuong, nhaSanXuat);
        this.quocGiaXachTay = quocGiaXachTay;
        this.trangThai = trangThai;
    }

    public String getQuocGiaXachTay() {
        return quocGiaXachTay;
    }

    public void setQuocGiaXachTay(String quocGiaXachTay) {
        this.quocGiaXachTay = quocGiaXachTay;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toCSV() {
        return id + "," + tenDienThoai + "," + String.format("%.0f", giaBan) + "," + soLuong + "," +
                nhaSanXuat + "," + quocGiaXachTay + "," + trangThai;
    }

    @Override
    public String toString() {
        return "[Xach Tay]   " + super.toString() +
                " | Quoc gia: " + quocGiaXachTay +
                " | Trang thai: " + trangThai;
    }
}