/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routing;

import core.Connection;
import core.DTNHost;
import core.Message;

/**
 *
 * @author Magnus
 */
public class SAWRouter_ImplementsDER implements RoutingDecisionEngine {

    public static final String NROF_COPIES = "nrofCopies";
    public static final String BINARY_MODE = "binaryMode";

    @Override
    public void connectionUp(DTNHost thisHost, DTNHost peer) {
    }

    @Override
    public void connectionDown(DTNHost thisHost, DTNHost peer) {
    }

    @Override
    public void doExchangeForNewConnection(Connection con, DTNHost peer) {
    
    }

    @Override
    public boolean newMessage(Message m) {
//diisi
    }

    @Override
    public boolean isFinalDest(Message m, DTNHost aHost) {
    //diisi 
    return true;
    }

    @Override
    public boolean shouldSaveReceivedMessage(Message m, DTNHost thisHost) {
    //pengecekan jumlah copy
    }

    @Override
    public boolean shouldSendMessageToHost(Message m, DTNHost otherHost) {
    //diisi true jika lcopy > 1, ngurangi jumlah copy
    }

    @Override
    public boolean shouldDeleteSentMessage(Message m, DTNHost otherHost) {
    return false;
    }

    @Override
    public boolean shouldDeleteOldMessage(Message m, DTNHost hostReportingOld) {
   return true;
    }

    @Override
    public RoutingDecisionEngine replicate() {
    
    }

}
