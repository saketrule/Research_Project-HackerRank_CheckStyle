import re,os
from os.path import join

def parse(folderPath = ''):
    if folderPath == '':
        folderPath = r'C:\Users\HP\Documents\hackerrank_top_solutions_dataset\red-john-is-back\java\output'
    arr = {}
    total = {}
    stats = {}
    for fileName in os.listdir(folderPath):
        if fileName[-4:] == '.txt':
            temp_dict = {}
            myfile = open(join(folderPath,fileName),'r')
            text = myfile.read()
            myfile.close()
            for exc_name in re.findall(r'\[[a-zA-Z0-9]*\]',text):
                if not exc_name in temp_dict:
                    temp_dict[exc_name] = 1
                else:
                    temp_dict[exc_name] = temp_dict[exc_name] + 1
                if not exc_name in total:
                    total[exc_name] = 1
                else:
                    total[exc_name] = total[exc_name] + 1
            for foundException in temp_dict:
                if not foundException in stats:
                    stats[foundException] = 1
                else:
                    stats[foundException] = stats[foundException] + 1
            arr[fileName] = temp_dict

    #print(total)
    print("\n\n\n\n")
    for item in sorted(stats):
        print(item+" -> ",stats[item])
