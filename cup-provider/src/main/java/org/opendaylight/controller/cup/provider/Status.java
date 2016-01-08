/*
 * Copyright (c) 2016 Sniggel and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
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