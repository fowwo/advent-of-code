# December 18th, 2024

from functools import cache

file = open(f"{__file__}/../input.txt", "r")
patterns = file.readline().strip().split(", ")
file.readline()

@cache
def f(s):
	if s == "": return 1
	n = 0
	for p in patterns:
		if s.startswith(p):
			n += f(s[len(p):])
	return n

n = 0
while (line := file.readline().strip()):
	n += f(line)
file.close()

print(n)
