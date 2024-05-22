package pt.ipb.dsys.peer.tools;

import org.jgroups.JChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class PeerTools {

  private static final Logger logger = LoggerFactory.getLogger(PeerTools.class);

  public static void prepare() {

  }

  public static void waitFor(JChannel channel) {
    try {
      JOptionPane.showMessageDialog(null, "Hit OK to disconnect!", String.format("Member (%s)", channel.address()), JOptionPane.INFORMATION_MESSAGE);
    } finally {
      logger.info("Disconnecting from cluster");
      channel.disconnect();
    }
  }

}
