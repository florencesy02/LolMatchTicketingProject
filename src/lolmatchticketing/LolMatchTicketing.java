package lolmatchticketing;

import java.io.IOException;

import lolmatchticketing.controller.LolMatchTicketingController;
import lolmatchticketing.model.MatchStorage;
import lolmatchticketing.model.Cart;
import lolmatchticketing.view.ConsoleView;

public class LolMatchTicketing {

    public static void main(String[] args) throws IOException {
        // model 생성
        MatchStorage matchStorage = new MatchStorage();
        Cart cart = new Cart();
        
        // view 생성
        ConsoleView view = new ConsoleView();
        
        // controller 생성 (model, view)
        LolMatchTicketingController controller = new LolMatchTicketingController(matchStorage, cart, view);
        controller.start();
    }
}
