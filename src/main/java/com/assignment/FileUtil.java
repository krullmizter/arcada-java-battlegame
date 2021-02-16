package com.assignment;

import java.io.*;

public class FileUtil {

    //Serialize the data to byte stream.
    public static void serialize(Object object, String file) {
        try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(file))) {

            obj.writeObject(object);
            obj.close();

        } catch (FileNotFoundException e) {
            System.out.println("A Save file was not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Take in the serialized data from data/*.txt and turn it into an object.
    public static Object deSerialize(String file) {
        Object outputObj = null;

        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream(file))) {

            outputObj = obj.readObject();
            obj.close();

        } catch (FileNotFoundException e) {
            System.out.println("A Save file was not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputObj;
    }
}
