import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Filler extends Base {

    private Random random = new Random();

    public List<? extends Number> fill(int volume, Class<? extends Number> aClass)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Number seed = aClass.getConstructor(String.class).newInstance("" + (byte)random.nextInt());
        return Stream.generate(() -> {
            if(seed instanceof Byte) {
                return (byte) random.nextInt();
            } else if(seed instanceof Short) {
                return (short)random.nextInt();
            } else if(seed instanceof Integer) {
                return random.nextInt();
            } else if(seed instanceof Long) {
                return random.nextLong();
            } else if(seed instanceof Float) {
                return random.nextFloat();
            } else {
                return random.nextDouble();
            }
        }).limit(volume).collect(Collectors.toList());
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Filler filler = new Filler();
        System.out.println(filler.fill(3, Float.class));
    }
}
