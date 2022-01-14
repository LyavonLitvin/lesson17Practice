import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String readString() {
        String inputDate = "";
        try  {
            inputDate = reader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputDate;
    }

    public void readerClose () {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isNumeric(String inputData) {
        try {
            Integer.parseInt(inputData);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
