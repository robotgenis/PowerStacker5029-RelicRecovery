package org.firstinspires.ftc.teamcode.PineappleRobotPackage.Examples.TestWithoutConfig;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleEnum;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobot;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleSensors.PineappleTouchSensor;

/**
 * Created by young on 8/7/2017.
 */

@TeleOp(name = "PineappleRobotTouchSensorTest", group = "Linear Opmode")



public class touchSensorExample extends LinearOpMode {
    PineappleRobot robot;

    PineappleTouchSensor touch;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new PineappleRobot(this);

        touch = robot.sensorHandler.newTouchSensor("t");

        waitForStart();
        while(opModeIsActive()) {
            robot.sayFeedBack(touch.sensorName, touch.getValue(PineappleEnum.PineappleSensorEnum.TOUCH));
            robot.updateFeedBack();
        }
    }
}
