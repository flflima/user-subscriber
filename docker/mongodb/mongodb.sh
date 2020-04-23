#!/usr/bin/env bash
echo 'Creating application user and db'
mongo ${MONGO_DATABASE} \
        --host "${MONGO_HOSTS}:${MONGO_PORTS}" \
        --authenticationDatabase admin \
        --eval "db.createUser({user: '${MONGO_USER}', pwd: '${MONGO_PASS}', \
                                roles: [{role: 'dbOwner', db: '${MONGO_DATABASE}'}]});"
