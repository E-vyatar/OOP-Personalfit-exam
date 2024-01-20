import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Catalogue {

    private List<Product> products;

    /**
     * constructor
     * @param file file to read the catalogue from
     */
    public Catalogue(Scanner file) {
        String line;
        products = new ArrayList<>();

        while (file.hasNextLine()) {
            line = file.nextLine();
            products.add(Product.read(line));
        }
    }

    /**
     * creates a human-friendly representation of this object
     * @return string containing human-friendly representation of this object
     */
    @Override
    public String toString() {
        String res = "Catalogue:\n";

        for (Product product : products) {
            res += product.toString() + "\n\n";
        }
        return res;
    }

    /**
     * add new shake to catalog based on user input.
     * can't be accessed while random shake is being chosen
     * @param inputScanner scanner to receive user input
     */
    public synchronized void addNewShake(Scanner inputScanner) {
        String res = "";

        System.out.println("What is the brand?");
        res += inputScanner.next() + "; ";

        System.out.println("What is the flavour?");
        res += inputScanner.next() + "; ";

        System.out.println("What is the size in weight (in grams)?");
        res += inputScanner.next() + " grams; ";

        System.out.println("What is the price (in euros)?");
        res += inputScanner.next() + " euros";

        products.add(Product.read(res));
    }

    /**
     * create a thread to choose and show the user a random shake
     */
    public synchronized void offerRandomShake() {
        ShakeRunnable shakeRunnable = new ShakeRunnable(products);
    }

    /**
     * write all the products to output file
     * @param fileWriter file to write to
     * @throws IOException if file can't be found
     */
    public void writeOutput(FileWriter fileWriter) throws IOException {
        for (Product product : products) {
            product.write(fileWriter);
            fileWriter.write("\n");
        }
    }

    /**
     * search bikes that works on muscle groups the user provided
     * @param inputScanner scanner to receive user input
     */
    public void searchMuscleGroup(Scanner inputScanner) {
        System.out.println("Choose a muscle group:");
        String choice = inputScanner.next();

        var x = products.stream()
                .filter(product -> product instanceof HomeTrainerBike)
                .filter(bikes -> ((HomeTrainerBike) bikes).getMuscleGroups().contains(choice))
                .toList();

        if (x.size() > 0) {
            x.stream().forEach(bikes -> System.out.println(bikes));
        }
        else {
            System.out.println("Non of our bikes train the " + choice + " muscle group");
        }
    }

    /**
     * check if this object and given object are equal
     * @param o object to compare to
     * @return boolean if objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalogue catalogue = (Catalogue) o;
        return Objects.equals(products, catalogue.products);
    }

    /**
     * hash method
     * @return int based on hashing object's attributes
     */
    @Override
    public int hashCode() {
        return Objects.hash(products);
    }
}