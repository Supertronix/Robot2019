https://media.screensteps.com/exported/Wpilib/2078/103933/FMS_Whitepaper.pdf

## The Field Wireless Access Point
The Field Wireless Access Point (WAP) broadcasts and receives wireless data from robots on the
playing field. The Field WAP hosts a hidden SSID for each robot scheduled to play on the field, all of
which are multiplexed over a single wireless interface. Each SSID is allocated a VLAN to the
corresponding Driver Station.
The Field WAP connects to the Score Switch through a 10/100/1000 Gigabit Ethernet trunk line. The
switch ports on the Field WAP are unused and unallocated, only the WAN (trunk) line is used.
** The Field WAP uses the 802.11n Wi-Fi standard, and the 5GHz band is reserved exclusively for
robots. The standard configuration employs a 20MHz channel, with the option for 40MHz, and
employs WPA2/AES encryption with a unique key per team, per event. **

## Robots
Each robot contains, at minimum, a wireless radio and robot controller (roboRIO). ** The wireless
radio is configured in bridge mode to communicate to the Field WAP using an assigned SSID and
WPA Key, which then communicates with the robot controller and any other devices on the robot
at the discretion of the team. At each event, each team is assigned a unique encryption key.
The robot radio is responsible for connecting to the field, as well as implementing the bandwidth
limit. Each team must configure their radio at the event before it will successfully link with the Field
WAP. Radio configuration kiosks are provided for this purpose. **

## Network Bandwidth
The FMS Field Network has limited bandwidth available. ** There is an imposed 4Mbit/s limit for each
team via the robot radios ** to ensure no one team overloads the system, causing packets to drop
for other teams. Given that each wireless SSID that the Field WAP handles is multiplexed, this adds
up to a total of 4x6=24Mbit/s for the Field WAP. All other traffic on the FMS Field Network is not
limited by bandwidth.
The Robot Radio prioritizes certain communications over others. ** Driver Station control and status
packets are the highest priority, followed by Network Tables, then all other traffic (e.g. video). **

## Driver Station and Robot Communications
The Driver Station to Robot Communication is identical to that of a system without the FMS in
terms of packets. The only difference is that on an FMS network, the packets are routed through
the FMS Field Network, then to the Robot. These packets include control data for your robot,
telling it what state it should be in and what the values of the joysticks are. The FMS does not send
any packets to your robot.
The following ports are opened for communication between your Robot and Driver Station. All
other ports are blocked. All ports are bidirectional unless otherwise stated.
- UDP/TCP 1180 - 1190: Camera Data
- TCP 1735: SmartDashboard
- UDP 1130: DS-to-Robot control data
- UDP 1140: Robot-to-DS status data
- HTTP 80: Camera/web interface
- HTTP 443: Camera/web interface (secure)
- UDP/TCP 554: Real-Time Streaming Protocol for h.264 camera streaming
- UDP/TCP 5800-5810: Team Use

Teams are permitted to utilize ports 5800-5810 for their own purposes, or any other open ports
(other than 1130 and 1140) if not already allocated.
Your robot will report data about itself to the Driver Station, which is then, in turn, forwarded to
the FMS. This includes data about your robot including what motors are being used, what
language it was programmed in, and other metadata. This is then forwarded to FIRST for statistical
purposes. This process is known as “Usage Reporting”, and is discussed at length later in this
whitepaper.
The FMS server communicates to the Driver Station through the Field Router, with a routing
exception, allowing the FMS server to send data to the team VLAN. This includes critical data, such
as what state the robot should be in, match time, and other details. The Driver Station also sends
data back, such as battery voltage. Logging of data is discussed at length later in this whitepaper.
