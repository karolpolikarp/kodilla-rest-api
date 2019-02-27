#!/usr/bin/env bash

getTasks() {
    if open -a safari http://localhost:8080/crud/v1/task/getTasks; then
        echo "Displaying tasks"
    else
        echo "error"
    fi
}

if ./runcrud.sh; then
    echo "runcrud.sh initialized"
    getTasks
else
    echo "error"
fi