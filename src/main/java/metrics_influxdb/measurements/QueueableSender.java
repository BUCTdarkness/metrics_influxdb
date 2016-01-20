package metrics_influxdb.measurements;

import metrics_influxdb.misc.BoundedFIFO;

import java.io.IOException;
import java.util.Collection;

public class QueueableSender extends AbstractSender {
    private final Collection<Measurement> measures;
    private int queueSize;
    
    protected QueueableSender(int queueSize) {
        this.queueSize = queueSize;
        measures = new BoundedFIFO<Measurement>(queueSize);
    }

    public void flush() {
        if (doSend(measures)) {
            measures.clear();
        }
    }

    public void send(Measurement m) {
        if (m == null) {
            return;     // NOOP for null measures
        }
        if (measures.size() == queueSize) {
            // we have already reached the maximumn number of measure that can be sent in one shot
            // let's send them before adding a new one
            if (doSend(measures)) {
                measures.clear();
            }
        }
        measures.add(m);
    }

    /**
     * Realizes the action to send the measures
     * @param measuresToSend the collection of measure to be sent
     * @return true if the measures have been sent and can be discarded, false otherwise
     */
    protected boolean doSend(Collection<Measurement> measuresToSend) {
        return true;
    }

    public void close() throws IOException {
        measures.clear();
    }
}
