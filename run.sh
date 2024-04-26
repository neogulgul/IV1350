#! /bin/bash

args="$@"

if [[ "$args" == "" ]]
then

args="example/goods.txt example/payment.txt"

fi

target_dir=target

java -cp $target_dir/main se.kth.iv1350.startup.Main $args
