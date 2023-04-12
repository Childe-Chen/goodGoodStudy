# coding=UTF-8
"""
日期、时间处理工具类
"""

import time
import datetime
import calendar


def generate_timestamp():
    """根据当前时间获取时间戳，返回整数"""
    return int(time.time())


def date_2datetime(dt):
    """
    date_2datetime(datetime.date(2013,7,13))
    >>> datetime.datetime(2013,7,13,0,0)
    """
    return datetime.datetime(dt.year, dt.month, dt.day)


def format_datetime(dt):
    """将mongodb保存的时间转格式，去除毫秒精度"""
    if not dt:
        return None
    return datetime.datetime(dt.year, dt.month, dt.day, dt.hour, dt.minute, dt.second)


def datetime_2string(dt=None, fmt='%Y-%m-%d-%H-%M'):
    """将datetime类型转换为字符串"""
    if not dt:
        dt = datetime.datetime.now()
    return datetime.datetime.strftime(dt, fmt)


def string_2datetime(dt_str, fmt='%Y-%m-%d %H:%M:%S'):
    """
    将字符串转换为datetime类型
    >>> string_2datetime("2011-09-07 12:30:09")
    datetime.datetime(2011, 9, 7, 12, 30, 9)
    """
    if not dt_str:
        return None
    return datetime.datetime.strptime(dt_str, fmt)


def string_2datetime2(dt_str, fmt='%Y-%m-%d'):
    return string_2datetime(dt_str[:10], fmt)


def string_2date(dt_str):
    """将字符串转换为date类型"""
    return datetime.datetime.strptime(dt_str, '%Y-%m-%d').date()


def get_start_datetime(dt=None):
    """根据日期、或时间取得当日00:00:00的时间；参数可以是date或datetime类型"""
    start = None
    if not dt:
        dt = datetime.date.today()
    if isinstance(dt, datetime.date):
        dt_str = dt.strftime("%Y-%m-%d") + " 00:00:00"
        start = datetime.datetime.strptime(dt_str, "%Y-%m-%d %H:%M:%S")
    return start


def get_end_datetime(dt=None):
    """根据日期、或时间取得当日23:59:59的时间；参数可以是date或datetime类型"""
    end = None
    if not dt:
        dt = datetime.date.today()
    if isinstance(dt, datetime.date):
        dt_str = dt.strftime("%Y-%m-%d") + " 23:59:59"
        end = datetime.datetime.strptime(dt_str, "%Y-%m-%d %H:%M:%S")
    return end


def get_start_hour(dt=None):
    """根据日期、或时间取得该小时00:00的时间；参数可以是date或datetime类型"""
    start = None
    if not dt:
        dt = datetime.date.today()
    if isinstance(dt, datetime.date):
        dt_str = dt.strftime("%Y-%m-%d %H") + ":00:00"
        start = datetime.datetime.strptime(dt_str, "%Y-%m-%d %H:%M:%S")
    return start


def get_end_hour(dt=None):
    """根据日期、或时间取得该小时59:59的时间；参数可以是date或datetime类型"""
    end = None
    if not dt:
        dt = datetime.date.today()
    if isinstance(dt, datetime.date):
        dt_str = dt.strftime("%Y-%m-%d %H") + ":59:59"
        end = datetime.datetime.strptime(dt_str, "%Y-%m-%d %H:%M:%S")
    return end


def get_time_delta(begin_dt, end_dt, time_fmt=None):
    """获取时间差，根据time_fmt返回数据，如果time_fmt=None，则返回days,hours,minutes,seconds"""
    days, hours, minutes, seconds, left_seconds = None, None, None, None, 0
    if isinstance(begin_dt, datetime.date) and isinstance(end_dt, datetime.date):
        days = (end_dt - begin_dt).days
        left_seconds = (end_dt - datetime.timedelta(days=days) - begin_dt).seconds
        hours = left_seconds / 3600
        tmp_seconds = left_seconds - hours * 3600
        minutes = tmp_seconds / 60
        seconds = tmp_seconds - minutes * 60

    if time_fmt == "DAYS":
        return days
    elif time_fmt == "HOURS":
        return days * 24 + hours
    elif time_fmt == "MINUTES":
        return (days * 24 + hours) * 60 + minutes
    elif time_fmt == "SECONDS":
        return (days * 24 * 3600) + left_seconds
    else:
        return days, hours, minutes, seconds


def time_is_someday(dt, days=0):
    """判断datetime或date是否是哪一天，默认是今天"""
    if not dt:
        return False
    someday = datetime.date.today() - datetime.timedelta(days=days)
    if isinstance(dt, datetime.datetime):
        return dt.date() == someday
    elif isinstance(dt, datetime.date):
        return dt == someday
    return False


def time_is_ndays_interval(dt, ndays):
    """计算datetime或date与今天是否相差n天，ndays为正数表示相差过去n天，为负数表示相差今后n天"""
    if not dt:
        return False
    before_ndays = datetime.date.today() - datetime.timedelta(days=ndays)
    if isinstance(dt, datetime.datetime):
        return dt.date() <= before_ndays
    elif isinstance(dt, datetime.date):
        return dt <= before_ndays
    return False


