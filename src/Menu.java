public class Menu {
    String nama;
    int harga;
    String kategori;
    int jumlahPItem;

    public Menu(String nama, int harga, String kategori, int jumlahPItem) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
        this.jumlahPItem = jumlahPItem;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getJumlahPItem() {
        return jumlahPItem;
    }

    public void setJumlahPItem(int jumlahPItem) {
        this.jumlahPItem = jumlahPItem;
    }
}
