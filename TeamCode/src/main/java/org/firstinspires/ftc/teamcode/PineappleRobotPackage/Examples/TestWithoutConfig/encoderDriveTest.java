package org.firstinspires.ftc.teamcode.PineappleRobotPackage.Examples.TestWithoutConfig;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleMotor;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobot;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleSensors.PineappleTouchSensor;

/**
 * Created by young on 8/7/2017.
 */
@TeleOp(name = "PineappleRobotEncoderDriveTest", group = "Linear Opmode")


public class encoderDriveTest extends LinearOpMode {
    PineappleRobot robot;

    PineappleMotor left;
    PineappleMotor right;
    PineappleTouchSensor touch;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new PineappleRobot(this);

        left = robot.motorHandler.newDriveMotor("left", 1, true , true, PineappleEnum.MotorLoc.LEFT, PineappleEnum.MotorType.NEV40);
        right = robot.motorHandler.newDriveMotor("right", 1, true , true, PineappleEnum.MotorLoc.RIGHT, PineappleEnum.MotorType.NEV40);

        touch = robot.sensorHandler.newTouchSensor("touch");

        robot.mapRobot();

        right.motorObject.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        robot.auto.encoderDrive(0.5, "4in",4);
        robot.drive.setDirectPower(-1, -1);
        Thread.sleep(1000);
    }
}
