import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * desc
 *
 * @author fanyin
 * @date 2019/10/30 14:25
 **/
public class ActivityPromoterVo implements Serializable {

    /**
     * 主播名称
     */
    private String name;

    /**
     * 快手号
     */
    private String ksNo;

    /**
     * 快手号
     */
    private String avatar;


    private Long slotId;

    private Long kdtId;

    private Long hot;

    private Integer refundRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKsNo() {
        return ksNo;
    }

    public void setKsNo(String ksNo) {
        this.ksNo = ksNo;
    }

    public Long getHot() {
        return hot;
    }

    public void setHot(Long hot) {
        this.hot = hot;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public Long getKdtId() {
        return kdtId;
    }

    public void setKdtId(Long kdtId) {
        this.kdtId = kdtId;
    }

    public Integer getRefundRate() {
        return refundRate;
    }

    public void setRefundRate(Integer refundRate) {
        this.refundRate = refundRate;
    }

    public static void main(String[] args) throws Exception {
        List<ActivityPromoterVo> list = new ArrayList<>();

//        FileReader fileReader = new FileReader("/Users/childe/Desktop/yzk.csv");
//        BufferedReader br = new BufferedReader(fileReader);

//        String line;
//        while((line = br.readLine()) != null){
//            String[] data = line.split(",");
//            ActivityPromoterVo vo = new ActivityPromoterVo();
//            vo.setKsNo(data[0]);
//            vo.setName(data[1]);
//            vo.setHot(Long.parseLong(data[2]));
//            vo.setAvatar("https://img.yzcdn.cn/upload_files/2019/11/04/Fu1kQRSw_mRug4j83NGrKjfWZeqV.png");
////            vo.setKdtId(Long.parseLong(data[2]));
////            vo.setRefundRate(Double.valueOf(data[3]).intValue());
////            vo.setAvatar(data[4]);
//            list.add(vo);
//        }
//
//        ActivityPromoterVo vo = new ActivityPromoterVo();
//        vo.setSlotId(736L);
//        vo.setNo("yw110016");
//        vo.setName("瑜大公子");
//        vo.setAvatar("https://img.yzcdn.cn/upload_files/2019/11/04/Fn_UqaklU01i5NYFOygFveYh54hj.jpg");
//
//        list.add(vo);
//
//        String d = JSONObject.toJSONString(list);
        System.out.println(JSON.toJSONString(null));
//        System.out.println(gzip(d));

//        String s = unGzip("H4sIAAAAAAAAAL19aVNUy5b2XyHO126POQ83OjpCUZxnceroIPZQW0FFnNGOfgMHFAQZFBUExAkEB5xQkDHi/hStXcOn4094V1ZJ7ayqc+77cnbE9qYEDufWMjPXWs+zpvyv//nNueRccM799o/fTly40HL+H+vXN54+/vuVq57f/LvXvP5iy6kzjt8QNJ5KnV9PENbrMV6P8HqBHeL4jiOFSziVHgm0DpBGHlPIp473e1PL8d/+/beT/oVt/m//YIQKxQX599+andMp+Kzs2Odw+ms4fzP34E74qg/+ZvMZ+G1GNBYaa/zb//7735WMcc1lIBX3mSaKYeS6ijuIOg4NsCa6QjKKGZMSlSTL3ekIh5//HOt/lHk0Hs7NZadWMn3vMj2TubslMQXnhCspRAwxNcMqEI7HYO8oc6kKtFRCedQTzHM1rtxAjQQh0QaGH3p/Ty8NZztmf7T1ZQe+hA9u5ZaX0/PzP9r6V8WEPceSEhVnN2XKEcTnLpJISO6nBCIkpYWDBSd+oIJKMYXADPFoN5c/wIINzLffXRVLIYQ5JjSOWIIhuHaYglBM+9wTrqcYl46nKSaBV7l7FCGCOCuJlXn7InN9IpzoCO+9hqNehN0Me2fzbdfyz77l7y+XriN8jNY8jqCeC1ffVzTlepzAkXOfBinNlM81IcxRlftn7oCOBM0PfQ3f9YGqmAtZUxTTfFu6iRhTDhoT5yYSLbgKlPAxovCdk3JcFw7IAa1mjqaVIlK4U3AZo5v48lX23u2iwnwNPzwuHrYtpJTwaUySOPsYBEJizw9YynFcQZWLsfKJy1xORIqrSq0mXFCCaSTkstnEPxY7zDcferMfP/+x2FmUt6jgq7JqRODQsZAxZOXUZ57iAVxNTqTDQW2I61DuuyoIMEIVsjJEEWPRhmbfdcL652w4/hUWbOSXVeEwUkQRJGNptMsV5gz2UmgPaY6EYhRUR7oO8wOKRdVxUyksw7OrcVdj5sFQdMrFS1k6awb/aKQEjyEhEgHIJ7jr+i7cS4cFAjyLkC7oN3Ylq5SQUzCPkc7kur/m7gyFswVFCSdHwL+Ygy+ICaceqQ5nHEx/LO2WPgsc39Upzjn1GDjBFNOBC1aDYJ2SlbdSCQrXODrp7jfpuRuwwlf94eRg6ZgxmAbMCWJxNhHcnvYdjLBEyEWelD7VLnE8z8MpwWjVMcOlJ5FomYcfMkvT4egdRcS/9DBMEfiEWII6vgSrQ4jDPe5IKrXSVLiK+dhhuNLDUA6fx1VJUClzXR9/HfGtT2HHPChYeu62ddAYCVGEAjGk9DnXno+FDypImHK0JG5hK7nvCBC7ajsF5dqyP/2TsLK9d8Pe99mOjzXf254LwksSUo4ZxziO0VHYFQ4nRqWFo6iCI6aMaukrFEjM/Co8AbCHRwJmnk+AqsAmZgdmwxdviuptawu4WwQwLs6VBKPo+uDhEE0JV6ZUQMCCOzgAX+6YP6ryhRgch4XMvrbX1GTnX9XUpOe+1fwcGxgvCQe+nRApdBxEQbXAgRaAbjQFtU4B5nHhTBgCXyNl1TXEFGP7gF++yi8PZWYHc89elGwhBTQCSCeGVJQEgmFKAyk8gDCA5nysA+FqmkLKV5VbxjhCcO4lqdJzXWHPQnh/vAZ02FhAsIaTI5YCK4XA/ceCYkh5PIWl5i5hWguwgdJLuUoJwT3Jq2wgReC5SXSq6aUH6YUhMIBYqN9LCkEI6Dngmjh7pz0F2gog1cVMBL6nfRJw7lAXYeK6DqmCDBLQq4VrPvTmxlcAKfwcu9dlFOPpDK00LUIzKXgsO+1zgIIuJTxACOkUGA5feNgBJoU9zbxKsEDB+wsLyf5ouwOCpueWwvlHP9q6wm8r4eyTzMjd3Iv2cHY87Lm+Kiqoh1CIUxVnRwHJpMCauBrwdMpzPAIWWwUYewC//YBXyIo1eDzLMWc7lsKbK99HX/4x15vpmA1fPsx1PMl968i+fWvc4Pz8qqzcuGbOYtkaV6IUhs3kcNI+kCCBWAr7KRoo5YL8VfxUUyoi9xd2DObnVvIvngCGmCzdSqbhhqtYxy18H/gK8bRDUg7Ih5H2iAd+TnIdOLKKN2vACzi6lPnh10D5wPjde52e6wS3F058K50w1WCfmYxjBQFma+ZIAA0p0EBg9Y50PQEqrriiLKjkAgzMLlGRMoeLT/MTk5mRDti2gfD+tdzMZP7NYPbN+wjaAD+kHOk41xCECnCg/BQAHFBoH6iF8DXxgAcYpam8hsAIuW2qjVZ3zP6x9KTAWVahbInzwV8HPCziCOgTqjAzNM/1XA7ejTOlfOnylAK0QCohNqFUMWTpSedw+Ob6z7G+6YI/jpCXZXngExBAEhYHeQHqhw104AamiIfBuaTAOLsCaKknNXWqLDfRmBIZHfb0aGF9zX5ahrMuGh0Lwyowl4jFYaaeVkBGAW2leAB8yncpBqAA2BAYAgfUVc1MBUXa9smZJ4+yfZ/BJ2emPsJmwmkXDr0S3TAhAd8AMIkjLHGkFnD0vnJwyk95HLbUAVtNgFY7onI3wYISOMHoVnY9ggW7WRS2nFWZmIpQhMYBiG6AAtBlOEGg0L4JQbg4hYBHY5fqVOBWbiZcYcWizcwPruRufA07puBqli4h1wDSWCwvDW6EuUoCPXYEApsDOBAMDkFKws4FrFIsEyDBFnwwx5mbeZftnMp2XS/+YhTMYpVaA4cFvEt4HF5KwHZ5Ao4YTtX1/RQHEu0AxE45jHiMVthuDGAClMBie30zsH7FcuDng4ozxowBNgJbFkPEulOpM2L//kuXuHfh4jrJ6zdtaNi9sYmJui2VWBEuAuLEQrDTnbmu8cz86+9Dw4AWN+4EmJgf6obfC1duwB+WdhIxbSIbscRsqpd8z4a9OzY1N1w9tKFpr2aXt5xu2OKeOLm5KqKIjNmJxJx4Aqt4uMUNTM/dD0dK2EGCDcCIxKKkdac1dRuuXD56KLh4tnb7VdHQcGBD/e6LG4Ideys9IIjHreBN7ssXMDvp5aeGjxYcYHpxMN/WWcZHQWe0EPFEbNx8rPZMs7Pj2Il13rqDl/ceqT92WJ+oa92kqqIOnCOFuYUhJkt8mVciWmC3WihF4oCIupPnkOfJHV7jgUP1dF3tpkuNzU37jx1JNdbvqzpdbrhytH0fnoNPyVyf+Dl29216ocdcvcW28FVXZAk5EGtgLXHkO6uP7Ntx4WzDmfNHLjUc3d5yDG26tGv3jm2n6o9U4S9KJY5u3/krzqnGIoI1jG/uXeZ5CR4Cgwf6KOKgw7ozB+oP1e/ade5gw4V9bN3xY/UH92DWcOL08U3bq9RXaCSiOEh6YSy98MJ448c3QS/yC/3ZT51FSaO9A5dDkYoV3Kw70XRqJ/Iubd6xfUfd5lb3cPPm2ouHN7KDqLm2evO4lMyy1d0zucm2zMu72TfjgBXMr4vwJj33Dk66dAcFODu4mXG8cd35bZucDQdSV5s3yNP15/btO3x4+9bD6JDWreeronJUCmVh7Nv9oB8KYxNiyHW+B8mKUpboCYBybhx4LAt42j0bnD+5Q/vNp0jj8T28YaNiJ8iW3Wc3VHliqaWOTjrsfWNw9v1rgBNLRBSsCaDWWCH2unO1V87ub7rg683bzrZs3cm27nWv8n3nHLyzKhtl2Jm2wMu1x5kX0z/HetvhumUHZgGrhnPTpf0CrdAC/n4szfAa9vgt9a3k6LHGjUcOrcP0cG3q8OZL+1pSVcKBXugIpxbh9C+PMdcRxRgw11JoHEusxmN7W2obRD3ae6X1CA+ONeyvvdB69UDLjsarVcdITTrAis28C4eXMl2LuZlXBQbcBrsGxO592Ps11/k5++JOlG4EKRWLlW6sO33R37V3a8v51KWGi42XDzY0H2lpOidcGVzdWKUPmBMrFAymBFgmMHcwLlGERnBJMHCQWBdu/7n6I7VN4tCV1gv7SP2ZzZd2b29p2r7V3VEZniYaIeATtkyZx49yfaO5nk6TgFruseOBcLRCkzgMrq7xQr2orwNXeGBzy9H6hma8d90+snEHb8ZV5kMbrGylb0bGwHz8nyoEyoWJzSkZyz+cxlvV8ZZ1jnv61LGTzq59G/29m5iuv0B3VOW1KUOC2/j9M6yi4YUdA9yUfTojjPePgllUKSl1LAFbak9vXrepbtPxhgOtZ5uPUOWSo0LsRfucndW0nBEW2ZBs19vsq7bsq3uZ0bZwvpQ85EAEOFE8ls86vemMl9qyZV/q4P5d2w9tW3ex6cju5l37+RG30rIBnmNwtaNte9JlErCv+n603cl1LqcXJ9Lznwq/LEESgalWnKtYcO7sgZ1XT+xSOw9f9Y9vPrF957na3fUtzYek2HG5KubCMGeROwAgB9wsvzwEKC43vpJ/1w6IE6BnxCsoCMhELPJY13TlcutmLE9vO7Wnbt/5xm0HGzeercWNG/wtlfpAsQJzZWWSFp7BMtUepez195FuC5YQpgERaxXr3p0/e1IccDdc1scdfVZcvbL38OHzzbXk2E7dUF3wAf470tfMyGTmRWdm5G5+/MOfRoSkJoxRQWNB4ma0a6M8j2uPHd9BTp4+eWZdvTh39RA/13TgYrVvVcgqoSiaN+A8NX8dDRKCAOcRJB7p4Scaad32uuPuoS3n9uzxdm5t2H8UN7ZuaDpWhT0J4aSsBKDm92zfaxOXLG0bNYlqY1HiyHR8y9GWfXsaxMVjW/fsFU07Gg5tvNpwNth2rn5PNdgUcMcjSNL/BVzWz7G+TwAzF0DaKGghcKzoXt35M1tbmy7UCnqa1h08fLLuKtZX9m/aH7TuqmSHFNgNwO1IqBsP08ujsECmb1XlJhyINZcx/Xxzy6mzruNvbN3v7t/kNOzBp1XT8cv7m/YfrMRJFOyCwpEh2bRzXXruWzgxHVkOzAVlQKgT2i2kAHVblGbpaTh9O7w/nrnzMnf9ZZTtk1KJeKnItUgFqsWRdYbdN2AVq0lKtsLSRTC1IB+KFx75/7e2iIvyfFAfaGPu3hB4rR9t/XZJThTA4YDxWDwdOBPs82hdKz++a1fjCXK1pTnlnt0rtqEWcaXKX1HBLKKa6x8OF+dN+KH3dXjtcTFIAvsHjj4KQoC/p7Ho1YmzJ3bWobqLF1xx5fyuM42+Ds4cvNx84MD2SlxOQOmYtgJMy33h7JPw2+dikCT3/nF65VnR7oKUNsw0aQVw/PH09dyGk5u4PKHR5vPumaN674YmtuVU09Ut7vGq1B8mkltAM5x9ZRJABov0Zb705+bfhO3txtu+e5G7sZS/3WvleYkETUfxXNiJK/TylUPbL4nmi427vb2Hz+6rla2te07V1lVLCvTdQuuZpcHMyJdMV6c59YG+8ONHQxBHJovZyrLQGCg2iRl9OlO759LBpkM7g5OX6y5fPrBl367Te4OmppM7LpyoUh4w0HYRxNQgqE0RI5dU+0dbdxFQFTKskRYxuDWcxkrA1F246GxvPn9m347Tewk/cshRp7fs2HOY1QfNlUE8DPdM8uiWZt6+yo9N5SduGcjyo62nWHmQnhvPt3X+aItCPlRSsEY8Vj1W3Xmkak+e37PNP79p1z568Oyh8y7i8lI9P7G7yqdohJWVgvnQmx8dKmjS419h7xKyJ1RrgAfx7Pi5xs2uPnAQ0WDj5sZDWw5ulY0XWhx97iiqxM0myUaZleJ9N5V9fDM99wbUB77CKqhSf/bTMuxhKagnFQG0HSupWnfRPXJ+N21qRi31tJWer73IDtata6JHWy5VFVciwpB1zNmZN+HLqczwN5Ph7yuFe8DFMEniMY5TqvHCltRGzpt2ttSmztXXXvB36quX2Ea/qnoWUwAlVkBgcCXzoBc0xZzmX4NSyjERCkBgLFB66PL2liuuPBDsOFznHT7adOzA1uDA2YMbd1T6GSMlsc4392wSoMP30UdANYyrHprOtz3OvR8NlzvCns5w+rMd9RaCGVeajJKAd0MYW+bx631Y6fluuIA1NX9dbWcKz7COV21X1+xeOnLyYn3TyQt7nG1y+7GD+Gijqrt0Ytup6lpuwBbWXQynO7M3v4JdLK+YxYghZar2Y53zWb2/1avfd/rEto0HecOJQ2eubPblvr0okJUJIdBhorFVy/18IvO5MzczH956C0sTHIWBtKBExU1VXTmxm1/Emw9ccvSeXcf4uSv0cNOWo0dPudWBWoWZFe82xq+QIfg5drc6I0kwQYhiHYsfnZKbdyC+oeXSZdF4ZNe5K+e9U5t2s5ZtJzdXZjIopUJbtaf54dnfc23Xfs+MrpSQKxGUi1i36+S2UzvkZW9r6+YT2w+ebm0KWo8e2dW46WLTkV3VGAGAoVUHtngHHBhwIpP/HrmLSfZ6qbJFwidwMI2x9LPpwp5NFw7yfQ2XD284HTRcqlOy8cj+Czs3bq0qBuKKUisGFN59DnY4M2RKnTMjXWFvFxzoYkk4bjJEIhZaPbflwPHmrWdbPbR92+Gjm4XY6m50drde2X6lqeoYwXbYxeIfP6e//YIsBl0VIntg2SqDewaGx6wormtxMTl7kZ526rdeOt+65+LBA/SCc2Vd67kNmyqlVAjgfHTZ0ktjxrw9fZb7dD1cflRAKgvphQVgfJGyKvOfoXhR21Oba51WsvtS89ZLp+v2nN1+9MTWw96G7Tv2nayyImBLsQVUHkyYNf21rLa9rItBcGB1msU5aOJTHLiuh0SKEmHK+cFFOI7nKelSjKqKTAu5disUNH47t/LNBPxu9+fvjYfdQ0BH+uG0yysJFGwjhf2PQ9sZChymFPE0SynuuYxIF/71YHilwcxVXQJIECQs01dA9sYADq5YmQyMwMYQCfocZxNNFRBwSk4C1xdMegDCA0lYSgZcoqAqzGGizCTin4BH88t38wMT6bnR7MKcVX6BBTNJhji7pjRcexyIlCaeT3kQCOVTl8nA41Q71UXETBFqFX71PTer42PRwxazt/ZXax8VmOp4iEXJQKW8lBYpjyOBXKxUCrmmzgi4hoOrUjACC2nFTYtHnB/qgAV6U1u7PdpKhIQCJhBHl4VyOYdN1ADAMXyy8gIHrqODCHEcoaoxKnwmsbIJb8ZN6P7lq2xP1x+L1xRGJe3AQDFxvFQp4FqFPOZp5Kd833dSjqeEDxaWK89LpaqaKygC0GKZw7lv30cmi0mFoupa6QQNmBlMU6zssuYOwUp6AtifMGnOlPJxwLQvSMrDf1LrBW6f2anJa7B+VSr9VVcXoRQQDECsGHJiJDzFCaEuR0h6RgMJXD1XEsmw41c3qQhQmLJglylL6/tY9H8m3rUa+4jylPAZMlZDqe8H2HFIyhUEB4EndaB8MGGeBJ1WJFVVVwU2xEb3uTsfYYXtb4Ew5Wa+hNPLho0UiiWLhZN2/IMphjVc+DibigIfzDZHBEtHBEx4KdhJj4GDcZkXsMq6dzh8MOuRfcxMfsw8M/HNzNK00SITYrh7z4D/vtc/x+49iGyQqaplsZouAg+sJBWBD/YbPKDjgZ30GHIIRQH23Kp7CnYcdigy5Q9WYEczo/1FNBH5P4E1igVkfZ9QHaTgZlIPrHQQ0MD0OPgpZVo33arSWEqZtGLE2YUZWMb6PL1GEco8fJleGA9vRdWxHLQSrkmsKmjqp0yGJcUQqLvjE+1i33Nc0+8Aml9dHAsG2UorZa+9Sc/3g41Mz93PfDZl2sVIbITHEOemgzjONno+qA0De8009sDmusAnU5T74LHBfDrVLkabJozIxfQtmFUItZvUxJ/UdXJTKcoY/vtYh62vu4hP7tt/4HLD6f0Xj7MmRXdvObejKTh8LHX20O8tzWUSmop3C37nR+9lvsyGk2Og3OUuGv6JsH8qRqG7WA/gwPUYHJupfvA1AmThYVfzlAfnjFhVswCmREuLt8zdyDwcCu+Pm8LmKKfDJNcsRlpuTRuGwf/YJjs//Dr8/KGGY26Clp0rJlPR8xggjx20REbLBWV/37Ss7VAVICmr1Nmo7ofFijBwlJtgGhBwjLZRuT4AzEV9RQKMpQ93BHM/CAwhAX0BlF3l9kxhMCkLqpoa0/n5X7Rv6UkU5pdawv/h36d5a9o4wNVEWdWb2bbH6bmRQpf1bK7nY+7LsF1jTygQ6xg9jmsSjTJTSW154RsPTbKkYJHNzYui+1EdAhFUc/737d3aBMTKNPZZts74i3Cu3WSYsgOzYJlLOweGhFIdI/yxNskAC3I7DVsoVEvPr8CNyzyfyC8bk2LnwQA9wGYTlNDOMU5Nd0l065YegRXJD98Kh5/bZk5oABUJ+QX4obUVK80/f2h6Y+5PWekiwFkiRuf+2kwa5xoLbp3hm/DljEkOTz9JL93/Y7GrGGO2ostAsoBExcpcr1EBYMOoLeKSyQoW5LPrvzDTBIhnQiaNAsjHdrEt6OSLafBP4bXH2eWe/IsxU1/ddyNzrxQAF1qBiChGJnBtEhIze8WScP5++G6wmGWLiuaA0BFwvX9bJL7ecYDFgIV3fMlcjKj2GQYI5znUS7mSVtMIU5YWHWfm/bDpUARXFYll2seE0DES5mtUSuC10vLrvXfDiWlTn1lowi/itSL8rcC+HLgNaHSMkjm+XjhgEx0Kfp0EKiA6hTkNMMOuA7ZAVjX2muJtRMqSB9mVpzXh+KPs8L1w/HO2p6vmT4vREZhDwKQJbalp37a6cdKLY7BMmcT9+5lrXbC9Ycdw+KqrPBMIwJ5JKWlCxtgUWmu7gHmpL7fyDZyWQSaFEGrh+04ZVZyAaQbrh2O0h63R9iFFreLczNfrNeYnUJ2a8kJmpIFu8KSAsDEZVoVJ+ttoTXhzpQZk6u+vyQ6MZWYWrDgzlZpqgLCJOYxCl3S0abNdpi79XR+158+YDlCOY4Qe10ZvmOJIcVtr84MrsG9wkqYsxxohZrtaZEZBaRqjw31tO4dM7Y9VPT+4UpN5+6KmEOGLbhtFhGj99+OOa3S0TJQ52g+9uTufs8t3sgv3jNeYf1TMZZW8LDY5ZhwjbLs2VdBA8cpCop2mCrdwqn+RngfDSA1WUUmZOThVYjGIbM9DWMVC5n8xsgdLZiqskuJhGHFhdZluv3j138ydK5ZawX6W2BdTSiCZGIrCCohypLg/x+492XDAfDWVNq/6zHfWwWoCzJAltGeghIZSWaG6D+HL19muxVKat1SQZjlZyTCgUJ1U0AQ2EA7MCkp0P0rPv81Of7TxHjY9JQSoT1Kkn1BmDwCYuJtevAN0vwicwrmJ3Mwri/Gbv065SszkUQWfZYnXOw2r6B0Ks8r6uiMPAeA7Dn5f41lKLKlVGjf+PLz7LPywWB39Au6hlY5RR7BGrmN8RHlFzcTQqm+NpveBfbNTU0oijESMyPVaY8KcWa4iMzeVGRvIf7oW9g7UmDlq77q/P/z6x1xPZmA5PXc3XGwr2WJFKE6KDZn5NgjbfHtudbTkvS6gQ8WDBlIEi0QAlAGFkjRGAm1tewnsEKxttJcd/ZnB92HfTGbofjgdVbNQokyRfWLXUGM7Imau4bsBIGWlKtwoKWpGOPAYoTqxnoB6mYCwbxgkdTzHBz4DLNIMhjIDiP4kf4esfrBwdia/0GOqqMZGfrRdN9UiC/1mNtnHsUz7q0IprjUDUSpiJu4lto/KLiQN++5mvlwLn9/IPihZPokoAUSdFA4ARgvmzFLet6/A7pkM7fvesH8yPT8Qtr/LXv9WlqQF44KoTIwsgk/D1qQYuHFwumH720JqdqAsFAsA2Qx8BLCcGCczA3+jE31/D5axxYCWcze6Tdvfx/Yic7TUBEChMsWsiSmw8fNW8tNU8ZkQwEJ56fJSSS8QnLBIKqBNTcTdnr25io3BMl/7Oda7UD1mFZvdEwDAEuK5pjIUWY1sRkEK/iLbM1XIz5oubNjSXJeVxcPAMhSKMR1orWkBqayRv7Bxt8J3Q2F7R3E0wS0hRGRkNDIJ8YQAvQmbKVyWwMve/Pq94x7gq/TS04qMNlJMmxbaxMLJEmxuRDZuDcEymMDcuMEZcCclrSBcJ5hrN2qBrYEJmbGnmddvM0/7/18UF3MEXjhB44LLoiuZJyPZZw9gwTcRLoCryVGMQ+VmUpZDVYBcVwJRxlS4jlABQ+AefE9XB+ThA5k1pzs7Nh7OdRcTxzXh3agOHXgcTUpJ4Wi0sJMXy32Z8aewDKAff1qMc5vao0e95ZEVxDmOMzGJr/c5o8IDFg/0GgkaIN/xA48hKT3spGR1vQwDe2IP5esG0fJTbzMzr8OOx1atghAxhhOsGT9pbsVnCymzojpYJR5Em6FUmidFaxnVZQ3/nzryg58BOQGpyE8AhOr4Fz1CBAHGxSghAGpSx9qiF7mezlxPDxi7N+HKmyKhBF97ryoJKYAhSyVVYukBxYmybPL8cwBPmQe9sEp7Wez1K2+eJJqYpu2k4o9calE2AjnXd7eIXP5stL5pDaRCxCigX6NZhqtldUZLgE/FkbjZ4Zlc37h1vCbxA5dYJBXgA8oGxMtqVet/kWu7lhldKQBn63TtoAbYcvBqLMYA37Vun6lQiVjHm0HPg8v3c2z8sc0ggT3RGKWWawyMUmG7tNzy0ga4bMXSQDvuaIZ5Ao6KUaS1VgzFqSgbPGq6Dwv13astuaVqHjPMhvM4Jcl8fQCAQ4IqCam04MRVCJniP6P+xJFVBWRg9pSykdTIx+z9EVjfH0WxUJPLUoQkpJ3EjJ+30t1YK2lgcd9MZd+NeZiAck6SipNhJEE7rVKe0ae5Dx/Dl1NAb0r8QQpNKIoxN31tImmTwIvo63/8R3ZgBFwr3P3//M9isqyE5jgxJSyJBRUBa2ArvZO/sRTOXU8vfY5KPqipEU+q7I8wk/u1mkPfD8MqOSTAl1ZdHfAZBtQnqWpJAhTQZoGZ0UlYhSGDq1Mv84/mcyMWaZCKwnEmFp3D2p77Zoolbz8wAxEs7FZy5kwS89pAYpUJQjK7v2zyhlnTX8GXZ+69y3YOpxcGStrJJdbg0BPaNjPxwCqJMbUSJhgyvGycUmHGScUMdCKARyuNE8vUAUO1agHz98x06czT2fDmij1HNzJu3PRcxRgmtTbxjMZavaLhdGf626hRiw3Nx1OnSm7T1FiayqmELhzwVGHlh02F6fJQ/sVYoWGnI3/9bXHiWsmWcNOAJnRiZ6qZQhY6u92f7en8i4lEwFzgCqCkZDMkldpdHHd70nMfiqY3fD1sVmWZE8ImwMhjWGK+nssghVKEeamUKrxs5ZgXNdyAyIB7SFaNMDWFEFazfubT57D7UXjn06/iZoVL0ULMpJn0Emdq2Bo3EO6RnfXsnyw+E/Vz7G5/duAm4MlwfCk38yoS0NRhIZFY7TpVQOSs4voCeQknB8OesbBjODe9nJt+ZhU7GeudFLA0E2g0KvNipjYWvNhyD3yt7mMEnmMKaZICTIgC9C8rsct2zKHSzApuMo1mfG1C4ihMsJU8zA4t59+1g66aUuyVG/l7HdmF29YrMnAzmSlKTMq3mkYgZnUPDX4O2zvNu40THSY6LErjA5jChYcBEsJy2OTErWbo3MPbxtsXCkyKQcwIACNZmHX6960vX+9i5hPtCRUInaIeSwEaoi7iHifadEpWxi41Mk48umSF3o3w2jjYNv/qH4vXIs8AqiJlcmEPVVHyBwKFI5O52ffpuTvFUT3Fr1GiBpwXifN05BohOjh+Uf66hKn8GxmLOqErpt8A9TajkJOKymAqqF2p87I3XJ6G9XPsXg9cPzMysXcQTF0Em7SUBjnFKEigBKGApByJmCc58pHrCmJaA6QjA1bdhqiEGY4e2ZTlnuzDW5knI+m5x9n55+HdN2ak48MP8JvZzm/p+Vc2hgLOk9RhU4wFsmaVmfD+6J8XF0VBEg0Kp2IMFFqjs8DgDqy40nJfOPWg2LeAKWZRVZbZuKRorDZl2NYVvPvI+NW5UbNty2YUxPfHIyCgnb8xL9cBuUxo24iWGtHKxKEpRZiayb6JxjcSOExT3pRUS5Ymyqo0CV+NAOb80dad6/kG699WG7KiWjszusE82ZAUD0MM0TJ1yA5OwCrMx7sbrgyGHaOACexzpQD1wRXFsC58PaIOcQIf3DcTvjStsTjlUcw9866optWvQsJGUsuJ3O8IHy6FU7Owvo+UWnjM5DltnkZOrG2MlvUmTA39SoQUhij8Kc3m8EkqMaQHGBxRq0oRjjczMpkdGKupKRYUVySTCpVRYFUSM3WAkGR5Y/3Y06JkuZnJ0vzVKMePiRQ8Mb7NmSLWJKbM00XAe9nHN4uDPMorOARnAlB1YrAKg0Oycuq/Bo5Mf83dfg3eFb7mB6KSUyq0AsVKKsxvwnb2m3G9X7OvusCiCCUtQwL4ScK/IrHUIDNvmFsvAnY9smdL/Mmr1pQJxeKMslxbmhohsA62MjzPjD4D9j/yX2F3u5kj03YtbH/73yVYYgbWSZlUoRVhcFJlPU/Z2w8UCvt6gPwDyCuRRVOGbd4YSyp+zQEsWW0TAzcLpzhh2hO7PqYXzXi31f7nKHCHhDB1asn1AAhuJc3T89OwVl8fiEJipjwSER5jrOBawxKE29GwscdF/xVh4dURS+VtWVIba6djjIhfa6eCGSsbyfnFpOWKgLOqWcGUZRWGaSUWyIZTi8hjtve6mXZS6DjOPp4uzz0p8ziL5Dwp2QQDGm1F7ArxCjMUY+LJX1fnMIP0KYDpxMitZNbhmveCCw2efyxeK63S4UrA7hgsUVLo3XRHWuNKC884hMPP8w9WIkRnHlFVMbo6+foAa0e5geMELOVx6qRSLOUimqKAkrlfNRGN4XIylu34aIrC5kb/OZsZf1raK3P6icXGKCec2Q+YFMJ1RSy8OnSlL9P+Kr0wYHMJUxso4bolZkkI1sguF+7vD9u7Ml2dxTn1f5ZIUZJSLZOj/2D/tT2H9uWDzND9/NSMEa/wJIZ58qcsLWvairHGifXFmBoOq47py5fCiNxJjUW+/03kYqUZLRxjBv3aVFWa1w6snoThdza6K8wS+0Vni6EUGw3A1TUDRxIr9JOwiVbp8KN50IpwejQ3Y9ICsKKDNW/u0eRqms0TK9ajJwA4OwY5UevAHOcXBsOnpZSAEgao8Biz7Naa34HLZM/SvBNOD+bez4MC34xq5bgsNNEm5vkxt7n12GcDNedv5l88gWUc7FJ/pvPbnzhYbUKliYmJzIvhlsm7/dr8rK9fl567D8C9ara7GRsAcDWp+RMUHACcW6S57V9MggBWeUVp2Vhm4JfAtpMLBCCt7Nxs4bWYzNvnhZ7xgfEfbd3ZpzPMjN62QnnAmDSLNdN1rUCKMOv9lXBypAikTFtHoXrB1LNVFxATZF5dTaya3XRlCatBYfXd0D+WnhRPu6QmBAtMk5v9UEwiW1Z5KuwdgEs3UdopU4crNEqu2V4TbA2Kznw1r0zDKqSlCrAqumoUCZ1cS6cEomq1JGRmumBFgc+qBnJZ6E5IjG6bOmsLi+bbHsGqDPZEhFEqTcFIJjagiph5v2Ve9sPgH4uPV1UTUPPzzEgZBAXWYyIISVVBKca5VR7waTlcAsZ9LdvZbUDoo0e5jg/lYyi4mWmeXA6PKmW/NBXe7kzPvSlNLq6gtlGFG5NY0RhPda817y2Y/XBX+81wccwIOTBrZgBHjFZLRGMMHVsreMfUauk0z16/fAzr51jv0z8Wu01Yr8ea8YjMS4YsqYtnNoLbvSVTmZnxmpqa/LMlE0uJSrGZwFzEeYd4zUjPDgNkH982Yc+pmfDmSmRFuFRmyE5iXRFAuK2SovzwrMlNFOJ1pc7r8qidsSHYjDhKLjImlF353FMcDPhnygn+wczvSKx3A0uwVlaf38fP4d1nhRRA8Q2LQk1PRHYYMlNskwo6IeDOFj0sTsPoGMwtTeU+AnZ/OWA/CoGZMC+UJOW9qKKaW97r51jPoxrTvvmh12oAAEQvKI3xoh9fLxT1HS5FCjHkI+76vhtQnHKFp5X0Mat+iwTYX9lopy6TNimUZZmIrD2gCEmMBE0sDIsLg0sss/Yqd3Mu2/0m1/3NFksq01SV2GAYRalVH5678zG9MJS9/SC73FPNCCWS8JdZYhumtLRmnOUXbobTnWa4yZ072WulCBMh4GCVTqxnHpnRC1Y0/dkLM4XVzEHorn4+WlKpSGJjdQnsmF24nhnpgGV8+o+2vuJjpgDY8vfLGualIJrEeSRSwgIaAPrkCu4Q5mOEHEJ8zrnv+wzxqvdaJNhRq6XJFA/DWn1VNz+4Er6aLEaOixfQhpiIA3hLikKY18DscHauczkzs/o4yl80lBZaGhRL6szNk/DErgp815dfHio+ZFkMSvSUXnnIvnlvj9rHxmGYHF5iSAAImT2E6pvp3jSbOXzvX0x0QERqGucVzrVCY2r3vn4fbAM/C1oO4KBYgvwXCQKTjzEP7yR1NbVBL9L2dA+MJ/nQm5kZ/jnWPZeea4O7aV7avfM6vBM9H2qqZ3ByE6uFeXtVlIt542G49BKW+QVsZSEV+a3ihUTT7sNJUl2WQPwZFraY3V9yM++ynVPZruvmF+n57uKYbcDWwIFLys7hH6hFYpVS0jzhYSv7UGa2C9b3toHM8y+594+yb6OTRoavyMTctTShM+uttbEp84L7q77vbc/hkMNuOORoCBTGACYISSxbZQbcW9wNrAysX4bcKPTq21FVxWbgHgHCysRizIARlShr1zZrFYr9db6eFF+JTm7CAiVc24VA5jX3cHgpP9T+z1mwPRakpUIzDeYxMdyoyyLg2d47psO2ukIE4D+lLKl4GjM1tnYL8Ie53HInHGU4VqrYF1ibMUuJjUQxnXD27IK+GdgsWDW52wvWQ9gaMKzGPDEXzIwxYZYx7mnLdtzKvu0P+26YJpvprmzfDVjmD6JcEIH/JXWWGAAJsmLyD7/BMk3Tyz259tJjZCbGZmZC66QiVAzujz0Dt/A2lZnvWUip/ItiH0CCRCdWDm/eNqUWdjGuAizHcDus9Le+CFFxbGbTJjd6mQJ/ig712Q2ze8WnoMoTGQq8KljqpB4eIwSMp1VqcedjtmP2d/hRgiHENI2SpIy/ea4X2S+hTb3N33sT3v8ESOlJZvqL6Qm59y4zsAwONbdyOz0fxUeBHGEzkjGx8IYJqpfFXFZewwJmNFJ8EOrPk0ECrDVNbLYN4CbY0EhvwX1mnjw2UP7La4M6+x7+aIsgp3k7hyVVjkzNe5ZW/iL34qVJJ99cscrxADlj4yCSa/IV2jrTafM0vYlXRZEqroCJAX9IDPpihaxirdzHW7DMW76FEDxctlu/niu98bVsHL+ZdxznId+1DkIFq2Ud5bMX4Z2ncKCrrW5//WyAmT+VXCkoEEYtyp5wgWWc13JPEa5HI+QkMlPvkzLEWAvTtBPtIBDX6Wtm/Xpb7q920HQkIU2T625UUuOydi7zxEZf97oqkmjGUalYsxWR9JBjisgRU0jAD4+5LhGAfLRLEKsaWQ04gxLrFaHl98Uin6xBJB3Faf3puQVwIDYLo8Y746QMsnlNgVhc1iTngWqPjme6OotNyuHLB7mZUfhl5NnMC/bAwJKrO2PI7q65NZtZfGoEnbq1LjM6abzH8KLldykXIinhMKemOdpyaOYZwUzXIqB3uIygxZ0GM0x9sJK5CjFZKClNLDAFsMrqJWybMmv6a42J8MyXygmxqcTlCfaIcGJPxMl9mAhvjWQHZgGERsxVU8MsYqjtmkcWCus0w7n21ZGFA+NVAUZKCjnwpF4lowqbN76tQERvrufNryKapSfFx90yN9rTyy9s5s/MdOrE3AYxo9lKIhaeYuwNe/vNo+XWC+ulKAABZ8ETe3fBlHFJadVbtL8BMlt8ZqaYQehLL/SYN1RuzxeFjjay0JMjE4zSAvMoO2szUcg0iXTkJq6ZwTjvb4LXKH9GzRy2xIllOYDblk05ftlrAAJoSd/7XMfrf0bdU8TMnonFbZsb6s86J0/VI9zIdx+t23PleN2l1NETnJ1o+uV1VwFAv2E7xXfoVz//ymVwCqgw6ej8qTMF4SUV//vf/xeoz28ZF7oAAA==");
//        List<ActivityPromoterVo> list = JSONArray.parseArray(s, ActivityPromoterVo.class);
//        for (int i = 0; i < list.size(); i++) {
//            ActivityPromoterVo a = list.get(i);
//            StringBuilder sb = new StringBuilder();
//            sb.append("INSERT INTO `ad_cps_activity_promoter` (`activity_id`, `slot_id`, `kdt_id`, `rank_factor_conf`, `intervene`, `platform_id`, `third_id`, `third_name`, `third_avatar`) VALUES (");
//            sb.append("3").append(",").append(0).append(",").append(a.kdtId).append(",").append("'{\"refundRate\":").append(a.refundRate).append("}',")
//                    .append(500).append(",").append(1).append(",'").append(a.no).append("','")
//                    .append(a.name).append("','").append(a.avatar).append("'");
//            sb.append(");");
//            System.out.println(sb.toString());
//        }
//        System.out.println();


//        String s = "[{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/61a2adaa76b2537c2f99f090c480d3ac.jpg\",\"kdtId\":42368562,\"name\":\"石家庄蕊姐\",\"no\":\"429169191\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/45957f785d4928410bb85a03aa3f1929.jpg\",\"kdtId\":43144770,\"name\":\"芈团\",\"no\":\"655258766\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/9418f6ac47c234b38f97868c3c64cb91.jpg\",\"kdtId\":42906222,\"name\":\"小.佛爷【电商达人】\",\"no\":\"368173281\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/7ea62d5b070675de6022e96a1652df8f.jpg\",\"kdtId\":42661405,\"name\":\"辰辰搭配\",\"no\":\"800151231\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/6402531306749d5c6bc8457ac9312fc1.jpg\",\"kdtId\":43002054,\"name\":\"欣悦妈咪\",\"no\":\"46409951\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/cb236d83ebc524185d3fe948d59224a8.jpg\",\"kdtId\":42786894,\"name\":\"陶子家\",\"no\":\"611356916\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/29658f86d103658aeabb151a37c4a938.jpg\",\"kdtId\":43173906,\"name\":\"大璇\",\"no\":\"772014721\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/ff671cdf4eaab638b118d2b4b526e589.jpg\",\"kdtId\":42563213,\"name\":\"徐家（徐小米）时尚穿搭\",\"no\":\"902640167\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/53d4c85f749527a55de2ba35db8ff100.jpg\",\"kdtId\":44030446,\"name\":\"等等·奶奶\",\"no\":\"1082820781\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/b581541cd69c095068434577ba4df316.jpg\",\"kdtId\":43137622,\"name\":\"MiMi教搭配\",\"no\":\"744950865\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/06f41c65bbdbf86a4f609067b7ac1b74.jpg\",\"kdtId\":43534294,\"name\":\"茶芙巷\",\"no\":\"615454771\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/c7d4fadb9e5553c4f99e49fb099219e7.jpg\",\"kdtId\":42863671,\"name\":\"猫七七姑娘\",\"no\":\"1122415204\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/0bb89da101700b0c77d39b2accc1e643.jpg\",\"kdtId\":43101621,\"name\":\"新潮坊826【电商达人】\",\"no\":\"368482170\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/0bad78ae22a5c5a73798936b84d1a41f.jpg\",\"kdtId\":43521758,\"name\":\"77英姐（冲刺100万）\",\"no\":\"1066492841\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/d559cd16d082248a972bccc1e5da6c5a.jpg\",\"kdtId\":43163593,\"name\":\"周周珍可爱 •625\",\"no\":\"135145117\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/81b6a52c0956a83864334397d80f714d.jpg\",\"kdtId\":42944753,\"name\":\"梦娜（男士搭配）\",\"no\":\"625304234\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/4c8bd27803e6b7e8f2d2ba1f402ac8bd.jpg\",\"kdtId\":42717200,\"name\":\"超 级 丹\",\"no\":\"605422769\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/63961f962e9937d4e5b0b511408b177f.jpg\",\"kdtId\":43131193,\"name\":\"大龙淘衣\",\"no\":\"73931512\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/32f64133f76c020732d19f6b93e08d8d.jpg\",\"kdtId\":44500433,\"name\":\"下去哥 【小娜娜】\",\"no\":\"88029631\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/08c5e1795b249964f97ceb88665c75e7.jpg\",\"kdtId\":43011820,\"name\":\"何仙姑168.\",\"no\":\"1223526912\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/9c8d08df8b146fdc9d2f55a3b012bba2.jpg\",\"kdtId\":42572666,\"name\":\"小西米\",\"no\":\"69476504\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/d5d59b325f0009e316d6c1a90c1c94c0.jpg\",\"kdtId\":43078654,\"name\":\"《小丽店》广州服装工厂\",\"no\":\"276680538\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/95deef71b9002ecac25c58f11cbc8df5.jpg\",\"kdtId\":41942894,\"name\":\"爽儿❤️户外舞蹈第七人\",\"no\":\"51545544\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/4b70e116d5ba2d328604e1de3f88bb90.jpg\",\"kdtId\":42393361,\"name\":\"刘鸿飞\",\"no\":\"149499804\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/6dd9d52c9a2ea116109c2ca41759fa7c.jpg\",\"kdtId\":42967116,\"name\":\"雪爷\",\"no\":\"239234479\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/ff694a7017e235c2fa7bc608c85834f8.jpg\",\"kdtId\":44422280,\"name\":\"张馨月\",\"no\":\"1161435098\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/fa7f1f8de89d55ad4726d92ce58c1c95.jpg\",\"kdtId\":43125193,\"name\":\"西爷～\",\"no\":\"431234568\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/d23814e948bcb502a5488d7b5e8da626.jpg\",\"kdtId\":42338404,\"name\":\"牛嫂\",\"no\":\"238055940\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/4579d5a09ce2c1073e301b624ac793a7.jpg\",\"kdtId\":43291327,\"name\":\"宝宝家精品服装\",\"no\":\"1128300046\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/c98eab48ae5f85fdb31ac836b06f56c5.jpg\",\"kdtId\":43176309,\"name\":\"大林琳衣橱（教\",\"no\":\"467253803\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/c2a796d23d8a1edec5107a6542036a67.jpg\",\"kdtId\":40022055,\"name\":\"苗苗家衣橱小号\",\"no\":\"140568237\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/bf0fbc6327eaad948db1e0b111b39efb.jpg\",\"kdtId\":43381849,\"name\":\"阿胶利嫂\",\"no\":\"259271412\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/eca4b87a35a602358bdc602087542f4b.jpg\",\"kdtId\":42786120,\"name\":\"\uD83D\uDC57购物狂\uD83D\uDC57贝姐教搭配\",\"no\":\"762475255\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/2614c66d2a65bdde51cda397ea42c43c.jpg\",\"kdtId\":16661281,\"name\":\"琴琴家\",\"no\":\"1144011726\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/Fleo6RRvv5ctu-75UDA_NBj46FGd.jpg\",\"kdtId\":43270523,\"name\":\"安若溪♛【BL】陌若心安\",\"no\":\"704974956\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FjU75OAPKDn_zVAjP94wGm_GbhkE.jpg\",\"kdtId\":42609132,\"name\":\"妞妞搭配小号专场\",\"no\":\"776310270\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/Fm93b_ywYVfuqCJz6__SAUNuAfKP.jpg\",\"kdtId\":44609546,\"name\":\"赵大侠（品质优选）\",\"no\":\"1071496670\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FiEZConaKZh-c-TwPXUZW9hFxD87.jpg\",\"kdtId\":42550815,\"name\":\"雨姐（冲500万）\",\"no\":\"822968829\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/Fkr0cc7KciSVU3-CDvinjRZXeiUQ.jpg\",\"kdtId\":42654316,\"name\":\"谢宝悦\",\"no\":\"145359802\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/Fq9XQKtq_osXv_YJpZ0DvMNKIlUX.jpg\",\"kdtId\":42933712,\"name\":\"syali❤️大鸭梨\",\"no\":\"45196264\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FoSUVUMMrT_tQ4-gZUTO14_hmgDJ.jpg\",\"kdtId\":43269068,\"name\":\"仟代家的小黑粉❤️\",\"no\":\"1422030867\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FhjlL0cvEKJKFExbWnECuWB4T0nC.jpg\",\"kdtId\":42935774,\"name\":\"\uD83C\uDF34言植童装\uD83C\uDF34电商中心\",\"no\":\"865685003\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FsIDaASeznA7mUrQQWWJHW0V99xs.jpg\",\"kdtId\":42837686,\"name\":\"金姐811\",\"no\":\"596754002\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FjmbqfskK9dnl2igO5_B84h2GNqA.jpg\",\"kdtId\":43379798,\"name\":\"叫张铁牛\",\"no\":\"667034521\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FrCyqRjtd9EIqpHL4HPbz5Qra1L1.jpg\",\"kdtId\":42554495,\"name\":\"聚森\",\"no\":\"562696495\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/Foc_OdpUx2YZiBXV-13WCeWEvQpe.jpg\",\"kdtId\":42506897,\"name\":\"牛嫂搭配师\",\"no\":\"1215976915\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FiZPpC_6U0PyxX5fZ_RCtxzSpKiz.jpg\",\"kdtId\":43339527,\"name\":\"中国拼货第一人\",\"no\":\"656918466\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FmudMPHpsev_uiwT_nXpjr6b7fzB.jpg\",\"kdtId\":42815221,\"name\":\"黑妹儿家.\",\"no\":\"165721805\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FrRrUXCj6VyxtQ2UoEvNJpjJHbK3.jpg\",\"kdtId\":42900830,\"name\":\"黑暗萝莉大美\",\"no\":\"1216928\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FitU6UF968SEpYU_n1P-Q2BK5n1s.jpg\",\"kdtId\":42943386,\"name\":\"真姐~教搭配\",\"no\":\"560043874\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/Fm1H8gp-abmlZkaMQBdPD49Ut3K9.jpg\",\"kdtId\":43340659,\"name\":\"阳阳童装大号破60万\",\"no\":\"693887794\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FpCmE-DFDg_SxqnX38b2Y66P0QaL.jpg\",\"kdtId\":43124245,\"name\":\"独秀秒杀店\",\"no\":\"587552857\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FmDoceGGQeTRMJVI-ujXNnMR5Xb1.jpg\",\"kdtId\":44604657,\"name\":\"鞋子姐《艾伦亲姐》\",\"no\":\"613985580\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FqSLzhM8LWzdgEhJLrCNUpnV76Kw.jpg\",\"kdtId\":44441548,\"name\":\"（阿龙）西魅优品\",\"no\":\"1131394637\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FjywxE17mIlOFQsiITiBqC1iAdGs.jpg\",\"kdtId\":43186911,\"name\":\"满满家小号\",\"no\":\"249071984\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/Fsqk6SbAw9ga9q6zyPWWsnC2ZL9_.jpg\",\"kdtId\":43149676,\"name\":\"木棉服饰\",\"no\":\"792443639\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/Fn0MB7s1CZgK2kmko-U6rzV5rjSu.jpg\",\"kdtId\":42558005,\"name\":\"大美妞 （电商达人）\",\"no\":\"662102620\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/Fm5hi3FJFgbVGrOOcLH_RY1ixAjZ.jpg\",\"kdtId\":43222526,\"name\":\"大 .琪\",\"no\":\"320783887\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FgGYpQO_6uZHOP6jK_VBz_qfIrUO.jpg\",\"kdtId\":42936637,\"name\":\"葵儿\",\"no\":\"256168\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FsoHxjtC63m3FTWkFz19yRDRfxMP.jpg\",\"kdtId\":43543867,\"name\":\"胖依依\",\"no\":\"549757466\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FnplqbadBxRbRDa_O1m8jgwRjRTe.jpg\",\"kdtId\":43313818,\"name\":\"DL-丹妮\",\"no\":\"1115634763\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FsoHxjtC63m3FTWkFz19yRDRfxMP.jpg\",\"kdtId\":43088118,\"name\":\"你宇哥护肤\",\"no\":\"607786234\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FsoHxjtC63m3FTWkFz19yRDRfxMP.jpg\",\"kdtId\":43210507,\"name\":\"范范家（教搭配）\",\"no\":\"94677806\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FjywxE17mIlOFQsiITiBqC1iAdGs.jpg\",\"kdtId\":43056654,\"name\":\"【大蒙子】时尚搭配\",\"no\":\"705428468\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FofQc3Fx5gMMih2zpnebqP6I0p6y.jpg\",\"kdtId\":44436468,\"name\":\"葛强（只做品质）587\",\"no\":\"8285732\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FhqhLF0Futb6ysMoid9foTwnSSJz.jpg\",\"kdtId\":42975492,\"name\":\"徐州平姐（诚信电商）\",\"no\":\"251985566\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FrAkD57h90EsboY9PAj4GljzGbg0.jpg\",\"kdtId\":43127586,\"name\":\"巧姐姐【浑身充满正能量】\",\"no\":\"27818039\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/Fhy3wyVJv6nuiNcPWqQC7xxOlCF0.jpg\",\"kdtId\":43185030,\"name\":\"潘朵拉（唐山人在广州）\",\"no\":\"108622829\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FoCOvTjVLfkwFwwSGQMmPfjjkKth.jpg\",\"kdtId\":43035453,\"name\":\"橘子童装教搭配「优品店」\",\"no\":\"745495340\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FtuaJnsoQKmP25XVa8mGKOW4Ufn7.jpg\",\"kdtId\":41556752,\"name\":\"欧韩馆\",\"no\":\"1373778565\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/Fs08CksOIdsDMQ3TqVsb057vU5hN.jpg\",\"kdtId\":43390189,\"name\":\"小静姐：\",\"no\":\"239988734\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FriEb9ST03fBEiVGTH7itpa9rY0w.jpg\",\"kdtId\":43384341,\"name\":\"孩的丫【丫丫姐】精选\",\"no\":\"478254879\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FubXsN3jn0pU3x3sCu4TF-j3Ypv8.jpg\",\"kdtId\":43024052,\"name\":\"紫天曹丽君\",\"no\":\"46747280\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/Fl8itGeB55jLpCerUCtdL9zv4Bd1.jpg\",\"kdtId\":43134661,\"name\":\"阿敏子\",\"no\":\"351268078\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FmVwJpyb7SfKWFcWYjZSHfSqTBKy.jpg\",\"kdtId\":43134241,\"name\":\"表哥❗️（普通话很厉害）\",\"no\":\"86644305\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/Fs08CksOIdsDMQ3TqVsb057vU5hN.jpg\",\"kdtId\":42850110,\"name\":\"涓涓二姐 【电商达人】\",\"no\":\"224819170\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FnbvXkuUjktOaI7JZT1Yi8FvhIlf.jpg\",\"kdtId\":42605652,\"name\":\"安然教穿搭\",\"no\":\"1040816918\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/Fq9RxcUQmhIBT5_hVoyEd7QP0f7E.jpg\",\"kdtId\":43412914,\"name\":\"梦泉贺冬冬921\",\"no\":\"1296328956\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FjyhN5u1ESva9OMZ5ry3WjGYYlbA.jpg\",\"kdtId\":43381474,\"name\":\"小鸭梨\",\"no\":\"212003197\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/Fl7EK05Apvw6iXMrysclDN4pIkEX.jpg\",\"kdtId\":43336971,\"name\":\"雷.老.板\",\"no\":\"9263560\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FkIlK7wcHxEhJTmxjfxYXMiDujXM.jpg\",\"kdtId\":43184360,\"name\":\"伊衣送衣服12点\",\"no\":\"701953025\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FjtODtT5Q_wWAmf_vF87iXRtLBH0.jpg\",\"kdtId\":42583357,\"name\":\"卢紫晗小朋友\",\"no\":\"75371262\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FrGSgnHqxc0JIWYE66HbBaNxyJyj.jpg\",\"kdtId\":43350146,\"name\":\"米乐童装（大号）破60万\",\"no\":\"44435204\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/Fpb12qu3maUHvsxOuTS3tay-xrAD.jpg\",\"kdtId\":43805871,\"name\":\"佟二堡貂得衣主任308\",\"no\":\"128438008\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/FilECax2NvnHvmFOqJYhHWcAJKQk.jpg\",\"kdtId\":43448119,\"name\":\"啦啦家教搭配\",\"no\":\"765806942\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/2d31fbbc06e3261082057aacc87b310f.jpg\",\"kdtId\":43704975,\"name\":\"奇迹（金钥匙\",\"no\":\"880035873\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/40fa4882c94e85cb427b069914735456.jpg\",\"kdtId\":43062064,\"name\":\"广州小阿磊\",\"no\":\"1092627701\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/231ac36452fbd647c340f724e7f570fe.jpg\",\"kdtId\":43424528,\"name\":\"静龍锦丝绸\",\"no\":\"1141640830\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/4898711f6e92cd35ff68d3b47fc539af.jpg\",\"kdtId\":43148234,\"name\":\"琢琢爱穿搭❤️❤️❤️\",\"no\":\"1098356305\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/87f8ece96ec5060b188e0b5682556a13.jpg\",\"kdtId\":42616776,\"name\":\"广州陈陈家CCJ\",\"no\":\"1100683908\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/68b552cd978211678cfa82ca022aa681.jpg\",\"kdtId\":43100625,\"name\":\"童鞋大王！810\",\"no\":\"816221521\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/26880c4c90dedddaeac86d43558ccee3.jpg\",\"kdtId\":43304121,\"name\":\"丹✨姐《小号》\",\"no\":\"968081102\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/95a2187c673761597e8d1f49d62ec1fb.jpg\",\"kdtId\":43356047,\"name\":\"丁丁家\",\"no\":\"233120296\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/106c85223b5007c416426a1b72741ad3.jpg\",\"kdtId\":43164824,\"name\":\"【阿萱童装】正能量\",\"no\":\"16106791\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/ddf1aa2eb621ffc79f8d770c7c5382ed.jpg\",\"kdtId\":43083110,\"name\":\"花花公子贵宾（服装精品）\",\"no\":\"1484198216\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/0fd2c950217a6f46cead3c4b31b4cf42.jpg\",\"kdtId\":43359918,\"name\":\"樱桃家潮童馆\",\"no\":\"105193469\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/fccd336fdf727b3acfc5c40a230f1cbb.jpg\",\"kdtId\":43528484,\"name\":\"长贵村主任\",\"no\":\"88619060\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/dd239fe2233c314ff3fe179de848d5b7.jpg\",\"kdtId\":43334707,\"name\":\"维维大码300斤以内\",\"no\":\"156158318\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/f3de0783e4095aad29b1dcabf8b17c65.jpg\",\"kdtId\":43139005,\"name\":\"火云鞋专注高品质\",\"no\":\"1205536810\",\"slotId\":1},{\"avatar\":\"https://img.yzcdn.cn/upload_files/2019/11/01/cd2eb4aa6491c152b652e35d3400c4a3.jp\",\"kdtId\":42695726,\"name\":\"吻吻家（丹姐教搭配\",\"no\":\"520874413\",\"slotId\":1}]\n";
//
//        System.out.println(gzip(s));
    }

    /**
     * 使用gzip进行压缩
     */
    public static String gzip(String primStr) {
        if (StringUtils.isEmpty(primStr)) {
            return primStr;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (GZIPOutputStream gzip = new GZIPOutputStream(out)) {
            gzip.write(primStr.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String zipStr =  Base64.getEncoder().encodeToString(out.toByteArray());

        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zipStr;
    }

    /**
     * 使用gzip进行解压缩
     */
    public static String unGzip(String compressedStr) {
        if (StringUtils.isEmpty(compressedStr)) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String decompressed = null;
        byte[] compressed = Base64.getDecoder().decode(compressedStr);

        try (ByteArrayInputStream in = new ByteArrayInputStream(compressed);
             GZIPInputStream ginzip = new GZIPInputStream(in)) {
            byte[] buffer = new byte[1024];
            int offset;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return decompressed;
    }
}
