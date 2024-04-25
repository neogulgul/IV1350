#! /bin/bash

# Script that draws a colored box with a prompt inside
# First argument is the prompt
# Second argument is the color (can be omitted)

border_padding=1
prompt="$1"
color="$2"

if [[ -z "$color" ]]
then
	color="1"
fi

function colored_print
{
	echo -n -e "\e[$2m$1\e[0m"
}

function repeated_print
{
	for ((i = 0; i < $(($3)); i++))
	do
		colored_print "$1" $2
	done
}

prompt_size=${#prompt}
horizontal_border_size=$(($prompt_size + $border_padding * 2))

colored_print "┌" $color
repeated_print "─" $color $horizontal_border_size
colored_print "┐" $color

echo

colored_print "│" $color
repeated_print " " $color $border_padding
colored_print "$prompt" $color
repeated_print " " $color $border_padding
colored_print "│" $color

echo

colored_print "└" $color
repeated_print "─" $color $horizontal_border_size
colored_print "┘" $color

echo
