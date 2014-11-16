This is a Java library for handling oracle logins. 
And my first try at using git. 


How to build:
I have a config file config.bat with contents

set java_home=C:\Program Files\Java\jdk1.8.0_11
set ant="C:\Program Files\NetBeans 8.0\extide\ant\bin\ant"
set oraclelibs=C:\oracle\product\11.2.0\home1\jdbc\lib
set junitjar=C:\Program Files\NetBeans 8.0\platform\modules\ext\junit-4.10.jar

The ant file build-oraclelogin.xml contains references to $oraclelibs and $junitjar.

To run the tests:
%ant% -f ".\build-oraclelogin.xml" test

or
%ant% -f ".\build-oraclelogin.xml" testgui
which just starts up the gui.





