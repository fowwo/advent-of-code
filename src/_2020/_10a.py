# December 3rd, 2021

file = open("input/2020/10.txt", "r")
arr = []

line = file.readline().strip()
while line:
	arr.append(int(line))
	line = file.readline().strip()
arr.append(0)
arr.sort()

one = 0
three = 1
for i in range(len(arr) - 1):
	diff = arr[i+1] - arr[i]
	if diff == 1:
		one += 1
	elif diff == 3:
		three += 1

print(one * three)
