/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import core.DTNHost;
import core.Message;
import core.MessageListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Magnus
 */
public class MessageDeleteReport extends Report implements MessageListener {

    private Map<DTNHost, Integer> deletMessage;
//    private List<Double> msgBufferTime;
    private int nrofDropped;
    private int nrofRemoved;

    public MessageDeleteReport() {
        init();
    }

    protected void init() {
        super.init();
        this.deletMessage = new HashMap<DTNHost, Integer>();
//        this.msgBufferTime = new ArrayList<Double>();
        this.nrofDropped = 0;
        this.nrofRemoved = 0;

    }

    @Override
    public void newMessage(Message m) {

    }

    @Override
    public void messageTransferStarted(Message m, DTNHost from, DTNHost to) {
    }

    @Override
    public void messageDeleted(Message m, DTNHost where, boolean dropped) {
        if (deletMessage.containsKey(where)) {
            deletMessage.put(where, deletMessage.get(where) + 1);
        } else {
            deletMessage.put(where, 1);
        }
        nrofRemoved++;

    }
//        if (isWarmupID(m.getId())) {
//            return;
//        }
//
//        if (dropped) {
//            this.nrofDropped++;
//        } else {
//            this.nrofRemoved++;
//        }
//
//        this.msgBufferTime.add(getSimTime() - m.getReceiveTime());
//    }

    @Override
    public void messageTransferAborted(Message m, DTNHost from, DTNHost to) {
    }

    @Override
    public void messageTransferred(Message m, DTNHost from, DTNHost to, boolean firstDelivery) {
    }

    @Override
    public void done() {
        write("Host\tPesan terdelete : ");
        for (Map.Entry<DTNHost, Integer> entry : deletMessage.entrySet()) {
            DTNHost key = entry.getKey();
            Integer value = entry.getValue();
            write(key + "\t" + value);
        }
        write(" " + nrofRemoved);
        super.done();
    }
}
