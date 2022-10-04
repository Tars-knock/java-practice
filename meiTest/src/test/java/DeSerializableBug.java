import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

public class DeSerializableBug implements Serializable {
    public Long valTest = 232L;

    @Serial
    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException{
        System.out.println("you are hacked");
        Runtime.getRuntime().exec("calc.exe");
    }

    private void writeObject(java.io.ObjectOutputStream out)
            throws IOException{
//        valTest++;
        System.out.println("are you serializing me?");
    }
}
