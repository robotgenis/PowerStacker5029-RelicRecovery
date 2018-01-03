package org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.Auto;

import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.PineappleRobotPackage.lib.PineappleRobotConstants;
import org.firstinspires.ftc.teamcode.RelicRecoveryOfficalFileAfterWV.RelicRecoveryResources.RelicRecoveryConfigV2Cleve;

/**
 * Created by ftcpi on 1/3/2018.
 */

public class RelicRecoveryAutonomousMainVCLEV extends RelicRecoveryConfigV2Cleve{
    enum Init {
        CALIBGYRO, FINDIMAGE, FINDKEY, GETJEWELCONFIG
    }
    enum Auto{
        JEWELDOWN, JEWELTURN, JEWELUP, DRIVEOFFPLAT, PDRIVEFORWARD, DRIVEFORWARD, TURNTOCRYPTO, DRIVEFORWARDTOCRYPTO, ALIGNTOCRYPTO;
    }

    Auto auto = Auto.JEWELDOWN;
    Init init = Init.CALIBGYRO;

    @Override
    public void runOpMode() throws InterruptedException {
        config(this);

        //load vuforia
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AdB8VB7/////AAAAGcfBp9I80URFkfBQFUyM+ptmQXBAMGx0svJKz7QE2nm20mBc/zI5sZNHfuP/ziIm+sYnO7fvPqUbFs8QWjRyXVEDmW4mMj+S+l+yaYRkpGZ/pmHyXiDb4aemHx0m70BulMNIce4+NVaCW5S/5BWNNev/AU0P+uWnHYuKNWzD2dPaRuprC4R6b/DgD1zeio1xlssYb9in9mfzn76gChOrE5B0ql6Q9FiHC5cTdacq2lKjm5nlkTiXz1e2jhVK3SddGoqM4FQ3mFks7/A88hFzlPfIIk45K2Lh7GvcVjuIiqNj5mTLaZJVqlsKdTQnKS4trJcc1YV9sjdbmh1agtn1UePy91fDj9uWSBdXvpIowv4B";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        vuforia.setFrameQueueCapacity(1);
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
        VuforiaTrackables relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
        VuforiaTrackableDefaultListener listener = (VuforiaTrackableDefaultListener) relicTemplate.getListener();
        //This is not needed as it is a repeat from the line above
        //VuforiaTrackableDefaultListener track = (VuforiaTrackableDefaultListener) relicTrackables.get(0).getListener();



        relicTrackables.activate();

        RelicRecoveryVuMark keyColumn = RelicRecoveryVuMark.UNKNOWN;

        int val = 1;
        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addLine("Init");
            switch (init) {

                case CALIBGYRO:

                    break;
                case FINDIMAGE:
                    break;
                case FINDKEY:
                    break;
                case GETJEWELCONFIG:
                    break;
            telemetry.update();
            }
            if(listener.getPose() != null) {
                keyColumn = RelicRecoveryVuMark.from(relicTemplate);
            }
            if (val == 5) {
                val = 1;
            }
            String dots = "";
            for (int i = 0; i < val; i++) {
                dots += ".";
            }

            telemetry.addLine("Waiting for Start");
            if(keyColumn == null){
                telemetry.addLine("Finding Image" + dots);
            }else{
                telemetry.addData("Key Column", keyColumn);
            }
            telemetry.update();

            Thread.sleep(200);
            val++;

        }
        waitForStart();

        auto = Auto.PDRIVEFORWARD;

        double startingPos = 0;
        while (opModeIsActive()){
            switch (auto){
                case JEWELDOWN:
                    break;
                case JEWELTURN:
                    break;
                case JEWELUP:
                    break;
                case DRIVEOFFPLAT:
                    break;
                case PDRIVEFORWARD:
                    startingPos = driveFrontLeft.getEncoderPosition();
                    robotHandler.drive.mecanum.setPower(.5,-.5);
                    break;
                case DRIVEFORWARD:
                    switch (keyColumn){
                        double pos = driveFrontLeft.getEncoderPosition();
                        double dis = 1000;
                        double rotation = 4*Math.PI;
                        case UNKNOWN:
                            keyColumn = RelicRecoveryVuMark.CENTER;
                            break;
                        case LEFT:
                            if(pos - startingPos > dis)
                                auto = Auto.TURNTOCRYPTO;
                            break;
                        case CENTER:
                            if(pos - startingPos > dis + )
                                auto = Auto.TURNTOCRYPTO;
                            break;
                        case RIGHT:
                            if(pos - startingPos > 400)
                                auto = Auto.TURNTOCRYPTO;
                            break;
                    }
                    if(driveFrontRight.getEncoderPosition() > )
                    break;
                case TURNTOCRYPTO:
                    break;
                case DRIVEFORWARDTOCRYPTO:
                    break;
                case ALIGNTOCRYPTO:
                    break;
            }
        }
    }
}
