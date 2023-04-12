import requests
import pycookiecheat

# 参数
kw = [{'kdtId': 114275841,
       'url': 'https://api.map.baidu.com/geocoding/v3/?output=json&ak=iIqZmy3CGe1z4zzEPqruCGfa&address=广东省深圳市福田区沙头街'},
      {'kdtId': 114275948,
       'url': 'https://api.map.baidu.com/geocoding/v3/?output=json&ak=iIqZmy3CGe1z4zzEPqruCGfa&address=上海市上海市嘉定区宝翔路158弄9号103'}]

kdtId2ShopIdDict = {114275841:83281417, 114275948:83281521}

# 请求头
headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36"
}

if __name__ == '__main__':

    template = "UPDATE team_contact SET lng = '{}', lat = '{}' WHERE kdt_id = {} AND lng = '30.0' AND lat = '120.0';"
    # template = "UPDATE team SET lng = '{}', lat = '{}' WHERE id = {} and kdt_id={} AND lng = '30.0' AND lat = '120.0';"

    for data in kw:
        cookie = pycookiecheat.chrome_cookies(data["url"])
        response = requests.get(
            data["url"],
            headers=headers,
            cookies=cookie,
            verify=True
        )

        if(response.json()['result']):
            lng = response.json()['result']['location']['lng']
            lat = response.json()['result']['location']['lat']
            print(template.format(lng, lat, kdtId2ShopIdDict[data["kdtId"]], data["kdtId"]))
        else:
            print(data["kdtId"])


