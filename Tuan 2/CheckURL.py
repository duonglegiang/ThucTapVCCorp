import numpy as np
import re 
  
def CheckURL(string): 
    regex = r"http[s]?://(www.)?([a-zA-Z]|[0-9])+.[a-z]{2,6}/(([a-zA-Z]|[0-9]|[$-_@.&+]|[!*\(\),])+)?"
    # print(regex)
    url = re.fullmatch(regex,string)  
    # print(url)
    # # kk = [x[0] for x in url] 
    # # print(kk)
    if url is None:
        return 0
    else:
        return 1
      
string = 'http://www.tiki.vn/dien-thoai-may-tinh-bang/c1789?src=mega-menu'
checkk = CheckURL(string)
if(checkk == 1):
    print("Chuoi la URL")
else:
    print("chuoi khong phai la URL")