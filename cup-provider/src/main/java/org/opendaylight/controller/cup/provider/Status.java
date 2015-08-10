package org.opendaylight.controller.cup.provider;

public enum Status {
    Cold(0),
    Heating(1);

    private int status;

    private Status(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int s) {
        this.status = s;
    }

}