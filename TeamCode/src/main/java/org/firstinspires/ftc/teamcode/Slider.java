package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Slider extends EncoderMotorOps {
    private Robot robot;
    private Gamepad gamepad;
    private double manual_speed_factor = 1.0;
    static final private double auto_power = 0.5;
    static final private int pos_max = 7800;
    static final private int pos_min = 0;
    private int pos_middle = 3900;
   //private int pos_auton = 1000;

    private int pos_auton = 1000;
    private boolean verbose = false;
    private int motor_ticks = 1425;
    private int rev_ticks = 250;


    public Slider(Robot robot, Gamepad gamepad)
    {
        super(robot, robot.motorSlider, pos_min, pos_max, auto_power, true);
        this.robot = robot;
        this.gamepad = gamepad;
    }

    public void LowBasket() {autoOp(robot.slider_LowBasket_ticks);}
//    Move slider height to Low basket
    public void LowChamber() {autoOp(robot.slider_LowChamber_ticks);}
//    Move slider height to Low Chamber
    public void HighBasket() {autoOp(robot.slider_HighBasket_ticks);}
//    Move slider height to Low basket
    public void HighChamber() {
        autoOp(robot.slider_HighChamber_ticks);
    }

    public void setPosAbsolute(int ticks) {
        autoOp(ticks);
        logUpdate();
    }
    public void fold() {
        autoOp(0);
    }

    public void controlOp(double power)
    {
        manualOp(power * manual_speed_factor);
        logUpdate();
    }
}
