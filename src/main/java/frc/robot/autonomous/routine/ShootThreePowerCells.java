package frc.robot.autonomous.routine;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.autonomous.*;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.misc.Gyro;
import frc.robot.subsystems.misc.Limelight;
import frc.robot.subsystems.shooter.Loader;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.commands.LoadNShoot;
import frc.robot.subsystems.shooter.commands.RevUpShooter;

public class ShootThreePowerCells extends SequentialCommandGroup {
    public ShootThreePowerCells(Drivetrain drivetrain, Gyro gyro, Loader loader, Shooter shooter, Intake intake, Odometry odometry, Limelight limelight, StartingPosition startingPosition, Trajectories trajectories) {
        addCommands(new InitializeCommand(drivetrain, odometry, gyro, startingPosition));
        switch (startingPosition) {
            case LOADING_STATION:
                addCommands(new Ramsete(odometry, drivetrain, trajectories.loadingStartToAimingPosition()));
                break;
            case CENTER:
                addCommands(new Ramsete(odometry, drivetrain, trajectories.centerStartToAimingPosition()));
                break;
            case SHOOTING:
                addCommands(new Ramsete(odometry, drivetrain, trajectories.shootingStartToAimingPosition()));
                break;
        }
        addCommands(new ParallelCommandGroup(
                new RevUpShooter(shooter, 54316351),
                new AutoTargetAiming(drivetrain, limelight)
        ));
        addCommands(new LoadNShoot(loader, intake));
    }
}
