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
        Pose2d initialPose = new Pose2d(0, 72, Math.toRadians(90));
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
                //.waitSeconds(2)
                .strafeTo(new Vector2d(0,0));
                //.waitSeconds(3);


        Action wait = tab1.fresh()
                .waitSeconds(2)
                .build();


        Action trajectoryActionPushSample = tab1.fresh()
                .strafeTo(new Vector2d(0, 75))
                .strafeTo(new Vector2d(36, 40))
//                .strafeTo(new Vector2d(36, 9))
//                .strafeTo(new Vector2d(52, 9))
//                .turn(180)
//                .strafeTo(new Vector2d(52, 35))
//                .strafeTo(new Vector2d(25,35))
//                .waitSeconds(0.5)
                .build();


        Action trajectoryRotate180 = tab1.fresh()
                        .turn(180)
                        .waitSeconds(0.5)
                        .build();



        Actions.runBlocking(claw.closeClawAction());
        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        claw.closeClawAction(),
                        arm.setChamberAction(),
                        wrist.setHighChamberAction(),
                        wait,
                        tab1.build(),
                        wait,
                        claw.openClawAction(),
                        wait,
                        wrist.setSpecimenAction(),
                        wait,
                        trajectoryActionPushSample
//                        wrist.setSpecimenAction(),
//                        claw.closeClawAction(),
//                        arm.setChamberAction(),
//                        wait,
//                        build,
//                        arm.setChamberAction(),
//                        wait,
//                        claw.openClawAction()
                )
        );
        telemetry.update();
    }
}

