package godil.InflationCalculator.io;

import godil.InflationCalculator.model.IndexChange;
import godil.InflationCalculator.model.ItemValueChange;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOHelper {
    /**
     * @param filePath
     * @return object
     */
    public static IndexChange readData(String filePath) {
        Exception e = new Exception("bad format");
        List<ItemValueChange> list = new ArrayList<>();
        IndexChange ic;
        Scanner file;
        double years;
        try {
            file = new Scanner(new File(filePath));
            // find number
            if (file.hasNextDouble()) {
                years = file.nextDouble();
                if (file.hasNextLine()) file.nextLine();
            } else {
                throw e;
            }
            // process rest of file
            while (file.hasNextLine()) {
                String line = file.nextLine();
                // check format
                if (!line.matches("^\\S+;\\d+(\\.\\d+)?;\\d+(\\.\\d+)?;\\d+(\\.\\d+)?$")) throw e;
                String[] v = line.split(";");
                list.add(new ItemValueChange(v[0], Double.valueOf(v[1]), Double.valueOf(v[2]), Double.valueOf(v[3])));
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

    /**
     * @param ic
     * @param filePath
     */
    public static void writeData(IndexChange ic, String filePath) {
        try {
            //new file
            PrintWriter w = new PrintWriter(new FileWriter(new File(filePath), false));
            //write number
            w.println(ic.getYearsBetween());
            //write lines
            for (ItemValueChange vc : ic.getValueChanges()) {
                w.println(vc.getItemName() + ';' + vc.getQuantity() + ';' + vc.getPrice1() + ';' + vc.getPrice2());
            }
            w.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
}
