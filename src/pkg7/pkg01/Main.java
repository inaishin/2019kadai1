/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg7.pkg01;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Item hotcoffee = new Item(1, "ホットコーヒー", 280); //Item型Hotcoffeインスタンス生成,仮引数に渡す値入力
        Item tea = new Item(2, "紅茶", 260); //Item型Hotcoffeインスタンス生成,仮引数に渡す値入力
        Item icecoffee = new Item(3, "アイスコーヒー", 200);
        Item icetea = new Item(4, "アイスティー", 260);

        Item[] items = new Item[20];
        items[0] = hotcoffee;
        items[1] = tea;
        items[2] = icecoffee;
        items[3] = icetea;

        Scanner scan = new Scanner(System.in);

        int inputNum; //入力された商品番号
        int inputQua; //入力数量
        int inputPay; //入力支払額
        int inputTicket; //割引有無判断
        int inputOff; //入力割引数
        int menuNo;
        int allPrice = 0;
        int count = 4;

        while (true) {
            System.out.println("パスワードを入力してください(パスワードはkbcに設定)");
            String pass = scan.next(); //文字列の入力
            if (pass.equals("kbc")) {
                System.out.println();
                break;
            } else {
                System.out.println("パスワードが違います、もう一度入力してください");
                count--;
                System.out.println("入力可能:あと" + count);
                System.out.println();
                    if (count < 1) {
                        System.out.println("10秒入力できません");
                        TimeUnit.SECONDS.sleep(10); 
                        System.out.println("10秒経過しました");
                        System.out.println();
                   }
            }
        }

        while (true) {//無限ループ
            //表示    
            System.out.println("ITECafeシステム");
            System.out.println(" " + hotcoffee.getNo() + ":" + hotcoffee.getName());
            System.out.println(" " + tea.getNo() + ":" + tea.getName());
            System.out.println(" " + icecoffee.getNo() + ":" + icecoffee.getName());
            System.out.println(" " + icetea.getNo() + ":" + icetea.getName());
            System.out.println(); //1行空ける

            //入力
            System.out.println("番号を入力して下さい。");
            try {
                String inputStr = scan.next(); //文字列の入力
                inputNum = Integer.parseInt(inputStr); //int型への変換
                System.out.println(items[inputNum - 1].getNo() + ":" + items[inputNum - 1].getName() + "" + items[inputNum - 1].getPrice() + "円が追加されました");
                System.out.println(); //1行空ける

                System.out.println("数量を入力してください");
                String inputQ = scan.next(); //文字列の入力
                inputQua = Integer.parseInt(inputQ); //int型への変換
                int all = items[inputNum - 1].getPrice() * inputQua; //税抜き合計
                System.out.println("合計金額(税抜き)は" + all + "円です");
                System.out.println(); //1行空ける                    

                while (true) {
                    System.out.println("商品入力を続ける場合は0,支払に進む場合は1を押してください");
                    String inputNo = scan.next(); //文字列の入力
                    menuNo = Integer.parseInt(inputNo);
                    System.out.println(); //1行空ける

                    if (menuNo == 0) {
                        System.out.println("番号を入力して下さい。");
                        String inputStr1 = scan.next(); //文字列の入力
                        inputNum = Integer.parseInt(inputStr1); //int型への変換
                        System.out.println(items[inputNum - 1].getNo() + ":" + items[inputNum - 1].getName() + "" + items[inputNum - 1].getPrice() + "円が追加されました");
                        System.out.println(); //1行空ける

                        System.out.println("数量を入力してください");
                        String inputQ1 = scan.next(); //文字列の入力
                        inputQua = Integer.parseInt(inputQ1); //int型への変換
                        all += items[inputNum - 1].getPrice() * inputQua;
                        System.out.println("合計金額(税抜き)は" + all + "円です");
                        System.out.println(); //1行空ける

                    } else if (menuNo == 1) {
                        System.out.println(); //1行空ける
                        break;
                    }

                }
                while(true){
                    System.out.println("割引チケットはありますか(有=1 or 無=0)");
                    String Ticket = scan.next(); //文字列の入力
                    inputTicket = Integer.parseInt(Ticket); //int型への変換
                    if(inputTicket == 1||inputTicket == 0){
                        System.out.println(); //1行空ける
                        break;
                    } 
                    else {
                        System.out.println("もう一度入力してください");  
                        System.out.println(); //1行空ける
                    }
                 
                }

                if (inputTicket == 1) {
                    while (true) {
                        System.out.println("1~10割引のうちに該当する数字を入力してください");
                        String Off = scan.next(); //文字列の入力
                        inputOff = Integer.parseInt(Off); //int型への変感
                        if (inputOff > 0 && inputOff < 11) {
                            break;
                        } else if (inputOff < 1 || inputOff > 10) {
                            System.out.println("もう一度入力してください");
                            System.out.println(); //1行空ける
                        }
                    }
                    int tax = all;
                    int taxOff = (tax - tax * inputOff / 10) * 108 / 100;
                    System.out.println("税込み価格は" + taxOff + "円です");
                    System.out.println(); //1行空ける

                    allPrice += (tax - tax * inputOff / 10);

                    while (true) {//無限ループ  

                        System.out.println("支払額を入力してください");
                        String pay = scan.next(); //文字列の入力
                        inputPay = Integer.parseInt(pay); //int型への変換
                        if (taxOff > inputPay) {
                            System.out.println((taxOff - inputPay) + "円足りません");
                            System.out.println(); //1行空ける
                        } else if (inputPay > taxOff) {
                            System.out.println("おつりは" + (inputPay - taxOff) + "円です");
                            System.out.println(); //1行空ける
                            break;
                        }
                    }
                } else if (inputTicket == 0) {
                    int tax = all * 108 / 100;
                    allPrice += all;
                    System.out.println("税込み価格は" + tax + "円です");

                    while (true) {//無限ループ                
                        System.out.println("支払額を入力してください");
                        String pay = scan.next(); //文字列の入力
                        inputPay = Integer.parseInt(pay); //int型への変換
                        if (tax > inputPay) {
                            System.out.println((tax - inputPay) + "円足りません");
                            System.out.println(); //1行空ける
                        } else if (inputPay > tax) {
                            System.out.println("おつりは" + (inputPay - tax) + "円です");
                            System.out.println(); //1行空ける
                            break;
                        }
                    }
                }
                    
                
                System.out.println("現在の売上は" + allPrice + "円");
                System.out.println();
                System.out.println();

            } catch (Exception e) { //エラー処理
                System.out.println("設定されてない数値です");
                for (int i = 0; i < 3; i++) {
                    System.out.println();
                }
            }

        }

    }
}
