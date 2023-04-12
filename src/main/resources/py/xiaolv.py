import requests
import pycookiecheat

# 参数
kw = {'projectKeyName': 'PROJ-12109', 'iterationId': '3381'}

# 请求头
headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36"
}

def yewuyu(data):
    if data['owner']['nickname'] in huiyuan:
        return '会员'
    elif data['owner']['nickname'] in jiaoyi:
        return '交易'
    elif data['owner']['nickname'] in shangpin:
        return '商品'
    elif data['owner']['nickname'] in yingxiao:
        return '营销'
    else:
        return '未知'


# https://xiaolv.qima-inc.com/#/demand/search?show=true&ids=105659

if __name__ == '__main__':
    url = 'https://xiaolv-api.qima-inc.com/demandIteration/getDemandListByIterationId'
    cookie = pycookiecheat.chrome_cookies(url)
    response = requests.get(
        url,
        params=kw,
        headers=headers,
        cookies=cookie,
        verify=False
    )

    huiyuan = ['Allen', '赵震安']
    jiaoyi = ['陈虹静']
    shangpin = ['西元']
    yingxiao = ['姝涵', '暗流', '陈苏明', '亮仔']

    lianjie = 'https://xiaolv.qima-inc.com/#/demand/search?show=true&ids='
    dataList = response.json()['data']
    for data in dataList:
        lj = lianjie + str(data['demandId'])
        developer = ''
        for dev in data['partner']['developer']:
            developer = developer + dev['nickname'] + '/'
        print(data['name'] + ',' + yewuyu(data) + ',' + lj + ',否,2021/11/18,,,' + developer + ',,' + data['owner']['nickname'])
