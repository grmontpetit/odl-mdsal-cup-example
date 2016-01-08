/*
 * Copyright (c) 2016 Sniggel and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.controller.cup.data;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import org.opendaylight.controller.cup.provider.Status;

/**
 * Using an object to store the cup data
 * state is not a good practice as it may
 * introduce data inconsistency between
 * the mdsal data store and the cup
 * object.
 * @author sniggel
 *
 */
public class CupState {

    private final AtomicLong amountOfCupsInStock = new AtomicLong(100);
    private final AtomicLong cupsMade = new AtomicLong(0);
    private final AtomicLong cupTemperatureFactor = new AtomicLong(1000);
    private final AtomicReference<Future<?>> currentHeatCupTask = new AtomicReference<>();
    private Status cupStatus = Status.Cold;
    public CupState(){}
    public CupState(Status cupStatus) {
        super();
        this.cupStatus = cupStatus;
    }
    public Status getCupStatus() {
        return cupStatus;
    }
    public void setCupStatus(Status cupStatus) {
        this.cupStatus = cupStatus;
    }
    public AtomicLong getAmountOfCupsInStock() {
        return amountOfCupsInStock;
    }
    public AtomicLong getCupsMade() {
        return cupsMade;
    }
    public AtomicLong getCupTemperatureFactor() {
        return cupTemperatureFactor;
    }
    public AtomicReference<Future<?>> getCurrentHeatCupTask() {
        return currentHeatCupTask;
    }
    public void setCupStatus(int status) {
        this.cupStatus.setStatus(status);
    }
}
