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
        Pose2d initialPose = new Pose2d(0, -70, Math.toRadians(90));
        robot = new Robot(hardwareMap, telemetry);
        slider = new Slider(robot, gamepad2);
        arm = new Arm(robot);
        wrist = new Wrist(robot);
        claw = new Claw(robot);
        leds = new Leds(robot);
        leds.setPattern(0);


        TrajectoryActionBuilder tab1 = robot.mDrive.actionBuilder(initialPose)
                .splineTo(new Vector2d(0,-27), Math.toRadians(90));

        //.waitSeconds(3);


        Action wait = tab1.fresh()
                .waitSeconds(2)
                .build();


        Action trajectoryActionPushSamples = tab1.fresh()
                .lineToYConstantHeading(-45)
                .setTangent(0)
                .splineToConstantHeading(new Vector2d(35,-30), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(53,0), Math.toRadians(0))
                .setTangent(Math.toRadians(90))
                .lineToYConstantHeading(-60)
                .lineToY(-10)
                .setTangent(0)
                .lineToX(61)
                .setTangent(Math.toRadians(90))
                .lineToY(-60)
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
                        wrist.setSpecimenAction(),
                        wrist.setStartingFoldAction(),
                        wait,
                        trajectoryActionPushSamples
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

