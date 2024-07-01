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

            if (rq.getErrMsg().equals("오타 있음(id)")) {
                continue;

            }

            switch (rq.getActionMethod()) {
                case "exit":
                    systemController.exit();
                    system_status = 0;
                    break;
                case "add":
                    motivationController.add();
                    break;
                case "list":
                    motivationController.list();
                    break;
                case "delete":
                    motivationController.delete(rq);
                    break;
                case "edit":
                    motivationController.edit(rq);
                    break;
                default:
                    System.out.println("사용할 수 없는 명령어입니다");
                    break;
            }

        }
    }
}