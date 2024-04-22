#! /bin/bash

junit_platform_console_standalone=dependencies/testing/junit-platform-console-standalone-1.10.2.jar

if [[ "$1" == "help" ]]; then

java -jar $junit_platform_console_standalone execute -h

else

java -jar $junit_platform_console_standalone execute\
     --disable-banner\
     --details=tree\
     --class-path bin/test\
     --scan-class-path

fi
