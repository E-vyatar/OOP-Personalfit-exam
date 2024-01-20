import java.util.List;
import java.util.Objects;

public class ShakeRunnable implements Runnable{

    private Thread thread;
    private List<Product> products;

    /**
     * constructor
     * @param products list of products to query through
     */
    public ShakeRunnable(List<Product> products) {
        thread = new Thread(this);
        this.products = products;

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * body of thread action
     */
    @Override
    public void run() {
        var x = products.stream()
                .filter(product -> product instanceof ProteinShake)
                .toList();

        int random = (int) (Math.random() * x.size());
        System.out.println(x.get(random).toString() + "\n");
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
        ShakeRunnable that = (ShakeRunnable) o;
        return Objects.equals(thread, that.thread) && Objects.equals(products, that.products);
    }

    /**
     * hash method
     * @return int based on hashing object's attributes
     */
    @Override
    public int hashCode() {
        return Objects.hash(thread, products);
    }
}
