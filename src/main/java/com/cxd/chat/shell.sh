#!/bin/sh
# chkconfig: 345 78 78
# description: Normal RC script for rcp.

#. /etc/init.d/functions

SERVER_NAME=chat
MAIN_CLASS_USER_SHARD=com.cxd.chat.WebsocketChatServer
# it is just avalable for BootstrapPug
MAIN_PARAM_DEL_MER=Y
MAIN_CLASS=$MAIN_CLASS_USER_SHARD
DIRNAME=`dirname $0`
PROGNAME=`basename $0`
SERVER_HOME=`pwd`
echo $SERVER_HOME
echo java_home:$JAVA_HOME

JAVA_PARAM="-Xms128m -Xmx1024m -XX:NewSize=32M -XX:MaxNewSize=64M"
#DEBUG_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -XX:ErrorFile=/home/xiaodong.chen/hs_error%p.log -Xrunjdwp:transport=dt_socket,address=10090,server=y,suspend=n"
# $DEBUG_OPTS
JAVA_OPTS="$JAVA_PARAM"

start()
{
        pid0=`ps -ef|grep "java"|grep $MAIN_CLASS|grep $SERVER_HOME | awk '{print $2}'`
        if [ "$pid0" = "" ] ; then
                echo "ready."

                for i in $SERVER_HOME/JavaNio.jar;
                        do CLASS_PATH_LIB=$i:"$CLASS_PATH_LIB";
                done
                echo $CLASS_PATH_LIB
                java -server $JAVA_OPTS -DsrvHome=$SERVER_HOME -Dhtml_locate="/home/admin/web-deploy" -DmainClass=$MAIN_CLASS -Dfile.encoding=GBK -classpath $CLASS_PATH_LIB $MAIN_CLASS "$MAIN_PARAM_DEL_MER" > /home/admin/web-deploy/logs/chat.log
                RETVAL=$?
                echo "$SERVER_NAME startup!"
                return $RETVAL
        else
                echo "$SERVER_NAME[$pid0] is alive already."
        fi
}

stop()
{
        pid0=`ps -ef|grep "java"|grep $MAIN_CLASS|grep $SERVER_HOME | awk '{print $2}'`
        if [ "$pid0" = "" ] ; then
                echo "No $SERVER_NAME alive."
        else
                kill  $pid0
                echo "Wait for a moment please..."
                for i in $(seq 0 20)
				do
				        sleep 1
				        pid=`ps -ef|grep "java"|grep $MAIN_CLASS|grep $SERVER_HOME |awk '{print $2}'`
				        if [ "$pid" = "" ] ; then
				                echo "$SERVER_NAME[$pid0] is stopped in $i seconds."
				                echo "$SERVER_NAME shutdown."
				                break;
				        elif [ "$i" = "20" ] ; then
				                kill -9 $pid
				                echo "$SERVER_NAME[$pid0] is force killed after $i seconds."
				                break;
				        fi
				done
        fi
}

status()
{
        pid0=`ps -ef|grep "java"|grep $MAIN_CLASS|grep $SERVER_HOME | awk '{print $2}'`
        if [ "$pid0" = "" ] ; then
                echo "No $SERVER_NAME alived."
                RETVAL=1
        else
                echo "$SERVER_NAME[$pid0] is alived."
                RETVAL=0
        fi
}

case "$1" in
        start)
                start
                ;;
        stop)
                stop
                ;;
        status)
                status
                ;;
        restart)
                stop
                start
                ;;
        help)
                echo "Usage: $0 [OPTION]"
                echo ""
                echo "  start        start $SERVER_NAME"
                echo "  stop         stop $SERVER_NAME"
                echo "  help         display this help and exit"
                ;;
        *)
                echo "Usage: $0 {start|stop|status|restart}"
                RETVAL=1
esac

exit $RETVAL
