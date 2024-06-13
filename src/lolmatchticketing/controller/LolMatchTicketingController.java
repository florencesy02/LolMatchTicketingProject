package lolmatchticketing.controller;

import lolmatchticketing.model.Admin;
import lolmatchticketing.model.MatchStorage;
import lolmatchticketing.model.Cart;
import lolmatchticketing.model.Customer;
import lolmatchticketing.view.ConsoleView;

public class LolMatchTicketingController {

    ConsoleView view;
    MatchStorage mMatchStorage;
    Cart mCart;
    Customer mCustomer;
    Admin mAdmin;
    
    String[] menuList = {
            "0. 종료",
            "1. 경기 정보 보기",
            "2. 티켓 장바구니 보기",
            "3. 티켓 장바구니에 추가",
            "4. 티켓 장바구니에서 삭제",
            "5. 티켓 수량 변경",
            "6. 티켓 장바구니 비우기",
            "7. 티켓 주문",
            "8. Administor Mode" // 새로운 메뉴 항목
    };
    
    String[] adminMenuList = {
            "0. 종료",
            "1. 경기 정보 추가",
            "2. 경기 정보 삭제",
            "3. 경기 정보 보기",
            "4. 경기 정보 파일 저장",
    };
    
    public LolMatchTicketingController(MatchStorage matchStorage, Cart cart, ConsoleView view) {
        this.view = view;
        mMatchStorage = matchStorage;
        mCart = cart;
        mAdmin = new Admin();
    }

    public void start() {
        welcome();
        registerCustomerInfo();
        
        int menu;
        
        do {
            menu = view.selectMenu(menuList);
            
            switch (menu) {
                case 1 -> viewMatchInfo();
                case 2 -> viewCart();
                case 3 -> addTicket2Cart();
                case 4 -> deleteTicketInCart();
                case 5 -> updateTicketInCart();
                case 6 -> resetCart();
                case 7 -> order();
                case 8 -> adminMode(); // 새로운 메뉴 처리
                case 0 -> end();
                default -> view.showMessage("잘못된 메뉴 번호입니다.");
            }
        } while (menu != 0);    
    }

    private void welcome() {
        view.displayWelcome();
    }
    
    private void registerCustomerInfo() {
        mCustomer = new Customer();
        view.inputCustomerInfo(mCustomer);
    }

    private void viewMatchInfo() {
        view.displayMatchInfo(mMatchStorage);
    }
    
    private void viewCart() {
        view.displayCart(mCart);
    }

    private void addTicket2Cart() {
        view.displayMatchInfo(mMatchStorage);
        int matchId = view.selectMatchId(mMatchStorage);
        mCart.addItem(mMatchStorage.getMatchById(matchId));
        view.showMessage(">> 티켓을 장바구니에 추가하였습니다.");    
    }
    
    private void deleteTicketInCart() {
        view.displayCart(mCart);
        if (!mCart.isEmpty()) {
            int matchId = view.selectMatchId(mCart);
            if (view.askConfirm(">> 해당 티켓을 삭제하려면 yes를 입력하세요 : ", "yes")) {
                mCart.deleteItem(matchId);
                view.showMessage(">> 해당 티켓을 삭제했습니다.");
            }
        }
    }
    
    private void updateTicketInCart() {
        view.displayCart(mCart);
        if (!mCart.isEmpty()) {
            int matchId = view.selectMatchId(mCart);
            int quantity = view.inputQuantity(0, mMatchStorage.getMaxQuantity());
            mCart.updateQuantity(matchId, quantity);
        }
    }

    private void resetCart() {
        view.displayCart(mCart);
        if (!mCart.isEmpty()) {
            if (view.askConfirm(">> 티켓 장바구니를 비우려면 yes를 입력하세요 : ", "yes")) {
                mCart.resetCart();
                view.showMessage(">> 티켓 장바구니를 비웠습니다.");
            }
        }
    }
    
    private void order() {
        getDeliveryInfo();
        viewOrderInfo();
        if (view.askConfirm("주문하시려면 yes를 입력하세요 : ", "yes") ) {
            mCart.resetCart();
        }
    }
    
    private void getDeliveryInfo() {
        view.inputDeliveryInfo(mCustomer);    
    }

    private void viewOrderInfo() {
        view.displayOrder(mCart, mCustomer);
    }
    
    private void adminMode() {
        if (!authenticateAdmin()) {
            view.showMessage("관리자 모드로 전환할 수 없습니다.");
            return;
        }
        
        int menu;
        do {
            menu = view.selectMenu(adminMenuList);
            
            switch (menu) {
                case 1 -> addMatch2Storage();
                case 2 -> deleteMatchInStorage();
                case 3 -> viewMatchInfo();
                case 4 -> saveMatchList2File();
                case 0 -> adminEnd();
                default -> view.showMessage("잘못된 메뉴 번호입니다.");
            }
        } while (menu != 0);
    }

    private boolean authenticateAdmin() {
        view.showMessage("관리자 모드 진입을 위한 관리자 인증");
        String id = view.inputString("관리자 ID : ");
        String password = view.inputString("관리자 password : ");
        return mAdmin.login(id, password);
    }
    
    private void addMatch2Storage() {
        view.showMessage("새로운 경기를 추가합니다.");
        mMatchStorage.addMatch(view.inputString("경기 이름 : "),
                view.inputString("팀 1 : "), view.inputString("팀 2 : "),
                view.readNumber("티켓 가격 : "));
    }
    
    private void deleteMatchInStorage() {
        if (mMatchStorage.isEmpty()) {
            view.showMessage("경기 목록에 경기가 없습니다.");
            return;
        }
        viewMatchInfo();
        int matchId = view.selectMatchId(mMatchStorage);
        if (view.askConfirm(">> 해당 경기를 삭제하려면 yes를 입력하세요 : ", "yes")) {
            mMatchStorage.deleteItem(matchId);
            view.showMessage(">> 해당 경기를 삭제했습니다.");
        }
    }

    private void saveMatchList2File() {
        if (mMatchStorage.isSaved()) {
            view.showMessage("경기 정보가 파일과 동일합니다.");
        } else {
            mMatchStorage.saveMatchList2File();
            view.showMessage("경기 정보를 저장하였습니다.");
        }
    }

    // 관리자 모드 종료 메서드
    private void adminEnd() {
        if (!mMatchStorage.isSaved()) {
            view.showMessage("수정한 경기 정보가 파일로 저장되지 않았습니다.");
            if (view.askConfirm("경기 정보를 저장하려면 yes를 입력하세요 : ", "yes")) {
                mMatchStorage.saveMatchList2File();
            }
        }
        view.showMessage("관리자 모드가 종료되었습니다.\n");
    }
    
    private void end() {
        view.showMessage(">> Lol Match Ticketing System을 종료합니다.");
    }
}
