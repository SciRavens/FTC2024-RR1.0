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
public class BlueAutonTestApp extends LinearOpMode {
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
        Pose2d initialPose = new Pose2d(-12, 70, Math.toRadians(90));
        robot = new Robot(hardwareMap, telemetry);
        slider = new Slider(robot, gamepad2);
        arm = new Arm(robot);
        wrist = new Wrist(robot);
        claw = new Claw(robot);
        leds = new Leds(robot);
        leds.setPattern(0);


        TrajectoryActionBuilder tab1 = robot.mDrive.actionBuilder(initialPose)
//                .setTangent(Math.toRadians(0))
                .strafeTo(new Vector2d(-12,17.5));

        //.waitSeconds(3);


        Action wait = tab1.fresh()
                .waitSeconds(2)
                .build();


        Action trajectoryActionPushSamples = tab1.fresh()
                .strafeTo(new Vector2d(-12,25))
                .strafeTo(new Vector2d(-75,25))
                .strafeTo(new Vector2d(-75,0))
                .strafeTo(new Vector2d(-105,0))
                .strafeTo(new Vector2d(-105,100))
                .strafeTo(new Vector2d(-105,30))
                .strafeTo(new Vector2d(-135,30))
                .strafeTo(new Vector2d(-115,110))
                .strafeTo(new Vector2d(-115,50))
                .strafeTo(new Vector2d(-140,50))
                .strafeTo(new Vector2d(-140,100))
                .build();

        Action trajectoryActionPushSamplesSpline = tab1.fresh()
                .splineTo(new Vector2d(-30,-30), Math.toRadians(180))
                .build();

        Action trajectoryRotate180 = tab1.fresh()
                        .turn(180)
                        .waitSeconds(0.5)
                        .build();

        Actions.runBlocking(claw.closeClawAction());
        Actions.runBlocking(arm.setFoldAction());
        Actions.runBlocking(wrist.setStartingFoldAction());

        waitForStart();
        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        arm.setChamberAction(),
                        wrist.setHighChamberAction(),
                        slider.sliderHighChamberAction(),
                        tab1.build(),
                        slider.sliderInitialPoseAction(),
                        claw.openClawAction(),
                        arm.setFoldAction(),
                        wrist.setStartingFoldAction(),
                        trajectoryActionPushSamples

//                        arm.setSampleAction(),
//                        wrist.setSampleAction(),
//                        claw.openClawAction()
//                        wait,
//                        claw.openClawAction(),
//                        wrist.setSpecimenAction(),
//                        wrist.setStartingFoldAction(),
//                        wait,
//                        trajectoryActionPushSamples
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

