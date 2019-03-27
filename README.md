** TODO: **
quand un ou l'autre des minirupteurs de l'attrapeur est activé mettre digital ouptut 3 à true.


# Robot2019

Cobra le robot 2019 pour la compétition FRC

## Code des librairies WPI

https://github.com/wpilibsuite/allwpilib/

## Librairies jar de wpi

C:\Users\Public\frc2019\maven\edu\wpi\first\wpilibj\wpilibj-java\2019.2.1

## Api pour TalonSRX

http://www.ctr-electronics.com/downloads/api/java/html/classcom_1_1ctre_1_1phoenix_1_1motorcontrol_1_1can_1_1_talon_s_r_x.html
https://github.com/CrossTheRoadElec/Phoenix-api/blob/master/java/src/com/ctre/phoenix/motorcontrol/can/TalonSRX.java

### Limit Switch homing default

https://phoenix-documentation.readthedocs.io/en/latest/ch14_MCSensor.html

## Exemples pour TalonSRX

https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/tree/master/Java 

## Documentation pour les drives Victor SP

https://github.com/Supertronix/Robot2019/blob/master/doc/Victor-SP-Quick-Start-Guide.pdf

## Camera live web

http://roborio-5910-frc:1181/stream.mjpg

## REV Robotics Smart Robot Servo (SRS) 

https://github.com/Supertronix/Robot2019/blob/master/doc/REV-41-1097-UM.pdf

### Java doc Servo

http://first.wpi.edu/FRC/roborio/beta/docs/java/edu/wpi/first/wpilibj/Servo.html


## How to program Encoders with PID Subsystem

https://www.chiefdelphi.com/t/how-to-program-encoders-with-pid-subsystem/140456/3

The PID Subsystem was designed for a single sensor input and a single output. The way you’re doing it will use a single sensor and send the same output value to both the left and right motors, which would allow you to drive a distance approximately straight. Is that what you want?

