# December 2nd, 2024

import re

file = open(f"{__file__}/../input.txt", "r")
lines = [ line.strip() for line in file ]
file.close()

n = 0
do = True
for line in lines:
	for d, x, y in re.findall("(do\\(\\)|don't\\(\\))|mul\\((\\d+),(\\d+)\\)", line):
		if d:
			do = (d == "do()")
		elif do:
			n += int(x) * int(y)
print(n)
