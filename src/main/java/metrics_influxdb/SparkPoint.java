package metrics_influxdb;

import java.util.List;

/**
 * Created by fightingbird on 2016/1/20.
 */
public class SparkPoint {
    private List<String> point;

    public List<String> getPoint() {
        return point;
    }

    public void setPoint(List<String> point) {
        this.point = point;
    }
}

