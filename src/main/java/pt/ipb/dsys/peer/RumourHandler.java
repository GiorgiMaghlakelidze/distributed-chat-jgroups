package pt.ipb.dsys.peer;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.jgroups.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RumourHandler implements Receiver {

  private static final Logger logger = LoggerFactory.getLogger(RumourHandler.class);

  private final JChannel channel;

  public RumourHandler(JChannel channel) {
    this.channel = channel;
  }

  @Override
  public void receive(Message msg) {
  }

  @Override
  public void viewAccepted(View view) {
    logger.info("[{}] Group membership changed: {}", channel.address(), view.getMembers().size());
  }

}
