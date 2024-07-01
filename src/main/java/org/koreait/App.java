package org.koreait;

import org.koreait.motivation.controller.MotivationController;
import org.koreait.system.controller.SystemController;

public class App {

    byte system_status = 1;

    public void run() {
        System.out.println("== motivation execution ==");

        SystemController systemController = new SystemController();
        MotivationController motivationController = new MotivationController();

        while (system_status == 1) {
            System.out.print("command) ");
            String cmd = Container.getScanner().nextLine().trim();

            if (cmd.length() == 0) {
                System.out.println("명령어 입력해");
                continue;
            }

            Rq rq = new Rq(cmd);

            switch (rq.getActionMethod()) {
                case "exit":
                    systemController.exit();
                    system_status = 0;
                    // while 문이 true 였을 때 아래의 break가 switch만 빠져나오게 하는 코드이므로
                    // while 문에 system_status르 넣어주고 exit 실행되었을 때 0이 되도록 하여 false로 만들어 while문을
                    // 빠져나갈수 있도록 만들었다.
                    break;
                case "add":
                    motivationController.add();
                    break;
                case "list":
                    motivationController.list();
                    break;
                case "delete":
//                    motivationController.delete();
                default:
                    System.out.println("사용할 수 없는 명령어입니다");
                    break;
            }

            //  라우팅 코드 간결화로 어떤 기능이 들어왔는지 switch로 묶어줌으로써
            // 한눈에 어떻게 실행될지 보인다.
        }
    }
}