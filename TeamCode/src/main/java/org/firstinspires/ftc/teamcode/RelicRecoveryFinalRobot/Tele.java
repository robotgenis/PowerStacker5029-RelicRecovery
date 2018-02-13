package org.firstinspires.ftc.teamcode.RelicRecoveryFinalRobot;

import com.kauailabs.navx.ftc.navXPIDController;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * Created by Brandon on 1/8/2018.
 */
@TeleOp(name = "TeleOp")

public class Tele extends Config {
    @Override
    public void runOpMode() throws InterruptedException {
        config(this);
        while(!isStarted()&&!isStopRequested()){
            telemetry.addData("PID","CALIBRATING");
        calibration_complete = !navx_device.isCalibrating();
        if (!calibration_complete) {
        } else {
            navx_device.zeroYaw();
            yawPIDResult = new navXPIDController.PIDResult();
        }}
        waitForStart();

//        boolean bClicked = false;
        double collectorSpeed = 0;
        double  PIDrotationOut = 0;
        boolean PIDON = true;
        ElapsedTime collectorRPMTimer = new ElapsedTime();
        ElapsedTime collectorRPM= new ElapsedTime();
        double prervPos = motorCollectLeft.getEncoderPosition();
        double prervTime = collectorRPMTimer.milliseconds();
        while (opModeIsActive()) {
            double encoderDif = motorCollectLeft.getEncoderPosition()-prervPos;
            double timeDif = collectorRPMTimer.milliseconds()-prervTime;
            double RPM = Math.abs((60000/timeDif)*encoderDif);
            telemetry.addData("Encoder Val Right", motorCollectRight.getEncoderPosition());
            telemetry.addData("Encoder Val Left", motorCollectLeft.getEncoderPosition());
            telemetry.addData("Collector RPM", RPM);
            if (gamepad1.right_stick_x<=0.07&&gamepad1.right_stick_x>=-0.07){
                if (yawPIDController.isNewUpdateAvailable(yawPIDResult)) {
                    if (yawPIDResult.isOnTarget()) {
                        PIDrotationOut = 0.0;
                    } else {
                        PIDrotationOut = yawPIDResult.getOutput();
                    }
                }
            } else {
                yawPIDController.setSetpoint(navx_device.getYaw());
                PIDrotationOut = 0;
            }
            if (gamepad1.right_bumper){
                PIDON = false;
            }
            if (gamepad1.left_bumper){
                PIDON = true;
            }
            if (!PIDON){
                PIDrotationOut=0;
            }
            robotHandler.drive.mecanum.updateMecanum(gamepad1, (gamepad1.right_bumper) ? 0.7 : 1.0, PIDrotationOut);
            collectorSpeed = (gamepad1.right_trigger > 0.10) ? gamepad1.right_trigger : (gamepad1.left_trigger > 0.10) ? -gamepad1.left_trigger : 0;
            motorCollectRight.setPower(collectorSpeed);
            motorCollectLeft.setPower(-collectorSpeed);
            motorLift.setPower((gamepad2.dpad_up) ? -1 : (gamepad2.dpad_down) ? 1 : 0);
            motorRelic.setPower(-gamepad2.left_stick_y);
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
                servoRelicGrab.setPosition(Constants.relic.grabClose);
            } else if (gamepad2.left_bumper) {
                servoRelicGrab.setPosition(Constants.relic.grabOpen);
            } else if (gamepad2.right_trigger>0.2){
                servoRelicTurn.setPosition(Constants.relic.turnStraight);
            }else if (gamepad2.left_trigger>0.2){
                servoRelicTurn.setPosition(Constants.relic.turnDown);
            } else if (gamepad1.y){
                servoAlignRight.setPosition(Constants.alignment.ALIGNRIGHTDOWN);
            }
            else if (gamepad1.x){
                servoAlignLeft.setPosition(Constants.alignment.ALIGNLEFTDOWN);
            }
            else {
                servoAlignRight.setPosition(Constants.alignment.ALIGNRIGHTUP);
                servoAlignLeft.setPosition(Constants.alignment.ALIGNLEFTUP);

            }
            telemetry.update();
            if (collectorRPM.milliseconds()>500){
             prervPos = motorCollectLeft.getEncoderPosition();
             prervTime = collectorRPMTimer.milliseconds();
            collectorRPM.reset();
            }

        }
    }
}
