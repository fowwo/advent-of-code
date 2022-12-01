# December 1st, 2022

file = open(f"{__file__}/../input.txt", "r")

total = 0
high = 0
for line in file.readlines():
	line = line.strip()
	if line:
		total += int(line)
	else:
		high = max(total, high)
		total = 0
print(high)
