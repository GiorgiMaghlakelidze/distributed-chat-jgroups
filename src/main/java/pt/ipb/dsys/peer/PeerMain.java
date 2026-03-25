package pt.ipb.dsys.peer;

import org.jgroups.JChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ipb.dsys.peer.jgroups.DefaultProtocols;

public class PeerMain {

  private static final Logger logger = LoggerFactory.getLogger(PeerMain.class);

  public static final String CLUSTER_NAME = "PeerCluster";

  public static final String GOSSIP_ROUTER_HOSTNAME = "192.168.1.162";

  private static final int GOSSIP_ROUTER_PORT = 12001;

  public static void main(String[] args) {
    try(JChannel channel = new JChannel(DefaultProtocols.gossipRouter(GOSSIP_ROUTER_HOSTNAME, GOSSIP_ROUTER_PORT))){
      channel.setDiscardOwnMessages(true).setReceiver(new RumourHandler(channel)).connect(CLUSTER_NAME);
      while(true){
        Thread.sleep(2000);
      }

    }catch (Exception e){
      logger.error(e.getMessage(), e);
    }
  }

}
