package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import androidx.annotation.NonNull;

public class Slider extends EncoderMotorOps {
    private Robot robot;
    private Gamepad gamepad;
    private double manual_speed_factor = 1.0;
    static final private double auto_power = 0.5;
    static final private int pos_max = 7800;
    static final private int pos_min = 0;



    public Slider(Robot robot, Gamepad gamepad)
    {
        super(robot, robot.motorSlider, pos_min, pos_max, auto_power, true);
        this.robot = robot;
        this.gamepad = gamepad;
    }

    public void setPower(double power) {
        robot.motorSlider.setPower(power);
    }
    public int getCurrentPosition() {
        return robot.motorSlider.getCurrentPosition();
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

    public class SliderFold implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                setPower(-0.5);
                initialized = true;
            }

            double pos = getCurrentPosition();
            packet.put("sliderPos", pos);
            if (pos > 0) {
                return true;
            } else {
                setPower(0);
                return false;
            }
        }
    }
    public Action sliderFoldAction() {
        return new SliderFold();
    }

    public class SliderLowChamber implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                setPower(0.5);
                initialized = true;
            }

            double pos = getCurrentPosition();
            packet.put("sliderPos", pos);
            if (pos < robot.slider_LowChamber_ticks) {
                return true;
            } else {
                setPower(0);
                return false;
            }
        }
    }
    public Action sliderLowChamberAction() {
        return new SliderLowChamber();
    }

    public class SliderHighChamber implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                setPower(0.5);
                initialized = true;
            }

            double pos = getCurrentPosition();
            packet.put("sliderPos", pos);
            if (pos < robot.slider_HighChamber_ticks) {
                return true;
            } else {
                setPower(0);
                return false;
            }
        }
    }
    public Action sliderHighChamberAction() {
        return new SliderHighChamber();
    }

    public class SliderHighBasket implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                setPower(0.5);
                initialized = true;
            }

            double pos = getCurrentPosition();
            packet.put("sliderPos", pos);
            if (pos < robot.slider_HighBasket_ticks) {
                return true;
            } else {
                setPower(0);
                return false;
            }
        }
    }
    public Action sliderHighBasketAction() {
        return new SliderLowBasket();
    }

    public class SliderLowBasket implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                setPower(0.5);
                initialized = true;
            }

            double pos = getCurrentPosition();
            packet.put("sliderPos", pos);
            if (pos < robot.slider_LowBasket_ticks) {
                return true;
            } else {
                setPower(0);
                return false;
            }
        }
    }
    public Action sliderLowBasketAction() {
        return new SliderLowBasket();
    }

}
