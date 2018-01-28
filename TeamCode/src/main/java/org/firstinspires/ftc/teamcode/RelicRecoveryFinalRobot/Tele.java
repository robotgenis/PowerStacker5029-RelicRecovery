package org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


/**
 * Created by Brandon on 1/8/2018.
 */
@TeleOp(name = "TeleOp")

public class Tele extends Config {
    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

        waitForStart();

//        boolean bClicked = false;
        double collectorSpeed = 0;

        while (opModeIsActive()) {
            robotHandler.drive.mecanum.updateMecanum(gamepad1, (gamepad1.right_bumper) ? 0.7 : 1.0);
            collectorSpeed = (gamepad1.right_trigger > 0.10) ? gamepad1.right_trigger : (gamepad1.left_trigger > 0.10) ? -gamepad1.left_trigger : 0;
            motorCollectRight.setPower(collectorSpeed);
            motorCollectLeft.setPower(-collectorSpeed);
            motorLift.setPower((gamepad2.dpad_up) ? -1 : (gamepad2.dpad_down) ? 1 : 0);
            motorRelic.setPower(gamepad2.left_stick_y);
            if (gamepad2.y) {
                servoFlipL.setPosition(Constants.flip.leftUp);
                servoFlipR.setPosition(Constants.flip.rightUp);
            } else if (gamepad2.x) {
                servoFlipL.setPosition(Constants.flip.leftFlat);
                servoFlipR.setPosition(Constants.flip.rightFlat);
            } else if (gamepad2.a) {
                servoFlipL.setPosition(Constants.flip.leftDown);
                servoFlipR.setPosition(Constants.flip.rightDown);
            } else if (gamepad2.right_bumper) {
                servoAlignLeft.setPosition(Constants.alignment.ALIGNLEFTDOWN);
            } else if (gamepad2.left_bumper) {
                servoAlignRight.setPosition(Constants.alignment.ALIGNRIGHTDOWN);
            } else {
                servoAlignRight.setPosition(Constants.alignment.ALIGNRIGHTUP);
                servoAlignLeft.setPosition(Constants.alignment.ALIGNLEFTUP-.3);

            }
            telemetry.update();


        }
    }
}
