package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;


@Config
@Autonomous(name = "AutonTest", group = "Autonomous")
public class AutonTestApp extends LinearOpMode {
    public Robot robot;
    public DriveTrain DT;
    public Slider slider;
    public Arm arm;
    public Wrist wrist;
    public Claw claw;

    RevBlinkinLedDriver.BlinkinPattern pattern;
    Leds leds;
    @Override
    public void runOpMode() throws InterruptedException {
        // TBD: set the initial position
        Pose2d initialPose = new Pose2d(11.8, 61.7, Math.toRadians(90));
        robot = new Robot(hardwareMap, telemetry);
        slider = new Slider(robot, gamepad2);
        arm = new Arm(robot);
        wrist = new Wrist(robot);
        claw = new Claw(robot);
        leds = new Leds(robot);
        leds.setPattern(0);


        TrajectoryActionBuilder tab1 = robot.mDrive.actionBuilder(initialPose)
                // Modify the following to something like go to line etc.
                //.lineToYSplineHeading(33, Math.toRadians(0))
                //.waitSeconds(2)
                //.setTangent(Math.toRadians(90))
                //.lineToY(48)
                //.setTangent(Math.toRadians(0))
                //.lineToX(32)
                //.strafeTo(new Vector2d(44.5, 30))
                //.turn(Math.toRadians(180))
                //.lineToX(47.5)
                //.waitSeconds(3);
                .lineToY(48)
                .waitSeconds(3);

        Action trajectoryActionComeback = tab1.fresh()
                .strafeTo(new Vector2d(48, 12))
                .build();

        Actions.runBlocking(claw.closeClawAction());
        Action trajAction = tab1.build();
        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        trajAction,
                        arm.setChamberAction(),
                        wrist.setChamberAction(),
                        claw.closeClawAction(),
                        trajectoryActionComeback
                )
        );
    }
}

