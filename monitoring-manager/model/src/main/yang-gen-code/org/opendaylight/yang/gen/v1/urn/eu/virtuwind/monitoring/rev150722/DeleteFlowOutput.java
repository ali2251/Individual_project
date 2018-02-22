package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>monitoring</b>
 * <br>(Source path: <i>META-INF/yang/monitoring.yang</i>):
 * <pre>
 * container output {
 *     leaf success {
 *         type string;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>monitoring/delete-flow/output</i>
 *
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutputBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutputBuilder
 *
 */
public interface DeleteFlowOutput
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:monitoring",
        "2015-07-22", "output").intern();

    java.lang.String getSuccess();

}

