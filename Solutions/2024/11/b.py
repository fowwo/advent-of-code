# December 10th, 2024

from functools import cache

file = open(f"{__file__}/../input.txt", "r")
ints = [ int(x) for x in file.readline().strip().split() ]
file.close()

@cache
def f(x, n):
	if n == 0: return 1
	if x == 0: return f(1, n - 1)
	if len(str(x)) & 1 == 0:
		s = str(x)
		a, b = int(s[:len(s) // 2]), int(s[len(s) // 2:])
		return f(a, n - 1) + f(b, n - 1)
	return f(x * 2024, n - 1)

print(sum(f(x, 75) for x in ints))
