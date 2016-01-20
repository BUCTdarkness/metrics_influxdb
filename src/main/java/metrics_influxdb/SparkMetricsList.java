package metrics_influxdb;

import java.util.List;

/**
 * Created by fightingbird on 2016/1/20.
 */
public class SparkMetricsList {
    private List<SparkMetrics> list;

    public List<SparkMetrics> getList() {
        return list;
    }

    public void setList(List<SparkMetrics> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        String output = "";
        if (list.size() > 0) {
            output += list.get(0);
            for (int i = 1; i < list.size(); ++i) {
                output += "\n" + list.get(i);
            }
        }
        return output;
    }
}
