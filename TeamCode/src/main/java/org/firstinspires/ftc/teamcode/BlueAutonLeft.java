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
@Autonomous(name = "BlueAutonTest", group = "Autonomous")
public class BlueAutonLeft extends LinearOpMode {
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
                .strafeTo(new Vector2d(-12,5));

        TrajectoryActionBuilder tab2 = robot.mDrive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(-12,5));

        //.waitSeconds(3);


        Action wait = tab1.fresh()
                .waitSeconds(2)
                .build();


        Action blueTrajectoryActionPushSamples = tab1.fresh()
                .strafeTo(new Vector2d(-12,70)) //moves back from specimen hanging
                .strafeTo(new Vector2d(-85,70)) //strafes to left side
                .strafeTo(new Vector2d(-85,20)) //1st moves forward
                .strafeTo(new Vector2d(-115,20)) //1st moves to the side a little bit
                .strafeTo(new Vector2d(-115,110)) // pushes first sample
                .strafeTo(new Vector2d(-115,20)) //2nd moves forward
                .strafeTo(new Vector2d(-145,20)) // 2nd strafes a little bit
                .strafeTo(new Vector2d(-145,110)) // pushes second sample
                .strafeTo(new Vector2d(-145,40)) //3rd moves forward
                .strafeTo(new Vector2d(-180,40)) //3rd strafes a little bit
                .strafeTo(new Vector2d(-180,130)) //pushes last sample
                .build();

        Action redTrajectoryActionPushSamples = tab1.fresh()
                .strafeTo(new Vector2d(-12,70)) //moves back from specimen hanging
                .strafeTo(new Vector2d(-85,70)) //strafes to left side
                .strafeTo(new Vector2d(-85,0)) //1st moves forward
                .strafeTo(new Vector2d(-125,0)) //1st moves to the side a little bit
                .strafeTo(new Vector2d(-125,100)) // pushes first sample
                .strafeTo(new Vector2d(-125,0)) //2nd moves forward
                .strafeTo(new Vector2d(-170,0)) // 2nd strafes a little bit
                .strafeTo(new Vector2d(-170,100)) // pushes second sample
                .strafeTo(new Vector2d(-170,0)) //3rd moves forward
                .strafeTo(new Vector2d(-195,0)) //3rd strafes a little bit
                .strafeTo(new Vector2d(-195,120)) //pushes last sample
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
//                        redTrajectoryActionPushSamples
                        blueTrajectoryActionPushSamples

                )
        );
        telemetry.update();
    }
}

