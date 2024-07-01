package org.koreait;

import org.koreait.motivation.controller.MotivationController;
import org.koreait.system.controller.SystemController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    public void run() {
        System.out.println("== motivation execution ==");

        SystemController systemController = new SystemController();
        MotivationController motivationController = new MotivationController();

        while (true) {
            System.out.print("command) ");
            String cmd = Container.getScanner().nextLine().trim();

            if (cmd.equals("exit")) {
                systemController.exit();
                break;
            } else if (cmd.length() == 0) {
                System.out.println("명령어 입력해");
                continue;
            }

            if (cmd.equals("add")) {
                motivationController.add();
            } else if (cmd.equals("list")) {
                motivationController.list();
            } else if (cmd.startsWith("delete")) {

                Rq rq = new Rq(cmd);

                // (delete? id =)이라는 형태는 delete말고 다른 기능을 실행할때 필요하다.
                // 다른 기능을 쓸때에도 똑같은 기능이 필요한데 같은 코드를 쓰게 되면 비효율적이기에
                // 이를 담을수 있는 Request인 Rq 클래스를 만들어서 그안에 넣고 가져와서 사용

                System.out.println(rq.getActionMethod());
                System.out.println(rq.getParams("id"));
                System.out.println(rq.getParams("source"));
                System.out.println(rq.getParams("motivation"));

//                motivationController.delete();
            } else {
                System.out.println("사용할 수 없는 명령어입니다");
            }
        }
    }
}