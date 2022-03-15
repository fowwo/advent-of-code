# December 25th, 2021

file = open(f"{__file__}/../input.txt", "r")
lines = file.read().split("\n")

count = 0
for i in range(1, len(lines) - 1):
	if int(lines[i]) > int(lines[i - 1]):
		count += 1
print(count)
