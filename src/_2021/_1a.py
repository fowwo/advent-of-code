# December 25th, 2021

file = open("input/2021/1.txt", "r")
lines = file.read().split("\n")

count = 0
for i in range(1, len(lines) - 1):
	if int(lines[i]) > int(lines[i - 1]):
		count += 1
print(count)
