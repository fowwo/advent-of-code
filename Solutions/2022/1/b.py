# December 1st, 2022

file = open(f"{__file__}/../input.txt", "r")

total = 0
elves = []
for line in file.readlines():
	line = line.strip()
	if line:
		total += int(line)
	else:
		elves.append(total)
		total = 0
elves = sorted(elves, reverse = True)
print(elves[0] + elves[1] + elves[2])
