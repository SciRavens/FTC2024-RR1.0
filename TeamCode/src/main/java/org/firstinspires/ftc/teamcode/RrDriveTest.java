package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "RR-Drive")
public class RrDriveTest extends LinearOpMode {
    public Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry);

        waitForStart();
        while(opModeIsActive()) {
            // Get gamepad inputs for driving
            double y = -gamepad1.left_stick_y;  // Forward/backward (inverted)
            double x = -gamepad1.left_stick_x;  // Strafe (adjusted for mecanum drift)
            double rx = gamepad1.right_stick_x;  // Rotation

            // Set the drive power using the MecanumDrive class
            robot.mDrive.setDrivePowers( new PoseVelocity2d( new Vector2d(y, x), rx) );

            // Optional telemetry for debugging
            telemetry.addData("y", y);
            telemetry.addData("x", x);
            telemetry.addData("rx", rx);
            telemetry.update();
        }
    }
}

