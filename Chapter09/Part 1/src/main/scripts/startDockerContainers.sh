#!/usr/bin/env bash

set -euo pipefail

docker run -d -p 4444:4444 --name selenium-hub selenium/hub:3.11.0
docker run -d --link selenium-hub:hub selenium/node-firefox:3.11.0
docker run -d --link selenium-hub:hub selenium/node-chrome:3.11.0

echo -n "Waiting for grid to load."
while ! curl http://127.0.0.1:4444/grid/console > /dev/null 2>&1
do
  echo -n "."
  sleep 1
done
echo " "
echo "Connected to grid successfully"