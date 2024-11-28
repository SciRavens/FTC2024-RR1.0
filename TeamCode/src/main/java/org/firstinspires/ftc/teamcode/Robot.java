package org.firstinspires.ftc.teamcode;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

public class Robot {
    public DcMotor motorFR = null; // Front Right
    public DcMotor motorFL = null; // Front Left
    public DcMotor motorBR = null; // Back Right
    public DcMotor motorBL = null; // Back Left

    public DcMotorEx motorSlider; // Slider
    public Servo servoArm; // Elbow or Arm
    //public CRServo servoArm; // Elbow or Arm
    public Servo servoWrist; // Wrist

    public Servo servoCR; // Claw Right
    public Servo servoCL; // Claw left
    public Telemetry telemetry;

    public WebcamName webcam;
    public MecanumDrive mDrive;

    // Claw positions
    public double claw_left_open = 0.8;
    public double claw_right_open = 0.2;
    public double claw_left_close = 0.15;
    public double claw_right_close = 0.85;
    public double claw_right_close_wide = 0.8;
    public double claw_left_close_wide = 0.8;

    // Arm positions
    public double arm_pos_starting = 0.8;
    public  double arm_pos_sample = 0.15;
    public double arm_pos_basket = 0.4;
    public double arm_pos_specimen = 0.15;
    public double arm_pos_chamber = 0.4;

    // Wrist positions
    public double wrist_pos_starting = 0.9;
    public double wrist_pos_sample  = 0.6;
    public double wrist_pos_specimen = 0.4;
    public double wrist_pos_high_chamber = 0.65;
    public double wrist_pos_low_chamber = 0.65;
    public double wrist_pos_basket = 0.65;

    // Slider positions
    public int slider_Intial_Pose_ticks = -60;
    public int slider_LowBasket_ticks = 1000;
    public int slider_HighBasket_ticks = 2700; // finished needs testing
    public int slider_LowChamber_ticks = 1004;
    public int slider_HighChamber_ticks = 1725; // finished needs testing

    public int slider_ChamberAuton_ticks = 10;

    public RevBlinkinLedDriver led;
    public int wrist_pos_chamber_auton;


    public Robot(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        motorFL = hardwareMap.get(DcMotor.class, "leftFront");
        motorFR = hardwareMap.get(DcMotor.class, "rightFront");
        motorBL = hardwareMap.get(DcMotor.class, "leftRear");
        motorBR = hardwareMap.get(DcMotor.class, "rightRear");
        motorSlider = hardwareMap.get(DcMotorEx.class, "sliders");
        //servoArm = hardwareMap.get(Servo.class, "arm");
        servoArm = hardwareMap.get(Servo.class, "arm");
        servoWrist = hardwareMap.get(Servo.class, "claw_arm");
        servoCL = hardwareMap.get(Servo.class, "claw_left");
        servoCR = hardwareMap.get(Servo.class, "claw_right");
        webcam = hardwareMap.get(WebcamName.class, "Webcam 1");
        led = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");
        led.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);


        mDrive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
    }

}
