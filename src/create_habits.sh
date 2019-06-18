#!/bin/bash
FNAME=habits.java
echo "//TODO" > $FNAME
for i in {1..7}
do
  cp $FNAME ${i}_$FNAME
done
rm $FNAME
