package frc.robot.subsystems.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Climber extends SubsystemBase {
    //The Neo Motor is used to pull the hook down
    //The 775 Motor is used to lift the hook up
    private CANSparkMax down;
    private TalonSRX up;

    public Climber(){
        down = new CANSparkMax(RobotMap.Manipulator.Climber.NEO_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);
        up = new TalonSRX(RobotMap.Manipulator.Climber.TALONSRX_PORT);

        down.setOpenLoopRampRate(RobotMap.Manipulator.Climber.CLIMBER_RAMP_RATE);
    }

    public double getPostion() {
        return down.getEncoder().getPosition();
    }

    public void resetEncoders() {
        down.getEncoder().setPosition(0);
    }

    public CANSparkMax getDownMotor() {
        return down;
    }

    public TalonSRX getUpMotor(){
        return up;
    }

    public void set(int power) {
        up.set(ControlMode.PercentOutput, power);
    }
}
