package org.opendaylight.yang.gen.v1.inocybe.rev141116;
import org.opendaylight.yangtools.yang.binding.NotificationListener;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.NoMoreCups;


/**
 * Interface for receiving the following YANG notifications defined in module <b>cup</b>
 * <br />(Source path: <i>META-INF/yang/cup.yang</i>):
 * <pre>
 * notification noMoreCups {
 *     description
 *         "Indicates that there are no available cups to make tea.";
 *     status CURRENT;
 * }
 * </pre>
 */
public interface CupListener
    extends
    NotificationListener
{




    /**
     * Indicates that there are no available cups to make tea.
     */
    void onNoMoreCups(NoMoreCups notification);

}

