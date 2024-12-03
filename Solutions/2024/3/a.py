# December 2nd, 2024

import re

file = open(f"{__file__}/../input.txt", "r")
lines = [ line.strip() for line in file ]
file.close()

n = 0
for line in lines:
	for x, y in re.findall(r"mul\((\d+),(\d+)\)", line):
		n += int(x) * int(y)
print(n)
