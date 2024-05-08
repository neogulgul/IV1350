#! /bin/bash

args="$@"

if [[ "$args" == "" ]]
then

args="-p examples/1.Default"

fi

target_dir=target

java -cp $target_dir/main se.kth.iv1350.startup.Main $args
