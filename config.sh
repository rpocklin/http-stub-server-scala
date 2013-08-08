#!/bin/bash
set -e

# $1 is directory of stub JSON + environment.sh (by default)
# $2 is override location of environment settings (host/port)

directory="${1:-stub-examples}"
env_file="${2:-${directory}/environment.sh}"

source "$env_file"

url="http://$host:$port/_control/responses"
log_url="http://$host:$port/_control/requests/0"
clear_logs_url="http://$host:$port/_control/requests"
clear_url="http://$host:$port/_control/responses"

cmd="curl --http1.0 -X POST -d"
clear_cmd="curl -X DELETE"
log_cmd="curl -v"

content_type="--header \"Content-Type:application/json\""
