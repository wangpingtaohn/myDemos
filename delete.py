# coding=utf-8

import os
import shutil

path = './'

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