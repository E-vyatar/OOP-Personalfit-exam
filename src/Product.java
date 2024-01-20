import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Product {

    private String brand;
    private double price;

    /**
     * brand getter
     * @return the value of brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * price getter
     * @return the value of price
     */
    public double getPrice() {
        return price;
    }

    /**
     * brand setter
     * @param brand value to set the brand to
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * price setter
     * @param price value to set the price to
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * creates a product from given information
     * @param line the string with the information
     * @return product of type according to the information, otherwise basic product object
     */
    public static Product read(String line) {
        Scanner lineScanner = new Scanner(line);
        Product product;

        String type = lineScanner.next();

        lineScanner.useDelimiter("; ");

        switch (type) {
            case "HomeTrainerBike":
                product = new HomeTrainerBike(lineScanner.next(), lineScanner.next(),
                        lineScanner.next(), lineScanner.next());
                break;
            case "SpinningBike":
                product = new SpinningBike(
                        lineScanner.next(), lineScanner.next(), lineScanner.next(),
                        lineScanner.next(), lineScanner.next(), lineScanner.next());
                break;
            case "ProteinShake":
                product = new ProteinShake(
                        lineScanner.next(), lineScanner.next(), lineScanner.next());
                break;
            default:
                product = new Product();
        }


        lineScanner.useDelimiter(" ");
        lineScanner.next();
        product.setPrice(lineScanner.nextDouble());

        return product;
    }

    /**
     * creates a human-friendly representation of this object
     * @return string containing human-friendly representation of this object
     */
    @Override
    public String toString() {
        return "Product{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }

    /**
     * write price of object to file in the same format as it was read from
     * @param fileWriter filewriter with the file to write to
     * @throws IOException if file can't be found
     */
    public void write(FileWriter fileWriter) throws IOException {
        fileWriter.write(price + " euros");
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
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(brand, product.brand);
    }

    /**
     * hash method
     * @return int based on hashing object's attributes
     */
    @Override
    public int hashCode() {
        return Objects.hash(brand, price);
    }
}
