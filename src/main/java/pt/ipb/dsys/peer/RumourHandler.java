package pt.ipb.dsys.peer;

import org.jgroups.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ipb.dsys.peer.model.Rumour;
import pt.ipb.dsys.peer.util.Sleeper;

import java.util.*;

public class RumourHandler implements Receiver {

  private static final Logger logger = LoggerFactory.getLogger(RumourHandler.class);

  private final JChannel channel;

  public static String RUMOR = "Who the hell is steve jobs??";
  private List<Address> previousMembers = new ArrayList<>();

  public RumourHandler(JChannel channel) {
    this.channel = channel;
  }

  @Override
  public void receive(Message msg) {
    Rumour rumour = msg.getObject();
//    logger.info("{}: {}", msg.src(), rumour.getRumour());
    System.out.println("\n" + msg.src() + ": " + rumour.getRumour());
  }

  @Override
  public void viewAccepted(View view) {
    if(previousMembers.isEmpty()){
      previousMembers = new ArrayList<>(view.getMembers());
      System.out.println("\nYou joined the chat successfully!");
      Thread thread = new Thread(() -> {startTexting();});
      thread.start();
      return;
    }

//    logger.info("[{}] Group membership changed: {}, ", channel.address(), view.getMembers().size());

    List<Address> list1 = null;
    List<Address> list2 = null;
    String message = "";
    if(previousMembers.size() > view.getMembers().size()){
      list1 = previousMembers;
      list2 = view.getMembers();
      message = " left the chat";
    }else{
      list1 = view.getMembers();
      list2 = previousMembers;
      message = " joined the chat";
    }

    for(Address address1 : list1){
      boolean p = false;
      for(Address address2 : list2){
        if(address1 == address2){
          p = true;
          break;
        }
      }
      if(p == false){
        System.out.println("\n" + address1 + message);
      }
    }
    previousMembers = new ArrayList<>(view.getMembers());

//    startTexting();






//    for(Address address : view.getMembers()){
//      boolean itsMe = Objects.equals(address, channel.address());
//      if(itsMe || alreadyTold.contains(address)){
//        continue;
//      }
//      //send gossip to everyone
//      sendRumour(address, new Rumour(RUMOR));
//      alreadyTold.add(address);
//    }
  }

  private void startTexting(){
    Scanner scanner = new Scanner(System.in);
    while(true){
      String message = scanner.nextLine();
      sendRumour(null, new Rumour(message));
    }
  }
  private void sendRumour(Address address, Rumour rumour) {
    try{
      rumour.increaseRumour();
      channel.send(new ObjectMessage(address, rumour));
    }catch(Exception e){}
  }

}
