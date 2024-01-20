import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class ProteinShake extends Product{

    private String flavour;
    private double sizeInWeight;

    /**
     * constructor
     * @param brand brand of the product
     * @param flavour flavour of the product
     * @param sizeInWeight size in weight of the product
     */
    public ProteinShake(String brand, String flavour, String sizeInWeight) {
        this.setBrand(brand);
        this.flavour = flavour;
        Scanner scanner = new Scanner(sizeInWeight);
        this.sizeInWeight = Double.parseDouble(scanner.next());
    }

    /**
     * creates a human-friendly representation of this object
     * @return string containing human-friendly representation of this object
     */
    @Override
    public String toString() {
        return "Protein Shake:" + "\n"
                + "Flavour: " + flavour + "\n"
                + "Size in weight: " + sizeInWeight + "\n"
                + "Price: " + this.getPrice();
    }

    /**
     * write object to file in the same format as it was read from
     * @param fileWriter filewriter with the file to write to
     * @throws IOException if file can't be found
     */
    @Override
    public void write(FileWriter fileWriter) throws IOException {
        String res = this.getClass().getSimpleName();
        res += " " + getBrand() + "; ";
        res += flavour + "; ";
        res += sizeInWeight + " grams; ";

        fileWriter.write(res + this.getPrice() + " euros");
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
        if (!super.equals(o)) return false;
        ProteinShake that = (ProteinShake) o;
        return Double.compare(that.sizeInWeight, sizeInWeight) == 0
                && Objects.equals(flavour, that.flavour);
    }

    /**
     * hash method
     * @return int based on hashing object's attributes
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), flavour, sizeInWeight);
    }
}
