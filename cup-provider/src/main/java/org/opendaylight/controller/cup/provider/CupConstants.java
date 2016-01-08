/*
 * Copyright (c) 2016 Sniggel and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.controller.cup.provider;

import java.util.concurrent.atomic.AtomicLong;

import org.opendaylight.yang.gen.v1.inocybe.rev141116.DisplayString;

public class CupConstants {

    public static final DisplayString CUP_MANUFACTURER = new DisplayString("Opendaylight");

    public static final DisplayString CUP_MODEL_NUMBER = new DisplayString("Model 1 - Binding Aware");

    public static final AtomicLong CUP_TEMPERATURE = new AtomicLong( 1000 );

}
