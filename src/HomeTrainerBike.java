import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HomeTrainerBike extends Product{

    private String model;
    private List<String> muscleGroups;
    private boolean electronicDisplay;

    /**
     * constructor
     * @param brand brand of the product
     * @param model model of the product
     * @param muscleGroups muscle groups the product works on
     * @param electronicDisplay is this product has an electronic display
     */
    public HomeTrainerBike(String brand, String model,
                           String muscleGroups, String electronicDisplay) {
        this.setBrand(brand);
        this.model = model;

        this.muscleGroups = new ArrayList<>(Arrays.asList(muscleGroups.split(", ")));

        if (!electronicDisplay.equals("TRUE")) {
            this.electronicDisplay = false;
        } else {
            this.electronicDisplay = true;
        }

    }

    /**
     * list of products getter
     * @return list of products
     */
    public List<String> getMuscleGroups() {
        return muscleGroups;
    }

    /**
     * creates a human-friendly representation of this object
     * @return string containing human-friendly representation of this object
     */
    @Override
    public String toString() {
        return "Home Trainer Bike:\n" +
                toStringPrime();

    }

    /**
     * body of creating a string of this object's information
     * that its subclasses can also use
     * @return body of a string of this object's information
     */
    public String toStringPrime() {
        return "Brand: " + this.getBrand() + "\n"
                + "Model: " + this.model + "\n"
                + "Works on following muscle groups: " + String.join(", ", muscleGroups) + "\n"
                + "Electronic display: " + electronicDisplay + "\n"
                + "Price: " + this.getPrice();
    }

    /**
     * main writing object information to file method
     * @param fileWriter filewriter with the file to write to
     * @throws IOException if file can't be found
     */
    @Override
    public void write(FileWriter fileWriter) throws IOException {
        fileWriter.write(this.getClass().getSimpleName());
        writePrime(fileWriter);
        fileWriter.write(this.getPrice() + " euros");
    }

    /**
     * body of writing to a file the object's information
     * that its subclasses can also use
     * @param fileWriter file to write to
     * @throws IOException if file can't be found
     */
    public void writePrime(FileWriter fileWriter) throws IOException {
        String res = " " + this.getBrand() + "; ";
        res += model + "; ";

        for (int i = 0; i < muscleGroups.size(); ++i) {
            res += muscleGroups.get(i);
            if (i < muscleGroups.size() - 1) {
                res += ", ";
            }
        }
        res += "; ";

        if (electronicDisplay) {
            res += "TRUE; ";
        }
        else {
            res += "FALSE; ";
        }

        fileWriter.write(res);
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
        HomeTrainerBike that = (HomeTrainerBike) o;
        return electronicDisplay == that.electronicDisplay
                && Objects.equals(model, that.model)
                && Objects.equals(muscleGroups, that.muscleGroups);
    }

    /**
     * hash method
     * @return int based on hashing object's attributes
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), model, muscleGroups, electronicDisplay);
    }
}
