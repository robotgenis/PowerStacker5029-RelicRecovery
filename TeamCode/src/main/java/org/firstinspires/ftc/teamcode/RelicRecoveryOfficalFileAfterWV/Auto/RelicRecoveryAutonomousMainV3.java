package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.Auto;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFile.RelicResources.RelicRecoveryEnums;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.RelicRecoveryResources.RelicRecoveryConfigV3;

import java.util.ArrayList;

/**
 * Created by Brandon on 12/5/2017.
 */

public class RelicRecoveryAutonomousMainV3 extends RelicRecoveryConfigV3 {


    autoCount count;



    @Override
    public void runOpMode() throws InterruptedException {
        count = new autoCount(telemetry);
        count.setCount("a");
        count.sayCount();
        telemetry.update();
        //load robot
        config(this);
        //load everything
        aLoadSwitchBoard();

        //wait for start
        waitForStart();
        count.setCount("b");

        while (opModeIsActive()){
            switch (colorPosition){
                case REDFRONT:
                    switch (count.getCount()){
                        case "b":
                            //TODO scan

                            break;
                        case "c":
                            //TODO jewels

                            break;
                        case "d":
                            //drive off platform
                            //TODO add encoder drive at the end
                            if(driveOffPlatform(.5))
                                count.setCount("e");
                            break;
                        case "e":
                            //TODO turn

                            break;
                        case "f":
                            //TODO line up to wall

                            break;
                        case "g":
                            //TODO insert glyph

                            break;
                        case "h":
                            //only runs once
                            //TODO get ready to collect glyph and track encoders


                            count.setCount("i");
                            break;
                        case "i":
                            //TODO drive to glyph and collect two
                            encoderTracking.add();
                            break;
                        case "j":
                            //only runs once
                            //TODO stop encoders and

                            count.setCount("k");
                            break;
                        case "k":
                            //TODO drive back using encoders

                            break;
                        default:
                            telemetry.addData("ERROR NOT LINE LETTER",count.getCount());
                            break;
                    }
                    break;
                case REDBACK:

                    break;
                case BLUEFRONT:

                    break;
                case BLUEBACK:

                    break;
            }

            count.sayCount();
            telemetry.update();
        }
    }

    private int driveOff = 0;
    public boolean driveOffPlatform(double motorSpeed){
        robotHandler.drive.mecanum.update(motorSpeed, -motorSpeed);
        double robotTilt = getTilt();
        if(robotTilt > 3){
            driveOff = 1;
        }else if(robotTilt < 1 && driveOff == 1){
            driveOff = 2;
        }else if(driveOff == 3){
            //TODO encoder drive then return true
        }
        telemetry.addData("Drive Off", driveOff);
        return false;
    }

    public double getTilt(){
        return 0.0;
    }

    public double getHeading(){
        return 0.0;
    }
}

class autoCount{

    private Telemetry telemetry;
    private String count = "a";

    autoCount(Telemetry telemetry){
        this.telemetry = telemetry;
    }

    void setCount(String set){
        count = set;
    }

    void sayCount(){
        telemetry.addData("Count", count);
    }

    String getCount(){
        return count;
    }
}