package me.wcy.music.model;

import java.util.ArrayList;

/**
 * Created by han78 on 2017/6/5.
 */

public class ReceiveMessGroup {
    private ArrayList<ReceiveMess> receiveMessesList;

    public ReceiveMessGroup(ArrayList<ReceiveMess> receiveMessesList) {
        this.receiveMessesList = receiveMessesList;
    }

    public ArrayList<ReceiveMess> getReceiveMessesList() {
        return receiveMessesList;
    }

    public void setReceiveMessesList(ArrayList<ReceiveMess> receiveMessesList) {
        this.receiveMessesList = receiveMessesList;
    }
}
