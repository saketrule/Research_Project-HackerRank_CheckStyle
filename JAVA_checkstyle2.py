import os
from os.path import join,exists

def checkstyle(folderPath = ''):
    if folderPath == '':
        folderPath = r'C:\Users\HP\Documents\hackerrank_top_solutions_dataset\red-john-is-back\java'
    checkFolder = r'C:\Users\HP\Downloads\style_checker\stylecheck'
    confFile = join(checkFolder, 'conf.xml')
    jarFile = join(checkFolder, 'checkstyle-8.1-all.jar')
    outputFolder = join(folderPath,'output')
    commandPre = 'java -jar ' + jarFile + ' -c ' + confFile + ' '
    if not exists(outputFolder):
        os.makedirs(outputFolder)
    for fileName in os.listdir(folderPath):
        if os.path.isfile(join(folderPath,fileName)):
            if len(fileName) > 6 and fileName[-5:] == '.java':
                filePath = join(folderPath,fileName)
                commandPost = ' > ' + join(outputFolder, fileName[:-5] + '.txt')
                print(commandPre + filePath + commandPost)
                os.system(commandPre + filePath + commandPost)
    print('\n\nDone')

mfold = r'F:\DATABASE_HACKERRANK'
for subfold in os.listdir(mfold):
    subfol = join(mfold,subfold)
    if os.path.isdir(subfol):
        for cfold in os.listdir(subpath):
            print(cfold)
