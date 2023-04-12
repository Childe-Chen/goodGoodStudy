import requests
import pycookiecheat
import time
import datetime
import json
import dateutils

# 参数
kw = {"dashboardId": "1745", "cardId": "32251", "chartId": "31520", "drillingDownFilters": [], "globalFilters": [
    {"alias": "par", "comment": "", "createdAt": 1616395379000, "derived": "false", "expression": "",
     "fieldId": "73021", "isAggregated": "false", "isChecked": "true", "itemId": "4849", "itemType": 1, "name": "par",
     "originFormat": 100, "originType": 1, "state": 0, "type": 6, "updatedAt": 1616395379000,
     "condition": {"filterType": "BT", "betweenType": "CLOSE_CLOSE", "filterValue": [1635696000000, 1636646399999]},
     "chartId": "31520"},
    {"alias": "店铺类型", "comment": "店铺类型", "createdAt": 1616395379000, "derived": "false", "expression": "",
     "fieldId": "73018", "isAggregated": "false", "isChecked": "true", "itemId": "4849", "itemType": 1,
     "name": "team_type_name", "originFormat": 0, "originType": 1, "state": 0, "type": 1, "updatedAt": 1616395379000,
     "condition": {"filterType": "IN", "betweenType": "CLOSE_CLOSE", "filterValue": ["微商城单店"]}, "chartId": "31520"}],
      "cardFilters": [], "linkageFieldFilters": [], "isCleared": "false"}

# 请求头
headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36",
    "Content-Type": "application/json; charset=utf-8",
}

if __name__ == '__main__':
    # url = 'https://data.qima-inc.com/garden-api-request/bi/v2/api/dashboard/runCard'
    # cookie = pycookiecheat.chrome_cookies(url)
    # response = requests.post(
    #     url,
    #     json=kw,
    #     headers=headers,
    #     cookies=cookie
    # )
    # print(response.json()['data'])

    print(dateutils.generate_timestamp())


    # dataList = response.json()['data']
    # for data in dataList:
    #     lj = lianjie + str(data['demandId'])
    #     developer = ''
    #     for dev in data['partner']['developer']:
    #         developer = developer + dev['nickname'] + '/'
    #     print(data['name'] + ',' + yewuyu(data) + ',' + lj + ',否,2021/11/18,,,' + developer + ',,' + data['owner']['nickname'])