def time_is_recent(dt, **kwargs):
    """这个函数其实跟上面的类似，只不过能够支持对hour、second进行判断"""
    if dt and isinstance(dt, datetime.datetime) and kwargs and (
            not set(kwargs.keys()) - set(['days', 'hours', 'minutes', 'seconds'])):
        last_time = datetime.datetime.now() - datetime.timedelta(**kwargs)
        return dt >= last_time
    else:
        return False


def time_humanize(dt):
    """将时间转换为"n天前"，使其更人性化，增加可读性"""
    result = ""
    if dt:
        time_ = ""
        if isinstance(dt, datetime.datetime):
            time_ = dt.strftime('%H:%M')
            dt = dt.date()
        days = (datetime.date.today() - dt).days
        if days == 0:
            result = "今天 %s" % time_
        elif days == 1:
            result = "昨天 %s" % time_
        elif days == 2:
            result = "前天 %s" % time_
        elif days > 2:
            result = "%s天前" % days
        return result
    return dt


def days_diff_interval(ed):
    """获取日期与当前日期相差的天数"""
    if not ed:
        return -1
    bd = datetime.date.today()
    oneday = datetime.timedelta(days=1)
    count = 0
    if ed > bd:
        while bd != ed:
            ed = ed - oneday
            count += 1
        return count
    elif ed == bd:
        return 0
    else:
        while bd != ed:
            ed = ed + oneday
            count -= 1
        return count


def get_1st_of_last_month(date):
    """
    获取上个月第一天的日期
    :return: 返回日期
    """
    if date:
        today = date
    else:
        today = datetime.datetime.today()
    year = today.year
    month = today.month
    if month == 1:
        month = 12
        year -= 1
    else:
        month -= 1
    res = datetime.datetime(year, month, 1)
    return res


def get_1st_of_next_month(date):
    """
    获取下个月的1号的日期
    :return: 返回日期
    """
    if date:
        today = date
    else:
        today = datetime.datetime.today()
    year = today.year
    month = today.month
    if month == 12:
        month = 1
        year += 1
    else:
        month += 1
    res = datetime.datetime(year, month, 1)
    return res


def get_date_list(start_date, end_date, fmt="%Y-%m-%d"):
    """根据给定的初始日期（‘2017-07-09’）来获取每一天日期的列表"""
    date_list = []
    start_date = datetime.datetime.strptime(start_date, fmt)
    end_date = datetime.datetime.strptime(end_date, fmt)
    while start_date <= end_date:
        date_str = start_date.strftime(fmt)
        date_list.append(date_str)
        start_date += datetime.timedelta(days=1)
    return date_list


def get_1st_of_last_week(index):
    """
    index:1 - 7 代表上周星期一到星期日
    获取上个月第一天的日期
    :return: 返回日期
    """
    if not index:
        return None
    now = datetime.datetime.now()
    date = now - datetime.timedelta(days=now.weekday() + (8 - index))
    return date


def get_date_of_week(index):
    """
    index:1 - 7 代表本周星期一到星期日
    :return: 返回日期
    """
    if not index:
        return None
    now = datetime.datetime.now()
    date = now - datetime.timedelta(days=now.weekday() - index + 1)
    return date


def get_flday_of_month(year=None, month=None):
    """获取某年某月的第一天和最后一天"""
    year = int(year) if year else datetime.date.today().year
    month = int(month) if month else datetime.date.today().month

    # 获取当月第一天的星期和当月的总天数
    fd_of_week, month_range = calendar.monthrange(year, month)

    # 获取当月的第一天
    fd_of_month = datetime.datetime(year=year, month=month, day=1)
    ld_of_month = datetime.datetime(year=year, month=month, day=month_range)

    return fd_of_month, ld_of_month


def is_valid_datetime(dt_str, fmt='%Y-%m-%d %H:%M:%S'):
    """字符转换成时间，有转换成功标志"""
    valid = True
    result = None
    try:
        if not dt_str:
            valid = False
        else:
            result = datetime.datetime.strptime(dt_str, fmt)
    except ValueError:
        valid = False
    return valid, result


def format_datetime_2date(dt):
    """
    将mongodb保存的日期时间格式转为日期格式
    :param dt: mongodb保存的datetime日期时间格式
    :return: 返回转换后的日期格式（datetime.date类型）
    """
    if not dt:
        return None
    return datetime.date(dt.year, dt.month, dt.day)


def calculate_interval_days(begin_date, end_date=datetime.date.today()):
    """
    计算给定的两个日期间隔天数,默认计算给定天数到今天的间隔
    :param begin_date: 开始时间（datetime.date类型）
    :param end_date: 结束时间（datetime.date类型）
    :return: 返回间隔天数（int类型）
    """
    if begin_date is not None and begin_date <= end_date:
        delta = (end_date - begin_date).days
    else:
        delta = 0
    return delta