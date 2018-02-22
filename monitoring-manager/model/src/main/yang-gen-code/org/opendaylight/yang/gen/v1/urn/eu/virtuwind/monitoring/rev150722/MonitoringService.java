package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722;
import org.opendaylight.yangtools.yang.binding.RpcService;
import org.opendaylight.yangtools.yang.common.RpcResult;
import java.util.concurrent.Future;


/**
 * Interface for implementing the following YANG RPCs defined in module <b>monitoring</b>
 * <br>(Source path: <i>META-INF/yang/monitoring.yang</i>):
 * <pre>
 * rpc get-stats {
 *     output {
 *         leaf stats {
 *             type string;
 *         }
 *     }
 * }
 * rpc install-flow {
 *     output {
 *         leaf success {
 *             type string;
 *         }
 *     }
 * }
 * rpc delete-flow {
 *     output {
 *         leaf success {
 *             type string;
 *         }
 *     }
 * }
 * </pre>
 *
 */
public interface MonitoringService
    extends
    RpcService
{




    Future<RpcResult<GetStatsOutput>> getStats();
    
    Future<RpcResult<InstallFlowOutput>> installFlow();
    
    Future<RpcResult<DeleteFlowOutput>> deleteFlow();

}

