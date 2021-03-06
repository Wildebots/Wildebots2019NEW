// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4902.Wildebots2019NEW.subsystems;


import org.usfirst.frc4902.Wildebots2019NEW.commands.ArcadeDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class DriveTrain extends Subsystem {
    private double FlippRotationZOrientation = 1.0; //-1.0 So its flipped to correct orientation of the robot. i.e. left is left and right is right. If set to 1.0 the left is right and right is left.

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private PWMVictorSPX leftMotor;
    private PWMVictorSPX rightMotor;
    private DifferentialDrive differentialDrive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public DriveTrain() {
        super(); // DO NOT REMOVE THIS
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        leftMotor = new PWMVictorSPX(9);
        addChild("Left Motor",leftMotor);
        leftMotor.setInverted(false); // set both motors to true inverted to make fwd and backward in relation to the robot
        
        rightMotor = new PWMVictorSPX(0);
        addChild("Right Motor",rightMotor);
        rightMotor.setInverted(false);
        
        differentialDrive = new DifferentialDrive(leftMotor, rightMotor);
        addChild("Differential Drive",differentialDrive);
        differentialDrive.setSafetyEnabled(true);
        differentialDrive.setExpiration(0.1);
        differentialDrive.setMaxOutput(1.0);

        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new ArcadeDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    // This method turns on the drive train motors appropriately by getting the X and Y Axis inputs from Joystick.
    public void drive(XboxController stick)
    {
        double stick_reading = Math.abs(stick.getX(Hand.kLeft));
        // System.out.println("Stick Reading Absolute" + stick_reading);
        if(stick_reading > 0.0 && stick_reading <= 0.2)
        {
            differentialDrive.arcadeDrive(stick.getY(Hand.kLeft), FlippRotationZOrientation*0.5*stick.getX(Hand.kLeft));
        }
        else if (stick_reading > 0.2 && stick_reading <= 0.5)
        {
            differentialDrive.arcadeDrive(stick.getY(Hand.kLeft), FlippRotationZOrientation*0.6*stick.getX(Hand.kLeft));
        }
        else if (stick_reading > 0.5 && stick_reading <= 0.8)
        {
            differentialDrive.arcadeDrive(stick.getY(Hand.kLeft), FlippRotationZOrientation*0.7*stick.getX(Hand.kLeft));
        }
        else if (stick_reading > 0.8 && stick_reading <= 1.0)
        {
            differentialDrive.arcadeDrive(stick.getY(Hand.kLeft), FlippRotationZOrientation*0.75*stick.getX(Hand.kLeft));
        }

        //differentialDrive.arcadeDrive(stick.getY(Hand.kLeft), 0.75*stick.getX(Hand.kLeft)); // 75% of speed when turning   
    }

    public void drive(double leftSpeed, double rightSpeed)
    {
        differentialDrive.tankDrive(leftSpeed,rightSpeed);
    }

    // This method stops the motors by setting the speeds to 0.
    public void end()
    {
        differentialDrive.tankDrive(0, 0);
        differentialDrive.stopMotor();
    }
}
