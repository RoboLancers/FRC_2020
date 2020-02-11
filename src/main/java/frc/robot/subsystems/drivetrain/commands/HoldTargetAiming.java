package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Limelight;

import java.util.HashSet;
import java.util.Set;

public class HoldTargetAiming extends InstantCommand {
    Drivetrain drivetrain;
    Limelight limelight;
    double leftPower, rightPower, turningOffset, distanceOffset;
    double turningkP = 0.006;
    double distancekP = 0.02;
    double allowedDistanceError = 20;

    public HoldTargetAiming(Drivetrain drivetrain, Limelight limelight) {
        this.drivetrain = drivetrain;
        this.limelight = limelight;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        if (limelight.hasTarget()) {
            turningOffset = limelight.getXOffset();
            distanceOffset = allowedDistanceError - limelight.getYOffset();
            leftPower = (distanceOffset * distancekP) + (turningOffset * turningkP);
            rightPower = (distanceOffset * distancekP) - (turningOffset * turningkP);
            drivetrain.getLeft().getMaster().set(leftPower);
            drivetrain.getRight().getMaster().set(rightPower);
        }
    }
}
