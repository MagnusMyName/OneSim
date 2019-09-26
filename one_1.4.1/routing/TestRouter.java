/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routing;

import core.Connection;
import core.DTNHost;
import core.Message;
import core.Settings;

/**
 *
 * @author Magnus
 */
public class TestRouter extends ActiveRouter {

    public static final String LABEL_PROPERTY = "label";

    private String Label;

    public TestRouter(Settings s) {
        super(s);
        if (s.contains(LABEL_PROPERTY)) {
            this.Label = s.getSetting(LABEL_PROPERTY);
        } else {
            this.Label = "Antok";
        }
    }

    public TestRouter(TestRouter prototype) {
        super(prototype);
        this.Label = prototype.Label;
    }

    public boolean createNewMessage(Message m) {
        return super.createNewMessage(m);

    }

    public void changeConnetion(Connection con) {
        super.changedConnection(con);
        DTNHost partner = con.getOtherNode(getHost());
        if (con.isUp()) {
            System.out.println("Koneksi terhubung dengan node ; " + partner);

        }

    }

    @Override
    public MessageRouter replicate() {
        return new TestRouter(this);
    }

}
