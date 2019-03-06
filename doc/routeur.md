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
