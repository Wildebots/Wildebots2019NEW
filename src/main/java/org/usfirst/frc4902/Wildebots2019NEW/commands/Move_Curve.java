package org.usfirst.frc4902.Wildebots2019NEW.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4902.Wildebots2019NEW.Robot;
import edu.wpi.first.wpilibj.Timer;

public class Move_Curve extends Command 
{
    private final Timer m_timer = new Timer();
    private final double exe_seconds, LeftSpeed, RightSpeed;

    public Move_Curve(double leftpower, double rightpower, double t_seconds) // Constructor
    {
        requires(Robot.driveTrain);
        this.exe_seconds = t_seconds;
        this.LeftSpeed = leftpower;
        this.RightSpeed = rightpower;
    }

    @Override
    protected void initialize()
    {
        Robot.driveTrain.drive(0.0, 0.0); // Get the motors ready to drive and stop it as initialization
        m_timer.reset();
        m_timer.start();
    }

    @Override
    protected void execute() 
    {
        if(m_timer.get() < this.exe_seconds)
        {
            Robot.driveTrain.drive(this.LeftSpeed, this.RightSpeed); // Speed values are between [-1.0..1.0]
            //System.out.println("Executing Move_Curve");
        }
        
    }

    @Override
    protected boolean isFinished()
    {
        return (m_timer.get() >= this.exe_seconds);
    }

    // Called once after isFinished returns true
    @Override
    protected void end() 
    {
        Robot.driveTrain.end(); // Stop the motor
        m_timer.reset();
    }

    @Override
    protected void interrupted() 
    {
        end(); // If the command is interrupted for whatever reason kindly stop the command
    }
}