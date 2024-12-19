# December 18th, 2024

from functools import cache

file = open(f"{__file__}/../input.txt", "r")
patterns = file.readline().strip().split(", ")
file.readline()

@cache
def f(s):
	if s == "": return True
	for p in patterns:
		if s.startswith(p):
			if f(s[len(p):]):
				return True
	return False

n = 0
while (line := file.readline().strip()):
	n += f(line)
file.close()

print(n)
