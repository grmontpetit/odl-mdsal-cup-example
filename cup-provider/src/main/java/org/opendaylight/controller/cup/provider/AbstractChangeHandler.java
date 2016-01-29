/*
 * Copyright (c) 2016 Sniggel and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.controller.cup.provider;

import java.util.Collection;

import static com.google.common.base.Preconditions.checkNotNull;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.DataObjectModification;
import org.opendaylight.controller.md.sal.binding.api.DataTreeChangeListener;
import org.opendaylight.controller.md.sal.binding.api.DataTreeIdentifier;
import org.opendaylight.controller.md.sal.binding.api.DataTreeModification;
import org.opendaylight.yangtools.concepts.ListenerRegistration;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;

public abstract class AbstractChangeHandler<T extends DataObject> implements DataTreeChangeListener<T>, AutoCloseable {

    protected final DataBroker dataProvider;
    protected final ListenerRegistration<AbstractChangeHandler<T>> registeredListener;

    public AbstractChangeHandler(DataBroker dataProvider, DataTreeIdentifier<T> pointOfInterest) {
        this.dataProvider = checkNotNull(dataProvider);
        this.registeredListener = dataProvider.registerDataTreeChangeListener(checkNotNull(pointOfInterest), this);
    }

    @Override
    public void onDataTreeChanged(Collection<DataTreeModification<T>> changes) {
        for (DataTreeModification<T> change : changes) {
            DataObjectModification<T> rootNode = change.getRootNode();
            InstanceIdentifier<T> rootIdentifier = change.getRootPath().getRootIdentifier();
            switch (rootNode.getModificationType()) {
            case WRITE:
                onWrite(rootNode, rootIdentifier);
                break;
            case DELETE:
                onDelete(rootNode, rootIdentifier);
                break;
            case SUBTREE_MODIFIED:
                onSubtreeModified(rootNode, rootIdentifier);
                break;
            }
        }
    }

    protected abstract void onWrite(DataObjectModification<T> rootNode, InstanceIdentifier<T> rootIdentifier);

    protected abstract void onDelete(DataObjectModification<T> rootNode, InstanceIdentifier<T> rootIdentifier);

    protected abstract void onSubtreeModified(DataObjectModification<T> rootNode, InstanceIdentifier<T> rootIdentifier);

    @Override
    public void close() throws Exception {
        registeredListener.close();
    }

}
