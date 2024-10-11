#!/usr/bin/env bash

HOST=$1
shift
PORT=$1
shift

TIMEOUT=30

echo "Waiting for $HOST:$PORT to become available..."

while ! nc -z $HOST $PORT; do
  sleep 1
  TIMEOUT=$((TIMEOUT - 1))
  if [ $TIMEOUT -eq 0 ]; then
    echo "Timeout while waiting for $HOST:$PORT"
    exit 1
  fi
done

echo "$HOST:$PORT is available, starting the application..."
exec "$@"
