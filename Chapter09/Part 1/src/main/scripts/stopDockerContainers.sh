#!/usr/bin/env bash

set -euo pipefail

docker stop $(docker ps -q)
docker rm $(docker ps -qa)