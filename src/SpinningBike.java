import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class SpinningBike extends HomeTrainerBike{
    private double flywheelWeight;
    private String resistanceMechanism;

    /**
     * constructor
     * @param brand brand of the product
     * @param model model of the product
     * @param muscleGroups muscle groups the product works on
     * @param electronicDisplay is this product has an electronic display
     * @param flywheelWeight weight of this product's flywheel
     * @param resistanceMechanism resistance mechanism of this product
     */
    public SpinningBike(String brand, String model, String muscleGroups,
                        String electronicDisplay, String flywheelWeight,
                        String resistanceMechanism) {
        super(brand, model, muscleGroups, electronicDisplay);
        Scanner scanner = new Scanner(flywheelWeight).useDelimiter("KG");
        this.flywheelWeight = Double.parseDouble(scanner.next());
        this.resistanceMechanism = resistanceMechanism;
    }

    /**
     * creates a human-friendly representation of this object
     * @return string containing human-friendly representation of this object
     */
    @Override
    public String toString() {
        return "Spinning Bike:\n"
                + toStringPrime() + "\n"
                + "Weight of flywheel: " + flywheelWeight + "\n"
                + "Resistance mechanism: " + resistanceMechanism;
    }

    /**
     * write object to file in the same format as it was read from
     * @param fileWriter filewriter with the file to write to
     * @throws IOException if file can't be found
     */
    @Override
    public void write(FileWriter fileWriter) throws IOException {
        fileWriter.write(this.getClass().getSimpleName());
        super.writePrime(fileWriter);
        fileWriter.write(flywheelWeight + "KG; " + resistanceMechanism + "; ");
        fileWriter.write(this.getPrice() + " euros");
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
        SpinningBike that = (SpinningBike) o;
        return Double.compare(that.flywheelWeight, flywheelWeight) == 0
                && Objects.equals(resistanceMechanism, that.resistanceMechanism);
    }

    /**
     * hash method
     * @return int based on hashing object's attributes
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), flywheelWeight, resistanceMechanism);
    }
}
