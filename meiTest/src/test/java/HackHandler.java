import java.io.*;

public class HackHandler {
    public static void main(String[] args) throws IOException {
        DeSerializableBug bug = new DeSerializableBug();
        bug.valTest = 233L;

        File test = new File("C:\\Users\\Tars\\Desktop\\bug.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(test);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(bug);
        objectOutputStream.close();
    }
}

class testYou{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File test = new File("C:\\Users\\Tars\\Desktop\\bug.txt");
        FileInputStream fileInputStream = new FileInputStream(test);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        DeSerializableBug bug = (DeSerializableBug) objectInputStream.readObject();
        System.out.println(bug.valTest);
        System.out.println("OMG what happen");
    }
}
