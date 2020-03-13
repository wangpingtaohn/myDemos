# coding=utf-8

import os
import shutil

path = './'

# “/”：表示根目录，在windows系统下表示某个盘的根目录，如“E:\”；
# “./”：表示当前目录；（表示当前目录时，也可以去掉“./”，直接写文件名或者下级目录）
# “../”：表示上级目录

# dirs = os.listdir(path)

# for file in dirs:
# 	print(file)
for root, dirs, files in os.walk(path):
	# for name in files:
		# print(os.path.join(root,name))
	for name in dirs:
		# print(os.path.join(root,name))
		if name == '.git' or name == 'build' or name == '.gradle':
			# print(os.path.join(root,name))
			shutil.rmtree(os.path.join(root,name))
			print('删除【' + os.path.join(root,name) + '】完成')