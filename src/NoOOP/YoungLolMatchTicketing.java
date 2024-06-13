package NoOOP;

import java.util.Scanner;

public class YoungLolMatchTicketing {

    static final int NUM_MENU = 4;

    public static void main(String[] args) {
        displayWelcome();
        
        boolean quit = false;
        while (!quit) {
            int menu = displayGetMenu();
            switch (menu) {
            case 1:
                menuMatchList();
                break;
            case 2:
                menuCartList();
                break;
            case 3:
                menuAddCartItem();
                break;
            case 4:
                menuClearCart();
                break;
            case 0:
                menuExit();
                quit = true;
                break;
            default:
                menuWrongNumber();
            }
        }
    }
    
    static void displayWelcome() {
        String welcome = "*****************************************\n"
                + "*    Welcome to Young's LoL Match Ticketing    *\n"
                + "*****************************************";
        System.out.println(welcome);
    }
    
    static int displayGetMenu() {
        String menuStr = "=========================================\n"
                + "1. 경기 목록 보기\n"
                + "2. 장바구니 보기\n"
                + "3. 장바구니에 티켓 추가\n"
                + "4. 장바구니 비우기\n"
                + "0. 종료\n"
                + "=========================================";
        
        System.out.println(menuStr);
        System.out.print(">> 메뉴 선택 :"); 
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    static void menuMatchList() {
        System.out.println("MatchList");
        // 실제로 경기를 보여주는 로직이 추가되어야 합니다.
    }
    
    static void menuCartList() {
        System.out.println("CartList");
        // 실제로 장바구니를 보여주는 로직이 추가되어야 합니다.
    }
    
    static void menuAddCartItem() {
        System.out.println("AddCartItem");
        // 실제로 장바구니에 티켓을 추가하는 로직이 추가되어야 합니다.
    }
    
    static void menuClearCart() {
        System.out.println("ClearCart");
        // 실제로 장바구니를 비우는 로직이 추가되어야 합니다.
    }
    
    static void menuExit() {
        System.out.println(">> Young's LoL Match Ticketing을 종료합니다.");
    }
    
    static void menuWrongNumber() {
        System.out.println("없는 메뉴입니다. 0번부터 " + NUM_MENU + "번까지의 메뉴 중에서 선택하세요.");
    }
}
