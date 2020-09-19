package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOHelper {
    public static IndexChange readData(String filePath) {
        Exception e = new Exception("bad format");
        List<ValueChange> list = new ArrayList<>();
        IndexChange ic;
        Scanner file;
        double years;
        try {
            file = new Scanner(new File(filePath));
            if (file.hasNextDouble()) {
                years = file.nextDouble();
                if (file.hasNextLine()) file.nextLine();
            } else {
                throw e;
            }
            while (file.hasNextLine()) {
                String line = file.nextLine();
                if (!line.matches("^\\S+;\\d+(\\.\\d+)?;\\d+(\\.\\d+)?;\\d+(\\.\\d+)?$")) throw e;
                String[] v = line.split(";");
                list.add(new ValueChange(v[0], Double.valueOf(v[1]), Double.valueOf(v[2]), Double.valueOf(v[3])));
            }
            file.close();
        } catch (Exception h) {
            //e.printStackTrace();
            ic = new IndexChange(new ArrayList<>(), 0);
            return ic;
        }
        ic = new IndexChange(list, years);
        return ic;
    }

    public static void writeData(IndexChange ic, String filePath) {
        try {
            PrintWriter w = new PrintWriter(new FileWriter(new File(filePath), false));
            w.println(ic.getYearsBetween());
            for (ValueChange vc : ic.getValueChanges()) {
                w.println(vc.getItemName() + ';' + vc.getQuantity() + ';' + vc.getPrice1() + ';' + vc.getPrice2());
            }
            w.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
}
