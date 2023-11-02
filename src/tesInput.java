import java.util.ArrayList;

public class tesInput {

    public static void main(String[] args) {
        // Create an array list
        ArrayList<Menu> menuRestoran = new ArrayList<>();
        menuRestoran.add(new Menu("Nasi Padang", 25000, "Makanan", 0));
        menuRestoran.add(new Menu("Ayam Goreng", 15000, "Makanan", 0));
        menuRestoran.add(new Menu("Sate Ayam", 18000, "Makanan", 0));
        menuRestoran.add(new Menu("Sop Ayam", 20000, "Makanan", 0));
        menuRestoran.add(new Menu("Kopi Susu", 5000, "Minuman", 0));
        menuRestoran.add(new Menu("Es Teh Manis", 5000, "Minuman", 0));
        menuRestoran.add(new Menu("Es Teh Tawar", 4000, "Minuman", 0));
        menuRestoran.add(new Menu("Jus Jeruk", 7000, "Minuman", 0));

        // Get the index of the input string in the array list, ignoring case
        String input = "Es Teh Manis"; // Ganti dengan input yang ingin Anda periksa
        int index = 0;
        for (int i = 0; i < menuRestoran.size(); i++) {
            Menu menu = menuRestoran.get(i);
            if (menu.getNama().equalsIgnoreCase(input)) {
                index = i; // Mengembalikan indeks jika ditemukan
            }
        }

        if (index != -1) {
            System.out.println("Item ditemukan di indeks: " + index);
        } else {
            System.out.println("Item tidak ditemukan.");
        }
    }
}