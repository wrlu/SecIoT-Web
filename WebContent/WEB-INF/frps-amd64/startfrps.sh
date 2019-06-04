#!/bin/sh
cd $(dirname `readlink  -f $0`)
chmod +x frps
./frps -c frps.ini
