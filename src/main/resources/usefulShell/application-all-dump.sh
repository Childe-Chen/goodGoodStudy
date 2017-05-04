#!/usr/bin/env bash
JAVA_HOME=/usr/java
OUTPUT_HOME=~/output
DEPLOY_HOME=`dirname $0`
HOST_NAME=`hostname`

DUMP_PIDS=`ps  --no-heading -C java -f --width 1000 | grep "$DEPLOY_HOME" |awk '{print $2}'`
if [ -z "$DUMP_PIDS" ]; then
    echo "The server $HOST_NAME is not started!"
    exit 1;
fi

DUMP_ROOT=$OUTPUT_HOME/dump
if [ ! -d $DUMP_ROOT ]; then
	mkdir $DUMP_ROOT
fi

DUMP_DATE=`date +%Y%m%d%H%M%S`
DUMP_DIR=$DUMP_ROOT/dump-$DUMP_DATE
if [ ! -d $DUMP_DIR ]; then
	mkdir $DUMP_DIR
fi

echo -e "Dumping the server $HOST_NAME ...\c"
for PID in $DUMP_PIDS ; do
	$JAVA_HOME/bin/jstack $PID > $DUMP_DIR/jstack-$PID.dump 2>&1
	echo -e ".\c"
	$JAVA_HOME/bin/jinfo $PID > $DUMP_DIR/jinfo-$PID.dump 2>&1
	echo -e ".\c"
	$JAVA_HOME/bin/jstat -gcutil $PID > $DUMP_DIR/jstat-gcutil-$PID.dump 2>&1
	echo -e ".\c"
	$JAVA_HOME/bin/jstat -gccapacity $PID > $DUMP_DIR/jstat-gccapacity-$PID.dump 2>&1
	echo -e ".\c"
	$JAVA_HOME/bin/jmap $PID > $DUMP_DIR/jmap-$PID.dump 2>&1
	echo -e ".\c"
	$JAVA_HOME/bin/jmap -heap $PID > $DUMP_DIR/jmap-heap-$PID.dump 2>&1
	echo -e ".\c"
	$JAVA_HOME/bin/jmap -histo $PID > $DUMP_DIR/jmap-histo-$PID.dump 2>&1
	echo -e ".\c"
	if [ -r /usr/sbin/lsof ]; then
	/usr/sbin/lsof -p $PID > $DUMP_DIR/lsof-$PID.dump
	echo -e ".\c"
	fi
done
if [ -r /usr/bin/sar ]; then
/usr/bin/sar > $DUMP_DIR/sar.dump
echo -e ".\c"
fi
if [ -r /usr/bin/uptime ]; then
/usr/bin/uptime > $DUMP_DIR/uptime.dump
echo -e ".\c"
fi
if [ -r /usr/bin/free ]; then
/usr/bin/free -t > $DUMP_DIR/free.dump
echo -e ".\c"
fi
if [ -r /usr/bin/vmstat ]; then
/usr/bin/vmstat > $DUMP_DIR/vmstat.dump
echo -e ".\c"
fi
if [ -r /usr/bin/mpstat ]; then
/usr/bin/mpstat > $DUMP_DIR/mpstat.dump
echo -e ".\c"
fi
if [ -r /usr/bin/iostat ]; then
/usr/bin/iostat > $DUMP_DIR/iostat.dump
echo -e ".\c"
fi
if [ -r /bin/netstat ]; then
/bin/netstat > $DUMP_DIR/netstat.dump
echo -e ".\c"
fi
echo "OK!"