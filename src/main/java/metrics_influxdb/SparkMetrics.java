package metrics_influxdb;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shenjiyi on 2016/1/20.
 */
public class SparkMetrics {
    private String name;
    private List<String> columns;
    private List<List<Double>> points;


    @Override
    public String toString() {
//        String output = "";
//        String formatname = name.replaceAll("\\.", "_");
//        String formatname2 = formatname.replaceAll("\\-", "_");
//        output += formatname2;
//        int vindex = 0;
//        for (int i = 0; i < columns.size(); ++i) {
//            if (columns.get(i).equals("value")) {
//                vindex = i;
//                continue;
//            }
//            output += "," + columns.get(i) + "=" + points.get(0).get(i);
//        }
//        output += " value=" + points.get(0).get(vindex);
//        return output;
        String output = "";
        String []splitedname = name.split("\\.");
        String appid = splitedname[0].replaceAll("\\-", "_");
        String tablename = "";
        for (int i = 1; i < splitedname.length; i++) {
            if (i == 1) {
                tablename = splitedname[1];
            }
            else {
                tablename += "_" + splitedname[i];
            }
        }

        output += tablename + ",appid=" + appid;

        int vindex = 0;
        for (int i = 0; i < columns.size(); ++i) {
            if (columns.get(i).equals("value")) {
                vindex = i;
                continue;
            }
            output += "," + columns.get(i) + "=" + points.get(0).get(i);
        }

        DecimalFormat    df   = new DecimalFormat("######0.00");

        Double value = points.get(0).get(vindex);
//        if (value > 0 & value < 1) {
//            value *= 100;
//        } else if (value > 1024.0 * 1024.0) {
//            value /= 1024.0;
//            value /= 1024.0;
//        }
//        value =((int)(value*100))/100;
        output += " value=" + value;
        return output;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<List<Double>> getPoints() {
        return points;
    }

    public void setPoints(List<List<Double>> points) {
        this.points = points;
    }
}
