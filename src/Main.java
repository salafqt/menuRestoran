import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double totalPI = 0;
        double totalBiaya = 0;
        double totalPajak = 0;
        int jumlahPesan = 0;
        int bonusMinum = 0;
        String strukBonus = "";

        Menu[] menuRestoran = {
            new Menu("Nasi Padang", 25000, "Makanan", 0),
            new Menu("Ayam Goreng", 15000, "Makanan", 0),
            new Menu("Sate Ayam", 18000, "Makanan", 0),
            new Menu("Sop Ayam", 20000, "Makanan", 0),

            new Menu("Kopi Susu", 5000, "Minuman", 0),
            new Menu("Es Teh Manis", 5000, "Minuman", 0),
            new Menu("Es Teh Tawar", 4000, "Minuman", 0),
            new Menu("Jus Jeruk", 7000, "Minuman", 0),
        };


        // ============ MENU ============
        System.out.println("Daftar Menu Restoran:");
        System.out.println(" === Makanan === ");
        for (Menu menu : menuRestoran) {
            if (menu.kategori == "Makanan") {
                System.out.println(menu.nama + ": Rp. " + menu.harga);
            }
        }
        System.out.println(" === Minuman === ");
        for (Menu menu : menuRestoran) {
            if(menu.kategori == "Minuman"){
                System.out.println(menu.nama + ": Rp. " + menu.harga);
            }
        }
        // ============ End Menu ============


        // ============ Pesan ============
        while (jumlahPesan <= 4) {
            System.out.println("Masukkan pesanan (Nama = Jumlah) / Selesai: ");
            String pesanan = input.nextLine();

            if (pesanan.equals("selesai")) {
                break;
            }
            
            for (Menu menu : menuRestoran) {
                String[] pesananSplit = pesanan.split(" = ");
                String namaPesanan = pesananSplit[0];

                if (menu.getNama().equalsIgnoreCase(namaPesanan)) {
                    if(menu.getJumlahPItem() != 0){
                        menu.setJumlahPItem(menu.getJumlahPItem() + Integer.parseInt(pesananSplit[1]));
                    }else{
                        menu.setJumlahPItem(Integer.parseInt(pesananSplit[1]));
                    }
                    jumlahPesan++;
                    break;
                }
                
                // for (int i = 0; i < menuRestoran.length; i++) {
                //     if (menuRestoran[i].nama.equalsIgnoreCase(namaPesanan)) {
                //         menuRestoran[i].jumlahPItem += Integer.parseInt(pesananSplit[1]);
                //         if (menu.nama.equalsIgnoreCase(namaPesanan)) {
                //             totalBiayaPI = menu.harga * menu.jumlahPItem;
                //             jumlahPesan++;
                //             break;
                //         }
                //     }
                // }         
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
        // jumlahPItem =  
        // jumlahItem =  

        if (totalBiaya > 100000) {
            totalBiaya *= 0.9;
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
                for (int i = 0; i < menuRestoran.length; i++) {
                    if (menuRestoran[i].nama.equalsIgnoreCase(bonus)) {
                        bonusMinum = menuRestoran[i].harga;
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
            System.out.println("Total Biaya: Rp. " + totalBiaya);
            System.out.println("Pajak (10%): Rp. " + totalPajak);
            System.out.println("Biaya Pelayanan: Rp. 20000");
            System.out.println("----------------------------");
            System.out.println("Total Akhir: Rp. " + (totalBiaya + totalPajak + 20000));
        }else{
            System.out.println("----------------------------");
            System.out.println("Anda Tidak Memesan Makanan/ Minuman");
            System.out.println("----------------------------");
        }
        // ============ End Cetak Struk ============

        input.close();
    }
}
