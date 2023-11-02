import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<Menu> menuRestoran = new ArrayList<>();
            menuRestoran.add(new Menu("Nasi Padang", 25000, "Makanan", 0));
            menuRestoran.add(new Menu("Ayam Goreng", 15000, "Makanan", 0));
            menuRestoran.add(new Menu("Sate Ayam", 18000, "Makanan", 0));
            menuRestoran.add(new Menu("Sop Ayam", 20000, "Makanan", 0));
            menuRestoran.add(new Menu("Kopi Susu", 5000, "Minuman", 0));
            menuRestoran.add(new Menu("Es Teh Manis", 5000, "Minuman", 0));
            menuRestoran.add(new Menu("Es Teh Tawar", 4000, "Minuman", 0));
            menuRestoran.add(new Menu("Jus Jeruk", 7000, "Minuman", 0));

        // ============ Menu Utama ============
        int pilihanMenu = 0;
        while (pilihanMenu !=3) {
            System.out.print("\033[H\033[2J");
            System.out.println("=== Menu Restoran ===");
            System.out.println("1. Pemesanan");
            System.out.println("2. Pengelolaan Menu");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            pilihanMenu = Integer.parseInt(input.nextLine());

            switch (pilihanMenu) {
                case 1:
// ============ PEMESANAN ============
                    double totalPI = 0;
                    double totalBiaya = 0;
                    double totalPajak = 0;
                    double potonganSeratus = 0;
                    int jumlahPesan = 0;
                    int bonusMinum = 0;
                    String strukBonus = "";

                    // ============ MENU ============
                    System.out.println("Daftar Menu Restoran:");
                    System.out.println(" === Makanan === ");
                    for (Menu menu : menuRestoran) {
                        if (menu.kategori.equalsIgnoreCase("Makanan")) {
                            System.out.println(menu.nama + ": Rp. " + menu.harga);
                        }
                    }
                    System.out.println(" === Minuman === ");
                    for (Menu menu : menuRestoran) {
                        if(menu.kategori.equalsIgnoreCase("Minuman")){
                            System.out.println(menu.nama + ": Rp. " + menu.harga);
                        }
                    }
                    // ============ End Menu ============


                    // ============ Pesan ============
                    while (true) {
                        System.out.println("Masukkan pesanan (Nama = Jumlah) / Selesai: ");
                        String pesanan = input.nextLine();

                        if (pesanan.equals("selesai")) {
                            break;
                        }

                        String[] pesananSplit = pesanan.split(" = ");
                        String namaPesanan = pesananSplit[0];
                        int iPesanan = -1;
                        for (int i = 0; i < menuRestoran.size(); i++) {
                            Menu menu = menuRestoran.get(i);
                            if (menu.getNama().equalsIgnoreCase(namaPesanan)) {
                                iPesanan = i; // Mengembalikan indeks jika ditemukan
                            }
                        }

                        if (iPesanan != -1) {
                            Menu menu = menuRestoran.get(iPesanan);
                            if(menu.getJumlahPItem() != 0){
                                menu.setJumlahPItem(menu.getJumlahPItem() + Integer.parseInt(pesananSplit[1]));
                            }else{
                                menu.setJumlahPItem(Integer.parseInt(pesananSplit[1]));
                            }
                            jumlahPesan++;
                        } else {
                            System.out.println("Item tidak ditemukan.");
                        }

                        
                    }
                    // ============ End Pesan ============


                    // ============ Hitung Biaya ============
                    for (Menu menu : menuRestoran) {
                        if (menu.jumlahPItem>0) {
                            totalPI = menu.getHarga() * menu.getJumlahPItem();
                            totalBiaya += totalPI;
                        }
                    }

                    totalPajak = totalBiaya * 0.1;

                    if (totalBiaya > 100000) {
                        totalBiaya *= 0.9;
                        potonganSeratus = totalBiaya *0.1;
                    }

                    if (totalBiaya > 50000) {
                        System.out.println("-------------------------------------------------------------------------");
                        System.out.println("Selamat! Anda mendapatkan penawaran beli satu gratis satu untuk minuman!");
                        for (Menu menu : menuRestoran) {
                            if(menu.kategori == "Minuman"){
                                System.out.println(menu.nama + ": Rp. " + menu.harga);
                            }
                        }
                        System.out.println("Pilih salah satu Minuman! / Tidak");
                        String bonus = input.nextLine();
                        if(bonus != "tidak"){
                            for (int i = 0; i < menuRestoran.size(); i++) {
                                if (menuRestoran.get(i).nama.equalsIgnoreCase(bonus)) {
                                    bonusMinum = menuRestoran.get(i).harga;
                                    strukBonus = bonus + "\t1 + 1\t" + bonusMinum + "\t" + bonusMinum;
                                }
                            }      
                            totalBiaya += bonusMinum;
                        }
                    }
                    // ============ End Hitung Biaya ============


                    // ============ Cetak Struk ============
                    if (totalBiaya > 0) {
                        System.out.println("\nStruk Pesanan:");
                        System.out.println("----------------------------");
                        System.out.println("Item\t\tJumlah\tHarga");
                        for (Menu menu : menuRestoran) {
                            if (menu.jumlahPItem>0) {
                                System.out.println(menu.nama + "\t" + menu.jumlahPItem + "\t" + menu.harga + "\t" + (menu.harga * menu.jumlahPItem));
                            }
                        }
                        if (!strukBonus.isEmpty()) {
                            System.out.println(strukBonus);
                        }
                        System.out.println("----------------------------");
                        System.out.println("Total Biaya:\t Rp. " + totalBiaya);
                        System.out.println("Pajak (10%):\t Rp. " + totalPajak);
                        if (totalBiaya > 100000) {
                            System.out.println("Diskon  (10%):\t Rp. " + potonganSeratus);
                        }
                        System.out.println("Biaya Pelayanan: Rp. 20000");
                        System.out.println("----------------------------");
                        System.out.println("Total Akhir: Rp. " + (totalBiaya + totalPajak + 20000));
                    }else{
                        System.out.println("----------------------------");
                        System.out.println("Anda Tidak Memesan Makanan/ Minuman");
                        System.out.println("----------------------------");
                    }
                    // ============ End Cetak Struk ============
// ============ END PEMESANAN ============
                    System.out.println("Ingin melanjutkan ke transaksi berikutnya? (Ya/Tidak)");
                    String jawaban = input.nextLine();

                    if (jawaban.equalsIgnoreCase("Ya")) {
                        for (int i = 0; i < menuRestoran.size(); i++) {
                            menuRestoran.get(i).jumlahPItem = 0;
                        }
                        break;
                    }else{
                        System.out.println("Terima kasih telah menggunakan aplikasi ini!");
                        System.exit(0);
                    }
                    


                case 2:
// ============ PENGELOLAAN MENU ============
                    int pilihanMenuPengelolaan = 0;
                    while (pilihanMenuPengelolaan != 4) {
                        System.out.println("=== Menu Pengelolaan ===");
                        System.out.println("1. Tambah Menu");
                        System.out.println("2. Ubah Harga");
                        System.out.println("3. Hapus Menu");
                        System.out.println("4. Kembali");
                        System.out.print("Pilih menu: ");
                        pilihanMenuPengelolaan = Integer.parseInt(input.nextLine());

                        switch (pilihanMenuPengelolaan) {
                            case 1:
                                // Tambah Menu
                                System.out.println("Tambah Menu");
                                System.out.print("Masukkan nama menu: ");
                                String namaMenu = input.nextLine();
                                System.out.print("Masukkan harga menu: ");
                                int hargaMenu = Integer.parseInt(input.nextLine());
                                System.out.print("Masukkan kategori menu (Makanan, Minuman): ");
                                String kategoriMenu = input.nextLine();

                                menuRestoran.add(new Menu(namaMenu, hargaMenu, kategoriMenu, 0));
                                System.out.println("Menu berhasil ditambahkan");
                                break;
                            case 2:
                                // Ubah Harga
                                System.out.println("Ubah Harga");
                                System.out.println("Daftar menu:");
                                for (int i = 0; i < menuRestoran.size(); i++) {
                                    System.out.println(i + 1 + ". " + menuRestoran.get(i).nama + " - Rp. " + menuRestoran.get(i).harga);
                                }
                                System.out.print("Pilih menu yang ingin diubah harganya: ");
                                int nomorMenu = Integer.parseInt(input.nextLine()) - 1;

                                if (nomorMenu < 0 || nomorMenu >= menuRestoran.size()) {
                                    System.out.println("Menu tidak terdapat pada daftar");
                                } else {
                                    System.out.print("Masukkan harga baru: ");
                                    int hargaBaru = Integer.parseInt(input.nextLine());
                                    System.out.println("Apakah Anda yakin ingin mengubah harga " + menuRestoran.get(nomorMenu).nama + "? (Ya/Tidak)");
                                    String konfirmasi = input.nextLine();
                                    if (konfirmasi.equalsIgnoreCase("Ya")) {
                                        menuRestoran.get(nomorMenu).harga = hargaBaru;
                                        System.out.println("Harga menu berhasil diubah");
                                    } else {
                                        System.out.println("Proses penghapusan dibatalkan");
                                    }
                                }
                                break;
                            case 3:
                                // Hapus Menu
                                System.out.println("Hapus Menu");
                                System.out.println("Daftar menu:");
                                for (int i = 0; i < menuRestoran.size(); i++) {
                                    System.out.println(i + 1 + ". " + menuRestoran.get(i).nama + " - Rp. " + menuRestoran.get(i).harga);
                                }
                                System.out.print("Pilih menu yang ingin dihapus: ");
                                int nomorHapus = Integer.parseInt(input.nextLine()) - 1;

                                if (nomorHapus < 0 || nomorHapus >= menuRestoran.size()) {
                                    System.out.println("Pilihan tidak terdapat pada daftar");
                                } else {
                                    // Konfirmasi penghapusan
                                    System.out.println("Apakah Anda yakin ingin menghapus menu " + menuRestoran.get(nomorHapus).nama + "? (Ya/Tidak)");
                                    String konfirmasi = input.nextLine();
                                    if (konfirmasi.equalsIgnoreCase("Ya")) {
                                        menuRestoran.remove(nomorHapus);
                                        System.out.println("Menu berhasil dihapus");
                                    } else {
                                        System.out.println("Proses penghapusan dibatalkan");
                                    }
                                }
                                break;
                            case 4:
                                System.out.println("Kembali ke menu awal!!");
                                break;
                            default:
                                System.out.println("Pilihan menu tidak valid!");
                        }
                    }
                    break;
// ============ END PENGELOLAAN MENU ============
                case 3:
                    // Keluar
                    System.out.println("Terima kasih telah menggunakan aplikasi ini!");
                    break;
                default:
                    System.out.println("Pilihan menu tidak valid!");
            }
        }
        // ============ End Menu Utama ============
        input.close();
    }
}
    
